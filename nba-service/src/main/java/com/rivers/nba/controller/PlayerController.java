package com.rivers.nba.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.view.ResponseVo;
import com.rivers.nba.dto.PlayerDTO;
import com.rivers.nba.model.PlayerModel;
import com.rivers.nba.service.PlayerService;
import com.rivers.nbaservice.proto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
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
                        .setPosition(i.getPosition())
                        .setSalary(i.getSalary())
                        .setTeam(i.getTeam())
                        .build())
                .collect(Collectors.toList());
        return GetNbaPlayerListRes.newBuilder().addAllPlayers(list)
                .setTotal(Integer.parseInt(String.valueOf(players.getTotal()))).build();
    }

    @PostMapping("syncPlayer")
    public ResponseVo syncPlayer() {
        playerService.savePlayerInfo();
        return ResponseVo.ok();
    }

    @PostMapping("getPlayerDetail")
    public GetPlayerDetailRes getPlayerDetail(@RequestBody GetPlayerDetailReq req) {
        int playerId = req.getPlayerId();
        if (playerId == 0) {
            return GetPlayerDetailRes.failed(-700001, "playerId为空");
        }
        PlayerDTO player = playerService.getPlayerDetail(playerId);
        return GetPlayerDetailRes
                .newBuilder()
                .setPlayer(Player.newBuilder()
                        .setPlayerName(player.getDraftKingsName())
                        .setPosition(player.getPosition())
                        .setCollege(player.getCollege())
                        .setPhotoUrl(player.getPhotoUrl())
                        .setHeight(player.getHeight())
                        .setWeight(player.getWeight())
                        .setJersey(player.getJersey())
                        .setTeamId(player.getTeamId())
                        .build())
                .build();
    }
}
