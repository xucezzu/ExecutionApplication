package com.bubble.execute.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;

import java.util.List;

/**
 * @author 徐长策
 * Date: 2018/02/28
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

public class FragmentViewPagerAdapter implements RadioGroup.OnCheckedChangeListener {
    private View.OnClickListener mOnClickListener;
    private OnCurrentTabListener mOnCurrentTabListener;
    private FragmentActivity mFragmentActivity;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private int fragmentId;
    /**
     * 当前Tab页面索引，默认为首页
     **/
    private int currentTab = 1;

    public FragmentViewPagerAdapter(FragmentActivity fragmentActivity, RadioGroup radioGroup,
                                    List<Fragment> fragments, int fragmentId, View.OnClickListener onClickListener) {
        this.fragmentId = fragmentId;
        this.mFragmentActivity = fragmentActivity;
        this.mRadioGroup = radioGroup;
        this.mFragments = fragments;
        this.mOnClickListener = onClickListener;

        // 默认显示首页
        FragmentTransaction fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragmentId, fragments.get(currentTab));
        fragmentTransaction.commit();

        radioGroup.setOnCheckedChangeListener(this);
        // 日志打印默认进入程序显示的Fragment页面
        LogUtil.d("当前Fragment【FragmentAdapter】:" + getCurrentTab());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            if (mRadioGroup.getChildAt(i).getId() == checkedId) {
                Fragment fragment = mFragments.get(i);
                FragmentTransaction fragmentTransaction = obtainFragmentTransaction(i);
                getCurrentFragment().onPause(); // 停止当前的Fragment,可以尝试 onStop()

                if (fragment.isAdded()) {
                    fragment.onResume();
                } else {
                    fragmentTransaction.add(fragmentId, fragment);
                }
                // 显示目标tab
                showTab(i);
                fragmentTransaction.commit();
                LogUtil.d("当前Fragment【FragmentAdapter】:" + getCurrentTab());
                mRadioGroup.getChildAt(i).setOnClickListener(mOnClickListener);
            }
        }
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction fragmentTransaction = mFragmentActivity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentTab) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return fragmentTransaction;
    }

    /**
     * 切换tab
     */
    private void showTab(int index) {
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            FragmentTransaction fragmentTransaction = obtainFragmentTransaction(index);

            if (index == i) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
            fragmentTransaction.commit();
        }
        // 更新目标tab为当前tab
        currentTab = index;
        mOnCurrentTabListener.onCurrentTab(currentTab);
    }

    public int getCurrentTab() {
        return currentTab;
    }

    private Fragment getCurrentFragment() {
        return mFragments.get(currentTab);
    }

    public interface OnCurrentTabListener {
        void onCurrentTab(int currentTab);
    }

    public void setOnCurrentTabListener(OnCurrentTabListener currentTabListener) {
        this.mOnCurrentTabListener = currentTabListener;
    }
}
