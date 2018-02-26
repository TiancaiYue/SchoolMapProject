package com.keyuan.schoolmap.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.HomeCategory;

/**
 * Created by Administrator on 2017/9/21.
 */

public class HomeCategoryAdapter extends BaseQuickAdapter<HomeCategory, BaseViewHolder> {
    public HomeCategoryAdapter() {
        super(R.layout.item_home_category);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeCategory item) {
        holder.setImageResource(R.id.iv_category_icon,item.getIconResId());
        holder.setText(R.id.tv_category_name,item.getCategoryName());
    }
}
