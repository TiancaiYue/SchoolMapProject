package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.StudentListAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxLogTool;
import com.keyuan.schoolmap.widget.RxEmptyLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 学籍列表
 */
public class StudentListActivity extends BaseActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private StudentListAdapter mAdapter;
    private RxEmptyLayout mEmptyLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_student_list;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StudentListAdapter();
        mAdapter.setPreLoadNumber(2);
        mRecyclerView.setAdapter(mAdapter);

        mEmptyLayout = (RxEmptyLayout) findViewById(R.id.empty_layout);
        mEmptyLayout.setOnEmptyLayoutClickListener(new RxEmptyLayout.OnEmptyLayoutClickListener() {
            @Override
            public void onEmptyLayoutClick(int status) {
                if (status != RxEmptyLayout.NETWORK_LOADING) {
                    getData();
                }
            }
        });

    }

    @Override
    public void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                initPop();
            }
        });
    }

    @Override
    public void initData() {
        List<Values> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(new Values());
        }
        mAdapter.addData(mList);
    }

    private void getData() {
        String url = "https://www.baidu.com";
        OkGo.<String>get(url).tag(mContext).execute(new StringCallback() {

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                RxLogTool.e("onStart");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_LOADING);
            }

            @Override
            public void onSuccess(Response<String> response) {
                RxLogTool.e("onSuccess");
                //mEmptyLayout.hide();
                mEmptyLayout.setEmptyDataContent("亲，没有更多数据了");
                mEmptyLayout.show(RxEmptyLayout.EMPTY_DATA);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                RxLogTool.e("onError");
                mEmptyLayout.show(RxEmptyLayout.NETWORK_ERROR);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                RxLogTool.e("onFinish");
            }
        });
    }

    /**
     * 确认绑定弹框
     */
    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_link_to_student, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_student_list, null);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        (view.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        EditText editText = (EditText) view.findViewById(R.id.et_person_number);
        (view.findViewById(R.id.tv_sure)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                    showToast("请输入身份证号！");
                } else {
                    if (editText.getText().toString().trim().length() == 18) {
//                        httpGoToPayFor(etPayNumber.getText().toString().trim().substring(0, 6));
                    } else {
                        showToast("请检查身份证号!");
                    }
                }
            }
        });
    }
}
