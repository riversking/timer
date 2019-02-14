package com.rivers.user.api.dto;


import lombok.Data;

import java.io.Serializable;


/**
 * @author riverskingking
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = 6581029953195073637L;

    private Integer page;

    private Integer pageSize;

}
