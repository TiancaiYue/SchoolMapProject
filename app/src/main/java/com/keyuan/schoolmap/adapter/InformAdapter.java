package com.keyuan.schoolmap.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;

public class InformAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public InformAdapter() {
        super(R.layout.item_info);
    }

    @Override
    protected void convert(BaseViewHolder helper, Values item) {
        if (!item.isChoose()) {
            (helper.itemView.findViewById(R.id.iv_round)).setVisibility(View.VISIBLE);
        } else {
            (helper.itemView.findViewById(R.id.iv_round)).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.ll_more);
        helper.addOnClickListener(R.id.iv_detail);
    }
}
