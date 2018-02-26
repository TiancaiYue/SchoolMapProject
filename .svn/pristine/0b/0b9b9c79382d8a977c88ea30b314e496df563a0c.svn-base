package com.keyuan.schoolmap.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.activity.ClassRoomDetailsActivity;
import com.keyuan.schoolmap.adapter.SearchEquipementAdapter1;
import com.keyuan.schoolmap.adapter.SearchEquipementAdapter2;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.entify.Values;
import com.keyuan.schoolmap.tool.RxActivityTool;
import com.keyuan.schoolmap.tool.RxToastTool;

/**
 * 设备
 * Created by zmy on 2017/12/4.
 */

public class EquipmentFragment extends BaseFragment {
    private SearchEquipementAdapter1 searchEquipementAdapter1;
    private SearchEquipementAdapter2 searchEquipementAdapter2;
    private RecyclerView recyclerview1;
    private RecyclerView recyclerview2;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_boutique;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        recyclerview1 = (RecyclerView) view.findViewById(R.id.recyclerview1);
        recyclerview2 = (RecyclerView) view.findViewById(R.id.recyclerview2);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview1.setLayoutManager(layoutManager1);
        searchEquipementAdapter1 = new SearchEquipementAdapter1();
        recyclerview1.setAdapter(searchEquipementAdapter1);
        searchEquipementAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                searchEquipementAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        for (int i = 0; i < searchEquipementAdapter1.getData().size(); i++) {
                            searchEquipementAdapter1.getData().get(i).setChoose(false);
                        }
                        searchEquipementAdapter1.getData().get(position).setChoose(true);
                        searchEquipementAdapter1.notifyDataSetChanged();
                    }
                });
            }
        });
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.addData(new Values());
        searchEquipementAdapter1.getData().get(0).setChoose(true);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview2.setLayoutManager(layoutManager2);
        searchEquipementAdapter2 = new SearchEquipementAdapter2();
        recyclerview2.setAdapter(searchEquipementAdapter2);
        searchEquipementAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < searchEquipementAdapter2.getData().size(); i++) {
                    searchEquipementAdapter2.getData().get(i).setChoose(false);
                }
                searchEquipementAdapter2.getData().get(position).setChoose(true);
                searchEquipementAdapter2.notifyDataSetChanged();
                RxActivityTool.skipActivity(getActivity(), ClassRoomDetailsActivity.class);
//                RxToastTool.showShort("暂无该教室的相关信息！");
            }
        });
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.addData(new Values());
        searchEquipementAdapter2.getData().get(4).setChoose(true);
    }
}
