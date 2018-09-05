package com.bubble.execute.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/8/31
 * 版权所有 © 徐长策
 */
public class TabTargetDateFragment extends Fragment {
    private static String TAG = TabTargetDateFragment.class.getName();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mTargetDateView = inflater.inflate(R.layout.fragment_target_date, container, false);
        return mTargetDateView;
    }
}
