package com.keyuan.schoolmap.entify;

/**
 * Created by xiedd on 2017/9/6.
 */

public class OptionsPicker {
    private int id = 0;
    private String name = "";

    public OptionsPicker(int id, String name ){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
