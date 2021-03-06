package com.clouddeer.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.clouddeer.account.client.SpreadClient;
import com.clouddeer.account.client.TagClient;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.*;
import com.clouddeer.account.biz.CdAccountBiz;
import com.clouddeer.account.util.WeboUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyichuan
 */
@RestController
@RequestMapping(value = "account", produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CdAccountController {

    private static Logger logger = LoggerFactory.getLogger(CdAccountController.class);

    @Autowired
    private CdAccountBiz accountService;

    @Resource
    private SpreadClient spreadClient;

    @Resource
    private TagClient tagClient;

    @Autowired
    private WeboUtil weboUtil;


    /**
     * 账号列表
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "list")
    @PreAuthorize("hasAuthority('res1')")
    public ResponseVo accountList(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        if (cdAccount.getPage() != null && cdAccount.getPageSize() != null) {
            PageHelper.startPage(cdAccount.getPage(), cdAccount.getPageSize());
        }
        PageInfo<CdAccount> cdAccountPage = accountService.getAccountList(cdAccount);
        rvo.setMessage("操作成功");
        rvo.setData(cdAccountPage);
        return rvo;
    }

    /**
     * 作废授权账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "delete")
    public ResponseVo accountWeiboDelete(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        accountService.deleteAccount(cdAccount);
        rvo.setMessage("操作成功");
        return rvo;
    }

    /**
     * 分享 以及价格
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "isShareUpdate")
    public ResponseVo isShareUpdate(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        accountService.updateAccountPrice(cdAccount);
        rvo.setMessage("操作成功");
        return rvo;
    }

    /**
     * 搜索账号
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "search")
    public ResponseVo searchAccount(@RequestBody RequestVo<CdAccount> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount cdAccount = vo.getParam();
        List<CdAccount> cdAccountList = accountService.selectAccountDynamic(cdAccount);
        rvo.setMessage("操作成功");
        rvo.setData(cdAccountList);
        return rvo;
    }

    @RequestMapping(value = "planAccountList")
    public ResponseVo planAccountList(@RequestBody RequestVo<List<Integer>> vo) {
        ResponseVo rvo = ResponseVo.ok();
        List<CdAccount> planAccounts = accountService.getPlanAccount(vo.getParam());
        rvo.setMessage("操作成功");
        rvo.setData(planAccounts);
        return rvo;
    }

    @RequestMapping(value = "accountDetail")
    public ResponseVo accountDetail(@RequestBody RequestVo<Integer> vo) {
        ResponseVo rvo = ResponseVo.ok();
        CdAccount account = accountService.getAccountById(vo.getParam());
        JSONObject planResult = spreadClient.planResult(vo);
        JSONObject accountArticleResult = spreadClient.accountArticleResult(vo);
        JSONObject tagResult = tagClient.accountTagResult(vo);
        List<Plan> planList = planResult.getJSONArray("rsp").toJavaList(Plan.class);
        List<CdArticle> articleList = accountArticleResult.getJSONArray("rsp").toJavaList(CdArticle.class);
        List<CdTagLibrary> tagList = tagResult.getJSONArray("rsp").toJavaList(CdTagLibrary.class);
        account.setPlanList(planList);
        account.setArticleList(articleList);
        account.setTagList(tagList);
        rvo.setMessage("操作成功");
        rvo.setData(account);
        return rvo;
    }

    @RequestMapping(value = "getUserIdByAccountId")
    public ResponseVo getUserIdByAccountId(@RequestBody RequestVo<List<Integer>> vo) {
        ResponseVo rvo = ResponseVo.ok();
        List<Integer> userIdList = accountService.getUserId(vo.getParam());
        rvo.setMessage("操作成功");
        rvo.setData(userIdList);
        return rvo;
    }


    @RequestMapping(value = "checkAccountStatus")
    public ResponseVo checkAccountStatus(@RequestBody RequestVo<String> vo) {
        ResponseVo rvo = ResponseVo.ok();
        Integer i = weboUtil.getCookie(vo.getParam());
        if (i.equals(1)) {
            rvo = ResponseVo.fail(ErrorCodeConstant.ECODE_0302, "Cookie不存在");
            return rvo;
        }
        return rvo;
    }
}