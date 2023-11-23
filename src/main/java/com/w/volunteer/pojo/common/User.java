package com.w.volunteer.pojo.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/10 下午7:36
 **/
@Data
public class User implements Serializable {

    private String userName;

    private String tel;

    private String password;

    private Integer sex;

    private Integer character;

    private String email;

    private Date createTime;

    private String token;

    private Long id;
}
