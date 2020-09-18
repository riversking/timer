package com.rivers.nba.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.nba.controller.PlayerController;
import com.rivers.nba.dao.PlayerDao;
import com.rivers.nba.dto.PlayerDTO;
import com.rivers.nba.model.PlayerModel;
import com.rivers.nba.utils.HttpClientUtils;
import com.rivers.nbaservice.proto.GetNbaPlayerListReq;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PlayerService extends ServiceImpl<PlayerDao, PlayerModel> {

    private Logger logger = LoggerFactory.getLogger(PlayerService.class);


    @Value("${nba.key}")
    private String nbaKey;

    @Value("${nba.url}")
    private String nbaUrl;

    @Resource
    private PlayerDao playerDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void savePlayerInfo() {
        String bodyStr = HttpClientUtils.get(nbaUrl + "Players?key=" + nbaKey);
        if (StrUtil.isNotBlank(bodyStr)) {
            List<PlayerModel> playerList = JSONObject.parseArray(bodyStr, PlayerModel.class);
            if (playerList.isEmpty()) {
                return;
            }
            List<Integer> ids = playerDao.selectPlayerId();
            List<PlayerModel> players = playerList.stream()
                    .peek(i -> {
                        i.setCreateUser("T00001");
                        i.setUpdateUser("T00001");
                        i.setHeight((int) (i.getHeight() * 2.54));
                        i.setPhotoUrl(StringEscapeUtils.unescapeJava(i.getPhotoUrl()));
                    }).collect(Collectors.toList());
            if (ids.isEmpty()) {
                saveBatch(players);
            } else {
                players.forEach(i -> {
                    if (ids.contains(i.getPlayerId())) {
                        QueryWrapper<PlayerModel> wrapper = new QueryWrapper<>();
                        wrapper.eq("player_id", i.getPlayerId());
                        playerDao.update(i, wrapper);
                    } else {
                        playerDao.insert(i);
                    }
                });
            }
            CompletableFuture.runAsync(() -> {
                redisTemplate.delete("nba_player_list");
                List<PlayerModel> list = players
                        .stream()
                        .sorted(Comparator.comparing(PlayerModel::getPlayerId))
                        .collect(Collectors.toList());
                redisTemplate.opsForList().rightPushAll("nba_player_list", list);
            });
        }
    }

    public IPage<PlayerModel> playerPage(GetNbaPlayerListReq req) {
        int pageNum = req.getPageNum();
        int pageSize = req.getPageSize();
        Page<PlayerModel> page = new Page<>(pageNum, pageSize);
        List<PlayerModel> playerList = redisTemplate
                .opsForList().range("nba_player_list", (pageNum - 1) * pageSize, pageSize - 1);
        if (StrUtil.isBlank(req.getPlayerName()) && StrUtil.isBlank(req.getPosition()) && StrUtil.isBlank(req.getTeam())
                && req.getPlayerId() == 0) {
            if (Objects.nonNull(playerList) && !playerList.isEmpty()) {
                page.setRecords(playerList);
                page.setTotal(redisTemplate.opsForList().size("nba_player_list"));
                return page;
            }
        }
        PlayerDTO player = new PlayerDTO();
        player.setPlayerId(req.getPlayerId());
        player.setPlayerName(req.getPlayerName());
        player.setPosition(req.getPosition());
        player.setTeam(req.getTeam());
        logger.info("GetNbaPlayerListReq req {} {}", pageNum, pageSize);
        return playerDao.selectPlayerPage(page, player);
    }

}
