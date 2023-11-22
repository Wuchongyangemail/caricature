package com.w.volunteer.pojo.common;

import lombok.Data;
import java.io.Serializable;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/9 下午9:58
 **/
@Data
public class School implements Serializable {

    private Long id;

    private String schoolName;

    private Long areaId;

    private String volunteerCode;

    private String areaName;
}
