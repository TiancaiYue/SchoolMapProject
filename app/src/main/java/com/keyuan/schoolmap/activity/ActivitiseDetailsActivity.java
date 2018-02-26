package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.BannerImageLoader;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.PileLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动详情
 */
public class ActivitiseDetailsActivity extends BaseActivity implements View.OnClickListener {
    private Banner mBanner;
    private TextView tvTitle;
    private TextView tvMoney;
    private LinearLayout llThump;
    private TextView tvThump;
    private LinearLayout llShare;
    private TextView tvShare;
    private TextView tvTime;
    private TextView tvPosition;
    private TextView tvDetails;
    private RelativeLayout relState;
    private Button btnSure;
    private PileLayout pileLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_activitise_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mBanner = (Banner) findViewById(R.id.banner);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        llThump = (LinearLayout) findViewById(R.id.ll_thump);
        tvThump = (TextView) findViewById(R.id.tv_thump);
        llShare = (LinearLayout) findViewById(R.id.ll_share);
        tvShare = (TextView) findViewById(R.id.tv_share);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvPosition = (TextView) findViewById(R.id.tv_position);
        tvDetails = (TextView) findViewById(R.id.tv_details);
        relState = (RelativeLayout) findViewById(R.id.rel_state);
        btnSure = (Button) findViewById(R.id.btn_sure);
        pileLayout = (PileLayout) findViewById(R.id.pile_layout);
        initBanner(view);
    }

    @Override
    public void initEvent() {
        llShare.setOnClickListener(this);
        llThump.setOnClickListener(this);
        btnSure.setOnClickListener(this);
    }

    @Override
    public void initData() {
        String[] urls = {"http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"
        };
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < urls.length; i++) {
            View view = inflater.inflate(R.layout.item_praise, null);
            PorterShapeImageView imageView = (PorterShapeImageView) view.findViewById(R.id.iv_head_image);
            PorterShapeImageView imageView1 = (PorterShapeImageView) view.findViewById(R.id.iv_head_image1);
            TextView textView = (TextView) view.findViewById(R.id.tv_count);
            if (i == urls.length - 1) {
                Glide.with(this).load(urls[i]).into(imageView);
                pileLayout.addView(view);
                textView.setText("+\n" + 6);
                textView.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
                imageView1.setAlpha(0.6f);
            } else {
                Glide.with(this).load(urls[i]).into(imageView);
                textView.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
                pileLayout.addView(view);
            }
        }
    }

    /**
     * 初始化banner
     */
    private void initBanner(View view) {
        List<Object> images = new ArrayList<>();
        images.add("http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg");
        images.add("http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg");
        mBanner = (Banner) view.findViewById(R.id.banner);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_thump:
                break;
            case R.id.ll_share:
                break;
            case R.id.btn_sure:
                RxActivityTool.skipActivity(ActivitiseDetailsActivity.this, ApplyOnlineActivity.class);
                break;
        }
    }
}
