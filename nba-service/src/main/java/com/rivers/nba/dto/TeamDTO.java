package com.rivers.nba.dto;

import cn.hutool.db.Page;
import com.rivers.core.bean.LoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamDTO extends Page {

    private Integer teamId;

    private String name;

    private String conference;

    private LoginUser user;

}
