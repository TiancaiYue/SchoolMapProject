package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.ApprovalDetailsAdapter;
import com.keyuan.schoolmap.adapter.GridViewAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 审核详情
 */
public class ApprovalDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTitle;
    //<---请假--->
    private LinearLayout llAskForLeave;
    private TextView tvTypeLeave;
    private TextView tvStartLeave;
    private TextView tvEndLeave;
    private TextView tvReasonLeave;
    //<---活动--->
    private LinearLayout llActivities;
    private TextView tvTypeActivities;
    private TextView tvPersonCountActivities;
    private TextView tvMoneyActivities;
    private TextView tvStartActivities;
    private TextView tvEndActivities;
    private TextView tvReasonActivities;
    //<---补签--->
    private LinearLayout llSignedSupplement;
    private TextView tvDataSigned;
    private TextView tvClassSigned;
    private TextView tvReasonSigned;
    //<---补签--->
    private LinearLayout llAcomplaint;
    private TextView tvTypeAcomplaint;
    private TextView tvReasonAcomplaint;
    //<---店铺--->
    private LinearLayout llShop;
    private TextView tvTypeShop;
    private TextView tvPersonNumber;
    private TextView tvReasonShop;

    private Button btnAgree, btnReject;
    private LinearLayout llBtn;//如果是我的申请把这个隐藏掉
    private RecyclerView recyclerShow;
    private RecyclerView recyclerPerson;
    private RelativeLayout relChoosePerson;
    //拒绝时批注
    private EditText etNote;
    //显示图片
    private GridViewAdapter gridViewAdapter;
    //显示流程
    private ApprovalDetailsAdapter approvalDetailsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_approval_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        //<---请假--->
        llAskForLeave = (LinearLayout) findViewById(R.id.ll_ask_for_leave);
        tvTypeLeave = (TextView) findViewById(R.id.tv_type_leave);
        tvStartLeave = (TextView) findViewById(R.id.tv_start_leave);
        tvEndLeave = (TextView) findViewById(R.id.tv_end_leave);
        tvReasonLeave = (TextView) findViewById(R.id.tv_reason_leave);
        //<---活动--->
        llActivities = (LinearLayout) findViewById(R.id.ll_activities);
        tvTypeActivities = (TextView) findViewById(R.id.tv_type_activities);
        tvPersonCountActivities = (TextView) findViewById(R.id.tv_person_count_activities);
        tvMoneyActivities = (TextView) findViewById(R.id.tv_money_activities);
        tvStartActivities = (TextView) findViewById(R.id.tv_start_activities);
        tvEndActivities = (TextView) findViewById(R.id.tv_end_activities);
        tvReasonActivities = (TextView) findViewById(R.id.tv_reason_activities);
        //<---补签--->
        llSignedSupplement = (LinearLayout) findViewById(R.id.ll_signed_supplement);
        tvDataSigned = (TextView) findViewById(R.id.tv_data_signed);
        tvClassSigned = (TextView) findViewById(R.id.tv_class_signed);
        tvReasonSigned = (TextView) findViewById(R.id.tv_reason_signed);
        //<---补签--->
        llAcomplaint = (LinearLayout) findViewById(R.id.ll_acomplaint);
        tvTypeAcomplaint = (TextView) findViewById(R.id.tv_type_acomplaint);
        tvReasonAcomplaint = (TextView) findViewById(R.id.tv_reason_acomplaint);
        //<---店铺--->
        llShop = (LinearLayout) findViewById(R.id.ll_shop);
        tvTypeShop = (TextView) findViewById(R.id.tv_type_shop);
        tvPersonNumber = (TextView) findViewById(R.id.tv_person_number);
        tvReasonShop = (TextView) findViewById(R.id.tv_reason_shop);

        btnAgree = (Button) findViewById(R.id.btn_agree);
        btnReject = (Button) findViewById(R.id.btn_reject);
        llBtn = (LinearLayout) findViewById(R.id.ll_btn);
        recyclerShow = (RecyclerView) findViewById(R.id.recycler_show);
        recyclerPerson = (RecyclerView) findViewById(R.id.recycler_person);
        etNote = (EditText) findViewById(R.id.et_note);
        relChoosePerson = (RelativeLayout) findViewById(R.id.rel_choose_person);
    }

    @Override
    public void initEvent() {
        btnAgree.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        relChoosePerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agree:
                break;
            case R.id.rel_choose_person:
                hideSoftKeyBoard(this);
                Intent intent1 = new Intent(ApprovalDetailsActivity.this, SearchReviewerActivity.class);
                intent1.putExtra("whichActivity", "ApprovalDetailsActivity");
                startActivityForResult(intent1, 0x101);
                break;
            case R.id.btn_reject:
                hideSoftKeyBoard(this);
                if (!TextUtils.isEmpty(etNote.getText().toString().trim())) {
                } else {
                    Toast.makeText(this, "请输入批注！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        //显示图片
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerShow.setLayoutManager(linearLayoutManager);
        String urls = "http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg," +
                "http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg," +
                "http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg," +
                "http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg," +
                "http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg," +
                "http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg," +
                "http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg," +
                "http://f.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf4e74b4f28fbd3572c10dfcf48.jpg";
        if (urls != null && urls.length() > 0) {
            recyclerShow.setVisibility(View.VISIBLE);
            List<String> imageList = Arrays.asList(urls.split(","));
            List<String> imageInfo = new ArrayList<>();
            if (!imageList.isEmpty()) {
                for (int n = 0; n < imageList.size(); n++) {
                    imageInfo.add(imageList.get(n));
                    Log.v("审批图片", imageList.get(n));
                }
            }
            gridViewAdapter = new GridViewAdapter(imageInfo, ApprovalDetailsActivity.this);
            gridViewAdapter.setOnItemClickListener(new GridViewAdapter.onItemClick() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(new Intent(ApprovalDetailsActivity.this, BigImageViewActrivity.class).putExtra("imageUrl", urls));
                }
            });
            recyclerShow.setAdapter(gridViewAdapter);
        } else {
            recyclerShow.setVisibility(View.GONE);
        }
        //显示流程
        CustomLinearLayoutManager mLayoutManager = new CustomLinearLayoutManager(this);
        mLayoutManager.setScrollEnabled(false);
        recyclerPerson.setLayoutManager(mLayoutManager);
        approvalDetailsAdapter = new ApprovalDetailsAdapter();
        recyclerPerson.setAdapter(approvalDetailsAdapter);
        approvalDetailsAdapter.addData(new Values());
        approvalDetailsAdapter.addData(new Values());
        approvalDetailsAdapter.addData(new Values());
        approvalDetailsAdapter.addData(new Values());
    }
}
