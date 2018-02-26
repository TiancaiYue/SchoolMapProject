package com.keyuan.schoolmap.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.Values;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/21.
 */

public class HotMessageAdapter extends BaseQuickAdapter<Values, BaseViewHolder> {
    public HotMessageAdapter() {
        super(R.layout.item_message_hot);
    }

    @Override
    protected void convert(BaseViewHolder holder, Values item) {
        ((PorterShapeImageView) holder.itemView.findViewById(R.id.iv_avatar)).setImageResource(R.drawable.ic_address_list);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ImageInfo info = new ImageInfo();
            info.setThumbnailUrl("http://www.9hxb.com/huixiaobao/upload/20171124/201711241748050072.jpg");
            info.setBigImageUrl("http://www.9hxb.com/huixiaobao/upload/20171124/201711241748050072.jpg");
            imageInfo.add(info);
        }
        if (imageInfo != null) {
            ((NineGridView) holder.itemView.findViewById(R.id.nine_grid)).setSingleImageSize(186);
        }
        if (item.isChoose()){
            holder.itemView.findViewById(R.id.ll_thump).setSelected(true);
        }else {
            holder.itemView.findViewById(R.id.ll_thump).setSelected(false);
        }
        ((NineGridView) holder.itemView.findViewById(R.id.nine_grid)).setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
        holder.addOnClickListener(R.id.ll_comments);
        holder.addOnClickListener(R.id.ll_thump);
    }
}
