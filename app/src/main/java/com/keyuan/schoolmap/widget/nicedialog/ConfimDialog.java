package com.keyuan.schoolmap.widget.nicedialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.keyuan.schoolmap.R;

/**
 * Created by Administrator on 2018/2/6.
 */

public class ConfimDialog extends BaseNiceDialog {
    private String titleString;
    private onOkClick onOkClick;
    public void setMessageText(String s){
        titleString = s;
    }
    public void setOnOkClickListener(onOkClick listener) {
        onOkClick = listener;
    }

    public interface onOkClick {
        void onSetOKClick(View view);
    }

    public static ConfimDialog newInstance(String titleString) {
        Bundle bundle = new Bundle();
        bundle.putString("title", titleString);
        ConfimDialog dialog = new ConfimDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        titleString = bundle.getString("title");
    }

    @Override
    public int intLayoutId() {
        return R.layout.confirm_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        holder.setText(R.id.title, "提示");
        holder.setTextColor(R.id.message, Color.parseColor("#b99042"));
        holder.setText(R.id.message, titleString);
        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkClick != null) {
                    onOkClick.onSetOKClick(v);
                }
                dialog.dismiss();
            }
        });
    }
}
