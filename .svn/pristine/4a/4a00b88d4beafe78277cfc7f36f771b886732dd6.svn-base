package com.keyuan.schoolmap.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.keyuan.schoolmap.widget.Open;

/**
 * Created by xiedd on 2017/12/11.
 */

public class RxDeviceTool {

    /**
     * 是否有Internet连接
     *
     * @return
     */
    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) Open.getInstance().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }
}
