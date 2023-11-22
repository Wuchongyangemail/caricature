package com.w.volunteer.pojo.result;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/16 下午9:30
 **/
@Data
public class CollegeDto {

    private String schoolName;

    private String volunteerCode;

    private String area;

    private BigDecimal probability;

    private BigDecimal maxGrade;

    private BigDecimal avgGrade;

    private BigDecimal minGrade;

    private BigDecimal maxRank;

    private BigDecimal avgRank;

    private BigDecimal minRank;
}
