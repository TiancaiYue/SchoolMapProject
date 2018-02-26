package com.keyuan.schoolmap.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.SignInRecordActivity;
import com.keyuan.schoolmap.adapter.SignInListAdapter;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 签到
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RelativeLayout relViewAttendanceRecords;
    private PorterShapeImageView ivHeadView;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvSiginData;
    private RecyclerView recyclerview;
    private ImageView ivSignBack;
    private TextView tvNowPosition;
    private TextView tvNowPositionDetails;
    private SignInListAdapter mAdapter;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private double latitude = 0.0, longitude = 0.0;   // 纬度，经度
    private String address = "";
    private int type = 1;//是否签到
    private int pageindex = 1;
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,    //通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
            Manifest.permission.ACCESS_FINE_LOCATION,   //通过GPS芯片接收卫星的定位信息，定位精度达10米以内
            Manifest.permission.WRITE_EXTERNAL_STORAGE,  //允许程序写入外部存储，如SD卡上写文件
            Manifest.permission.READ_EXTERNAL_STORAGE,   //允许程序读取外部存储，如SD卡上写文件
            //Manifest.permission.READ_PHONE_STATE   //访问电话状态
    };
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    private static final int PERMISSON_REQUESTCODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initView();
        initOnClick();
        return view;
    }

    private void initView() {
        relViewAttendanceRecords = (RelativeLayout) view.findViewById(R.id.rel_view_attendance_records);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvSiginData = (TextView) view.findViewById(R.id.tv_sigin_data);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        ivSignBack = (ImageView) view.findViewById(R.id.iv_sign_back);
        tvNowPosition = (TextView) view.findViewById(R.id.tv_now_position);
        tvNowPositionDetails = (TextView) view.findViewById(R.id.tv_now_position_details);
        //初始化定位
        initLocation();
        initList();
    }

    private void initOnClick() {
        relViewAttendanceRecords.setOnClickListener(this);
        ivSignBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_view_attendance_records:
                Intent intent = new Intent(getActivity(), SignInRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_sign_back:
                mAdapter.addData(new Values());
                break;
            default:
                break;
        }
    }

    /**
     * 初始化列表
     */
    private void initList() {
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setScrollEnabled(false);
        recyclerview.setItemAnimator(null);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter = new SignInListAdapter();
        recyclerview.setAdapter(mAdapter);
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                latitude = loc.getLatitude();
                longitude = loc.getLongitude();
                address = loc.getAddress();
//                tvNowPosition.setText(loc.getStreet());
//                tvNowPositionDetails.setText(loc.getAddress());
                stopLocation();
            } else {
                RxLogTool.v("定位失败");
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        //resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        isNeedCheck = false;
        locationClient.stopLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    /**
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(getActivity(),
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        } else {
            startLocation();
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    getActivity(), perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。请点击设置-权限-打开所需权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isNeedCheck = true;
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
