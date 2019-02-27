package com.clouddeer.account.controller;

import com.clouddeer.account.biz.CdAccountBiz;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.util.BtimeUtil;
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

@RestController
@RequestMapping(value = "account/btime", produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CdBtimeAccountController {
    @Resource
    private CdAccountBiz accountService;

    @Autowired
    private BtimeUtil btimeUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 新增授权账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "add")
    public ResponseVo btimeAccountAdd(@RequestBody RequestVo<CdAccount> vo){
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
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
        cdAccount.setCdAccountBtime(btimeUtil.getBtimeInfo(cdAccount));
        accountService.addAccountBtime(cdAccount);
        rvo.setMessage("操作成功");
        return rvo;
    }

    /**
     * 输入图片验证码
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/saveCode")
    public ResponseVo saveCode(@RequestBody RequestVo vo) {
        ResponseVo rvo = ResponseVo.ok();
        String code = vo.getParam().toString();
        if (code.equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0303, "缺少参数--验证码");
            return rvo;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("btime", code);
        btimeUtil.open();
        return rvo;
    }

    @RequestMapping(value = "update")
    public ResponseVo btimeAccountEdit(@RequestBody RequestVo<CdAccount> vo) {
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
        cdAccount.setCdAccountBtime(btimeUtil.getBtimeInfo(cdAccount));
        accountService.updateBtimeAccount(cdAccount);
        rvo.setMessage("操作成功");
        return rvo;
    }
}
