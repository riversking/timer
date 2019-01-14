package com.rivers.user.api.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;


@SuppressWarnings("all")
public class Page implements Serializable {

    private static final long serialVersionUID = 6581029953195073637L;

    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    private Integer id;

    /**
     * @return page
     */
    public Integer getPage() {
        return page;
    }


    /**
     * \
     *
     * @param page
     */

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
