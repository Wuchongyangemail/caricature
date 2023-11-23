package com.w.volunteer.pojo.param;

import lombok.Data;

import java.util.Date;

@Data
public class UserParam {

    private String userName;

    private String password;

    private Date createTime;

    private Integer sex;

    private String email;

    private String tel;

    private Integer character;

    private Long id;
}
