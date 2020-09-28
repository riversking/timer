package com.rivers.nba.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.nba.dao.TeamDao;
import com.rivers.nba.dto.TeamDTO;
import com.rivers.nba.model.TeamModel;
import com.rivers.nba.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeamService extends ServiceImpl<TeamDao, TeamModel> {

    @Resource
    private TeamDao teamDao;

    @Value("${nba.key}")
    private String nbaKey;

    @Value("${nba.url}")
    private String nbaUrl;

    @Transactional(rollbackFor = Exception.class)
    public void syncTeam() {
        String bodyStr = HttpClientUtils.get(nbaUrl + "AllTeams?key=" + nbaKey);
        if (StrUtil.isNotBlank(bodyStr)) {
            List<TeamModel> teamList = JSONObject.parseArray(bodyStr).toJavaList(TeamModel.class);
            Integer hasData = teamDao.hasData();
            List<TeamModel> teams = teamList.stream().peek(i -> {
                i.setCreateUser("T00001");
                i.setUpdateUser("T00001");
            }).collect(Collectors.toList());
            if (Objects.isNull(hasData)) {
                saveBatch(teams);
            } else {
                teams.forEach(i -> {
                    QueryWrapper<TeamModel> wrapper = new QueryWrapper<>();
                    wrapper.eq("team_id", i.getTeamId());
                    teamDao.update(i, wrapper);
                });
            }
        }
    }

    public IPage<TeamModel> teamPage(TeamDTO teamDTO) {
        int pageNumber = teamDTO.getPageNumber();
        int pageSize = teamDTO.getPageSize();
        Page<TeamModel> page = new Page<>(pageNumber, pageSize);
        return teamDao.selectTeamPage(page, teamDTO);
    }

    public List<TeamModel> teamList() {
        QueryWrapper<TeamModel> wrapper = new QueryWrapper<>();
        wrapper.select("team_id", "name");
        return teamDao.selectList(wrapper);
    }


}
