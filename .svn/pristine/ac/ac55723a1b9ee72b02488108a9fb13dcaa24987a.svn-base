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

public class HotShopAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public HotShopAdapter() {
        super(R.layout.item_hot_shop);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        //设置宽高比例
        int prossLong = RxScreenTool.getScreenWidth(mContext) - RxScreenTool.dp2px(mContext, 36);
        Utils.setViewHeightByViewWidthAndRation(mContext, holder.itemView.findViewById(R.id.iv_image), prossLong / 2, 3, 2);
    }
}
