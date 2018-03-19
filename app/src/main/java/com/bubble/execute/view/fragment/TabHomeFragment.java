package com.bubble.execute.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;


/**
 * @author 徐长策
 * Date: 2018/03/01
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

public class TabHomeFragment extends Fragment {
    private ImageView mImageTitle, mImageLeft, mImageRight;
    private TextView mTextTitle;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageTitle = view.findViewById(R.id.view_image_title);
        mImageLeft = view.findViewById(R.id.view_image_left);
        mImageRight = view.findViewById(R.id.view_image_right);
        mTextTitle = view.findViewById(R.id.view_text_title);
        mTextTitle.setVisibility(View.GONE);
        mImageLeft.setVisibility(View.GONE);
        mImageRight.setVisibility(View.GONE);
        mImageTitle.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i("Fragment", "TabHomeFragment--onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("Fragment", "TabHomeFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("Fragment", "TabHomeFragment--onCreateView");
        View mHomeView = inflater.inflate(R.layout.fragment_home, container, false);
        return mHomeView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("Fragment", "TabHomeFragment--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
