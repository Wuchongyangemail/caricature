package com.w.volunteer.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wcy
 * @Date 2022/6/4 上午10:26
 **/
@Data
public class LoginParam implements Serializable {

    private String userCode;

    private String password;

    private String email;

    private String code;
}
