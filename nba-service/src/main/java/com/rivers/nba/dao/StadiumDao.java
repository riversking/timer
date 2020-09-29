package com.rivers.nba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rivers.nba.dto.StadiumDTO;
import com.rivers.nba.model.StadiumModel;
import org.apache.ibatis.annotations.Param;

/**
 * StadiumDao
 *
 * @author riversking
 * @Date 2020-09-25 12:16
 */
public interface StadiumDao extends BaseMapper<StadiumModel> {

    Integer hasData();

    IPage<StadiumModel> selectStadiumPage(Page<?> page, @Param("record") StadiumDTO stadiumDTO);


}