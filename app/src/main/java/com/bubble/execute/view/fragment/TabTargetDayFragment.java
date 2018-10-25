package com.bubble.execute.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bubble.execute.R;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.activity.TargetEditActivity;
import com.bubble.execute.view.impl.ITargetDayFragmentView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/8/31
 * 版权所有 © 徐长策
 */
public class TabTargetDayFragment extends Fragment implements ITargetDayFragmentView, View.OnClickListener {
    private static String TAG = TabTargetDayFragment.class.getName();

    private Button mButtonToEdit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i(TAG, "TabTargetDayFragment--onViewCreated");
        mButtonToEdit = view.findViewById(R.id.button_to_edit);
        mButtonToEdit.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "TabTargetDayFragment--onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "TabTargetDayFragment--onCreateView");
        View mTargetDayView = inflater.inflate(R.layout.fragment_target_day, container, false);
        return mTargetDayView;
    }

    @Override
    public void toTargetDayEditActivity() {
        Intent intent = new Intent(getContext(), TargetEditActivity.class);
        intent.putExtra(ConstantUtil.PASSWORD_ACTIVITY_TYPE, 1);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_to_edit:
                toTargetDayEditActivity();
                break;

            default:
                break;
        }
    }
}
