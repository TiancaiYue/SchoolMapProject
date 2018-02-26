package com.keyuan.schoolmap.adapter;


import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.common.Constants;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.entify.HotCourse;
import com.keyuan.schoolmap.tool.RxBigDecimalTool;
import com.keyuan.schoolmap.tool.RxGlideTool;
import com.keyuan.schoolmap.tool.RxScreenTool;

import org.raphets.roundimageview.RoundImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class FQAdapter extends BaseQuickAdapter<HotCourse.CourseInfo.Rows, BaseViewHolder> {
    public FQAdapter() {
        super(R.layout.item_fq);
    }

    @Override
    protected void convert(BaseViewHolder holder, HotCourse.CourseInfo.Rows item) {
        holder.setText(R.id.tv_gift, item.getTitle());
        if (!TextUtils.isEmpty(item.getCourse_price())) {
            holder.setText(R.id.tv_money, RxBigDecimalTool.toDecimal(item.getCourse_price(), 2));
        }
        if (!TextUtils.isEmpty(item.getCommission_points()) && item.getCommission_points().contains(".")) {
            holder.setText(R.id.tv_integral, item.getCommission_points().substring(0, item.getCommission_points().indexOf(".")) + "积分");
        } else {
            holder.setText(R.id.tv_integral, item.getCommission_points() + "积分");
        }
//        holder.setText(R.id.tv_integral, item.getCommission_points() + "积分");
        if (!TextUtils.isEmpty(item.getShow_pics())) {
            List<String> imageList = Arrays.asList(item.getShow_pics().split(","));
            RoundImageView imageView = (RoundImageView) holder.itemView.findViewById(R.id.iv_gift_image);
            if (imageList != null && imageList.size() > 0) {
                //设置宽高比例
//                int viewWith = imageView.getWidth();
                int prossLong = RxScreenTool.getScreenWidth(mContext) - RxScreenTool.dp2px(mContext, 24);
                Utils.setViewHeightByViewWidthAndRation(mContext, imageView, prossLong, 3, 2);
                RxGlideTool.loadImageViewLoding(mContext, Constants.IMAGE_MAIN_ENGINE + imageList.get(0), imageView, R.drawable.ic_default_image, R.drawable.ic_default_image);
            }

        }
    }

}
