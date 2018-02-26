package com.keyuan.schoolmap.base;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.ClipImageActivity;
import com.keyuan.schoolmap.common.Constants;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.tool.RxGlideTool;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.tool.RxToastTool;
import com.keyuan.schoolmap.widget.RxLoadDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/10/26.
 */

public abstract class BaseTakePhotoActivity extends AppCompatActivity implements IBaseView {
    protected static final String TAG = BaseTakePhotoActivity.class.getSimpleName();
    protected Context mContext;
    protected View mContentView;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求打开相机权限
    private static final int TAKE_PHOTO_REQUEST_CODE = 105;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片临时文件
    private File tempFile;
    // 头像Bitmap数据
    private Bitmap avatarBitmap;
    //url
    public String backUrl1;
    public String backUrl2;
    public String backUrl3;
    public String backUrl4;
    public String backUrl5;
    public String backUrl6;
    public String backUrl7;
    public String backUrl8;
    public String backUrl9;
    public String backUrl10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCameraTempFile(savedInstanceState);
        mContext = this;
        RxActivityTool.addActivity(this);
        mContentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(mContentView);
        initView(savedInstanceState, mContentView);
        initEvent();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(mContext);
    }

    protected void showToast(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RxToastTool.setGravity(Gravity.CENTER, 0, 0);
                RxToastTool.showShort(msg);
            }
        }).start();
    }

    protected RxLoadDialog mLoadDialog;

    protected void showLoading() {
        if (!isFinishing()) {
            dismissLoading();
            mLoadDialog = new RxLoadDialog(mContext);
            mLoadDialog.show();
        }
    }

    protected void dismissLoading() {
        try {
            if (!isFinishing()
                    && mLoadDialog != null
                    && mLoadDialog.isShowing()) {
                mLoadDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        EventBus.getDefault().unregister(this);
        RxActivityTool.finish(this);
    }

    /**
     * 上传图片
     */
    public void uploadHeadImage() {
        View view = LayoutInflater.from(BaseTakePhotoActivity.this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(BaseTakePhotoActivity.this).inflate(R.layout.fragment_home, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(BaseTakePhotoActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions((Activity) BaseTakePhotoActivity.this, new String[]{Manifest.permission.CAMERA},
                            TAKE_PHOTO_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCarema();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(BaseTakePhotoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions((Activity) BaseTakePhotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统图库
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 上传头像文件到服务器
     *
     * @param cropImagePath
     */
    public void httpUpDataUrl(String cropImagePath, final ImageView imageView, int index) {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File(cropImagePath));
        OkGo.<String>post(Constants.UploadFile)
                .tag(this)
                .addFileParams("files", files)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        RxLogTool.v("上传图片返回:" + response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            String url = jsonObject.getString("path");
                            if (!TextUtils.isEmpty(url) && url.contains("http")) {
                                RxGlideTool.loadImageViewLoding(BaseTakePhotoActivity.this, url, imageView, R.drawable.ic_default_image, R.drawable.ic_default_image);
                            } else {
                                RxGlideTool.loadImageViewLoding(BaseTakePhotoActivity.this, Constants.IMAGE_MAIN_ENGINE + url, imageView, R.drawable.ic_default_image, R.drawable.ic_default_image);
                            }
                            switch (index) {
                                case 0:
                                    backUrl1 = url;
                                    break;
                                case 1:
                                    backUrl1 = url;
                                    break;
                                case 2:
                                    backUrl2 = url;
                                    break;
                                case 3:
                                    backUrl3 = url;
                                    break;
                                case 4:
                                    backUrl4 = url;
                                    break;
                                case 5:
                                    backUrl5 = url;
                                    break;
                                case 6:
                                    backUrl6 = url;
                                    break;
                                case 7:
                                    backUrl7 = url;
                                    break;
                                case 8:
                                    backUrl8 = url;
                                    break;
                                case 9:
                                    backUrl9 = url;
                                    break;
                            }
                            RxLogTool.v("上传头像的链接：" + url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图" +
                "片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        // 解决7.0开启相机权限
        if (Build.VERSION.SDK_INT < 24) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
            startActivityForResult(intent, REQUEST_CAPTURE);
        } else {
            //兼容android7.0 使用共享文件的形式
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, REQUEST_CAPTURE);
        }
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    public void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {//请求打开相机权限
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 获得权限
                gotoCarema();
            } else {  // 权限被拒绝
                String msg = "需要打开相机权限，请到应用设置中打开";
                AlertDialog dialog = new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("去设置", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 获得权限
                gotoPhoto();
            } else {  // 权限被拒绝
                String msg = "需要读写手机存储权限，请到应用设置中打开";
                AlertDialog dialog = new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("去设置", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        }
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
