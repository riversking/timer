package com.rivers.nba.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.nba.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
