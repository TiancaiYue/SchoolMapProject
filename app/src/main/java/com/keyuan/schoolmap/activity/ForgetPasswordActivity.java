package com.keyuan.schoolmap.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.keyuan.schoolmap.MainActivity;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxActivityTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {
    private TextView tvSchoolName;
    private LinearLayout llChooseSchool;
    private EditText etUserName;
    private EditText etWriteVerification;
    private TextView tvGetVerification;
    private EditText etPassword;
    private Button btnLogin;
    private OptionsPickerView chooseView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tvSchoolName = (TextView) findViewById(R.id.tv_school_name);
        llChooseSchool = (LinearLayout) findViewById(R.id.ll_choose_school);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etWriteVerification = (EditText) findViewById(R.id.et_write_verification);
        tvGetVerification = (TextView) findViewById(R.id.tv_get_verification);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(ForgetPasswordActivity.this, MainActivity.class);
                RxActivityTool.finish(ForgetPasswordActivity.this);
            }
        });
        llChooseSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                list.add("无锡科技职业学院");
                initStateChoose1(list, tvSchoolName);
            }
        });
    }

    @Override
    public void initData() {
        (findViewById(R.id.ll_left)).setEnabled(false);
        (findViewById(R.id.ll_left)).setVisibility(View.INVISIBLE);
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
