package com.keyuan.schoolmap.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.widget.nicedialog.BaseNiceDialog;
import com.keyuan.schoolmap.widget.nicedialog.ConfimDialog;
import com.keyuan.schoolmap.widget.nicedialog.NiceDialog;
import com.keyuan.schoolmap.widget.nicedialog.ViewConvertListener;
import com.keyuan.schoolmap.widget.nicedialog.ViewHolder;

public class DialogTestActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog_test;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    public void showDialog2(View view) {
        NiceDialog.init()
                .setLayoutId(R.layout.commit_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final EditText editText = holder.getView(R.id.edit_input);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showDialog3(View view) {
        NiceDialog.init()
                .setLayoutId(R.layout.ad_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setWidth(210)
                .setOutCancel(false)
                .setAnimStyle(R.style.EnterExitAnimation)
                .show(getSupportFragmentManager());
        ConfimDialog confimDialog = new ConfimDialog();
        confimDialog.setMessageText("ddd");
        confimDialog.setOutCancel(false);
        confimDialog.show(getSupportFragmentManager());
    }


    public void showDialog4(View view) {
        NiceDialog.init()
                .setLayoutId(R.layout.loading_layout)
                .setWidth(100)
                .setHeight(100)
                .setDimAmount(0)
                .show(getSupportFragmentManager());
    }

    public void showDialog5(View view) {
        ConfimDialog confimDialog = new ConfimDialog();
        confimDialog.setMessageText("确定提交吗？");
        confimDialog.setMargin(60);
        confimDialog.setOutCancel(false);
        confimDialog.show(getSupportFragmentManager());
        confimDialog.setOnOkClickListener(new ConfimDialog.onOkClick() {
            @Override
            public void onSetOKClick(View view) {
                showToast("sdfffdsdf");
            }
        });
        //*************newInstance()*********************
//        ConfirmDialog.newInstance("2")
//                .setMargin(60)
//                .setOutCancel(false)
//                .show(getSupportFragmentManager());
    }
}
