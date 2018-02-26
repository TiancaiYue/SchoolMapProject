package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.base.BaseTakePhotoActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心
 */
public class PersonalDataActivity extends BaseTakePhotoActivity implements View.OnClickListener {
    private ImageView ivHeadImage;
    private EditText et_username;
    private EditText etNickname;
    private TextView tvSex;
    private EditText etWeChat;
    private EditText etQq;
    private EditText etTel;
    private EditText etAddress;
    private TextView tvStudent;
    private TextView tvTeacher;
    private OptionsPickerView chooseView;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求打开相机权限
    private static final int TAKE_PHOTO_REQUEST_CODE = 105;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片临时文件
    private File tempFile;
    // 头像Bitmap数据
    private Bitmap avatarBitmap;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    avatarBitmap = BitmapFactory.decodeFile(cropImagePath);
                    httpUpDataUrl(cropImagePath, ivHeadImage, 1);
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        ivHeadImage = (ImageView) findViewById(R.id.iv_head_image);
        et_username = (EditText) findViewById(R.id.et_user_name);
        etNickname = (EditText) findViewById(R.id.et_nickname);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        etWeChat = (EditText) findViewById(R.id.et_weChat);
        etQq = (EditText) findViewById(R.id.et_qq);
        etTel = (EditText) findViewById(R.id.et_tel);
        etAddress = (EditText) findViewById(R.id.et_address);
        tvStudent = (TextView) findViewById(R.id.tv_student);
        tvTeacher = (TextView) findViewById(R.id.tv_teacher);
    }

    @Override
    public void initEvent() {
        findViewById(R.id.ll_right).setOnClickListener(this);
        findViewById(R.id.ll_picture).setOnClickListener(this);
        findViewById(R.id.ll_sex).setOnClickListener(this);
        findViewById(R.id.ll_choose_student).setOnClickListener(this);
        findViewById(R.id.ll_teacher).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_right:
                break;
            case R.id.ll_picture:
                uploadHeadImage();
                break;
            case R.id.ll_sex:
                List<String> sexList = new ArrayList<>();
                sexList.add("男");
                sexList.add("女");
                initStateChoose1(sexList, tvSex);
                break;
            case R.id.ll_choose_student:
                startActivity(new Intent(PersonalDataActivity.this, StudentListActivity.class));
                break;
            case R.id.ll_teacher:
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
