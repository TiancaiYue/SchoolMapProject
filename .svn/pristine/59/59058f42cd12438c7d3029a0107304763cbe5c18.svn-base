package com.keyuan.schoolmap.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.tool.BannerImageLoader;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.tool.RxStatusBarTool;
import com.keyuan.schoolmap.widget.NoScrollWebView;
import com.keyuan.schoolmap.widget.ObserveScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 教室详情
 */
public class ClassRoomDetailsActivity extends BaseActivity implements ObserveScrollView.ScrollListener {
    private RelativeLayout rlHeader;
    private RelativeLayout mTitleBar;
    private TextView tvTitle;
    private ImageView ivLeft;
    private LinearLayout llShadowLeft;
    private TextView tvName;
    private TextView tvPosition;
    private TextView tvTeacher;
    private TextView tvStudent;
    private TextView tvTime;
    private TextView tvLinkName;
    private LinearLayout llPhone;
    private TextView tvPhone;
    private TextView tvArea;
    private LinearLayout llUrl;
    private TextView tvUrl;
    private ObserveScrollView mObserveScrollView;
    private ImageView ivArrowsShadowLeft;
    private Banner mBanner;
    private NoScrollWebView mWebView;
    private View viewStatusBar;
    private int statusBarHeight;

    @Override
    public int getLayoutId() {
        RxStatusBarTool.setStatusBar(this, true);
        return R.layout.activity_class_room_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        llShadowLeft = (LinearLayout) findViewById(R.id.ll_shadow_left);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPosition = (TextView) findViewById(R.id.tv_position);
        tvTeacher = (TextView) findViewById(R.id.tv_teacher);
        tvStudent = (TextView) findViewById(R.id.tv_student);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvLinkName = (TextView) findViewById(R.id.tv_link_name);
        llPhone = (LinearLayout) findViewById(R.id.ll_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvArea = (TextView) findViewById(R.id.tv_area);
        llUrl = (LinearLayout) findViewById(R.id.ll_url);
        tvUrl = (TextView) findViewById(R.id.tv_url);
        rlHeader = (RelativeLayout) findViewById(R.id.rl_header);
        mObserveScrollView = (ObserveScrollView) findViewById(R.id.observe_scroll_view);
        ivArrowsShadowLeft = (ImageView) findViewById(R.id.iv_arrows_shadow_left);
        viewStatusBar = view.findViewById(R.id.view_status_bar);
        viewStatusBar.getBackground().mutate().setAlpha(0);
        mTitleBar = (RelativeLayout) findViewById(R.id.title_bar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("教室详情");
        ivLeft = (ImageView) findViewById(R.id.iv_left);
        initBanner();
        initWebView();
        // 首次进来是影藏TitleBar，状态栏透明
        mTitleBar.setVisibility(View.GONE);
        viewStatusBar.getBackground().mutate().setAlpha(0);
        statusBarHeight = RxStatusBarTool.getStatusBarHeight(this);
        // 动态设置 viewStatusBar 的高度
        ViewGroup.LayoutParams params = viewStatusBar.getLayoutParams();
        params.height = statusBarHeight;
        viewStatusBar.setLayoutParams(params);
    }

    @Override
    public void initEvent() {
        mObserveScrollView.setOnScrollListener(this);
        ivArrowsShadowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxActivityTool.finish(mContext);
            }
        });
        llShadowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.finish(mContext);
            }
        });
        llPhone.setOnClickListener(new View.OnClickListener() {//打电话
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
                    dialogData(tvPhone.getText().toString().trim());
                } else {
                    showToast("该教室负责人还没有添加联系方式！");
                }
            }
        });
        llUrl.setOnClickListener(new View.OnClickListener() {//调网址链接
            @Override
            public void onClick(View v) {
                WebActivity.runActivity(ClassRoomDetailsActivity.this, "教室网址", "https://www.baidu.com/?tn=80035161_1_dg");
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

    private void initWebView() {
        mWebView = (NoScrollWebView) findViewById(R.id.web_view);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://baike.baidu.com/item/%E7%89%9B%E8%85%B1%E5%AD%90/886370?fr=aladdin");
    }

    private boolean firstEnter = true;
    private int bannerHeight;
    private int titleBarHeight;
    private int scrollHeight;

    @Override
    public void onScroll(int l, int t, int oldl, int oldt) {
        if (firstEnter) {
            firstEnter = false;
            viewStatusBar.getBackground().mutate().setAlpha(0);
            mTitleBar.getBackground().mutate().setAlpha(0);
            mTitleBar.setVisibility(View.VISIBLE);
            // 获取Banner的高度
            bannerHeight = mBanner.getHeight();
            // 标题栏高度的
            titleBarHeight = mTitleBar.getHeight();
            // 实际检测滚动区域高度
            scrollHeight = bannerHeight - statusBarHeight;
        }
        // 判断是不是在滚动检测范围之内
        if (t <= scrollHeight) {
            float progress = (float) t / scrollHeight;
            viewStatusBar.getBackground().mutate().setAlpha((int) (progress * 255));
            mTitleBar.getBackground().mutate().setAlpha((int) (progress * 255));
            // 滑动到一半距离时
            if (t < scrollHeight / 2) {
                ivArrowsShadowLeft.setVisibility(View.VISIBLE);
                ivLeft.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
            } else {
                ivArrowsShadowLeft.setVisibility(View.GONE);
                ivLeft.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
            }
        } else {
            viewStatusBar.getBackground().mutate().setAlpha(255);
            mTitleBar.getBackground().mutate().setAlpha(255);
        }
    }

    /**
     * 退出登录弹框
     */
    private void dialogData(String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_school_icon);
        builder.setTitle("提示");
        builder.setMessage("确定拨打电话" + phone + "？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Utils.makingCalls(ClassRoomDetailsActivity.this, tvPhone.getText().toString().trim());
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
