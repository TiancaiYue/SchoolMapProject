package com.keyuan.schoolmap.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.keyuan.schoolmap.R;

/**
 * Created by Administrator on 2017/12/15.
 */

public class LoginView extends ProgressDialog {
    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.layout_login);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {//开启
        super.show();
    }

    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
}
