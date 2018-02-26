package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.ActivitiesApplyAdapter;
import com.keyuan.schoolmap.adapter.GridImageAdapter;
import com.keyuan.schoolmap.adapter.SignedSupplementAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.DateUtils;
import com.keyuan.schoolmap.entify.Values;
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
 * 申请活动
 */
public class ActivtiesApplyActivity extends BaseActivity implements View.OnClickListener {
    private EditText etTitle;
    private EditText etMoney;
    private RelativeLayout relStartTime;
    private TextView tvStartTime;
    private RelativeLayout relEndTime;
    private TextView tvEndTime;
    private RelativeLayout relChoosePerson;
    private TextView tvChoosePerson;
    private RelativeLayout relPersonCount;
    private EditText etPersonCount;
    private RelativeLayout relAround;
    private TextView tvAround;
    private EditText etEnterTheReason;
    private Button btnSend;
    private RecyclerView recyclerView;
    //日期选择弹窗
    private TimePickerView pvCustomTime;
    private String startTime;
    private String endTime;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_start_time://开始时间
                hideSoftKeyBoard(this);
                initCustomTimePicker(0);
                pvCustomTime.show();
                break;
            case R.id.rel_end_time://结束时间
                if (!TextUtils.isEmpty(tvStartTime.getText().toString().trim())) {
                    hideSoftKeyBoard(this);
                    initCustomTimePicker(1);
                    pvCustomTime.show();
                } else {
                    Toast.makeText(this, "先选择开始日期！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rel_choose_person://选择审核人
                hideSoftKeyBoard(this);
                Intent intent1 = new Intent(ActivtiesApplyActivity.this, SearchReviewerActivity.class);
                intent1.putExtra("whichActivity", "AskForLeaveActivity");
                startActivityForResult(intent1, 0x101);
                break;
            case R.id.rel_around://可参与人院系
                initPopWindow();
                break;
            case R.id.btn_send:
                hideSoftKeyBoard(this);
                String currentTime = DateUtils.getStrCurrentSystemTime();
                RxLogTool.v("时间差：" + DateUtils.getTimeByDateDifferenceValues(startTime, endTime));
                if (TextUtils.isEmpty(etTitle.getText().toString())) {
                    showToast("请输入活动主题！");
                } else if (TextUtils.isEmpty(etMoney.getText().toString())) {
                    showToast("请输入预估经费！");
                } else if (TextUtils.isEmpty(tvStartTime.getText().toString())) {
                    showToast("请选择开始时间！");
                } else if (Integer.parseInt(DateUtils.getDistanceTimeByDateDifferenceValues(currentTime, tvStartTime.getText().toString().trim() + " 00:00:00", "yyyy-MM-dd HH:mm:ss", true)) > 0) {
                    showToast("开始日期不能选过去时间");
                } else if (TextUtils.isEmpty(tvEndTime.getText().toString())) {
                    showToast("请选择结束时间！");
                } else if (Integer.parseInt(DateUtils.getDistanceTimeByDateDifferenceValues(tvStartTime.getText().toString().trim() + " 00:00:00", tvEndTime.getText().toString().trim() + " 00:00:00", "yyyy-MM-dd HH:mm:ss", true)) > 0) {
                    Toast.makeText(this, "请检查结束开始日期！", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(DateUtils.getDistanceTimeByDateDifferenceValues(etPersonCount.getText().toString().trim() + " 00:00:00", tvEndTime.getText().toString().trim() + " 00:00:00", "yyyy-MM-dd HH:mm:ss", true)) > 0) {
                    Toast.makeText(this, "请输入可参与人数！", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(DateUtils.getDistanceTimeByDateDifferenceValues(tvStartTime.getText().toString().trim() + " 00:00:00", tvEndTime.getText().toString().trim() + " 00:00:00", "yyyy-MM-dd HH:mm:ss", true)) > 0) {
                    Toast.makeText(this, "请选择可参与院系！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etEnterTheReason.getText().toString())) {
                    showToast("请输入活动事由！");
                } else if (TextUtils.isEmpty(tvChoosePerson.getText().toString())) {
                    showToast("请选择审核人！");
                } else {
//                    uploadImages();
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_activties_apply;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        relStartTime = (RelativeLayout) findViewById(R.id.rel_start_time);
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
        relEndTime = (RelativeLayout) findViewById(R.id.rel_end_time);
        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
        relPersonCount = (RelativeLayout) findViewById(R.id.rel_person_count);
        etPersonCount = (EditText) findViewById(R.id.et_person_count);
        relAround = (RelativeLayout) findViewById(R.id.rel_around);
        tvAround = (TextView) findViewById(R.id.tv_around);
        relChoosePerson = (RelativeLayout) findViewById(R.id.rel_choose_person);
        tvChoosePerson = (TextView) findViewById(R.id.tv_choose_person);
        btnSend = (Button) findViewById(R.id.btn_send);
        etMoney = (EditText) findViewById(R.id.et_money);
        etTitle = (EditText) findViewById(R.id.et_title);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        etEnterTheReason = (EditText) findViewById(R.id.et_enter_the_reason);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ActivtiesApplyActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(ActivtiesApplyActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {
        relStartTime.setOnClickListener(this);
        relEndTime.setOnClickListener(this);
        relChoosePerson.setOnClickListener(this);
        relAround.setOnClickListener(this);
        relPersonCount.setOnClickListener(this);
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
                            PictureSelector.create(ActivtiesApplyActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ActivtiesApplyActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ActivtiesApplyActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 初始化popwindow
     */
    private void initPopWindow() {
        View popView = LayoutInflater.from(ActivtiesApplyActivity.this).inflate(R.layout.pop_choose_class, null);
        PopupWindow popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT,
                800);
        popupWindow.setAnimationStyle(android.R.style.DeviceDefault_ButtonBar_AlertDialog);
        popupWindow.setOutsideTouchable(true);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        params.alpha = 0.5f;
        View parent = LayoutInflater.from(ActivtiesApplyActivity.this).inflate(R.layout.activity_activties_apply, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        RecyclerView recyclerView = (RecyclerView) popView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ActivitiesApplyAdapter mAdapter = new ActivitiesApplyAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mAdapter.getData().get(position).isChoose()) {
                    mAdapter.getData().get(position).setChoose(false);
                } else {
                    mAdapter.getData().get(position).setChoose(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        (popView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        (popView.findViewById(R.id.tv_finish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = 0;
                if (mAdapter != null) {
                    for (int i = 0; i < mAdapter.getData().size(); i++) {
                        if (mAdapter.getData().get(i).isChoose()) {
                            n++;
                        }
                    }
                }
                tvAround.setText(n + "");
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 日期选择器1900-2100年
     */
    private void initCustomTimePicker(final int index) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (index == 0) {
                    startTime = DateUtils.getTimeString("yyyy-MM-dd HH:mm", date);
                    tvStartTime.setText(DateUtils.getTimeString("yyyy-MM-dd HH:mm", date));
                } else {
                    endTime = DateUtils.getTimeString("yyyy-MM-dd HH:mm", date);
                    tvEndTime.setText(DateUtils.getTimeString("yyyy-MM-dd HH:mm", date));
                }
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = true;
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(ActivtiesApplyActivity.this)
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
                        //.freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
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
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(ActivtiesApplyActivity.this)
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
                        //.compressMode(compressMode)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
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