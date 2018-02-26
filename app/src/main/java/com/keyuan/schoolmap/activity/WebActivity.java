package com.keyuan.schoolmap.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;


public class WebActivity extends BaseActivity implements View.OnClickListener {
    public final static String URL = "url";
    public final static String TITLE = "title";
    private TextView tvTitle;
    private ProgressBar pb;
    private WebView mWebView;
    private String url = "";
    private String title = "";

    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        url = getIntent().getStringExtra(URL);
        title = getIntent().getStringExtra(TITLE);
//        ((TextView) findViewById(R.id.tv_title)).setText(title == null ? "" : title);
        pb = (ProgressBar) findViewById(R.id.pb);
        mWebView = (WebView) findViewById(R.id.web_view);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title == null ? "" : title);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        pb.setMax(100);
        // 设置支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 设置适应屏幕
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        // 设置支持DomStorage
        mWebView.getSettings().setDomStorageEnabled(true);
        // 存储模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);  //不使用缓存
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
        mWebView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack())
                mWebView.goBack();
            else
                finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mWebView != null) {
            Log.d("WebActivity", "onDestroy");
            // 清除所有Cookie
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            CookieSyncManager.getInstance().sync();

            mWebView.setWebChromeClient(null);
            mWebView.setWebViewClient(null);
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.loadUrl("about:blank");
            mWebView.freeMemory();
            //mWebView.pauseTimers();
            mWebView = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

        }
    }
}
