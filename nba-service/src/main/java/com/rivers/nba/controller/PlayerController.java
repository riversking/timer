package com.rivers.nba.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.nba.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("test")
    @ResponseBody
    public ResponseVo test() {
        playerService.savePlayerInfo();
        return ResponseVo.ok();
    }


}
