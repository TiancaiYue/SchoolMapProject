package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.HotMessageAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;
import com.keyuan.schoolmap.widget.RxTitleBar;

/**
 * 我的校圈
 */
public class MineUpDataMessageActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, RxTitleBar.TitleBarClickListener {
    private RecyclerView recyclerView;
    private HotMessageAdapter hotMessageAdapter;//热门校圈
    private ImageView llAddNew;
    private RxTitleBar rxTitleBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_up_data_message;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rxTitleBar = (RxTitleBar) view.findViewById(R.id.rx_title);
        rxTitleBar.setRightIcon(R.drawable.ic_add_white);
    }

    @Override
    public void initEvent() {
        rxTitleBar.setTitleBarClickListener(this);
    }

    @Override
    public void initData() {
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(this);
        mLayoutManager.setScrollEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        hotMessageAdapter = new HotMessageAdapter();
        recyclerView.setAdapter(hotMessageAdapter);
        hotMessageAdapter.setOnItemChildClickListener(this);
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.ll_comments:
                RxActivityTool.skipActivity(this, CommentActivity.class);
                break;
            case R.id.ll_thump:
                if (hotMessageAdapter.getData().get(position).isChoose()) {
                    hotMessageAdapter.getData().get(position).setChoose(false);
                } else {
                    hotMessageAdapter.getData().get(position).setChoose(true);
                }
                hotMessageAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onRightClick() {
        RxActivityTool.skipActivity(this, AddActionDataActivity.class);
    }
}
