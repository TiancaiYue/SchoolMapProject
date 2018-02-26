package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.keyuan.schoolmap.MainActivity;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.base.BaseTakePhotoActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etUserName;
    private EditText etPassword;
    private TextView tvSchoolName;
    private LinearLayout llChooseSchool;
    private Button btnLogin;
    private TextView tvGoToRegister;
    private TextView tvForgetPassword;
    private ImageView ivWechat;
    private OptionsPickerView chooseView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvSchoolName = (TextView) findViewById(R.id.tv_school_name);
        llChooseSchool = (LinearLayout) findViewById(R.id.ll_choose_school);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvGoToRegister = (TextView) findViewById(R.id.tv_go_to_register);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        ivWechat = (ImageView) findViewById(R.id.iv_wechat);
    }

    @Override
    public void initEvent() {
        btnLogin.setOnClickListener(this);
        tvGoToRegister.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        llChooseSchool.setOnClickListener(this);
    }

    @Override
    public void initData() {
        (findViewById(R.id.ll_left)).setVisibility(View.INVISIBLE);
        (findViewById(R.id.ll_left)).setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.tv_go_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.ll_choose_school:
                List<String> list = new ArrayList<>();
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                initStateChoose1(list, tvSchoolName);
                break;
        }
    }

    /**
     * 选择列表
     */
    private void initStateChoose1(final List<String> arraryList, final TextView textView) {
        final List<String> nullList = new ArrayList<>();
        chooseView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = arraryList.get(options2).toString();
                textView.setText(tx);
            }
        })
                .setCancelColor(Color.parseColor("#b99042"))
                .setSubmitColor(Color.parseColor("#b99042")).build();
        (chooseView.findViewById(R.id.options1)).setVisibility(View.GONE);
        (chooseView.findViewById(R.id.options3)).setVisibility(View.GONE);
        chooseView.setNPicker(nullList, arraryList, nullList);
        chooseView.show();
    }
}
