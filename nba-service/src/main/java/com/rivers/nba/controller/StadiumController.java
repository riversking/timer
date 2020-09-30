package com.rivers.nba.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.view.ResponseVo;
import com.rivers.nba.dto.StadiumDTO;
import com.rivers.nba.model.StadiumModel;
import com.rivers.nba.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "nba/stadium", produces = MediaType.APPLICATION_JSON_VALUE)
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @PostMapping("syncStadium")
    public ResponseVo syncStadium() {
        stadiumService.syncStadium();
        return ResponseVo.ok();
    }

    @PostMapping("getStadiumPage")
    public ResponseVo getStadiumPage(@RequestBody StadiumDTO stadiumDTO) {
        IPage<StadiumModel> stadiumPage = stadiumService.getStadiumPage(stadiumDTO);
        return ResponseVo.ok(stadiumPage);
    }

    @PostMapping("stadiumList")
    public ResponseVo stadiumList() {
        List<StadiumModel> stadiumList = stadiumService.stadiumList();
        return ResponseVo.ok(stadiumList);
    }

}
