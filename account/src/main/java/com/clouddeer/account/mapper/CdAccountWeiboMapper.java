package com.clouddeer.account.mapper;

import com.clouddeer.account.entity.CdAccountWeibo;
import tk.mybatis.mapper.common.Mapper;


public interface CdAccountWeiboMapper extends Mapper<CdAccountWeibo> {

    CdAccountWeibo selectAccountInfo(Integer accountId);

    int deleteWeibo(CdAccountWeibo cdAccountWeibo);
}