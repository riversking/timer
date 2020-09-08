package com.rivers.nba.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.view.ResponseVo;
import com.rivers.nba.config.XxlJobConfig;
import com.rivers.nba.model.PlayerModel;
import com.rivers.nba.service.PlayerService;
import com.rivers.nbaservice.proto.GetNbaPlayerListReq;
import com.rivers.nbaservice.proto.GetNbaPlayerListRes;
import com.rivers.nbaservice.proto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "nba/player", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @PostMapping("playerList")
    public GetNbaPlayerListRes playerList(@RequestBody GetNbaPlayerListReq req) {
        IPage<PlayerModel> players = playerService.playerPage(req);
        List<Player> list = players.getRecords()
                .stream()
                .map(i -> Player
                        .newBuilder()
                        .setPlayerId(i.getPlayerId())
                        .setBirthCity(i.getBirthCity())
                        .setBirthDate(DateUtil.formatDate(i.getBirthDate()))
                        .setCollege(i.getCollege())
                        .setExperience(i.getExperience())
                        .setHeight(i.getHeight())
                        .setWeight(i.getWeight())
                        .setPlayerName(i.getDraftKingsName())
                        .build())
                .collect(Collectors.toList());
        return GetNbaPlayerListRes.newBuilder().addAllPlayers(list).setTotal(players.getTotal()).build();
    }

    @PostMapping("syncPlayer")
    public ResponseVo syncPlayer() {
        playerService.savePlayerInfo();
        return ResponseVo.ok();
    }
}
