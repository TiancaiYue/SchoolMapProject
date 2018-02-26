package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.InformAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.scwang.smartrefresh.layout.api.RefreshLayout;


/**
 * 近期通知
 */
public class InformActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private InformAdapter mAdapter;
    private int pageIndex = 1;
    private static boolean isFirstEnter = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refresh_layout);
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
        initListData();
    }

    private void initListData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InformAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.ll_more) {
            ImageView mIvDetail = (ImageView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.iv_detail);
            TextView mTvBrief = (TextView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.tv_brief);
            if (mIvDetail.isSelected()) {
                mIvDetail.setSelected(false);
                mTvBrief.setMaxLines(1);
            } else {
                mIvDetail.setSelected(true);
                mTvBrief.setMaxLines(1000);
            }
        }
    }
}
