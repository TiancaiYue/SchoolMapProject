package com.keyuan.schoolmap.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.SignedSupplementActivity;
import com.keyuan.schoolmap.adapter.SignedSupplementAdapter;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxToastTool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 判断是否登录
     */
//    public static boolean isLogin(Activity activity) {
//        if (hasToken(activity)) {
//            return true;
//        } else {
//            Intent intent = new Intent(activity, LoginActivity.class);
//            activity.startActivity(intent);
//        }
//        return false;
//    }

    /**
     * 是否有Token（登录状态）
     */
//
//    public static boolean hasToken(Context context) {
//        String token = UserUtils.getToken(context);
//        if (!Utils.isNull(token)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 通过View的宽和比例设置View的高 已知View的宽度，和view的比例大小，设置view的高度
     */
    public static void setViewHeightByViewWidthAndRation(Context context, View view, int width, int ratioWidth,
                                                         int rationHeight) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        float rate = (float) width / ratioWidth;
        params.height = (int) (rationHeight * rate);
        params.width = width;
        view.setLayoutParams(params);
    }

    /**
     *  
     * * 使用java正则表达式去掉多余的.与0 
     * * @param s 
     *  * @return  
     *      
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }
        return s;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 验证手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断是否为空
     *
     * @return
     */

    public static boolean isNull(Object object) {
        try {
            return null == object || "".equals(object.toString()) || "null".equals(object.toString())
                    || "[]".equals(object.toString());
        } catch (Exception e) {
        }
        return true;
    }

    /**
     * 判断是否为空
     *
     * @return false 为空,true 为非空
     */
    public static boolean judgeNull(Object obj) {
        if (isNull(obj)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 设置Textview 中划线
     */

    public static void setTextMiddleLine(View view) {
        if (view instanceof TextView) {
            TextPaint paint = ((TextView) view).getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            paint.setAntiAlias(true);
        }
    }

    /**
     * 判断该字符串是否为中文
     *
     * @param string
     * @return
     */
    public static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断内容是否为空，防止空指针出现,
     * <p>
     * 返回String类型
     */

    public static String content(Object object) {
        if (!isNull(object)) {
            return object + "";
        }
        return "";
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 关闭软键盘
     */

    public static void hideSoftKeyBoard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 拨打电话
     */
    public static void makingCalls(Context mContext, String phone) {
        if (phone == null && phone.length() == 0) {
            RxToastTool.showShort("未配置电话");
            return;
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            RxToastTool.showShort("未获取到拨打电话权限");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.toString().trim()));
            mContext.startActivity(intent);
        }
    }

    /**
     * 发送短信息
     */
    public static void sendSms(Context mContext, String phone, String message) {
        if (phone == null && phone.length() == 0) {
            RxToastTool.showShort("未配置电话");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
        intent.putExtra("sms_body", message);
        mContext.startActivity(intent);
    }

    /**
     * 发邮件
     */
    public static void sendMail(final Context mContext, String mail) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822"); // 真机上使用这行
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{mail});
        i.putExtra(Intent.EXTRA_SUBJECT, "");
        i.putExtra(Intent.EXTRA_TEXT, "");
        mContext.startActivity(Intent.createChooser(i,
                "请选择邮箱软件"));

//        Uri uri = Uri.parse(mail);
//        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//        PackageManager packageManager = mContext.getPackageManager();
//        List<ResolveInfo> applist = packageManager.queryIntentActivities(it, 0);
//        if (applist == null || applist.isEmpty()) {
//            Toast.makeText(mContext, "您还没有邮箱，请下载后再试！", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        mContext.startActivity(it);
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据身份名称返回省份简称
     *
     * @return
     */
    public static String proviceForShort(String province) {
//        for (int i = 0; i < carCityList.size(); i++) {
//            if (city.contains(carCityList.get(i).getCity())) {
//                return carCityList.get(i).getCode();
//            }
//        }
//        return "";

        if (province.contains("北京市"))
            return "京";
        else if (province.contains("天津市"))
            return "津";
        else if (province.contains("重庆市"))
            return "渝";
        else if (province.contains("上海市"))
            return "沪";
        else if (province.contains("河北省"))
            return "冀";
        else if (province.contains("山西省"))
            return "晋";
        else if (province.contains("辽宁省"))
            return "辽";
        else if (province.contains("吉林省"))
            return "吉";
        else if (province.contains("黑龙江省"))
            return "黑";
        else if (province.contains("江苏省"))
            return "苏";
        else if (province.contains("浙江省"))
            return "浙";
        else if (province.contains("安徽省"))
            return "皖";
        else if (province.contains("福建省"))
            return "闽";
        else if (province.contains("江西省"))
            return "赣";
        else if (province.contains("山东省"))
            return "鲁";
        else if (province.contains("河南省"))
            return "豫";
        else if (province.contains("湖北省"))
            return "鄂";
        else if (province.contains("湖南省"))
            return "湘";
        else if (province.contains("广东省"))
            return "粤";
        else if (province.contains("海南省"))
            return "琼";
        else if (province.contains("四川省"))
            return "川";
        else if (province.contains("贵州省"))
            return "贵";
        else if (province.contains("云南省"))
            return "云";
        else if (province.contains("陕西省"))
            return "陕";
        else if (province.contains("甘肃省"))
            return "甘";
        else if (province.contains("青海省"))
            return "青";
        else if (province.contains("台湾省"))
            return "台";
        else if (province.contains("内蒙古自治区"))
            return "蒙";
        else if (province.contains("广西壮族自治区"))
            return "桂";
        else if (province.contains("宁夏回族自治区"))
            return "宁";
        else if (province.contains("新疆维吾尔自治区 "))
            return "新";
        else if (province.contains("西藏自治区"))
            return "藏";
        else if (province.contains("香港特别行政区"))
            return "港";
        else if (province.contains("澳门特别行政区"))
            return "澳";
        else {
            return province;
        }
    }

    /**
     * 保存图片到本地
     */
    public static void loadImgSaveToLocal(String url, String filename) {
        try {
            File file = new File(filename);
            //得到图片地址
            byte[] data = readImage(url);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(data);
            //关闭流的这个地方需要完善一下
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //声明称为静态变量有助于调用
    public static byte[] readImage(String path) throws Exception {
        URL url = new URL(path);
        // 记住使用的是HttpURLConnection类
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //如果运行超过5秒会自动失效 这是android规定
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        //调用readStream方法
        return readStream(inStream);
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        //把数据读取存放到内存中去
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
}
