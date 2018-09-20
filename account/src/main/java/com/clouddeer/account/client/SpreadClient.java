package com.clouddeer.account.client;

import com.alibaba.fastjson.JSONObject;
import com.clouddeer.core.view.RequestVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CD-SPREAD")
public interface SpreadClient {

    @RequestMapping(value = "/spread/plan/getPlanByAccountId")
    JSONObject planResult(@RequestBody RequestVo<Integer> vo);

    @RequestMapping(value = "spread/launch/getLaunchByAccountId")
    JSONObject launchResult(@RequestBody RequestVo<Integer> vo);

    @RequestMapping(value = "spread/launch/getAccountArticle")
    JSONObject launchDetailResult(@RequestBody RequestVo<Integer> vo);

    @RequestMapping(value = "spread/launch/getAccountArticle")
    JSONObject accountArticleResult(@RequestBody RequestVo<Integer> vo);
}
