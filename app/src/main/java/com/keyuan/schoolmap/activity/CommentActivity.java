package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.CommentAdapter;
import com.keyuan.schoolmap.adapter.HotMessageAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class CommentActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
      mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(getContext());
        mLayoutManager.setScrollEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CommentAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
    }
}
