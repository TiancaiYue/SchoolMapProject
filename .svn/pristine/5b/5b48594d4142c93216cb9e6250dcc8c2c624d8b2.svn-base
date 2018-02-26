package com.keyuan.schoolmap.widget.SidebarView;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.entify.ContactsNew;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

public class MySortAdapter extends BaseAdapter implements SectionIndexer {
    private List<ContactsNew> list = null;
    private Context context = null;

    public MySortAdapter(Context context, List<ContactsNew> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = null;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_address_details_list, parent, false);
            ViewUtils.inject(mHolder, convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        ContactsNew contacts = list.get(position);
        mHolder.tvUsername.setText(contacts.getUsername());
        mHolder.tvTel.setText(contacts.getMob_phone());
        mHolder.tvItemFirstLetter.setText(contacts.getFirstLetter());
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            // 第一次出现该section
            mHolder.tvItemFirstLetter.setVisibility(View.VISIBLE);
            mHolder.tvItemFirstLetter.setText(contacts
                    .getFirstLetter());
        } else {
            mHolder.tvItemFirstLetter.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.tv_item_first_letter)
        private TextView tvItemFirstLetter;
        @ViewInject(R.id.tv_username)
        private TextView tvUsername;
        @ViewInject(R.id.tv_tel)
        private TextView tvTel;
        @ViewInject(R.id.iv_head_image)
        private PorterShapeImageView ivHeadImage;
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
        for (int i = 0; i < getCount(); i++) {
            String firstLetter = list.get(i).getFirstLetter();
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
        return list.get(position).getFirstLetter().charAt(0);
    }

}
