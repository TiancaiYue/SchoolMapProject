package com.keyuan.schoolmap.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.SignInRecordActivity;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.common.Constants;
import com.keyuan.schoolmap.common.DateUtils;
import com.keyuan.schoolmap.entify.AMBasePlusDto;
import com.keyuan.schoolmap.entify.NewsCallback;
import com.keyuan.schoolmap.tool.RxGlideTool;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Calendar;
import java.util.Date;

/**
 * 考勤统计
 */
public class AttendaceStatisticsFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private PorterShapeImageView ivImageView;
    private SmartRefreshLayout refreshLayout;
    private TextView tvName;
    private TextView tvTime;
    private ImageView ivTime;
    private RelativeLayout relCalendar;
    private TextView tvAttendanceNum;
    private TextView tvRestDays;
    private TextView tvLate;
    private TextView tvLeaveEarly;
    private TextView tvLackOfCard;
    private TextView tvAbsenteeism;
    private TextView tvField;
    private TextView tvLeave;
    private String nowTime;
    private String dataTime;
    //日期选择弹窗
    private TimePickerView pvCustomTime;
    private static boolean isFirstEnter = true;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_time:
                initCustomTimePicker();
                pvCustomTime.show();
                break;
            case R.id.rel_calendar:
                Intent intent = new Intent(getActivity(), SignInRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_attendace_statistics;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        ivImageView = (PorterShapeImageView) view.findViewById(R.id.iv_imageView);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_layout);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        ivTime = (ImageView) view.findViewById(R.id.iv_time);
        relCalendar = (RelativeLayout) view.findViewById(R.id.rel_calendar);
        tvAttendanceNum = (TextView) view.findViewById(R.id.tv_attendance_num);
        tvRestDays = (TextView) view.findViewById(R.id.tv_rest_days);
        tvLate = (TextView) view.findViewById(R.id.tv_late);
        tvLeaveEarly = (TextView) view.findViewById(R.id.tv_leave_early);
        tvLackOfCard = (TextView) view.findViewById(R.id.tv_lack_of_card);
        tvAbsenteeism = (TextView) view.findViewById(R.id.tv_absenteeism);
        tvField = (TextView) view.findViewById(R.id.tv_field);
        tvLeave = (TextView) view.findViewById(R.id.tv_leave);
    }

    @Override
    public void initEvent() {
        ivTime.setOnClickListener(this);
        relCalendar.setOnClickListener(this);
    }

    @Override
    public void initData() {
        nowTime = DateUtils.getStrCurrentSystemTime();
        dataTime = DateUtils.getStrCurrentSystemTime();
        tvTime.setText(DateUtils.getStringToFormatDate("yyyy年MM月", nowTime));
    }

    /**
     * 考勤签到统计
     */
    private void httpViewData() {

    }

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
        pvCustomTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                dataTime = DateUtils.getTimeString("yyyy-MM-dd HH:mm:ss", date);
                tvTime.setText(DateUtils.getTimeString("yyyy年MM月", date));
                httpViewData();
            }
        })
                .setDividerColor(Color.BLUE)
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
                .setType(new boolean[]{true, true, false, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.GRAY)
                .build();
    }
}
