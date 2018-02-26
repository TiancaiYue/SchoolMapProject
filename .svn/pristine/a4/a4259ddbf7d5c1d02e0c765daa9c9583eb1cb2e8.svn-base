package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseTakePhotoActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 添加商铺
 */
public class CreateShopActivity extends BaseTakePhotoActivity implements View.OnClickListener {
    private RelativeLayout relLeaveResult;
    private TextView tvType;
    private EditText etName;
    private EditText etPersonNumber;
    private ImageView ivIsPublic;//是否有营业执照
    private LinearLayout llIsPublic;
    private ImageView ivCharter;//营业执照
    private ImageView ivPersonOne;//身份证正面
    private ImageView ivPersonTwo;//身份证反面
    private EditText etEnterTheReason;//店铺介绍
    private RelativeLayout relChoosePerson;
    private TextView tvChoosePerson;
    private Button btnSend;
    //审核人
    private String reviewerName = "";
    //选择列表
    private OptionsPickerView chooseView;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //调用照相机返回图片临时文件
    private File tempFile;
    // 头像Bitmap数据
    private Bitmap avatarBitmap;
    //区别不同上传图片标志
    private int index = 1;

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
                    switch (index) {
                        case 1:
                            httpUpDataUrl(cropImagePath, ivCharter, index);
                            break;
                        case 2:
                            httpUpDataUrl(cropImagePath, ivPersonOne, index);
                            break;
                        case 3:
                            httpUpDataUrl(cropImagePath, ivPersonTwo, index);
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_shop;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        relLeaveResult = (RelativeLayout) findViewById(R.id.rel_leave_result);
        tvType = (TextView) findViewById(R.id.tv_type);
        etName = (EditText) findViewById(R.id.et_name);
        etPersonNumber = (EditText) findViewById(R.id.et_person_number);
        llIsPublic = (LinearLayout) findViewById(R.id.ll_is_public);
        ivIsPublic = (ImageView) findViewById(R.id.iv_is_public);
        ivCharter = (ImageView) findViewById(R.id.iv_charter);
        ivPersonOne = (ImageView) findViewById(R.id.iv_person_one);
        ivPersonTwo = (ImageView) findViewById(R.id.iv_person_two);
        etEnterTheReason = (EditText) findViewById(R.id.et_enter_the_reason);
        relChoosePerson = (RelativeLayout) findViewById(R.id.rel_choose_person);
        tvChoosePerson = (TextView) findViewById(R.id.tv_choose_person);
        btnSend = (Button) findViewById(R.id.btn_send);
    }

    @Override
    public void initEvent() {
        ivCharter.setOnClickListener(this);
        ivPersonOne.setOnClickListener(this);
        ivPersonTwo.setOnClickListener(this);
        ivIsPublic.setOnClickListener(this);
        relChoosePerson.setOnClickListener(this);
        relLeaveResult.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_choose_person:
                if (!TextUtils.isEmpty(tvType.getText().toString())) {
                    hideSoftKeyBoard(this);
                    Intent intent1 = new Intent(CreateShopActivity.this, SearchReviewerActivity.class);
                    intent1.putExtra("whichActivity", "AcomplaintActivity");
                    startActivityForResult(intent1, 0x101);
                } else {
                    showToast("请先选择店铺类型类型！");
                }
                break;
            case R.id.rel_leave_result: //选择类型
                hideSoftKeyBoard(this);
                List<String> stateList = new ArrayList<>();
                stateList.add("二手物品");
                stateList.add("代购");
                stateList.add("周边商铺");
                initStateChoose(stateList, tvType);
                chooseView.show();
                break;
            case R.id.iv_is_public:
                if (ivIsPublic.isSelected()) {
                    ivIsPublic.setSelected(false);
                    llIsPublic.setVisibility(View.GONE);
                } else {
                    ivIsPublic.setSelected(true);
                    llIsPublic.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_charter://营业执照
                index = 1;
                uploadHeadImage();
                break;
            case R.id.iv_person_one://图片正面
                index = 2;
                uploadHeadImage();
                break;
            case R.id.iv_person_two://图片反面
                index = 3;
                uploadHeadImage();
                break;
            case R.id.btn_send:
                hideSoftKeyBoard(this);
                if (TextUtils.isEmpty(tvType.getText().toString())) {
                    showToast("请选择店铺类型！");
                } else if (TextUtils.isEmpty(etEnterTheReason.getText().toString())) {
                    showToast("请输入店铺事由！");
                } else if (TextUtils.isEmpty(tvChoosePerson.getText().toString())) {
                    showToast("请选择审核人！");
                } else {
                    Log.v("图片返回", backUrl1 + "," + backUrl2 + "," + backUrl3);
                }
                break;
        }
    }

    /**
     * 选择列表
     */
    private void initStateChoose(List<String> arraryList, final TextView textView) {
        List<String> nullList = new ArrayList<>();
        chooseView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = arraryList.get(options2).toString();
                textView.setText(tx);
            }
        })
                .setCancelColor(Color.parseColor("#b99042"))
                .setSubmitColor(Color.parseColor("#b99042")).build();
        chooseView.setNPicker(nullList, arraryList, nullList);
        chooseView.show();
    }
}
