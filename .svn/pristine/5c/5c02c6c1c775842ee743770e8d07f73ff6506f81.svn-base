package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxToastTool;

/**
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone;
    private EditText etAdvice;
    private Button btnSubmit;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_right:
                finish();
                break;
            case R.id.btn_submit:
                checkInputInfo();
                break;
            default:
                break;
        }
    }

    /**
     * 检查输入信息
     */
    private void checkInputInfo() {
        if (TextUtils.isEmpty(etAdvice.getText().toString())) {
            RxToastTool.showShort("请输入遇到的问题和建议");
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            RxToastTool.showShort("请输入联系人电话");
        } else {
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        etAdvice = (EditText) findViewById(R.id.et_advice);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    public void initEvent() {
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
}
