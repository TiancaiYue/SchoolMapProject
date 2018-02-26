package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.SearchReviewerAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxToastTool;
import com.keyuan.schoolmap.widget.RxTitleBar;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 选择联系人
 * Created by zmy on 2017/1/12.
 */

public class SearchReviewerActivity extends BaseActivity implements View.OnClickListener, RxTitleBar.TitleBarClickListener {
    private RecyclerView mRecyclerView;
    private SearchReviewerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_contact;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        ((RxTitleBar) findViewById(R.id.rx_title)).setTitleBarClickListener(this);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SearchReviewerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    mAdapter.getData().get(i).setChoose(false);
                }
                mAdapter.getData().get(position).setChoose(true);
                mAdapter.notifyDataSetChanged();
            }
        });
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
        mAdapter.addData(new Values());
        mAdapter.addData(new Values());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_details:
                if (mAdapter != null) {

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRightClick() {
        boolean isHavaChoose = false;
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            if (mAdapter.getData().get(i).isChoose()) {
                isHavaChoose = true;
            }
        }
        if (!isHavaChoose) {
            RxToastTool.showShort("至少选择一个审批人！");
        }
    }
}
