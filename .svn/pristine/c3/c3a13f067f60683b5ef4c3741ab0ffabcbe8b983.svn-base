package com.keyuan.schoolmap.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.keyuan.schoolmap.service.AppUpdateService;
import com.keyuan.schoolmap.tool.RxToastTool;

/**
 * App更新工具类
 *
 * @author Administrator
 */
public class AppUpdateUtils {
    private static final String FILE_NAME = "towercranehome";
    private static AppUpdateUtils mInstance;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    public AppUpdateUtils() {
    }

    public static AppUpdateUtils getInstance() {
        if (mInstance == null) {
            mInstance = new AppUpdateUtils();
        }
        return mInstance;
    }

    public void update(Context context) {
        this.mContext = context;
//        checkUpdate();
    }

    /**
     * 检测更新
     */
//    private void checkUpdate() {
//        // TODO Auto-generated method stub
//        OkGo.<AMBasePlusDto<Version>>post(Constants.MAIN_ENGINE)
//                .params("mn", "UpdateVersion")
//                //.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
//                .execute(new NewsCallback<AMBasePlusDto<Version>>() {
//                    @Override
//                    public void onStart(Request<AMBasePlusDto<Version>, ? extends Request> request) {
//                        super.onStart(request);
//                    }
//
//                    @Override
//                    public void onSuccess(Response<AMBasePlusDto<Version>> response) {
//                        LogUtils.v("检查版本更新返回数据:" + response.body().getData().getVersionName());
////                        if (response.body().code == 0) {
//                        // 对比版本号
//                        AMBasePlusDto<Version> version = response.body();
//                        if (version == null)
//                            return;
//                        if (!("").equals(version.getData().getVersionName())) { // 防止版本号为空
//                            int systemVersion = Integer.parseInt(Utils.getVersion(mContext).replace(".", ""));
//                            int serviceVersion = Integer.parseInt(version.getData().getVersionName().replace(".", ""));
//                            Log.d("test", "systemVersion：" + systemVersion + "  serviceVersion:" + serviceVersion);
//                            if (serviceVersion > systemVersion) {
//                                startUpdate(version.getData().getUrl(), version.getData().getVersionData());
//                            } else {
//                                noUpdate();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<AMBasePlusDto<Version>> response) {
//                        super.onError(response);
//                        LogUtils.v("检查版本网络出错：" + response.body());
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                    }
//                });
//    }

    private void startUpdate(final String url, String dataString) {
        AlertDialog mDialog = new AlertDialog.Builder(mContext)
//                .setMessage(dataString)
//                .setMessageSize(16)
                //.setNegativeButton("取消", null)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.shichuang.entrepreneurshipplus&fromcase=40003"; // web address
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mContext.startActivity(intent);
//                        startService(url);
                    }
                }).create();
        TextView tv = new TextView(mContext);
        tv.setTextSize(14);//字体大小  
        tv.setTextColor(Color.BLACK);
//        Spannable sp = new SpannableString("检测到新版本，需要更新\n\n更新内容如下：\n" + dataString);
//        sp.setSpan(new AbsoluteSizeSpan(16, true), 0, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setPadding(48, 40, 20, 20);
        tv.setText("检测到新版本，需要更新");
        mDialog.setCustomTitle(tv);//不是setTitle()  
        mDialog.show();
    }

    private void noUpdate() {
        AlertDialog mDialog = new AlertDialog.Builder(mContext)
                .setMessage("当前已是最新版本无需更新")
                //.setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        mDialog.show();
    }

    private void startService(String url) {

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("新版本下载中...");
        //mProgressDialog.setMessage("");
        mProgressDialog.setMax(100);
        mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "后台下载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        mProgressDialog.show();

        // 启动Service 开始下载
        AppUpdateService.startUpdate(mContext, url, FILE_NAME, new AppUpdateService.OnProgressListener() {
            @Override
            public void onProgress(int progress) {
                if (mProgressDialog != null) {
                    mProgressDialog.setProgress(progress);
                }
            }

            @Override
            public void onSuccess(boolean isSuccess) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                // 失败提示
                if (isSuccess) {
                    RxToastTool.showShort("下载成功");
                } else {
                    RxToastTool.showShort( "下载失败");
                }
            }
        });
    }

    public class Version {
        private String Url;
        private String VersionName;
        private String VersionCode;
        private String VersionData;

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public String getVersionName() {
            return VersionName;
        }

        public void setVersionName(String versionName) {
            VersionName = versionName;
        }

        public String getVersionCode() {
            return VersionCode;
        }

        public void setVersionCode(String versionCode) {
            VersionCode = versionCode;
        }

        public String getVersionData() {
            return VersionData;
        }

        public void setVersionData(String versionData) {
            VersionData = versionData;
        }
    }
}
