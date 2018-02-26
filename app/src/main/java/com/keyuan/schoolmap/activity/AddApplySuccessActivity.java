package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keyuan.schoolmap.MainActivity;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxActivityTool;

/**
 * 活动报名成功   zmy
 */
public class AddApplySuccessActivity extends BaseActivity {
    private Button btnBack;
    private Button btnCheck;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_apply_success;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        btnBack = (Button) findViewById(R.id.btn_back);
        btnCheck = (Button) findViewById(R.id.btn_check);
    }

    @Override
    public void initEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(AddApplySuccessActivity.this, MainActivity.class);
                RxActivityTool.finish(AddApplySuccessActivity.this);
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(AddApplySuccessActivity.this, MyPlanningActivity.class);
                RxActivityTool.finish(AddApplySuccessActivity.this);
            }
        });
    }

    @Override
    public void initData() {

    }
}
