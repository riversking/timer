package com.clouddeer.account.client;

import com.alibaba.fastjson.JSONObject;
import com.clouddeer.core.view.RequestVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CD-INFRASTRUCTURE")
public interface TagClient {

    @RequestMapping(value = "/infrastructure/accountTag/list")
    JSONObject accountTagResult(@RequestBody RequestVo<Integer> vo);
}
