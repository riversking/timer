package com.clouddeer.account.mapper;

import com.clouddeer.account.entity.CdAccountToutiao;
import tk.mybatis.mapper.common.Mapper;

/**
 * 授权账户-头条表
 * 
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-13 15:27:02
 */

public interface CdAccountToutiaoMapper extends Mapper<CdAccountToutiao> {

    CdAccountToutiao selectToutiaoInfo(Integer accountId);

    int deleteToutiao(CdAccountToutiao cdAccountToutiao);
	
}
