package com.keyuan.schoolmap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.ApprovalDetailsAdapter;
import com.keyuan.schoolmap.adapter.GridViewAdapter;
import com.keyuan.schoolmap.adapter.SubmitExpenseAccountAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.BannerImageLoader;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.widget.CustomLinearLayoutManager;
import com.keyuan.schoolmap.widget.PileLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.keyuan.schoolmap.common.Utils.hideSoftKeyBoard;

/**
 * 报销处理详情
 */
public class ApprovalDetailsTwoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvNumber;
    private TextView tvData;
    private Button btnAgree, btnReject;
    private LinearLayout llBtn;//如果是我的申请把这个隐藏掉
    private RecyclerView recyclerList;
    private RecyclerView recyclerShow;
    private RecyclerView recyclerPerson;
    private RelativeLayout relChoosePerson;
    //拒绝时批注
    private EditText etNote;
    //报销明细
    private SubmitExpenseAccountAdapter submitExpenseAccountAdapter;
    //显示图片
    private GridViewAdapter gridViewAdapter;
    //显示流程
    private ApprovalDetailsAdapter approvalDetailsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_approval_two_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvData = (TextView) findViewById(R.id.tv_data);
        btnAgree = (Button) findViewById(R.id.btn_agree);
        btnReject = (Button) findViewById(R.id.btn_reject);
        llBtn = (LinearLayout) findViewById(R.id.ll_btn);
        recyclerList = (RecyclerView) findViewById(R.id.recycler_list);
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
                Intent intent1 = new Intent(ApprovalDetailsTwoActivity.this, SearchReviewerActivity.class);
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
        //报销明细
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(this);
        manager.setScrollEnabled(false);
        recyclerList.setLayoutManager(manager);
        submitExpenseAccountAdapter = new SubmitExpenseAccountAdapter();
        recyclerList.setAdapter(submitExpenseAccountAdapter);
        submitExpenseAccountAdapter.addData(new Values());
        submitExpenseAccountAdapter.addData(new Values());
        submitExpenseAccountAdapter.addData(new Values());
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
        if (null != urls && urls.length() > 0) {
            recyclerShow.setVisibility(View.VISIBLE);
            List<String> imageList = Arrays.asList(urls.split(","));
            List<String> imageInfo = new ArrayList<>();
            if (!imageList.isEmpty()) {
                for (int n = 0; n < imageList.size(); n++) {
                    imageInfo.add(imageList.get(n));
                    Log.v("审批图片", imageList.get(n));
                }
            }
            gridViewAdapter = new GridViewAdapter(imageInfo, ApprovalDetailsTwoActivity.this);
            gridViewAdapter.setOnItemClickListener(new GridViewAdapter.onItemClick() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(new Intent(ApprovalDetailsTwoActivity.this, BigImageViewActrivity.class).putExtra("imageUrl", urls));
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
    }
}