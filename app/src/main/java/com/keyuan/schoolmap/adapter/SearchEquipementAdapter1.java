package com.keyuan.schoolmap.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;

/**
 * 设备搜索(学院)
 * Created by zmy on 2017/8/17.
 */

public class SearchEquipementAdapter1 extends BaseQuickAdapter<Values, BaseViewHolder> {
    public SearchEquipementAdapter1() {
        super(R.layout.item_search_equipment);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        holder.setText(R.id.tv_name, "物联网与软件技术学院");
        if (item.isChoose()) {
            holder.setTextColor(R.id.tv_name, Color.parseColor("#b99042"));
        } else {
            holder.setTextColor(R.id.tv_name, Color.parseColor("#565656"));
        }
        holder.addOnClickListener(R.id.tv_name);
    }
}
