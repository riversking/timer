package com.rivers.nba.dto;

import com.rivers.core.bean.LoginUser;
import com.rivers.core.bean.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamDTO extends Page implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer teamId;

    private String name;

    private String conference;

    private LoginUser user;

}
