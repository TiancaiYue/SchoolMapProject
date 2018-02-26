package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxActivityTool;

/**
 * 活动在线报名
 */
public class ApplyOnlineActivity extends BaseActivity {
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvTime;
    private TextView tvPosition;
    private TextView tvDetail;
    private EditText etName;
    private EditText etPhone;
    private Button btnSure;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_online;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvPosition = (TextView) findViewById(R.id.tv_position);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnSure = (Button) findViewById(R.id.btn_sure);
    }

    @Override
    public void initEvent() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    showToast("请输入姓名！");
                } else if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    showToast("请输入手机号！");
                } else {
                    RxActivityTool.skipActivity(ApplyOnlineActivity.this, AddApplySuccessActivity.class);
                    RxActivityTool.finish(ApplyOnlineActivity.this);
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}
