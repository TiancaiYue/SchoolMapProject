package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.Constants;
import com.keyuan.schoolmap.entify.AMBasePlusDto;
import com.keyuan.schoolmap.entify.NewsCallback;
import com.keyuan.schoolmap.tool.RxGlideTool;
import com.keyuan.schoolmap.widget.RxQRCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.IOException;
import java.io.InputStream;

import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

/**
 * 分享
 */
public class ShareAppActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvQrCode;
    private ImageView mIvQrCode;
    private Button mBtnShare;
    private String qrCode = "";
    private String nickName = "";
    private Bitmap qrCodeImage = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_share_app;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mTvQrCode = (TextView) findViewById(R.id.tv_qr_code);
        mIvQrCode = (ImageView) findViewById(R.id.iv_qr_code);
        mBtnShare = (Button) findViewById(R.id.btn_share);
    }

    @Override
    public void initEvent() {
        mBtnShare.setOnClickListener(this);
    }

    @Override
    public void initData() {
        createQRCode();
        initHttpViewData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_share:
                showShare();
                break;
        }
    }

    /**
     * 初始化推荐好友数据
     */
    private void initHttpViewData() {
        OkGo.<AMBasePlusDto<InvitateFriends>>get(Constants.GetMyInvitateInfo)
                .params("token", "MY4F3LH36081378WS8XJG88SE37U08A7")
                .execute(new NewsCallback<AMBasePlusDto<InvitateFriends>>() {
                    @Override
                    public void onStart(Request<AMBasePlusDto<InvitateFriends>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onSuccess(Response<AMBasePlusDto<InvitateFriends>> response) {
                        if (response.body().getCode() == 0) {
                            if (response.body().getData() != null)
                                qrCode = response.body().getData().getRecommend_num();
                        }
                    }

                    @Override
                    public void onError(Response<AMBasePlusDto<InvitateFriends>> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissLoading();
                    }
                });
    }

    /**
     * 生成二维码
     */
    private void createQRCode() {
        //二维码生成方式一  推荐此方法
        qrCodeImage = RxQRCode.builder("http://www.9hxb.com/weixin/pages/registerAndForget.html?type=register&shareCode=" + qrCode).
                backColor(getResources().getColor(R.color.white)).
                codeColor(getResources().getColor(R.color.black)).
                codeSide(600).
                into(mIvQrCode);
    }

    /**
     * 分享
     */
    private void showShare() {
        // 缩略图
        UMImage thumb = new UMImage(this, qrCodeImage);
        // 分享链接
        UMWeb web = new UMWeb("http://www.9hxb.com/weixin/pages/registerAndForget.html?type=register&shareCode=" + qrCode);
        web.setTitle("邀请好友");//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(nickName + "邀请您注册会员");//描述

        new ShareAction(this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            showToast("请稍等...");
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            showToast("分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            showToast("分享失败");
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            showToast("取消分享");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    public class InvitateFriends {
        private String recommend_num;
        private String head_pic;
        private String nickname;
        private String phone_num;

        public String getRecommend_num() {
            return recommend_num;
        }

        public void setRecommend_num(String recommend_num) {
            this.recommend_num = recommend_num;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone_num() {
            return phone_num;
        }

        public void setPhone_num(String phone_num) {
            this.phone_num = phone_num;
        }
    }

}
