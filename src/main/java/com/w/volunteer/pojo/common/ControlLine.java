package com.w.volunteer.pojo.common;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/10 下午7:54
 **/
@Data
public class ControlLine implements Serializable {
    private Long id;

    private BigDecimal score;

    private BigDecimal rank;

    private Long areaId;

    private String department;

    private String ateaName;

    private String year;

    private String batch;
}
