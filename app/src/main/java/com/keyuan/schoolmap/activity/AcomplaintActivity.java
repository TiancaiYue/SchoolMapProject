package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.GridImageAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.DateUtils;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.widget.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 投诉
 */
public class AcomplaintActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout relLeaveResult;
    private TextView tvType;
    private EditText etEnterTheReason;
    private RelativeLayout relChoosePerson;
    private TextView tvChoosePerson;
    private Button btnSend;
    private RecyclerView recyclerView;
    //审核人
    private String reviewerName = "";
    //选择列表
    private OptionsPickerView chooseView;
    //上传图片
    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    private String urls;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x101 && resultCode == RESULT_OK) {
            reviewerName = data.getStringExtra("user_name");
            if (!reviewerName.isEmpty()) {
                tvChoosePerson.setText(reviewerName);
            }
        } else if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_acomplaint;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        relLeaveResult = (RelativeLayout) findViewById(R.id.rel_leave_result);
        tvType = (TextView) findViewById(R.id.tv_type);
        etEnterTheReason = (EditText) findViewById(R.id.et_enter_the_reason);
        relChoosePerson = (RelativeLayout) findViewById(R.id.rel_choose_person);
        tvChoosePerson = (TextView) findViewById(R.id.tv_choose_person);
        btnSend = (Button) findViewById(R.id.btn_send);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(AcomplaintActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(AcomplaintActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        relChoosePerson.setOnClickListener(this);
        relLeaveResult.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(AcomplaintActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(AcomplaintActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(AcomplaintActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
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
                    Intent intent1 = new Intent(AcomplaintActivity.this, SearchReviewerActivity.class);
                    intent1.putExtra("whichActivity", "AcomplaintActivity");
                    startActivityForResult(intent1, 0x101);
                } else {
                    showToast("请先选择投诉类型！");
                }
                break;
            case R.id.rel_leave_result: //选择类型
                hideSoftKeyBoard(this);
                List<String> stateList = new ArrayList<>();
                stateList.add("物业");
                stateList.add("教学");
                initStateChoose(stateList, tvType);
                chooseView.show();
                break;
            case R.id.btn_send:
                hideSoftKeyBoard(this);
                if (TextUtils.isEmpty(tvType.getText().toString())) {
                    showToast("请选择投诉类型！");
                } else if (TextUtils.isEmpty(etEnterTheReason.getText().toString())) {
                    showToast("请输入投诉事由！");
                } else if (TextUtils.isEmpty(tvChoosePerson.getText().toString())) {
                    showToast("请选择审核人！");
                } else {
//                    uploadImages();
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

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(AcomplaintActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        //.theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(
                                PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        //.previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        //.enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
//                        .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        //.compressMode(compressMode)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        //.withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        //.hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                        //.isGif(cb_isGif.isChecked())// 是否显示gif图片
//                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                        //.circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                        //.showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        // .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        //.openClickSound(cb_voice.isChecked())// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                        //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//                        .rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(AcomplaintActivity.this)
                        .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        //.theme(themeId)// 主题样式设置 具体参考 values/styles
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .selectionMode(
                                PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        //.previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        //.enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
//                        .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
//                        .compressMode(compressMode)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        //.withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        //.hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        //.openClickSound( )// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                        //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                        //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }
    };
}
