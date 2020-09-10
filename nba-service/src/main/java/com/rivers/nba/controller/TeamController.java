package com.rivers.nba.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.nba.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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

}
