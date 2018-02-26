package com.keyuan.schoolmap.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.ApplyActivity;
import com.keyuan.schoolmap.activity.HotActivitiesActivity;
import com.keyuan.schoolmap.activity.HotShopActivity;
import com.keyuan.schoolmap.activity.InformActivity;
import com.keyuan.schoolmap.activity.LookForThingsActivity;
import com.keyuan.schoolmap.activity.ShareAppActivity;
import com.keyuan.schoolmap.activity.ShopDetailsActivity;
import com.keyuan.schoolmap.activity.WebActivity;
import com.keyuan.schoolmap.adapter.HomeCategoryAdapter;
import com.keyuan.schoolmap.adapter.HotShopAdapter;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.entify.HomeCategory;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.interf.OnTabReselectListener;
import com.keyuan.schoolmap.tool.BannerImageLoader;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomGridLayoutManager;
import com.keyuan.schoolmap.widget.RxTitleBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmy on 2017/12/04.
 */

public class HomeFragment extends BaseFragment implements OnTabReselectListener, RxTitleBar.TitleBarClickListener {
    private Banner mBanner;
    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerHotShop;
    private LinearLayout llHotActivity;
    private LinearLayout llHotShop;
    private RxTitleBar rxTitleBar;
    private HotShopAdapter hotShopAdapter;//热门商铺
    private View view;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recycler_view_category);
        recyclerHotShop = (RecyclerView) view.findViewById(R.id.recycler_hot_shop);
        llHotActivity = (LinearLayout) view.findViewById(R.id.ll_hot_activity);
        llHotShop = (LinearLayout) view.findViewById(R.id.ll_hot_shop);
        rxTitleBar = (RxTitleBar) view.findViewById(R.id.rx_title);
        rxTitleBar.setRightIcon(R.drawable.ic_add_white);
        (view.findViewById(R.id.ll_left)).setVisibility(View.INVISIBLE);
        (view.findViewById(R.id.ll_left)).setEnabled(false);
    }

    @Override
    public void initEvent() {
        rxTitleBar.setTitleBarClickListener(this);
        llHotActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), HotActivitiesActivity.class);
            }
        });
        llHotShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), HotShopActivity.class);
            }
        });
    }

    @Override
    public void initData() {
        initCategory();
        initHotShop();
        initBanner();
    }

    @Override
    public void onRightClick() {
        RxActivityTool.skipActivity(getActivity(), ApplyActivity.class);
    }

    @Override
    public void onTabReselect() {

    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        List<Object> images = new ArrayList<>();
        images.add("http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg");
        images.add("http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg");
//        //设置banner样式
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
//        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                RxActivityTool.skipActivity(getActivity(), ShareAppActivity.class);
            }
        });
        mBanner.start();
    }

    /**
     * 分类
     */
    private void initCategory() {
        List<HomeCategory> homeCategoryList = new ArrayList<>();
        homeCategoryList.add(new HomeCategory(R.drawable.ic_chat_list, "聊天列表"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_hot_shop, "热门商铺"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_hot_message, "热门活动"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_hot_class, "热门课程"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_expressage, "菜鸟驿站"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_look_for, "事物中心"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_pick_up, "失物招领"));
        homeCategoryList.add(new HomeCategory(R.drawable.ic_inform, "近期通知"));

        CustomGridLayoutManager mLayoutManager = new CustomGridLayoutManager(getContext(), 4);
        mLayoutManager.setScrollEnabled(false);
        recyclerViewCategory.setLayoutManager(mLayoutManager);
        HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter();
        recyclerViewCategory.setAdapter(homeCategoryAdapter);
        homeCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0://聊天列表
                        showToast("敬请期待！");
                        break;
                    case 1://热门商铺
                        RxActivityTool.skipActivity(getActivity(), HotShopActivity.class);
                        break;
                    case 2://热门活动
                        RxActivityTool.skipActivity(getActivity(), HotActivitiesActivity.class);
                        break;
                    case 3://热门课程
                        showToast("敬请期待！");
                        break;
                    case 4://菜鸟驿站
                        WebActivity.runActivity(getActivity(), "菜鸟驿站", "https://www.baidu.com/?tn=80035161_1_dg");
                        break;
                    case 5://事物中心
                        WebActivity.runActivity(getActivity(), "事物中心", "https://www.baidu.com/?tn=80035161_1_dg");
                        break;
                    case 6://失物招领
                        RxActivityTool.skipActivity(getActivity(), LookForThingsActivity.class);
                        break;
                    case 7://近期通知
                        RxActivityTool.skipActivity(getActivity(), InformActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        homeCategoryAdapter.addData(homeCategoryList);
    }

    /**
     * 热门商铺
     */
    private void initHotShop() {
        CustomGridLayoutManager mLayoutManager = new CustomGridLayoutManager(getContext(), 2);
        mLayoutManager.setScrollEnabled(false);
        recyclerHotShop.setLayoutManager(mLayoutManager);
        hotShopAdapter = new HotShopAdapter();
        recyclerHotShop.setAdapter(hotShopAdapter);
        hotShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxActivityTool.skipActivity(getActivity(), ShopDetailsActivity.class);
            }
        });
        hotShopAdapter.addData(new Values());
        hotShopAdapter.addData(new Values());
        hotShopAdapter.addData(new Values());
        hotShopAdapter.addData(new Values());
    }
}
