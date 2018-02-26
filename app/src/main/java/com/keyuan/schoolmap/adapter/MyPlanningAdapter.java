package com.keyuan.schoolmap.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;

/**
 * Created by Jinxt on 2017/12/5.
 */

public class MyPlanningAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public MyPlanningAdapter() {
        super(R.layout.item_my_planning);
    }

    @Override
    protected void convert(BaseViewHolder helper, Values item) {

    }
}
