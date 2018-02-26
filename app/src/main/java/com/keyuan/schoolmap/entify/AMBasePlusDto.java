package com.keyuan.schoolmap.entify;

/**
 * Created by Administrator on 2017/8/1.
 */

public class AMBasePlusDto<T> extends AMBaseDto {
    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
