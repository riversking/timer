package com.rivers.nba.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.nba.dao.PlayerDao;
import com.rivers.nba.model.PlayerModel;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class PlayerService extends ServiceImpl<PlayerDao, PlayerModel> {

    @Value("${nba.key}")
    private String nbaKey;

    @Resource
    private PlayerDao playerDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void savePlayerInfo() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.sportsdata.io/v3/nba/scores/json/Players?key=" + nbaKey)
                .build();
        String bodyStr = "";
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                bodyStr = body.string();
            }
        } catch (IOException e) {
            log.error("NBA PLAYER ERROR", e);
        }
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
                redisTemplate.opsForList().leftPop("nba_player_list");
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
            redisTemplate.opsForList().leftPushAll("nba_player_list", players);
        }
    }

}
