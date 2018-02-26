package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.SignInRecordAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.DateUtils;
import com.keyuan.schoolmap.entify.Values;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 拜访记录
 */
public class SignInRecordActivity extends BaseActivity implements View.OnClickListener, OnDateSelectedListener {
    private Calendar calendar;
    private MaterialCalendarView materialCalendarView;
    private TextView tvTitle;
    private TextView tvDataChange;
    private LinearLayout llBack;
    private RecyclerView mRecyclerView;
    private SignInRecordAdapter signInRecordAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View header;
    private String data = "";//所要查询的data
    private int total = 0;
    private int pageindex = 1;
    private String startTime = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_in_record;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(Bundle savedInstanceState, View view) {
        calendar = Calendar.getInstance();
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.material_calendar_view);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        signInRecordAdapter = new SignInRecordAdapter();
        mRecyclerView.setAdapter(signInRecordAdapter);
        header = LayoutInflater.from(this).inflate(R.layout.head_visit_sign_record, (ViewGroup) findViewById(android.R.id.content), false);
        signInRecordAdapter.addHeaderView(header);
        initCalendar();
        initList();
    }

    @Override
    public void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        List<Values> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(new Values());
        }
        signInRecordAdapter.addData(mList);
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 初始化日历
     */
    private void initCalendar() {
        materialCalendarView.setTileHeightDp(28);
        materialCalendarView.setOnDateChangedListener(this);
        Calendar calendar = Calendar.getInstance();
        materialCalendarView.setSelectedDate(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data = format.format(calendar.getTime());
        startTime = data;
    }

    /**
     * 初始化列表
     */
    private void initList() {
        tvDataChange = (TextView) header.findViewById(R.id.tv_data_change);
        tvDataChange.setText(DateUtils.getStringToFormatDate("MM月dd日", DateUtils.getStrCurrentSystemTime()));
        startTime = DateUtils.getStrCurrentSystemTime();
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data = format.format(date.getDate());
        if (date.getMonth() + 1 < 10)
            tvDataChange.setText("0" + (date.getMonth() + 1) + "月" + date.getDay() + "日");
        else
            tvDataChange.setText((date.getMonth() + 1) + "月" + date.getDay() + "日");
        //startTime = data + " " + "00:00:00" + "," + data + " " + "23:59:59";
        startTime = data;
        pageindex = 1;
    }
}
