package com.clouddeer.account.mapper;


import com.clouddeer.account.entity.CdAccountWangYi;
import com.clouddeer.account.entity.CdAccountWeibo;
import tk.mybatis.mapper.common.Mapper;

public interface CdAccountWangYiMapper extends Mapper<CdAccountWangYi> {
    CdAccountWangYi selectAccountInfo(Integer accountId);

    int deleteWangYi(CdAccountWangYi cdAccountWangYi);
}
