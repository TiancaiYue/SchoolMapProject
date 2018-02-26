package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.LookForThingAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.bigimage.Info;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.tool.RxTabLayoutTool;
import com.keyuan.schoolmap.widget.RxEmptyLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 失物寻找
 */
public class LookForThingsActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LookForThingAdapter mAdapter;
    private RxEmptyLayout mEmptyLayout;
    private int positon = 0;//当前位置

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x101 && resultCode == RESULT_OK) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_look_for_things;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("失物寻找"));
        mTabLayout.addTab(mTabLayout.newTab().setText("拾物寻主"));
        RxTabLayoutTool.setIndicator(mContext, mTabLayout, 20, 20);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                positon = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        findViewById(R.id.ll_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positon == 1) {
                    startActivity(new Intent(LookForThingsActivity.this, AddLookForThingsDetailsActivity.class).putExtra("isLook", false));
                } else {
                    startActivity(new Intent(LookForThingsActivity.this, AddLookForThingsDetailsActivity.class).putExtra("isLook", true));
                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initData() {
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LookForThingAdapter();
        mAdapter.setPreLoadNumber(2);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(LookForThingsActivity.this, LookForThingsDetailsActivity.class);
                startActivityForResult(intent, 0x101);
            }
        });
        mEmptyLayout = (RxEmptyLayout) findViewById(R.id.empty_layout);
        mEmptyLayout.setOnEmptyLayoutClickListener(new RxEmptyLayout.OnEmptyLayoutClickListener() {
            @Override
            public void onEmptyLayoutClick(int status) {
                if (status != RxEmptyLayout.NETWORK_LOADING) {
                    getData();
                }
            }
        });
        List<Values> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(new Values());
        }
        mAdapter.addData(mList);
    }

    private void getData() {
        String url = "https://www.baidu.com";
        OkGo.<String>get(url).tag(mContext).execute(new StringCallback() {

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                RxLogTool.e("onStart");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_LOADING);
            }

            @Override
            public void onSuccess(Response<String> response) {
                RxLogTool.e("onSuccess");
                //mEmptyLayout.hide();
                mEmptyLayout.setEmptyDataContent("亲，没有更多数据了");
                mEmptyLayout.show(RxEmptyLayout.EMPTY_DATA);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                RxLogTool.e("onError");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_ERROR);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                RxLogTool.e("onFinish");
            }
        });
    }
}
