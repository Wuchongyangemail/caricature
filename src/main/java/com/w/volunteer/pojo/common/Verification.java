package com.w.volunteer.pojo.common;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wcy
 * @Date 2022/6/4 上午10:54
 **/
@Data
public class Verification {

    private Long id;

    private String usercode;

    private String password;

    private String email;

    private Date createTime;

    private String code;

}
