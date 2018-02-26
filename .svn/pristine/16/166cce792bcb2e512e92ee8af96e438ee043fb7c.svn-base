package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.FragmentAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.fragment.AttendaceStatisticsFragment;
import com.keyuan.schoolmap.fragment.SignInFragment;

import java.util.ArrayList;

/**
 * 考勤
 */
public class CheckingInActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout relSignIn, relAttendanceStatistics;
    private TextView tvSignIn, tvAttendanceStatistics;
    private View viewSignIn, viewAttendanceStatistics;
    private ViewPager viewpager;
    private ArrayList<Fragment> mFragmentList;
    private FragmentAdapter fragmentAdapter;
    private SignInFragment signInFragment;
    private AttendaceStatisticsFragment attendaceStatisticsFragment;
    private int nowPosition = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_sign_in:
                relSignInView();
                if (null != viewpager) {
                    viewpager.setCurrentItem(0);
                }
                break;
            case R.id.rel_attendance_statistics:
                relAttendanceStatisticsView();
                if (null != viewpager) {
                    viewpager.setCurrentItem(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_checking_in;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        relSignIn = (RelativeLayout) findViewById(R.id.rel_sign_in);
        relAttendanceStatistics = (RelativeLayout) findViewById(R.id.rel_attendance_statistics);
        tvSignIn = (TextView) findViewById(R.id.tv_sign_in);
        tvAttendanceStatistics = (TextView) findViewById(R.id.tv_attendance_statistics);
        viewSignIn = findViewById(R.id.view_sign_in);
        viewAttendanceStatistics = findViewById(R.id.view_attendance_statistics);
    }

    @Override
    public void initEvent() {
        relSignIn.setOnClickListener(this);
        relAttendanceStatistics.setOnClickListener(this);
    }

    @Override
    public void initData() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        if (nowPosition == 0) {
            relSignInView();
        } else {
            relAttendanceStatisticsView();
        }
        viewpagerView();
    }

    /**
     * 初始化viewpager
     */
    private void viewpagerView() {
        signInFragment = new SignInFragment();
        attendaceStatisticsFragment = new AttendaceStatisticsFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(signInFragment);
        mFragmentList.add(attendaceStatisticsFragment);
        fragmentAdapter = new FragmentAdapter(CheckingInActivity.this.getSupportFragmentManager(), mFragmentList, null);
        viewpager.setAdapter(fragmentAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    relSignInView();
                } else {
                    relAttendanceStatisticsView();
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    relSignInView();
                } else {
                    relAttendanceStatisticsView();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //考勤统计
    private void relSignInView() {
        tvSignIn.setTextColor(getResources().getColor(R.color.goldenYellow));
        viewSignIn.setBackgroundColor(getResources().getColor(R.color.goldenYellow));
        tvAttendanceStatistics.setTextColor(getResources().getColor(R.color.gray));
        viewAttendanceStatistics.setBackgroundColor(getResources().getColor(R.color.gray));
    }

    //签到
    private void relAttendanceStatisticsView() {
        tvAttendanceStatistics.setTextColor(getResources().getColor(R.color.goldenYellow));
        viewAttendanceStatistics.setBackgroundColor(getResources().getColor(R.color.goldenYellow));
        tvSignIn.setTextColor(getResources().getColor(R.color.gray));
        viewSignIn.setBackgroundColor(getResources().getColor(R.color.gray));
    }
}
