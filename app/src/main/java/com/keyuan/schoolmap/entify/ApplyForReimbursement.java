package com.keyuan.schoolmap.entify;

/**
 * Created by Administrator on 2017/7/31.
 */

/**
 * 提交报销订单
 */
public class ApplyForReimbursement {
    private String price = "";
    private int typeId = 0;
    private String typeName = "";
    private String description = "";

    public ApplyForReimbursement(String price, int typeId, String description) {
        this.price = price;
        this.typeId = typeId;
        this.description = description;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
