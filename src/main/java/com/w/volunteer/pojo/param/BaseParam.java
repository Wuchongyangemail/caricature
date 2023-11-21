package com.w.volunteer.pojo.param;

import lombok.Data;

@Data
public class BaseParam {
    private Integer limit;

    private Integer page;

    private Integer count;

    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;
}
