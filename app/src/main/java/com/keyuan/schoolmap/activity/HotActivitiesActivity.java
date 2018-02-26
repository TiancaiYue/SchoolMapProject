package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.HotActivitiesAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 热门活动
 */
public class HotActivitiesActivity extends BaseActivity implements View.OnClickListener {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private HotActivitiesAdapter mAdapter;
    private int pageIndex = 1;
    private int total = 0;
    private static boolean isFirstEnter = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_activities;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
    }

    @Override
    public void initEvent() {
        findViewById(R.id.ll_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(HotActivitiesActivity.this, ActivtiesApplyActivity.class);
            }
        });
    }

    @Override
    public void initData() {
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(getContext());
        mLayoutManager.setScrollEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HotActivitiesAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(HotActivitiesActivity.this, ActivitiseDetailsActivity.class));
            }
        });
    }
}
