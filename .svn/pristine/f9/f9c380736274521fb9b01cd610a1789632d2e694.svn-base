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

public class RecommendShopAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public RecommendShopAdapter() {
        super(R.layout.item_recommend_shop);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        //设置宽高比例
        int prossLong = RxScreenTool.getScreenWidth(mContext) - RxScreenTool.dp2px(mContext, 24);
        Utils.setViewHeightByViewWidthAndRation(mContext, holder.itemView.findViewById(R.id.iv_image), prossLong, 3, 1);
    }
}
