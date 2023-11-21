package com.w.volunteer.pojo.param;

import lombok.Data;

@Data
public class CommentParam extends BaseParam{
    private String carCode;

    private String code;

    private String name;

    private String url;

    private String carCommentCode;
}
