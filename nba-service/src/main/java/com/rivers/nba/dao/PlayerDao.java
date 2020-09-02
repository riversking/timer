package com.rivers.nba.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.nba.model.PlayerModel;

import java.util.List;

/**
 * PlayerDao
 *
 * @author riversking
 * @Date 2020-09-02 10:04
 */
public interface PlayerDao extends BaseMapper<PlayerModel> {

    List<Integer> selectPlayerId();

}
