package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.MyApprovalAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.RxTitleBar;

/**
 * 待我审批
 */
public class ForMyApprovalActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    private TabLayout mTabLayout;
    private MyApprovalAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onClick(View v) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_approval;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        if (getIntent().getBooleanExtra("is_mine", false)) {
            ((RxTitleBar) findViewById(R.id.rx_title)).setTitle("待我处理");
        } else {
            ((RxTitleBar) findViewById(R.id.rx_title)).setTitle("我的申请");
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        mTabLayout.addTab(mTabLayout.newTab().setText("已处理"));
        mTabLayout.addTab(mTabLayout.newTab().setText("未处理"));
        //中间画线
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(ForMyApprovalActivity.this, R.drawable.divider_vertical));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyApprovalAdapter();
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (getIntent().getBooleanExtra("is_mine", false)) {
            RxActivityTool.skipActivity(ForMyApprovalActivity.this, ApprovalDetailsActivity.class);
        } else {
            RxActivityTool.skipActivity(ForMyApprovalActivity.this, ApprovalDetailsTwoActivity.class);
        }
    }
}
