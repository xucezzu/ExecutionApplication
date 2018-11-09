package com.bubble.execute.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.activity.SettingActivity;
import com.bubble.execute.view.impl.ICountFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2016/10/24
 * 版权所有 © 徐长策
 */
public class TabCountFragment extends Fragment implements ICountFragment {
    private ImageView mImageSetting, mImageMessage;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageSetting = view.findViewById(R.id.count_image_setting);
        mImageMessage = view.findViewById(R.id.count_image_message);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i("TabMineFragment--onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("TabMineFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("TabMineFragment--onCreateView");
        View mHomeView = inflater.inflate(R.layout.fragment_count, container, false);
        return mHomeView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("TabMineFragment--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("CheckResult")
    private void initData() {
        RxView.clicks(mImageSetting).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                toSettingActivity();
            }
        });
    }

    @Override
    public void toSettingActivity() {
        Intent intent = new Intent(getActivity(), SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void toMessageActivity() {

    }

    @Override
    public void toUserActivity() {

    }

    @Override
    public void toHistoryDataActivity() {

    }

    @Override
    public void toFeedbackActivity() {

    }

    @Override
    public void checkUpdate() {

    }

    @Override
    public void shareOther() {

    }
}
