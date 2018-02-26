package com.keyuan.schoolmap.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.ShopDetailsAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.DateUtils;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomGridLayoutManager;

import java.util.Calendar;
import java.util.Date;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;
import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 商铺详情
 */
public class ShopDetailsActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ShopDetailsAdapter shopDetailsAdapter;
    private LinearLayout llCheckThumb;
    private TextView tvCheckThumb;
    //头部布局
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvWeChat;
    private TextView tvQQ;
    private TextView tvPostion;
    private ImageView ivPhone;
    private LinearLayout llStartTime;
    private LinearLayout llEndTime;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvSure;
    //日期选择弹窗
    private TimePickerView pvCustomTime;
    private String startTime;
    private String endTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        llCheckThumb = (LinearLayout) view.findViewById(R.id.ll_check_thumb);
        tvCheckThumb = (TextView) view.findViewById(R.id.tv_check_thumb);
        if (getIntent().getBooleanExtra("is_mine", false)) {
            llCheckThumb.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_title)).setText("我的店铺");
            ((TextView) findViewById(R.id.tv_right)).setText("添加");
            findViewById(R.id.tv_time).setVisibility(View.GONE);
            findViewById(R.id.ll_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxActivityTool.skipActivity(ShopDetailsActivity.this, AddShopThingActivity.class);
                }
            });
        }
    }

    @Override
    public void initEvent() {
        llCheckThumb.setOnClickListener(new View.OnClickListener() {//点赞
            @Override
            public void onClick(View v) {

            }
        });
        ivPhone.setOnClickListener(new View.OnClickListener() {//打电话
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tvPhone.getText().toString())) {
                    dialogData(tvPhone.getText().toString());
                } else {
                    showToast("该店主还没有添加联系方式！");
                }
            }
        });
        llStartTime.setOnClickListener(new View.OnClickListener() {//开始时间
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard(ShopDetailsActivity.this);
                initCustomTimePicker(0);
                pvCustomTime.show();
            }
        });
        llEndTime.setOnClickListener(new View.OnClickListener() {//结束时间
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard(ShopDetailsActivity.this);
                initCustomTimePicker(1);
                pvCustomTime.show();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {//确定
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvStartTime.getText().toString())) {
                    showToast("请选择开始时间");
                } else if (TextUtils.isEmpty(tvEndTime.getText().toString())) {
                    showToast("请选择结束时间");
                } else {

                }
            }
        });
    }

    @Override
    public void initData() {
        CustomGridLayoutManager mLayoutManager = new CustomGridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        shopDetailsAdapter = new ShopDetailsAdapter();
        View header = LayoutInflater.from(this).inflate(R.layout.head_shop_details, (ViewGroup) findViewById(android.R.id.content), false);
        shopDetailsAdapter.addHeaderView(header);
        ivPhone = (ImageView) header.findViewById(R.id.iv_phone);
        tvName = (TextView) header.findViewById(R.id.tv_name);
        tvPhone = (TextView) header.findViewById(R.id.tv_phone);
        tvWeChat = (TextView) header.findViewById(R.id.tv_weChat);
        tvQQ = (TextView) header.findViewById(R.id.tv_QQ);
        tvPostion = (TextView) header.findViewById(R.id.tv_position);
        llStartTime = (LinearLayout) header.findViewById(R.id.ll_start_time);
        llEndTime = (LinearLayout) header.findViewById(R.id.ll_end_time);
        tvStartTime = (TextView) header.findViewById(R.id.tv_start_time);
        tvEndTime = (TextView) header.findViewById(R.id.tv_end_time);
        tvSure = (TextView) header.findViewById(R.id.tv_sure);
        if (getIntent().getBooleanExtra("is_mine", false)) {
            (header.findViewById(R.id.ll_work_time)).setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(shopDetailsAdapter);
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
        shopDetailsAdapter.addData(new Values());
    }

    /**
     * 退出登录弹框
     */
    private void dialogData(String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_school_icon);
        builder.setTitle("提示");
        builder.setMessage("确定拨打电话" + phone + "？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Utils.makingCalls(ShopDetailsActivity.this, tvPhone.getText().toString());
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
                    startTime = DateUtils.getTimeString("HH:mm", date);
                    tvStartTime.setText(DateUtils.getTimeString("HH:mm", date));
                } else {
                    endTime = DateUtils.getTimeString("HH:mm", date);
                    tvEndTime.setText(DateUtils.getTimeString("HH:mm", date));
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
                .setType(new boolean[]{false, false, false, true, true, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();
    }
}
