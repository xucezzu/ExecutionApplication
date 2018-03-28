package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.SafePasswordBiz;
import com.bubble.execute.model.impl.ISafePasswordBiz;
import com.bubble.execute.model.listener.OnSafePasswordListener;
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
    private ISafePasswordBiz mISafePasswordBiz;

    public PasswordPresenter(Context context, IPasswordActivityView passwordActivityView){
        this.mContext = context;
        this.mIPasswordActivityView = passwordActivityView;
        this.mISafePasswordBiz = new SafePasswordBiz(mContext);
    }

    @Override
    public void isExistSafePassword() {
        mISafePasswordBiz.isExistSafePassword(mIPasswordActivityView.getUserId(), new OnSafePasswordListener.OnIsExistListener() {
            @Override
            public void onIsExistSuccess(String code, String msg) {

            }

            @Override
            public void onIsExistFailed(String code, String msg) {

            }
        });
    }

    @Override
    public void checkSafePassword() {
        mISafePasswordBiz.checkSafePassword(mIPasswordActivityView.getUserId(), mIPasswordActivityView.getSafePassword(), new OnSafePasswordListener.OnCheckListener() {
            @Override
            public void onCheckSuccess(String code, String msg) {

            }

            @Override
            public void onCheckFailed(String code, String msg) {

            }
        });
    }

    @Override
    public void updateSafePassword() {
        mISafePasswordBiz.updateSafePassword(mIPasswordActivityView.getUserId(), mIPasswordActivityView.getNewSafePassword(), new OnSafePasswordListener.OnUpdateListener() {
            @Override
            public void onUpdateSuccess(String code, String msg) {

            }

            @Override
            public void onUpdateFailed(String code, String msg) {

            }
        });
    }
}
