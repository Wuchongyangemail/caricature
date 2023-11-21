package com.w.volunteer.pojo.result;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/17 下午1:31
 **/
@Data
public class Result {

    private int total;

    private Object results;

    private String code;

    private String msg;

    private String token;
}
