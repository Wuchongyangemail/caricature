package com.w.volunteer.pojo.param;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/16 下午8:44
 **/
@Data
public class SearchParam implements Serializable {

    private String batch;

    private String department;

    private BigDecimal score;

    private BigDecimal rank;

    private String area;

    private Long areaId;

    private String schoolArea;

    private Integer pageSize;

    private Integer currentPage;

    private String search;


}
