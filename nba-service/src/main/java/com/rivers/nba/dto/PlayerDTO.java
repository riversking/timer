package com.rivers.nba.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String playerName;

    private String position;

    private Integer playerId;

    private String team;

    private Integer teamId;
}
