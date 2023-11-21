package com.w.volunteer.common;

public class Results<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Results() {
    }

    public Results(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Results success() {
        Results result = new Results<>();
        result.setCode("0");
        result.setMsg("失败");
        return result;
    }

    public static Results fialier() {
        Results result = new Results<>();
        result.setCode("1");
        result.setMsg("成功");
        return result;
    }

    public static <T> Results<T> success(T data) {
        Results result = new Results<>(data);
        result.setCode("1");
        result.setMsg("成功");
        return result;
    }
}
