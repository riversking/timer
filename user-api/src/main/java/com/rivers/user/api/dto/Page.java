package com.rivers.user.api.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author riversking
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = 6581029953195073637L;

    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;

}
