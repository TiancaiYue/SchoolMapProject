package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.GridViewTwoAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.base.WebPageActivity;
import com.keyuan.schoolmap.itemDecoration.DividerGridItemDecoration;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.SureCancelDialog;

import java.util.ArrayList;

/**
 * 申请操作
 */
public class ApplyActivity extends BaseActivity implements GridViewTwoAdapter.onItemClick {
    private RecyclerView recyclerView;
    private RelativeLayout relForMyApproval, relIInitiatedTheApproval;
    private TextView tvOne, tvTwo;
    private GridViewTwoAdapter mAdapter;
    private ArrayList<String> textlist;
    private ArrayList<Integer> imageList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        relForMyApproval = (RelativeLayout) view.findViewById(R.id.rel_for_my_approval);
        relIInitiatedTheApproval = (RelativeLayout) view.findViewById(R.id.rel_i_initiated_the_approval);
        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvTwo = (TextView) view.findViewById(R.id.tv_two);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void initEvent() {
        relForMyApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplyActivity.this, ForMyApprovalActivity.class).putExtra("is_mine", true));
            }
        });
        relIInitiatedTheApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplyActivity.this, ForMyApprovalActivity.class).putExtra("is_mine", false));
            }
        });
    }

    @Override
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(layoutManager);

        textlist = new ArrayList<>();
        imageList = new ArrayList<>();

        addCustomerListData();
        mAdapter = new GridViewTwoAdapter(this, imageList, textlist);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    //申请列表
    private void addCustomerListData() {
        textlist.clear();
        imageList.clear();
        textlist.add("请假");
        textlist.add("报销");
        textlist.add("签到");
        textlist.add("活动");
        textlist.add("商铺");
        textlist.add("补签");
        textlist.add("投诉");

        imageList.add(R.drawable.ic_leave);
        imageList.add(R.drawable.ic_reimbursement);
        imageList.add(R.drawable.ic_sign);
        imageList.add(R.drawable.ic_activity);
        imageList.add(R.drawable.ic_hot_shop);
        imageList.add(R.drawable.ic_retroactive);
        imageList.add(R.drawable.ic_report);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0://请假
                RxActivityTool.skipActivity(ApplyActivity.this, AskForLeaveActivity.class);
                break;
            case 1://报销
                RxActivityTool.skipActivity(ApplyActivity.this, SubmitExpenseAccountActivity.class);
                break;
            case 2://签到
                RxActivityTool.skipActivity(ApplyActivity.this, CheckingInActivity.class);
                break;
            case 3://活动
                RxActivityTool.skipActivity(ApplyActivity.this, ActivtiesApplyActivity.class);
                break;
            case 4://商铺
                final SureCancelDialog mDialog = new SureCancelDialog(this);
                //mDialog.setTitle("提示");
                mDialog.setSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RxActivityTool.skipActivity(ApplyActivity.this, CreateShopActivity.class);
                    }
                });
                mDialog.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.cancel();
                    }
                });
                mDialog.setllAgreeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxActivityTool.skipActivity(ApplyActivity.this, CreateShopWebViewActivity.class);
                    }
                });
                mDialog.show();
                break;
            case 5://补签
                RxActivityTool.skipActivity(ApplyActivity.this, SignedSupplementActivity.class);
                break;
            case 6://投诉
                RxActivityTool.skipActivity(ApplyActivity.this, AcomplaintActivity.class);
                break;
        }
    }
}
