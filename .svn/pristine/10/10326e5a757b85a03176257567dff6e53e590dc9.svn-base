package com.keyuan.schoolmap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.tool.RxGlideTool;

import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<String> imageList;
    private Context mContext;
    private onItemClick onItemClick;

    public GridViewAdapter(List<String> imageList, Context mContext) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picture, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            holder.itemView.setTag(position);
            RxGlideTool.loadImageViewLoding(mContext, imageList.get(position), ((ViewHolder) holder).ivImage, R.drawable.ic_add_picture, R.drawable.ic_add_picture);
        }
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setOnItemClickListener(onItemClick listener) {
        onItemClick = listener;
    }

    public interface onItemClick {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        if (onItemClick != null) {
            onItemClick.onItemClick(view, pos);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvText = (TextView) itemView.findViewById(R.id.iv_text);
        }
    }
}
