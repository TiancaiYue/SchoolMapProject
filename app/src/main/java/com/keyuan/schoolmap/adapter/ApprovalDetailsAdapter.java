package com.keyuan.schoolmap.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxScreenTool;

/**
 * Created by Administrator on 2017/9/21.
 */

public class ApprovalDetailsAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public ApprovalDetailsAdapter() {
        super(R.layout.item_approval_details);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        if (holder.getPosition() == 0) {
            RelativeLayout relativeLayout = (RelativeLayout) holder.itemView.findViewById(R.id.rel_item);
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();//获取按钮的布局
            layoutParams.height = RxScreenTool.dp2px(mContext, 40);
            relativeLayout.setLayoutParams(layoutParams);//设置修改后的布局。
            (holder.itemView.findViewById(R.id.view_next)).setVisibility(View.GONE);
        } else {
            (holder.itemView.findViewById(R.id.view_next)).setVisibility(View.VISIBLE);
        }
    }
}
