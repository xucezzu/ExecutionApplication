package com.bubble.execute.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.adapter.TargetFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Author：徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2016/10/24
 * 版权所有 © 徐长策
 */
public class TabCalendarFragment extends Fragment implements View.OnClickListener {
    private static String TAG = TabCalendarFragment.class.getName();
    private static final int TARGET_PAGE_DAY = 0;
    private static final int TARGET_PAGE_DATE = 1;
    private RelativeLayout mLayoutTargetDay, mLayoutTargetDate;
    private TextView mTextTargetDay, mTextTargetDate;
    private ViewPager mViewPagerTarget;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i(TAG, "TabNoteFragment--onViewCreated");
        mLayoutTargetDay = view.findViewById(R.id.layout_target_title_day);
        mLayoutTargetDate = view.findViewById(R.id.layout_target_title_date);
        mViewPagerTarget = view.findViewById(R.id.view_pager_target);
        mTextTargetDay = view.findViewById(R.id.text_target_title_day);
        mTextTargetDate = view.findViewById(R.id.text_target_title_date);
        // 设置点击事件
        mLayoutTargetDay.setOnClickListener(this);
        mLayoutTargetDate.setOnClickListener(this);
        mViewPagerTarget.addOnPageChangeListener(new TargetPageChangeListener());
        // 添加Fragment到List中
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TabTargetDayFragment());
        fragmentList.add(new TabTargetDateFragment());
        TargetFragmentAdapter targetFragmentAdapter = new TargetFragmentAdapter(getFragmentManager(), fragmentList);
        mViewPagerTarget.setAdapter(targetFragmentAdapter);
        mViewPagerTarget.setCurrentItem(0);
        // 首次进入先设置每日必修
        mLayoutTargetDay.setBackgroundResource(R.drawable.draw_target_day_title_check_yes);
        mTextTargetDay.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorWhite));
        mLayoutTargetDate.setBackgroundResource(R.drawable.draw_target_date_title_check_not);
        mTextTargetDate.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorBlack));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "TabNoteFragment--onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "TabNoteFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i(TAG, "TabNoteFragment--onCreateView");
        View mHomeView = inflater.inflate(R.layout.fragment_calendar, container, false);
        return mHomeView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "TabNoteFragment--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_target_title_day:
                // 点击每日必修
                mLayoutTargetDay.setBackgroundResource(R.drawable.draw_target_day_title_check_yes);
                mTextTargetDay.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorWhite));
                mLayoutTargetDate.setBackgroundResource(R.drawable.draw_target_date_title_check_not);
                mTextTargetDate.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorBlack));
                mViewPagerTarget.setCurrentItem(TARGET_PAGE_DAY);
                break;
            case R.id.layout_target_title_date:
                // 点击重要事项
                mLayoutTargetDay.setBackgroundResource(R.drawable.draw_target_day_title_check_not);
                mTextTargetDay.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorBlack));
                mLayoutTargetDate.setBackgroundResource(R.drawable.draw_target_date_title_check_yes);
                mTextTargetDate.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorWhite));
                mViewPagerTarget.setCurrentItem(TARGET_PAGE_DATE);
                break;
            default:
                break;
        }
    }

    /**
     * 设置一个ViewPager的监听类，当左右滑动的时候顶部的菜单栏也跟着变化
     */
    public class TargetPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case TARGET_PAGE_DAY:
                    mLayoutTargetDay.setBackgroundResource(R.drawable.draw_target_day_title_check_yes);
                    mTextTargetDay.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorWhite));
                    mLayoutTargetDate.setBackgroundResource(R.drawable.draw_target_date_title_check_not);
                    mTextTargetDate.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorBlack));
                    break;
                case TARGET_PAGE_DATE:
                    mLayoutTargetDay.setBackgroundResource(R.drawable.draw_target_day_title_check_not);
                    mTextTargetDay.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorBlack));
                    mLayoutTargetDate.setBackgroundResource(R.drawable.draw_target_date_title_check_yes);
                    mTextTargetDate.setTextColor(Objects.requireNonNull(getContext()).getColor(R.color.colorWhite));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
