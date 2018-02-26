package com.keyuan.schoolmap.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.BannerImageLoader;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 失物详情
 */
public class LookForThingsDetailsActivity extends BaseActivity {
    private Banner mBanner;

    @Override
    public int getLayoutId() {
        return R.layout.activity_look_for_things_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        initBanner();
    }

    @Override
    public void initEvent() {
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelData();
            }
        });
    }

    @Override
    public void initData() {

    }

    private void initBanner() {
        List<Object> images = new ArrayList<>();
        images.add("http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg");
        images.add("http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg");
        mBanner = (Banner) findViewById(R.id.banner);
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

            }
        });
        mBanner.start();
    }

    /**
     * 确定找到弹框
     */
    private void cancelData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提醒");
        builder.setMessage("确定找到主人或者物品吗？");
        builder.setIcon(R.drawable.ic_school_icon);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RxActivityTool.finish(LookForThingsDetailsActivity.this);
                setResult(RESULT_OK, null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
