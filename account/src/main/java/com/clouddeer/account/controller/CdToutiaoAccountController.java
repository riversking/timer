package com.clouddeer.account.controller;

import com.clouddeer.account.biz.CdAccountBiz;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.util.ToutiaoUtil;
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
@RequestMapping(value = "account/toutiao", produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CdToutiaoAccountController {

    @Resource
    private CdAccountBiz accountService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ToutiaoUtil toutiaoUtil;


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
        if (cdAccount.getAccountName().equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--账号");
            return rvo;
        }
        if (cdAccount.getPlatformName().equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--类型");
            return rvo;
        }
        if (cdAccount.getUserId() == null) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--用户id");
            return rvo;
        }
        cdAccount.setCdAccountToutiao(toutiaoUtil.getToutiaoInfo(cdAccount));
        accountService.addToutiaoAccount(cdAccount);
        rvo.setMessage("操作成功");
        return rvo;
    }

    /**
     * 新增授权账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "update")
    public ResponseVo toutiaoAccountEdit(@RequestBody RequestVo<CdAccount> vo) {
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
        if (cdAccount.getUserId() == null) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "缺少参数--用户id");
            return rvo;
        }
        cdAccount.setCdAccountToutiao(toutiaoUtil.getToutiaoInfo(cdAccount));
        accountService.updateToutiaoAccount(cdAccount);
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
        operations.set("tcode", code);
        toutiaoUtil.open();
        return rvo;
    }

    /**
     * 输入短信验证码
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/mobileCode")
    public ResponseVo mobileCode(@RequestBody RequestVo vo) {
        ResponseVo rvo = ResponseVo.ok();
        String code = vo.getParam().toString();
        if (code.equals("")) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0303, "缺少参数--手机验证码");
            return rvo;
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("MCode", code);
        toutiaoUtil.open();
        return rvo;
    }



}
