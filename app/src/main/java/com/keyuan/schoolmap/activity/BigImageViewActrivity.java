package com.keyuan.schoolmap.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.bigimage.PhotoView;
import com.keyuan.schoolmap.tool.RxGlideTool;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2017/10/26.
 */

public class BigImageViewActrivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mPager;

    @Override
    public int getLayoutId() {
        return R.layout.layout_big_image;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    public void initEvent() {
    }

    @Override
    public void initData() {
        String imageUrl = getIntent().getStringExtra("imageUrl");
        if (!TextUtils.isEmpty(imageUrl)) {
            final List<String> imageList = Arrays.asList(imageUrl.split(","));
            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
            mPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return imageList.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    PhotoView view = new PhotoView(BigImageViewActrivity.this);
                    view.enable();
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    String imageUrl = imageList.get(position);
                    RxGlideTool.loadImageViewLoding(BigImageViewActrivity.this, imageUrl, view, R.drawable.ic_default_image, R.drawable.ic_default_image);//报道图片未返回
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
