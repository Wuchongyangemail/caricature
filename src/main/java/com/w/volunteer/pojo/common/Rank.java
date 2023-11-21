package com.w.volunteer.pojo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/10 下午7:39
 **/
@Data
public class Rank implements Serializable {
    private Long id;

    private String volunteerCode;

    private String schoolName;

    private String year;

    private Long areaId;

    private Double rank;

    private String areaName;

    private String batch;

    private String department;
}
