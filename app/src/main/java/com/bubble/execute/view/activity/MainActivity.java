package com.bubble.execute.view.activity;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.bubble.execute.R;
import com.bubble.execute.view.adapter.FragmentViewPagerAdapter;
import com.bubble.execute.view.fragment.TabCalendarFragment;
import com.bubble.execute.view.fragment.TabCountFragment;
import com.bubble.execute.view.fragment.TabHomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐长策
 * Date: 2018/02/28
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

public class MainActivity extends BaseActivity {
    private RadioGroup mRadioGroup;
    private int mContent;
    public List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentViewPagerAdapter mFragmentViewPagerAdapter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initActivity() {

    }

    @Override
    public void initView() {
        initFragment();
    }

    @Override
    public void initData() {

    }

    public void initFragment() {
        mContent = R.id.tab_content;

        mFragments.add(new TabCalendarFragment());
        mFragments.add(new TabHomeFragment());
        mFragments.add(new TabCountFragment());

        mRadioGroup = findViewById(R.id.tabs_radio_group);
        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(this, mRadioGroup, mFragments, mContent);
    }
}
