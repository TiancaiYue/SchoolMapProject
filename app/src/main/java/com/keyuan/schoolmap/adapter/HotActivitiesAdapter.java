package com.keyuan.schoolmap.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxScreenTool;

/**
 * Created by Administrator on 2017/9/21.
 */

public class HotActivitiesAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public HotActivitiesAdapter() {
        super(R.layout.item_hot_activities);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        int prossLong = RxScreenTool.getScreenWidth(mContext) - RxScreenTool.dp2px(mContext, 24);
        Utils.setViewHeightByViewWidthAndRation(mContext, holder.itemView.findViewById(R.id.iv_image), prossLong, 3, 1);
    }
}
