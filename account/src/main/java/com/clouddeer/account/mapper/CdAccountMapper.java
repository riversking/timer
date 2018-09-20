package com.clouddeer.account.mapper;


import com.clouddeer.account.entity.CdAccount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CdAccountMapper extends Mapper<CdAccount> {

    List<CdAccount> selectAccountByUserId(CdAccount cdAccount);

    int updateIsShare(CdAccount record);

    List<CdAccount> selectDynamicPageQuery(CdAccount cdAccount);

    List<CdAccount> selectDynamic(CdAccount cdAccount);

    List<CdAccount> selectPlanAccount(List<Integer> ids);

    CdAccount selectById(Integer id);

    List<Integer> selectUserIdByAccountId(List<Integer> ids);
}