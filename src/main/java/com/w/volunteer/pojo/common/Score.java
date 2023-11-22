package com.w.volunteer.pojo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/10 下午7:37
 **/
@Data
public class Score implements Serializable {

    private Long id;

    private String volunteerCode;

    private String schoolName;

    private String year;

    private Long areaId;

    private Double grade;

    private String areaName;

    private String batch;

    private String department;

    private Double rank;

    private Long schoolAreaId;

    private String searchAreaName;
}
