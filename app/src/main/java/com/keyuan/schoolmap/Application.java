package com.keyuan.schoolmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.keyuan.schoolmap.base.BaseApplication;
import com.keyuan.schoolmap.widget.Open;
import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/12/4.
 */

public class Application extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Open.getInstance().init(this);     // 初始化Open
        initOKGO();
        initNineGridView();
        initShare();
    }

    private void initOKGO() {
        OkGo.getInstance().init(this)                               //必须调用初始化
                //.setOkHttpClient(builder.build())                 //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)                   //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)       //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                                  //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                        //全局公共头
//                .addCommonParams(params);                         //全局公共参数
    }

    /**
     * 初始化九宫格
     */
    private void initNineGridView() {
        NineGridView.setImageLoader(new GlideImageLoader());
    }

    /**
     * Picasso 加载
     */
    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
//            GlideUtils.loadImageViewLoding(context, url, imageView, R.drawable.ic_add_white, R.drawable.ic_add_white);
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_add_white)//
                    .error(R.drawable.ic_add_white)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    /**
     * 初始化友盟分享
     */
    private void initShare() {
        UMShareAPI.get(this);
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        PlatformConfig.setWeixin("wx2cd2e5ddcd8a2883", "7a4813e8d76f5bd24d148ce13d14c33e");
//        PlatformConfig.setQQZone("1106466023", "nJGl1WX8QAEklPWF");//应用宝未上线，暂未生成
        PlatformConfig.setSinaWeibo("998301723", "64a61cd07612c4e4aef07245ea96c9c3", "http://sns.whalecloud.com");
    }
}
