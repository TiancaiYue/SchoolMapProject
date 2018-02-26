package com.keyuan.schoolmap.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.AddressBookActivity;
import com.keyuan.schoolmap.activity.ChangePassWordActivity;
import com.keyuan.schoolmap.activity.CreateShopActivity;
import com.keyuan.schoolmap.activity.ForMyApprovalActivity;
import com.keyuan.schoolmap.activity.PersonalDataActivity;
import com.keyuan.schoolmap.activity.SettingActivity;
import com.keyuan.schoolmap.activity.ShopDetailsActivity;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.common.AppUpdateUtils;
import com.keyuan.schoolmap.tool.RxActivityTool;

/**
 * 我的
 * Created by Administrator on 2017/12/4.
 */

public class MineFragment extends BaseFragment {
    private View mineView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mineView = view;
    }

    @Override
    public void initEvent() {
        //个人信息
        (mineView.findViewById(R.id.iv_avatar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), PersonalDataActivity.class);
            }
        });
        //聊天列表
        (mineView.findViewById(R.id.ll_chat_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("暂未开放！");
            }
        });
        //待我处理
        (mineView.findViewById(R.id.ll_application_process)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForMyApprovalActivity.class).putExtra("is_mine", true));
            }
        });
        //我的申请
        (mineView.findViewById(R.id.ll_processing_progress)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForMyApprovalActivity.class).putExtra("is_mine", false));
            }
        });
        //学院通信录
        (mineView.findViewById(R.id.ll_address_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), AddressBookActivity.class);
            }
        });
        //我的商铺
        (mineView.findViewById(R.id.ll_my_shop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShopDetailsActivity.class).putExtra("is_mine", true));
            }
        });
        //设置
        (mineView.findViewById(R.id.ll_setting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), SettingActivity.class);
            }
        });
        //查看更新
        (mineView.findViewById(R.id.ll_configuration_urls)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUpdateUtils.getInstance().update(getActivity());
            }
        });
        //修改密码
        (mineView.findViewById(R.id.ll_change_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxActivityTool.skipActivity(getActivity(), ChangePassWordActivity.class);
            }
        });

    }

    @Override
    public void initData() {

    }
}
