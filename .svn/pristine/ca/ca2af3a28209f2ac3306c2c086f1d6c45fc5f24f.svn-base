package com.keyuan.schoolmap.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * 设置
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvVersionNumber;
    private LinearLayout llCompanyIntroduction;
    private LinearLayout llFeedback;
    private TextView tvCancel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_company_introduction:
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(SettingActivity.this, FeedBackActivity.class));
                break;
            case R.id.tv_cancel:
                cancelData();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tvVersionNumber = (TextView) findViewById(R.id.tv_version_number);
        llCompanyIntroduction = (LinearLayout) findViewById(R.id.ll_company_introduction);
        llFeedback = (LinearLayout) findViewById(R.id.ll_feedback);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
    }

    @Override
    public void initEvent() {
        llCompanyIntroduction.setOnClickListener(this);
        llFeedback.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tvVersionNumber.setText(getAppVersionName(this));
    }

    /**
     * 退出登录弹框
     */
    private void cancelData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                logout();
//                LogoutManageUtils.getInstance().logOut(SettingActivity.this);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

//    /**
//     * 退出登录
//     */
//    private void logout() {
//        EMClient.getInstance().logout(true, new EMCallBack() {
//            @Override
//            public void onSuccess() {
//                // TODO Auto-generated method stub
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogoutManageUtils.getInstance().logOut(SettingActivity.this);
//                    }
//                });
//            }
//
//            @Override
//            public void onProgress(int progress, String status) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void onError(int code, String message) {
//                // TODO Auto-generated method stub
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        LogoutManageUtils.getInstance().logOut(SettingActivity.this);
//                    }
//                });
//            }
//        });
//    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            RxLogTool.v("版本名称：" + versionName + ":版本号:" + versioncode);
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
