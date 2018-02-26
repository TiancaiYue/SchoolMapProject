package com.keyuan.schoolmap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.keyuan.schoolmap.activity.CheckingInActivity;
import com.keyuan.schoolmap.activity.DialogTestActivity;
import com.keyuan.schoolmap.activity.LookForThingsActivity;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.fragment.NavFragment;
import com.keyuan.schoolmap.interf.OnTabReselectListener;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.NavigationButton;
import com.keyuan.schoolmap.widget.slidingmenu.SlidingMenu;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {
    private static final int EXIT_APP_DELAY = 1000;
    private NavFragment mNavBar;
    private FragmentManager mFragmentManager;
    private boolean firstEnter = true;
    private SlidingMenu mSlidingMenu;
    private LinearLayout llMainUi;
    private long lastTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        //初始化滑动菜单
        initSlidingMenu();
        mFragmentManager = getSupportFragmentManager();
        mNavBar = ((NavFragment) mFragmentManager.findFragmentById(R.id.fag_nav));
        llMainUi = (LinearLayout) findViewById(R.id.ll_main_ui);
    }

    /**
     * 初始化滑动菜单
     */
    private void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        mSlidingMenu.setShadowDrawable(R.drawable.shadow);
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        // 设置滑动菜单的视图界面
        mSlidingMenu.setMenu(R.layout.fragment_slidingmenu);
        //饭卡充值
        (findViewById(R.id.tv_add_money)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "饭卡充值暂未开放！", Toast.LENGTH_SHORT).show();
            }
        });
        //签到考勤
        (findViewById(R.id.btn_one)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CheckingInActivity.class));
            }
        });
        //失物招领
        (findViewById(R.id.btn_two)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LookForThingsActivity.class));
            }
        });
        //我的课表
        (findViewById(R.id.btn_three)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(mContext, DialogTestActivity.class);
                Toast.makeText(mContext, "我的课表暂未开放！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firstEnter) {
            firstEnter = false;
            mNavBar.setup(this, mFragmentManager, R.id.main_container, this);   // 考虑到Fragment onActivityCreated 生命周期原因
        }
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
    }

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {
        //点击返回键关闭滑动菜单
        if (mSlidingMenu.isMenuShowing()) {
            mSlidingMenu.showContent();
        }
        if ((System.currentTimeMillis() - lastTime) > EXIT_APP_DELAY) {
            Snackbar.make(llMainUi, getString(R.string.press_twice_exit), Snackbar.LENGTH_SHORT)
                    .setAction(R.string.exit_directly, v -> {
                        MainActivity.super.onBackPressed();
                    })
                    .setActionTextColor(Color.parseColor("#00BCD4"))
                    .show();
            lastTime = System.currentTimeMillis();
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null
                && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            listener.onTabReselect();
            showToast(fragment.getClass().getSimpleName());
        }
    }
}
