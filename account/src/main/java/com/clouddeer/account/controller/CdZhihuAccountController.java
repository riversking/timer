package com.clouddeer.account.controller;

import com.clouddeer.account.biz.CdAccountBiz;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.util.ZhihuUtil;
import com.clouddeer.core.view.RequestVo;
import com.clouddeer.core.view.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "account/zhihu", produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CdZhihuAccountController {
    @Resource
    private CdAccountBiz accountService;

    @Autowired
    private ZhihuUtil zhihuUtil;

    /**
     * 新增授权账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "add")
    public ResponseVo zhihuAccountAdd(@RequestBody RequestVo<CdAccount> vo){
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
        cdAccount.setCdAccountZhihu(zhihuUtil.getZhihuInfo(cdAccount));
        accountService.addAccountZhihu(cdAccount);
        rvo.setMsg("操作成功");
        return rvo;
    }
}
