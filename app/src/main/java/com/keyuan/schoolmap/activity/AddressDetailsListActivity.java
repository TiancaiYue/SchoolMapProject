package com.keyuan.schoolmap.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.adapter.AddressDetailsListAdapter;
import com.keyuan.schoolmap.base.BaseActivity;
import com.keyuan.schoolmap.common.Constants;
import com.keyuan.schoolmap.common.GsonUtil;
import com.keyuan.schoolmap.common.RecyclerViewUtils;
import com.keyuan.schoolmap.common.Utils;
import com.keyuan.schoolmap.entify.AMBasePlusDto;
import com.keyuan.schoolmap.entify.Contacts;
import com.keyuan.schoolmap.entify.ContactsNew;
import com.keyuan.schoolmap.entify.NewsCallback;
import com.keyuan.schoolmap.widget.RxEmptyLayout;
import com.keyuan.schoolmap.widget.SidebarView.ChineseToPinyinHelper;
import com.keyuan.schoolmap.widget.SidebarView.SidebarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 通讯录详情
 */
public class AddressDetailsListActivity extends BaseActivity {
    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;
    @ViewInject(R.id.swipe_refresh_layout)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.sidebarView_main)
    private SidebarView sidebarView_main;
    @ViewInject(R.id.textView_dialog)
    private TextView textView_dialog;
    private AddressDetailsListAdapter adapter = null;
    @ViewInject(R.id.empty_layout)
    private RxEmptyLayout mEmptyLayout;
    private LinearLayoutManager mLayoutManager;
    private List<Contacts> contactsList;//从接口拿到的通讯录
    private List<ContactsNew> contactsNewList;//更具首字母排序更改后的通讯录

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_details_list;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        ViewUtils.inject(this);
        sidebarView_main.setTextView(textView_dialog);
    }

    @Override
    public void initEvent() {
        mEmptyLayout.setOnEmptyLayoutClickListener(new RxEmptyLayout.OnEmptyLayoutClickListener() {
            @Override
            public void onEmptyLayoutClick(int status) {
                if (status != RxEmptyLayout.NETWORK_LOADING) {
                    getContacts();
                }
            }
        });
    }

    @Override
    public void initData() {
        getContacts();
    }

    /**
     * 获取企信联系人
     */
    public void getContacts() {
        // citem 参数信息
        List<JSONObject> citemList = new ArrayList<JSONObject>();
        JSONObject oitemJson = new JSONObject();
        JSONObject mitemJson = new JSONObject();
        try {
            JSONObject citemJson1 = new JSONObject();
            citemJson1.put("name", "registertype");
            citemJson1.put("value", "1");
            citemList.add(citemJson1);
            JSONObject citemJson2 = new JSONObject();
            citemJson2.put("name", "state");
            citemJson2.put("value", "1");
            citemList.add(citemJson2);
            JSONObject citemJson3 = new JSONObject();
            citemJson3.put("name", "rec_no");
            citemJson3.put("value", 16494);
            citemJson3.put("operate", "not in");
            citemList.add(citemJson3);
            //oitem 参数信息
            oitemJson.put("name", "pinyin");
            oitemJson.put("value", "");
            //mitem 参数信息
            mitemJson.put("name", "photo,pinyin,rec_no,username,orgname,officename,chatnumber,phone,mob_phone,department_rec,email");
            mitemJson.put("value", "");
            //
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<AMBasePlusDto<String>>post(Constants.MAIN_ENGINE1)
                .params("mn", "Operation")
                .params("AccountId", 1)
                .params("userId", 16494)
                .params("Token", "636522246092996215")
                .params("opkey", "query")
                .params("citem", citemList.toString())
                .params("oitem", oitemJson.toString())
                .params("modelkey", "chatuserlist_key")
                .params("mitem", mitemJson.toString())
                .execute(new NewsCallback<AMBasePlusDto<String>>() {

                    @Override
                    public void onStart(Request<AMBasePlusDto<String>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onSuccess(Response<AMBasePlusDto<String>> response) {
                        String data = response.body().getData();
                        if (!TextUtils.isEmpty(data)) {
                            contactsList = GsonUtil.fromJsonList(data, Contacts.class);
                            contactsNewList = new ArrayList<ContactsNew>();
                            for (int i = 0; i < contactsList.size(); i++) {
                                ContactsNew contacts = new ContactsNew();
                                String username = contactsList.get(i).getUsername();
                                String phone = contactsList.get(i).getMob_phone();
                                String pinyin = ChineseToPinyinHelper.getInstance().getPinyin(
                                        username);
                                String firstLetter = pinyin.substring(0, 1).toUpperCase();
                                if (firstLetter.matches("[A-Z]")) {
                                    contacts.setFirstLetter(firstLetter);
                                } else {
                                    contacts.setFirstLetter("#");
                                }
                                contacts.setUsername(username);
                                contacts.setMob_phone(phone);
                                contactsNewList.add(contacts);
                                upDataList();
                            }
                        } else {
                            mEmptyLayout.setEmptyDataContent("亲，没有更多数据了");
                            mEmptyLayout.show(RxEmptyLayout.EMPTY_DATA);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissLoading();
                    }

                    @Override
                    public void onError(Response<AMBasePlusDto<String>> response) {
                        super.onError(response);
                        mEmptyLayout.show(RxEmptyLayout.NETWORK_ERROR);
                    }
                });
    }

    /**
     * 根据排序画界面
     */
    @SuppressLint("ResourceAsColor")
    private void upDataList() {
        Collections.sort(contactsNewList, new Comparator<ContactsNew>() {
            @Override
            public int compare(ContactsNew lhs, ContactsNew rhs) {
                if (lhs.getFirstLetter().equals("#")) {
                    return 1;
                } else if (rhs.getFirstLetter().equals("#")) {
                    return -1;
                } else {
                    return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
                }
            }
        });
        mSwipeRefreshLayout.setColorSchemeColors(R.color.swipeRefreshColor1, R.color.swipeRefreshColor2,
                R.color.swipeRefreshColor3, R.color.swipeRefreshColor4);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new AddressDetailsListAdapter();
        adapter.setPreLoadNumber(2);
        recyclerView.setAdapter(adapter);
        adapter.addData(contactsNewList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String phone = contactsNewList.get(position).getMob_phone();
                if (!TextUtils.isEmpty(phone)) {
                    dialogData(phone);
                } else {
                    showToast("您选择的联系人没有设置联系电话。");
                }
            }
        });

        sidebarView_main.setOnLetterClickedListener(new SidebarView.OnLetterClickedListener() {
            @Override
            public void onLetterClicked(String str) {
                int position = adapter.getPositionForSection(str
                        .charAt(0));
                RecyclerViewUtils.moveToPosition(mLayoutManager, recyclerView, position);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 退出登录弹框
     */
    private void dialogData(String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_school_icon);
        builder.setTitle("提示");
        builder.setMessage("确定拨打电话" + phone + "？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Utils.makingCalls(AddressDetailsListActivity.this, phone);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
