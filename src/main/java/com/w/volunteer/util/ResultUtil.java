package com.w.volunteer.util;

import com.w.volunteer.pojo.result.Result;

public class ResultUtil {

    public static void addCodeAndMsg(Result result, String code, String msg) {
        result.setCode(code);
        result.setMsg(msg);
    }
}
