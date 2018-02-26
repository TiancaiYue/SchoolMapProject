package com.keyuan.schoolmap.adapter;


import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.ContactsNew;
import com.keyuan.schoolmap.entify.Values;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class AddressDetailsListAdapter extends BaseQuickAdapter<ContactsNew, BaseViewHolder> implements SectionIndexer {
    public AddressDetailsListAdapter() {
        super(R.layout.item_address_details_list);
    }

    @Override
    protected void convert(BaseViewHolder holder, ContactsNew item) {
        holder.setText(R.id.tv_username, item.getUsername());
        holder.setText(R.id.tv_tel, item.getMob_phone());
        holder.setText(R.id.tv_item_first_letter, item.getFirstLetter());
        int section = getSectionForPosition(holder.getPosition());
        if (holder.getPosition() == getPositionForSection(section)) {
            // 第一次出现该section
            holder.itemView.findViewById(R.id.tv_item_first_letter).setVisibility(View.VISIBLE);
            ((TextView) holder.itemView.findViewById(R.id.tv_item_first_letter)).setText(item.getFirstLetter());
        } else {
            holder.itemView.findViewById(R.id.tv_item_first_letter).setVisibility(View.GONE);
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    // 做字母索引的时候常常会用到SectionIndexer这个接口
    // 1. getSectionForPosition() 通过该项的位置，获得所在分类组的索引号
    // 2. getPositionForSection() 根据分类列的索引号获得该序列的首个位置

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String firstLetter = getData().get(i).getFirstLetter();
            char firstChar = firstLetter.charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    // 根据position获取分类的首字母的Char ascii值
    @Override
    public int getSectionForPosition(int position) {
        return getData().get(position).getFirstLetter().charAt(0);
    }
}
