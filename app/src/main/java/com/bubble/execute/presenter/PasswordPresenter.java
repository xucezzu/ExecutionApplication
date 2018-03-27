package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.presenter.impl.IPasswordPresenter;
import com.bubble.execute.view.impl.IPasswordActivityView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class PasswordPresenter implements IPasswordPresenter {
    private Context mContext;
    private IPasswordActivityView mIPasswordActivityView;

    public PasswordPresenter(Context context, IPasswordActivityView passwordActivityView){
        this.mContext = context;
        this.mIPasswordActivityView = passwordActivityView;

    }

    @Override
    public void isExistSafePassword() {

    }

    @Override
    public void checkSafePassword() {

    }

    @Override
    public void updateSafePassword() {

    }
}
