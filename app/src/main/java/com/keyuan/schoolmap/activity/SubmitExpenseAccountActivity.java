package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.SubmitExpenseAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.ApplyForReimbursement;
import com.keyuan.schoolmap.entify.OptionsPicker;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 报销
 */
public class SubmitExpenseAccountActivity extends BaseActivity implements View.OnClickListener, SubmitExpenseAdapter.EditorAction {
    private TextView tvChooseData, tvReviewer, tvPictureNum;
    private EditText etCostSubject;
    private LinearLayout addNewItem;
    private LinearLayout llChooseData;
    private RelativeLayout relChooseApprover;
    private TextView tvTotalPrice;
    private Button btnSubmit;
    private RecyclerView mRecyclerView;
    private SubmitExpenseAdapter mAdapter;
    //日期选择弹窗
    private TimePickerView pvCustomTime;
    //添加审核人
    private int REQUEST_OK2 = 0x102;
    // 图片选择
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_expense_account;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tvChooseData = (TextView) findViewById(R.id.tv_choose_data);
        tvReviewer = (TextView) findViewById(R.id.tv_reviewer);
        tvPictureNum = (TextView) findViewById(R.id.tv_picture_num);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        etCostSubject = (EditText) findViewById(R.id.et_cost_subject);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        llChooseData = (LinearLayout) findViewById(R.id.ll_choose_data);
        addNewItem = (LinearLayout) findViewById(R.id.ll_add_new_item);
        relChooseApprover = (RelativeLayout) findViewById(R.id.rel_choose_approver);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setScrollEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SubmitExpenseAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addItem(new ApplyForReimbursement("", 0, ""));
        mAdapter.setOnEditorActionListener(this);
    }

    @Override
    public void initEvent() {
        addNewItem.setOnClickListener(this);
        llChooseData.setOnClickListener(this);
        relChooseApprover.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        findViewById(R.id.ll_picture_selector).setOnClickListener(this);
    }

    @Override
    public void initData() {
        List<OptionsPicker> expenseAccountType = new ArrayList<>();
        expenseAccountType.add(new OptionsPicker(0, "活动"));
        expenseAccountType.add(new OptionsPicker(1, "资料"));
        expenseAccountType.add(new OptionsPicker(2, "交通"));
        expenseAccountType.add(new OptionsPicker(3, "文具"));
        expenseAccountType.add(new OptionsPicker(4, "聚会"));
        expenseAccountType.add(new OptionsPicker(5, "比赛"));
        mAdapter.setExpenseAccountType(expenseAccountType);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OK2 && resultCode == RESULT_OK) {
            String reviewerName = data.getStringExtra("user_name");
            if (!reviewerName.isEmpty()) {
                tvReviewer.setText(reviewerName);
            }
        } else if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    tvPictureNum.setText(selectList.size() + "");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_new_item:
                hideSoftKeyBoard(this);
                List<ApplyForReimbursement> list = new ArrayList<>();
                list.add(new ApplyForReimbursement("", 0, ""));
                mAdapter.addItems(list);
                break;
            case R.id.ll_choose_data:
                hideSoftKeyBoard(this);
                initCustomTimePicker();
                pvCustomTime.show();
                break;
            case R.id.rel_choose_approver:
                hideSoftKeyBoard(this);
                Intent intent1 = new Intent(SubmitExpenseAccountActivity.this, SearchReviewerActivity.class);
                startActivityForResult(intent1, REQUEST_OK2);
                break;
            case R.id.ll_picture_selector:
                hideSoftKeyBoard(this);
                pictureSelector();
                break;
            case R.id.btn_submit:
                hideSoftKeyBoard(this);
                checkInfo();
                break;
            default:
                break;
        }
    }

    private void checkInfo() {
        if (mAdapter.getDatas().size() == 0) {
            showToast("请添加报销明细");
        } else if (TextUtils.isEmpty(etCostSubject.getText().toString())) {
            showToast("请输入费用主题");
        } else if (TextUtils.isEmpty(tvChooseData.getText().toString())) {
            showToast("请选择费用日期");
        } else if (TextUtils.isEmpty(tvReviewer.getText().toString())) {
            showToast("请选择审批人");
        } else {
        }
    }

    /**
     * 图片选择
     */

    private void pictureSelector() {
        PictureSelector.create(SubmitExpenseAccountActivity.this)
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
//                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
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
    }

    /**
     * 上传图片
     */
//    private void uploadImages() {
//        User userData = UserUtils.getCacheUserInfo(this);
//        String token = userData.getToken();
//        int userId = userData.getUserId();
//        int accountId = userData.getAccountId();
//        ArrayList<File> files = new ArrayList<>();
//        for (LocalMedia image : selectList) {
//            files.add(new File(image.getPath()));
//        }
//        OkGo.<String>post(Constants.MAIN_ENGINE)//
//                .tag(this)//
//                .params("mn", "UploadFile")
//                .params("userId", userId)
//                .params("Token", token)
//                .params("AccountId", accountId)
//                .addFileParams("files", files)
//                .converter(new StringConvert())//
//                .adapt(new ObservableResponse<String>())//
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        showLoading();
//                    }
//                })//
//                .observeOn(AndroidSchedulers.mainThread())//
//                .subscribe(new Observer<Response<String>>() {
//
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        //addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Response<String> response) {
//                        LogUtils.d(response.body());
//                        Gson gson = new Gson();
//                        AMBasePlusDto<List<String>> aMBaseDto = gson.fromJson(response.body(), AMBasePlusDto.class);
//                        if (aMBaseDto != null) {
//                            if (aMBaseDto.isResult()) {
//                                StringBuilder sbUrl = new StringBuilder();
//                                List<String> list = aMBaseDto.getData();
//                                if (list != null && list.size() > 0) {
//                                    for (int i = 0; i < list.size(); i++) {
////                                        String url = Constants.IMAGE_HEADER + "/CRM/CRM_UploadFiles/" + list.get(i) + ",";
//                                        String url = list.get(i) + ",";
//                                        sbUrl.append(url);
//                                    }
//                                }
//                                // 去掉最后一个逗号
//                                String urls = "";
//                                if (sbUrl.length() > 0) {
//                                    urls = sbUrl.toString().substring(0, sbUrl.length() - 1);
//                                }
//                                httpAddNewAccount(urls);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                        showToast("网络错误，请稍后再试");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        dismissLoading();
//                    }
//                });
//    }

    /**
     * 日期选择器1900-2100年
     */
    private void initCustomTimePicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {// /选中事件回调
                tvChooseData.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
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

    /**
     * 时间格式转换
     */
    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void onEditorAction() {
        if (mAdapter != null) {
            //listData.clear();
            List<ApplyForReimbursement> listData = mAdapter.getDatas();
            BigDecimal bgAmount = BigDecimal.ZERO;
            //String amount = "";
            for (int i = 0; i < listData.size(); i++) {
                BigDecimal bdPrivce = BigDecimal.ZERO;
                String privce = listData.get(i).getPrice();
                if (!privce.equals("")) {
                    bdPrivce = new BigDecimal(privce);
                }
                bgAmount = bgAmount.add(bdPrivce);
            }
            tvTotalPrice.setText(bgAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
    }
}
