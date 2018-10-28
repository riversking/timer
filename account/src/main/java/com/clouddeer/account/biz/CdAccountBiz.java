package com.clouddeer.account.biz;

import com.clouddeer.account.constant.AccountConstant;
import com.clouddeer.account.constant.ErrorCodeConstant;
import com.clouddeer.account.entity.*;
import com.clouddeer.account.mapper.*;
import com.clouddeer.account.util.Page;
import com.cloudder.utils.ExceptionUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 授权账户表
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-13 15:27:01
 */
@Service
@SuppressWarnings("all")
public class CdAccountBiz {

    @Resource
    private CdAccountMapper cdAccountMapper;

    @Resource
    private CdAccountWeiboMapper cdAccountWeiboMapper;

    @Resource
    private CdAccountToutiaoMapper cdAccountToutiaoMapper;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    private CdAccountWangYiMapper cdAccountWangYiMapper;

    @Resource
    private CdAccountZhihuMapper cdAccountZhihuMapper;

    @Resource
    private CdAccountBtimeMapper cdAccountBtimeMapper;

    /**
     * 新增授权账号
     *
     * @param cdAccount
     */
    public void addWeiboAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountWeibo cdAccountWeibo = cdAccount.getCdAccountWeibo();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUuid(UUID.randomUUID().toString());
            cdAccount.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setCreateUserId(1);
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.insertSelective(cdAccount);
            cdAccountWeibo.setAccountId(cdAccount.getId());
            cdAccountWeibo.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountWeibo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWeibo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWeibo.setCreateUserId(1);
            cdAccountWeibo.setUpdateUserId(1);
            cdAccountWeiboMapper.insertSelective(cdAccountWeibo);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 更新微博信息
     *
     * @param cdAccount
     */
    public void updateWeiboAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountWeibo cdAccountWeibo = cdAccount.getCdAccountWeibo();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateByPrimaryKeySelective(cdAccount);
            CdAccountWeibo caw = new CdAccountWeibo();
            caw.setAccountId(cdAccount.getId());
            caw.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            caw.setUpdateUserId(1);
            cdAccountWeiboMapper.deleteWeibo(caw);
            cdAccountWeibo.setAccountId(cdAccount.getId());
            cdAccountWeibo.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountWeibo.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWeibo.setCreateUserId(1);
            cdAccountWeibo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWeibo.setUpdateUserId(1);
            cdAccountWeiboMapper.insertSelective(cdAccountWeibo);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 查询授权账户列表
     *
     * @param cdAccount
     * @return
     */
    /**
     * 使用JAVA8 中lambda表达式替代 foreach循环
     * <p>
     * for (CdAccount a : cdAccountList) {
     * CdAccountWeibo cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(a.getId());
     * CdAccountToutiao cdAccountToutiao = cdAccountToutiaoMapper.selectToutiaoInfo(a.getId());
     * CdAccountWangYi cdAccountWangYi = cdAccountWangYiMapper.selectAccountInfo(a.getId());
     * a.setCdAccountWeibo(cdAccountWeibo);
     * a.setCdAccountToutiao(cdAccountToutiao);
     * a.setCdAccountWangYi(cdAccountWangYi);
     * }
     * <p>
     * <p>
     */
    public PageInfo<CdAccount> getAccountList(CdAccount cdAccount) {
        cdAccount.setRecordStatus(1);
        List<CdAccount> cdAccountList = cdAccountMapper.selectAccountByUserId(cdAccount);
        cdAccountList.forEach(a -> {
            CdAccountWeibo cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(a.getId());
            CdAccountToutiao cdAccountToutiao = cdAccountToutiaoMapper.selectToutiaoInfo(a.getId());
//            CdAccountWangYi cdAccountWangYi = cdAccountWangYiMapper.selectAccountInfo(a.getId());
            a.setCdAccountWeibo(cdAccountWeibo);
            a.setCdAccountToutiao(cdAccountToutiao);
//            a.setCdAccountWangYi(cdAccountWangYi);
        });
        PageInfo<CdAccount> pageInfo = new PageInfo(cdAccountList);
        return pageInfo;
    }

    /**
     * 分页查询授权账户列表
     *
     * @param cdAccount
     * @return
     */
    public Page<CdAccount> queryAccountList(CdAccount cdAccount) {
        List<CdAccount> cdAccountList = cdAccountMapper.selectDynamicPageQuery(cdAccount);
        CdAccountWeibo cdAccountWeibo = new CdAccountWeibo();
        for (CdAccount a : cdAccountList) {
            cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(a.getId());
            a.setCdAccountWeibo(cdAccountWeibo);
        }
        Page<CdAccount> page = new Page<CdAccount>();
        page.setPageNo(cdAccount.getPage());
        page.setPageSize(cdAccount.getPageSize());
        page.setTotalCount(3);
        page.setList(cdAccountList);
        return page;
    }

    /**
     * 删除授权账户
     *
     * @param cdAccount
     */
    public void deleteAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountWeibo cdAccountWeibo = new CdAccountWeibo();
        CdAccountToutiao cdAccountToutiao = new CdAccountToutiao();
        CdAccountWangYi cdAccountWangYi = new CdAccountWangYi();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_OFF);
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateByPrimaryKeySelective(cdAccount);
            if (cdAccount.getPlatformName().equals("weibo")) {
                cdAccountWeibo.setAccountId(cdAccount.getId());
                cdAccountWeibo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                cdAccountWeibo.setUpdateUserId(1);
                cdAccountWeiboMapper.deleteWeibo(cdAccountWeibo);
            }
            if (cdAccount.getPlatformName().equals("toutiao")) {
                cdAccountToutiao.setAccountId(cdAccount.getId());
                cdAccountToutiao.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                cdAccountToutiao.setUpdateUserId(1);
                cdAccountToutiaoMapper.deleteToutiao(cdAccountToutiao);
            }
            if (cdAccount.getPlatformName().equals("wangyi")) {
                cdAccountWangYi.setAccountId(cdAccount.getId());
                cdAccountWangYi.setUpdateDate(new Timestamp(System.currentTimeMillis()));
                cdAccountWangYi.setUpdateUserId(1);
                cdAccountWangYiMapper.deleteWangYi(cdAccountWangYi);
            }
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 是否共享账号 并给出价格
     *
     * @param cdAccount
     */
    public void updateAccountPrice(CdAccount cdAccount) {
        try {
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateIsShare(cdAccount);
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    public List<CdAccount> selectAccountDynamic(CdAccount cdAccount) {
        List<CdAccount> cdAccountList = cdAccountMapper.selectDynamic(cdAccount);
        CdAccountWeibo cdAccountWeibo = new CdAccountWeibo();
        for (CdAccount a : cdAccountList) {
            cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(a.getId());
            a.setCdAccountWeibo(cdAccountWeibo);
        }
        return cdAccountList;
    }

    /**
     * 新增网易账号
     *
     * @param cdAccount
     */
    public void addAccountWangYi(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountWangYi cdAccountWangYi = cdAccount.getCdAccountWangYi();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUuid(UUID.randomUUID().toString());
            cdAccount.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setCreateUserId(1);
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.insertSelective(cdAccount);
            cdAccountWangYi.setAccountId(cdAccount.getId());
            cdAccountWangYi.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountWangYi.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWangYi.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWangYi.setCreateUserId(1);
            cdAccountWangYi.setUpdateUserId(1);
            cdAccountWangYiMapper.insertSelective(cdAccountWangYi);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 新增知乎账号
     *
     * @param cdAccount
     */
    public void addAccountZhihu(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountZhihu cdAccountZhihu = cdAccount.getCdAccountZhihu();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUuid(UUID.randomUUID().toString());
            cdAccount.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setCreateUserId(1);
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.insertSelective(cdAccount);
            cdAccountZhihu.setAccountId(cdAccount.getId());
            cdAccountZhihu.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountZhihu.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountZhihu.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountZhihu.setCreateUserId(1);
            cdAccountZhihu.setUpdateUserId(1);
            cdAccountZhihuMapper.insertSelective(cdAccountZhihu);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }


    /**
     * 新增北京时间账号
     *
     * @param cdAccount
     */
    public void addAccountBtime(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountBtime cdAccountBtime = cdAccount.getCdAccountBtime();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUuid(UUID.randomUUID().toString());
            cdAccount.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setCreateUserId(1);
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.insertSelective(cdAccount);
            System.out.println("我是id：" + cdAccount.getId());
            cdAccountBtime.setAccountId(cdAccount.getId());
            cdAccountBtime.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountBtime.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountBtime.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountBtime.setCreateUserId(1);
            cdAccountBtime.setUpdateUserId(1);
            cdAccountBtimeMapper.insertSelective(cdAccountBtime);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 更新网易信息
     *
     * @param cdAccount
     */
    public void updateBtimeAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountBtime cdAccountBtime = cdAccount.getCdAccountBtime();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateByPrimaryKeySelective(cdAccount);
            CdAccountBtime cdAccountBtime1 = new CdAccountBtime();
            cdAccountBtime1.setAccountId(cdAccount.getId());
            cdAccountBtime1.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountBtime1.setUpdateUserId(1);
            cdAccountBtimeMapper.deleteBtime(cdAccountBtime1);
            cdAccountBtime.setAccountId(cdAccount.getId());
            cdAccountBtime.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountBtime.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountBtime.setCreateUserId(1);
            cdAccountBtime.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountBtime.setUpdateUserId(1);
            cdAccountBtimeMapper.insertSelective(cdAccountBtime);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 更新网易信息
     *
     * @param cdAccount
     */
    public void updateWangYiAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountWangYi cdAccountWangYi = cdAccount.getCdAccountWangYi();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateByPrimaryKeySelective(cdAccount);
            CdAccountWangYi cdAccountWangYi1 = new CdAccountWangYi();
            cdAccountWangYi1.setAccountId(cdAccount.getId());
            cdAccountWangYi1.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWangYi1.setUpdateUserId(1);
            cdAccountWangYiMapper.deleteWangYi(cdAccountWangYi1);
            cdAccountWangYi.setAccountId(cdAccount.getId());
            cdAccountWangYi.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountWangYi.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWangYi.setCreateUserId(1);
            cdAccountWangYi.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountWangYi.setUpdateUserId(1);
            cdAccountWangYiMapper.insertSelective(cdAccountWangYi);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 新增头条账号
     *
     * @param cdAccount
     */
    public void addToutiaoAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountToutiao cdAccountToutiao = cdAccount.getCdAccountToutiao();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUuid(UUID.randomUUID().toString());
            cdAccount.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setCreateUserId(1);
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.insertSelective(cdAccount);
            cdAccountToutiao.setAccountId(cdAccount.getId());
            cdAccountToutiao.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountToutiao.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountToutiao.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountToutiao.setCreateUserId(1);
            cdAccountToutiao.setUpdateUserId(1);
            cdAccountToutiaoMapper.insertSelective(cdAccountToutiao);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 更新头条信息
     *
     * @param cdAccount
     */
    public void updateToutiaoAccount(CdAccount cdAccount) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CdAccountToutiao cdAccountToutiao = cdAccount.getCdAccountToutiao();
        try {
            cdAccount.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccount.setIsSort(0);
            cdAccount.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccount.setUpdateUserId(1);
            cdAccountMapper.updateByPrimaryKeySelective(cdAccount);
            CdAccountToutiao cat = new CdAccountToutiao();
            cat.setAccountId(cdAccount.getId());
            cat.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cat.setUpdateUserId(1);
            cdAccountToutiaoMapper.deleteToutiao(cat);
            cdAccountToutiao.setAccountId(cdAccount.getId());
            cdAccountToutiao.setRecordStatus(AccountConstant.RECORD_STATUS_ON);
            cdAccountToutiao.setCreateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountToutiao.setCreateUserId(1);
            cdAccountToutiao.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            cdAccountToutiao.setUpdateUserId(1);
            cdAccountToutiaoMapper.insertSelective(cdAccountToutiao);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            ExceptionUtil.throwBusinessException(ErrorCodeConstant.ECODE_0301, e);
        }
    }

    /**
     * 通过plan的accountId列表查询账号
     *
     * @param ids
     * @return
     */
    public List<CdAccount> getPlanAccount(List<Integer> ids) {
        List<CdAccount> planAccountList = cdAccountMapper.selectPlanAccount(ids);
        CdAccountWeibo cdAccountWeibo = new CdAccountWeibo();
        CdAccountToutiao cdAccountToutiao = new CdAccountToutiao();
        for (CdAccount a : planAccountList) {
            cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(a.getId());
            cdAccountToutiao = cdAccountToutiaoMapper.selectToutiaoInfo(a.getId());
            a.setCdAccountWeibo(cdAccountWeibo);
            a.setCdAccountToutiao(cdAccountToutiao);
        }
        return planAccountList;
    }

    public CdAccount getAccountById(Integer id) {
        CdAccount cdAccount = cdAccountMapper.selectById(id);
        CdAccountWeibo cdAccountWeibo = cdAccountWeiboMapper.selectAccountInfo(cdAccount.getId());
        CdAccountToutiao cdAccountToutiao = cdAccountToutiaoMapper.selectToutiaoInfo(cdAccount.getId());
        cdAccount.setCdAccountWeibo(cdAccountWeibo);
        cdAccount.setCdAccountToutiao(cdAccountToutiao);
        return cdAccount;
    }

    public List<Integer> getUserId(List<Integer> ids) {
        return cdAccountMapper.selectUserIdByAccountId(ids);
    }


}