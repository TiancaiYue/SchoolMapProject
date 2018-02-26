package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.base.BaseTakePhotoActivity;
import com.keyuan.schoolmap.common.DateUtils;

import java.io.File;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 添加商铺商品
 */
public class AddShopThingActivity extends BaseTakePhotoActivity implements View.OnClickListener {
    private EditText etTitle;
    private EditText etPrice;
    private Button btnSend;
    private ImageView image;
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
                    //显示图片
//                    porterShapeImageView.setImageBitmap(avatarBitmap);
                    //httpUpDataUrl(avatarBitmap);
                    httpUpDataUrl(cropImagePath, image, 1);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                uploadHeadImage();
                break;
            case R.id.btn_send:
                hideSoftKeyBoard(this);
                if (TextUtils.isEmpty(etTitle.getText().toString())) {
                    showToast("请输入商品名！");
                } else if (TextUtils.isEmpty(etPrice.getText().toString())) {
                    showToast("请输入商品价格！");
                } else if (TextUtils.isEmpty(backUrl1)) {
                    showToast("请上传商品图片！");
                } else {
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shop_things;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        btnSend = (Button) findViewById(R.id.btn_send);
        etTitle = (EditText) findViewById(R.id.et_title);
        etPrice = (EditText) findViewById(R.id.et_price);
        btnSend = (Button) findViewById(R.id.btn_send);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void initEvent() {
        btnSend.setOnClickListener(this);
        image.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
}
