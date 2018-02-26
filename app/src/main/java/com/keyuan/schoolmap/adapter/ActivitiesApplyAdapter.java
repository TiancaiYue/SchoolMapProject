package com.keyuan.schoolmap.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;

/**
 * Created by Administrator on 2017/9/21.
 */

public class ActivitiesApplyAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public ActivitiesApplyAdapter() {
        super(R.layout.item_choose_class);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        if (item.isChoose()) {
            ((ImageView) holder.itemView.findViewById(R.id.iv_choose)).setImageResource(R.drawable.ic_selected);
        } else {
            ((ImageView) holder.itemView.findViewById(R.id.iv_choose)).setImageResource(R.drawable.ic_unselected);
        }
    }
}
