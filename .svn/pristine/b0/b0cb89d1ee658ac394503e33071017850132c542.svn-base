package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.NoScrollWebView;
import com.keyuan.schoolmap.widget.WebViewProgressBar;
import com.keyuan.schoolmap.widget.X5ProgressBarWebView;
import com.keyuan.schoolmap.widget.X5WebView;

/**
 * 开店协议
 */
public class CreateShopWebViewActivity extends BaseActivity {
    private X5WebView mWebView;
    private ProgressBar pb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_shop_web_view;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        findViewById(R.id.btn_agree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.finish(CreateShopWebViewActivity.this);
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @SuppressLint("WrongViewCast")
    @Override
    public void initData() {
        pb = (ProgressBar) findViewById(R.id.pb);
        mWebView = (X5WebView) findViewById(R.id.web_view);
        initWebView();
    }

    private void initWebView() {
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress >= 100) {
                    pb.setVisibility(View.GONE);
                }
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
}
