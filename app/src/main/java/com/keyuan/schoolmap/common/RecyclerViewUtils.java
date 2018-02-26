package com.keyuan.schoolmap.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/8/31.
 */

public class RecyclerViewUtils {
    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param position      要跳转的位置
     */
    public static void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int position) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(position);
        }
    }
}
