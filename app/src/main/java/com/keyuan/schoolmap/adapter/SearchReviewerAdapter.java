package com.keyuan.schoolmap.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.HomeCategory;
import com.keyuan.schoolmap.entify.Values;

/**
 * Created by Administrator on 2017/9/21.
 */

public class SearchReviewerAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public SearchReviewerAdapter() {
        super(R.layout.item_select_contact);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        if (item.isChoose()) {
            ((ImageView) holder.itemView.findViewById(R.id.iv_select_state)).setImageResource(R.drawable.ic_selected);
        } else {
            ((ImageView) holder.itemView.findViewById(R.id.iv_select_state)).setImageResource(R.drawable.ic_unselected);
        }
    }
}
