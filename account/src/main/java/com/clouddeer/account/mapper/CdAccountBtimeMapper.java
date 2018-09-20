package com.clouddeer.account.mapper;

import com.clouddeer.account.entity.CdAccountBtime;
import tk.mybatis.mapper.common.Mapper;

public interface CdAccountBtimeMapper extends Mapper<CdAccountBtime> {

    int deleteBtime(CdAccountBtime cdAccountBtime);
}
