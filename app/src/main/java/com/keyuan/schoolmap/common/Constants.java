package com.keyuan.schoolmap.common;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface Constants {
    //地址
    String MAIN_ENGINE = "http://www.9hxb.com/huixiaobao";
    //上传图片
    String UploadFile = MAIN_ENGINE + "/upload/uploadFile";
    //图片头
    String IMAGE_MAIN_ENGINE = "http://www.9hxb.com";
    //首页
    String HomeDetails_Url = MAIN_ENGINE + "/api/v1/basic/getHomePage";
    //获取课程类型
    String ClassType_Url = MAIN_ENGINE + "/api/v1/course/getCourseCategory";
    //获取指定课程列表
    String DesignatedCourseUrl = MAIN_ENGINE + "/api/v1/course/getDesignatedCourse";
    //测试头
    String MAIN_ENGINE1 = "https://cyjcloud.com/contract/MobService.ashx";
    // 支付宝配置信息
//    String ALIPAY_APP_ID = "2017092608938336";
    String Pay_Person = "3057152585@qq.com";//ok
    String ALIPAY_APP_ID = "2088821296273954";//ok
    String Back_URL = "http://www.9hxb.com/huixiaobao/api/payment/alipaypc/notify_url";
    String ALIPAY_RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALi0X7IBdBZl0B4UkSDOldBXOUY3+ABTTXefEl+5831r8qqzdpYNa7C" +
            "wJ79Hwy70Enk+93dHFi928WTv9oLDBvYFt2YCL+vysWFiYfnwKdLoaj9miyKgylgKYjrQH9isWqpCUVI6C/NAZ51H82wd0VJ/fqtTdcDeeeKSNaboGW2zAgMBAA" +
            "ECgYBwUZeD2qhqWl11fVJHnfiyaeg1UCGqqP2JyZzVw3FTCsrVjS6GzwB2wJhbFFuX+Oom+uhniz3tfSoMFHRWThyzhDdwVXtiALE6glCZ0srLJH/zKjjRWg+J+O" +
            "XIkukLOsl+pdY+JmD6R1o+0dUIgRW9dGpnsiVianawHmbJyUiCOQJBAOSqb1OqMeo1ibahpo5MSFd1+0U1s6PavzD1C8x4WmKA+G9/B9NNYJfhnYcB1B0g4ZQB+A" +
            "dO+PK4eOSEN2MBNrcCQQDOyKbEIhmQhXumNP2I4OAjvu7eWtn0Z/S603UQz/EeMoLTh1v92j97um8Puu7hl0oJamRzWQrEFSj5EXySfGTlAkAqhnfFu3Uk9WfzCw" +
            "UKma0qdsqQCHisFFZSKIBVOly0zP9m3Nz7svwvwsmk/fYQpLjO6b7SvSEXIq948A3M38DpAkBBfwCDYyGhywXNMT+U2VVlPRa/yxr59OtpOYz81EBVBj617UvP7g7" +
            "pi7Wdz+HWBmtm7BIBDxT/4ovlyAojA8+ZAkBLmfAYsygxTn/yklSQr3YmUfAWUmG+9rpWf/yKK30xMawIaQLXmdsgQNC20s/y5iZa5FowL7L3CoScdqwvHbSI";//ok

    // 微信配置信息
    String WX_TOTAL_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";   // 统一下单接口
    String WX_APP_ID = "wx2cd2e5ddcd8a2883";
    String WX_APP_SECRET = "31453809493282e85a8322b6623af7c8";


    //邀请会员
    String GetMyInvitateInfo = MAIN_ENGINE + "/api/v1/getMyInviteInfo";
}
