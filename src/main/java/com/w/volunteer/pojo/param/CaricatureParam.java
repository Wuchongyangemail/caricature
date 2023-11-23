package com.w.volunteer.pojo.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CaricatureParam extends BaseParam {
    private String name;

    private String url;

    private String inputValue;

    private Integer show;

    private Integer id;

    private String code;

    private String introduction;

    private String tag;

    private Integer end;

    private List<String> tags;

    private BigDecimal pageView;
}
