package com.keyuan.schoolmap.entify;

import java.io.Serializable;

/**
 * Created by xiedd on 2017/8/1.
 */

public class AMBaseDto implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
