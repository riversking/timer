package com.rivers.nba.job;

import com.rivers.nba.service.PlayerService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NbaJobHandle {

    @Autowired
    private PlayerService playerService;

    @XxlJob("playInfoJob")
    public ReturnT<String> playInfoJob(String param) throws Exception {
        playerService.savePlayerInfo();
        return ReturnT.SUCCESS;
    }

}
