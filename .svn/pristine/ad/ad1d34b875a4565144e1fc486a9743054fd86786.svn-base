package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.HotMessageAdapter;
import com.keyuan.schoolmap.adapter.HotShopAdapter;
import com.keyuan.schoolmap.adapter.RecommendShopAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomGridLayoutManager;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;
import com.keyuan.schoolmap.widget.RxTitleBar;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * 商铺
 */
public class HotShopActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecommendShopAdapter recommendShopAdapter;//热门商铺

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_shop;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void initEvent() {
        findViewById(R.id.ll_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(HotShopActivity.this, CreateShopActivity.class);
            }
        });
    }

    @Override
    public void initData() {
        CustomGridLayoutManager mLayoutManager = new CustomGridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recommendShopAdapter = new RecommendShopAdapter();
        recyclerView.setAdapter(recommendShopAdapter);
        recommendShopAdapter.setOnItemClickListener(this);
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
        recommendShopAdapter.addData(new Values());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        RxActivityTool.skipActivity(HotShopActivity.this, ShopDetailsActivity.class);
    }
}
