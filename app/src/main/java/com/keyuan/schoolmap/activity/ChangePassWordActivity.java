package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;

public class ChangePassWordActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPassword;
    private EditText etPasswordAgain;
    private EditText etPasswordAgain2;
    private Button btnSure;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_pass_word;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        etPassword = (EditText) findViewById(R.id.et_password);
        etPasswordAgain = (EditText) findViewById(R.id.et_password_again);
        etPasswordAgain2 = (EditText) findViewById(R.id.et_password_again2);
        btnSure = (Button) findViewById(R.id.btn_sure);
    }

    @Override
    public void initEvent() {
        btnSure.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                    Toast.makeText(this, "请输入原密码！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etPasswordAgain.getText().toString().trim())) {
                    Toast.makeText(this, "请输入新密码！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etPasswordAgain2.getText().toString().trim())) {
                    Toast.makeText(this, "请再次输入新密码！", Toast.LENGTH_SHORT).show();
                } else if (!etPasswordAgain.getText().toString().trim().equals(etPasswordAgain2.getText().toString().trim())) {
                    Toast.makeText(this, "请检查两次密码是否一致！", Toast.LENGTH_SHORT).show();
                } else {

                }
                break;
        }
    }
}
