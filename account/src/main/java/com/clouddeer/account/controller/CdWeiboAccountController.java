package com.clouddeer.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.clouddeer.account.biz.CdAccountBiz;
import com.clouddeer.account.client.SpreadClient;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.LaunchAccount;
import com.clouddeer.account.util.WeboUtil;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "account/weibo", produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CdWeiboAccountController {

    @Resource
    private CdAccountBiz accountService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WeboUtil weboUtil;

    @Autowired
    private SpreadClient spreadClient;

    /**
     * 新增授权账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "add")
    public ResponseVo weiboAccountAdd(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        if ("".equals(cdAccount.getAccountName())) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--账号");
            return rvo;
        }
        if ("".equals(cdAccount.getPlatformName())) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--类型");
            return rvo;
        }
        if ("".equals(cdAccount.getAccountPwd())) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--密码");
            return rvo;
        }
        if (cdAccount.getUserId() == null) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--用户id");
            return rvo;
        }
        cdAccount.setCdAccountWeibo(weboUtil.getWeboInfo(cdAccount));
        accountService.addWeiboAccount(cdAccount);
        rvo.setMsg("操作成功");
        return rvo;
    }

    @RequestMapping(value = "update")
    public ResponseVo weiboAccountEdit(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        if (cdAccount.getId() == null) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--账号id");
            return rvo;
        }
        if (cdAccount.getAccountName().equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--账号");
            return rvo;
        }
        if (cdAccount.getPlatformName().equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--类型");
            return rvo;
        }
        if (cdAccount.getAccountPwd().equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--密码");
            return rvo;
        }
        if (cdAccount.getUserId() == null) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--用户id");
            return rvo;
        }
        cdAccount.setCdAccountWeibo(weboUtil.getWeboInfo(cdAccount));
        accountService.updateWeiboAccount(cdAccount);
        rvo.setMsg("操作成功");
        return rvo;
    }

    @RequestMapping(value = "/saveCode")
    public ResponseVo saveCode(@RequestBody RequestVo vo) {
        ResponseVo rvo = ResponseVo.ok();
        String code = vo.getParam().toString();
        if (code.equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0303, "缺少参数--验证码");
            return rvo;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("vcode", code);
        weboUtil.open();
        return rvo;
    }

    @RequestMapping(value = "/sendWeiBo")
    public ResponseVo sendWeiBo(@RequestBody RequestVo<Integer> vo) {
        ResponseVo rvo = ResponseVo.ok();
        JSONObject launchDetailResult = spreadClient.launchDetailResult(vo);
        List<LaunchAccount> launchAccounts = launchDetailResult.getJSONArray("rsp").toJavaList(LaunchAccount.class);
        for (LaunchAccount la : launchAccounts) {
            CdAccount cdAccount = accountService.getAccountById(la.getAccountId());
            weboUtil.sendWeiBo(cdAccount,la.getCdArticle());
        }
        rvo.setMsg("操作成功");
        return rvo;
    }


}
