package com.rivers.nba.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.nba.dao.StadiumDao;
import com.rivers.nba.dto.StadiumDTO;
import com.rivers.nba.model.PlayerModel;
import com.rivers.nba.model.StadiumModel;
import com.rivers.nba.model.TeamModel;
import com.rivers.nba.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StadiumService extends ServiceImpl<StadiumDao, StadiumModel> {

    @Resource
    private StadiumDao stadiumDao;

    @Value("${nba.key}")
    private String nbaKey;

    @Value("${nba.url}")
    private String nbaUrl;

    public void syncStadium() {
        String bodyStr = HttpClientUtils.get(nbaUrl + "Stadiums?key=" + nbaKey);
        if (StrUtil.isNotBlank(bodyStr)) {
            List<StadiumModel> stadiumList = JSONObject.parseArray(bodyStr, StadiumModel.class);
            if (stadiumList.isEmpty()) {
                return;
            }
            List<StadiumModel> list = stadiumList.
                    stream()
                    .peek(i -> {
                        i.setCreateUser("T00001");
                        i.setUpdateUser("T00001");
                    })
                    .collect(Collectors.toList());
            Integer count = stadiumDao.hasData();
            if (Objects.isNull(count)) {
                saveBatch(list);
            } else {
                list.forEach(s -> {
                    QueryWrapper<StadiumModel> wrapper = new QueryWrapper<>();
                    wrapper.eq("stadium_id", s.getStadiumId());
                    stadiumDao.update(s, wrapper);
                });
            }
        }
    }

    public IPage<StadiumModel> getStadiumPage(StadiumDTO stadiumDTO) {
        int pageNumber = stadiumDTO.getpageNum();
        int pageSize = stadiumDTO.getPageSize();
        Page<TeamModel> page = new Page<>(pageNumber, pageSize);
        return stadiumDao.selectStadiumPage(page, stadiumDTO);
    }

    public List<StadiumModel> stadiumList() {
        QueryWrapper<StadiumModel> wrapper = new QueryWrapper<>();
        wrapper.select("stadium_id", "name");
        return stadiumDao.selectList(wrapper);
    }

}
