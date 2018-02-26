package com.keyuan.schoolmap.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;

/**
 * Created by zmy on 2018/1/15.
 */

public class SureCancelDialog extends BaseDialog {
    private TextView tvTitle;
    private TextView tvMessage;
    private TextView tvAgree;
    private Button btnContent;
    private Button btnCancel;
    private LinearLayout llIsChoose;

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvAgree() {
        return tvAgree;
    }

    public void setTvAgree(TextView tvAgree) {
        this.tvAgree = tvAgree;
    }

    public LinearLayout getLlIsChoose() {
        return llIsChoose;
    }

    public void setLlIsChoose(LinearLayout llIsChoose) {
        this.llIsChoose = llIsChoose;
    }

    public SureCancelDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public SureCancelDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public SureCancelDialog(Context context) {
        super(context);
        initView();
    }

    public SureCancelDialog(Activity context) {
        super(context);
        initView();
    }

    public SureCancelDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public TextView getTvMessage() {
        return tvMessage;
    }

    public void setTvMessage(TextView tvMessage) {
        this.tvMessage = tvMessage;
    }

    public Button getBtnContent() {
        return btnContent;
    }

    public void setBtnContent(Button btnContent) {
        this.btnContent = btnContent;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public void setSureListener(View.OnClickListener sureListener) {
        btnContent.setOnClickListener(sureListener);
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        btnCancel.setOnClickListener(cancelListener);
    }

    public void setllAgreeListener(View.OnClickListener agreeListener) {
        llIsChoose.setOnClickListener(agreeListener);
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_activity_details_layout, null);
        tvMessage = (TextView) dialogView.findViewById(R.id.tv_message);
        btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        btnContent = (Button) dialogView.findViewById(R.id.btn_content);
        tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        tvAgree = (TextView) dialogView.findViewById(R.id.tv_agree);
        tvAgree.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        llIsChoose = (LinearLayout) dialogView.findViewById(R.id.ll_is_choose);
        llIsChoose.setSelected(true);
        setContentView(dialogView);
    }

    @Override
    public void show() {
        super.show();
        String title = tvTitle.getText().toString().trim();
        if ("".equals(title)) {
            tvTitle.setVisibility(View.GONE);
        }
    }
}
