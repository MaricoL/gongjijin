package com.ruanzhong.gongjijin.utils;

public class AjaxResult {

    private Object[] data;
    private String msg;

    public AjaxResult(Object... data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
