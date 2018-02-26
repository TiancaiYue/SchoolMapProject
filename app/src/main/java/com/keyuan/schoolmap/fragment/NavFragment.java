package com.keyuan.schoolmap.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.keyuan.schoolmap.R;
import com.keyuan.schoolmap.base.BaseFragment;
import com.keyuan.schoolmap.widget.NavigationButton;

import java.util.List;

public class NavFragment extends BaseFragment implements View.OnClickListener {
    private NavigationButton mNavEquipment;
    private NavigationButton mNavMap;
    private NavigationButton mNavMessage;
    private NavigationButton mNavMine;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        mNavEquipment = (NavigationButton) view.findViewById(R.id.nav_item_home);
        mNavMap = (NavigationButton) view.findViewById(R.id.nav_item_nearby);
        mNavMessage = (NavigationButton) view.findViewById(R.id.nav_item_dynamic);
        mNavMine = (NavigationButton) view.findViewById(R.id.nav_item_mine);

        mNavEquipment.init(R.drawable.tab_menu_page1,
                R.string.tab_menu_shebei,
                HomeFragment.class);
        mNavMap.init(R.drawable.tab_menu_page2,
                R.string.tab_menu_map,
                EquipmentFragment.class);
        mNavMessage.init(R.drawable.tab_menu_page3,
                R.string.tab_menu_mess,
                MessageFragment.class);
        mNavMine.init(R.drawable.tab_menu_page4,
                R.string.tab_menu_me,
                MineFragment.class);
    }

    @Override
    public void initEvent() {
        mNavEquipment.setOnClickListener(this);
        mNavMap.setOnClickListener(this);
        mNavMessage.setOnClickListener(this);
        mNavMine.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View view) {
        if (view instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) view;
            doSelect(nav);
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();
        // do select first
        doSelect(mNavEquipment);
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this && fragment != null) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }

    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}
