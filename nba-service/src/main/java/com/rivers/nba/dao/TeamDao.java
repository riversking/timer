package com.rivers.nba.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rivers.nba.dto.PlayerDTO;
import com.rivers.nba.dto.TeamDTO;
import com.rivers.nba.model.PlayerModel;
import com.rivers.nba.model.TeamModel;
import org.apache.ibatis.annotations.Param;

/**
 * TeamDao
 *
 * @author riversking
 * @Date 2020-09-09 17:48
 */
public interface TeamDao extends BaseMapper<TeamModel> {

    Integer hasData();

    IPage<TeamModel> selectTeamPage(Page<?> page, @Param("record") TeamDTO teamDTO);

}
