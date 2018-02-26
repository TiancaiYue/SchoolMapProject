package com.keyuan.schoolmap.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.keyuan.schoolmap.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list_fragment;
    private ArrayList<String> list_title;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> list_fragment, ArrayList<String> list_title) {
        super(fm);
        this.list_fragment=list_fragment;
        this.list_title=list_title;
    }

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> mFragmnetList, List<String> mList) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=list_fragment.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
