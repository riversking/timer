package com.rivers.nba.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rivers.nba.dto.PlayerDTO;
import com.rivers.nba.model.PlayerModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * PlayerDao
 *
 * @author riversking
 * @Date 2020-09-02 10:04
 */
public interface PlayerDao extends BaseMapper<PlayerModel> {

    List<Integer> selectPlayerId();

    IPage<PlayerModel> selectPlayerPage(Page<?> page, @Param("record") PlayerDTO playDTO);

}
