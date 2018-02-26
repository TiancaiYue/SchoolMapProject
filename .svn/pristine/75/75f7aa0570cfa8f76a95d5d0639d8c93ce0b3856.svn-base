package com.keyuan.schoolmap.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.AddActionDataActivity;
import com.keyuan.schoolmap.activity.CommentActivity;
import com.keyuan.schoolmap.activity.MineUpDataMessageActivity;
import com.keyuan.schoolmap.adapter.HotMessageAdapter;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;

/**
 * 校圈信息
 * Created by zmy on 2017/12/4.
 */

public class MessageFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {
    private RecyclerView recyclerView;
    private HotMessageAdapter hotMessageAdapter;//热门校圈
    private LinearLayout llAddNew;
    private LinearLayout llMineMessage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        llAddNew = (LinearLayout) view.findViewById(R.id.ll_add_new);
        llMineMessage = (LinearLayout) view.findViewById(R.id.ll_mine_message);
    }

    @Override
    public void initEvent() {
        llAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), AddActionDataActivity.class);
            }
        });
        llMineMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), MineUpDataMessageActivity.class);
            }
        });
    }

    @Override
    public void initData() {
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(getContext());
        mLayoutManager.setScrollEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        hotMessageAdapter = new HotMessageAdapter();
        recyclerView.setAdapter(hotMessageAdapter);
        hotMessageAdapter.setOnItemChildClickListener(this);
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
        hotMessageAdapter.addData(new Values());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.ll_comments:
                RxActivityTool.skipActivity(getActivity(), CommentActivity.class);
                break;
            case R.id.ll_thump:
                if (hotMessageAdapter.getData().get(position).isChoose()){
                    hotMessageAdapter.getData().get(position).setChoose(false);
                }else {
                    hotMessageAdapter.getData().get(position).setChoose(true);
                }
                hotMessageAdapter.notifyDataSetChanged();
                break;
        }
    }
}
