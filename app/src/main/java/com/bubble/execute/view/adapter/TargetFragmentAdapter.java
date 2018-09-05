package com.bubble.execute.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/3
 * 版权所有 © 徐长策
 */
public class TargetFragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;

    public TargetFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
