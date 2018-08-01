package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.adapter.FragmentViewPagerAdapter;
import com.bubble.execute.view.fragment.TabCalendarFragment;
import com.bubble.execute.view.fragment.TabCountFragment;
import com.bubble.execute.view.fragment.TabHomeFragment;
import com.bubble.execute.widget.ExecRadioButton;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * Date: 2018/02/28
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

public class MainActivity extends BaseActivity {
    private final int FRAGMENT_HOME_BUTTON = 1;
    private RadioGroup mRadioGroup;
    private ExecRadioButton mRadioCalenda, mRadioHome, mRadioCount;
    private int mContent;
    /**
     * 当前显示的Fragment页面
     */
    private int mCurrentFragment;
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
        mRadioCalenda = findViewById(R.id.tab_radio_note);
        mRadioHome = findViewById(R.id.tab_radio_home);
        mRadioCount = findViewById(R.id.tab_radio_mine);
        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(this, mRadioGroup, mFragments, mContent, mOnClickListener);
        // 获取首次进来的默认Fragment页面
        mCurrentFragment = mFragmentViewPagerAdapter.getCurrentTab();
        // 监听当前Fragment页面变化
        mFragmentViewPagerAdapter.setOnCurrentTabListener(new FragmentViewPagerAdapter.OnCurrentTabListener() {
            @Override
            public void onCurrentTab(int currentTab) {
                // 设置当前的Fragment页面
                mCurrentFragment = currentTab;
            }
        });
        doVoiceTabButton();
    }

    /**
     * 监听RadioButton的点击事件
     */
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tab_radio_note:
                    LogUtil.d("点击位置【MainActivity】" + mCurrentFragment);
                    break;

                case R.id.tab_radio_home:
                    LogUtil.d("点击位置【MainActivity】" + mCurrentFragment);
                    break;

                case R.id.tab_radio_mine:
                    LogUtil.d("点击位置【MainActivity】" + mCurrentFragment);
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 长摁HOME键开始监听语音
     */
    @SuppressLint("CheckResult")
    private void doVoiceTabButton() {
        RxView.longClicks(mRadioHome)
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (FRAGMENT_HOME_BUTTON == mCurrentFragment) {
                            LogUtil.d("我可以长摁HOME键。。。");
                        } else {
                            LogUtil.d("我不可以长摁HOME键。。。");
                        }
                    }
                });
    }
}
