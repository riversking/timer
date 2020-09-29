package com.rivers.nba.dto;

import com.rivers.core.bean.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class StadiumDTO extends Page implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String country;
}
