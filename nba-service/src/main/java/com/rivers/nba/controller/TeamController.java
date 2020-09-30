package com.rivers.nba.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.view.ResponseVo;
import com.rivers.nba.dto.TeamDTO;
import com.rivers.nba.model.TeamModel;
import com.rivers.nba.service.TeamService;
import com.rivers.nbaservice.proto.GetPlayerDetailReq;
import com.rivers.nbaservice.proto.GetTeamDetailReq;
import com.rivers.nbaservice.proto.GetTeamDetailRes;
import com.rivers.nbaservice.proto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "nba/team", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("syncTeam")
    public ResponseVo syncTeam() {
        teamService.syncTeam();
        return ResponseVo.ok();
    }

    @PostMapping("getTeamPage")
    public ResponseVo getTeamPage(@RequestBody TeamDTO teamDTO) {
        IPage<TeamModel> teamModelIPage = teamService.teamPage(teamDTO);
        return ResponseVo.ok(teamModelIPage);
    }

    @PostMapping("getTeamList")
    public ResponseVo getTeamList() {
        return ResponseVo.ok(teamService.teamList());
    }

    @PostMapping("getTeamDetail")
    public GetTeamDetailRes getTeamDetail(@RequestBody GetTeamDetailReq req) {
        TeamModel teamDetail = teamService.getTeamDetail(req.getTeamId());
        Team team = Team.newBuilder()
                .setCity(teamDetail.getCity())
                .setConference(teamDetail.getConference())
                .setDivision(teamDetail.getDivision())
                .setLogoUrl(teamDetail.getWikipediaLogoUrl())
                .setTeamName(teamDetail.getName())
                .setStadiumId(teamDetail.getStadiumId())
                .build();
        return GetTeamDetailRes.newBuilder().setTeam(team).build();
    }

}
