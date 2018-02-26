package com.keyuan.schoolmap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.keyuan.schoolmap.R;

import java.util.ArrayList;

public class GridViewTwoAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private ArrayList<String> textList;
    private ArrayList<Integer> imageList;
    private Context mContext;
    private onItemClick onItemClick;

    public GridViewTwoAdapter(Context mContext, ArrayList<Integer> imageList, ArrayList<String> textList) {
        this.textList = textList;
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_crm, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).tvText.setText(textList.get(position) + "");
            ((ViewHolder) holder).ivImage.setImageResource(imageList.get(position));
            holder.itemView.setTag(position);
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
