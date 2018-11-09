package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.SafePasswordBiz;
import com.bubble.execute.model.impl.ISafePasswordBiz;
import com.bubble.execute.model.listener.OnSafePasswordListener;
import com.bubble.execute.presenter.impl.ISafePasswordPresenter;
import com.bubble.execute.view.impl.ISafePasswordActivity;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class SafePasswordPresenter implements ISafePasswordPresenter {
    private Context mContext;
    private ISafePasswordActivity mISafePasswordActivity;
    private ISafePasswordBiz mISafePasswordBiz;

    public SafePasswordPresenter(Context context, ISafePasswordActivity safePasswordActivity) {
        this.mContext = context;
        this.mISafePasswordActivity = safePasswordActivity;
        this.mISafePasswordBiz = new SafePasswordBiz(mContext);
    }

    @Override
    public void isExistSafePassword() {
        mISafePasswordBiz.isExistSafePassword(mISafePasswordActivity.getUserId(), new OnSafePasswordListener.OnIsExistListener() {
            @Override
            public void onIsExistSuccess(String code, String msg) {
                mISafePasswordActivity.dismissLoadingDataDialog();
                mISafePasswordActivity.getIsExistReturnData(code, msg);
            }

            @Override
            public void onIsExistFailed(String code, String msg) {
                mISafePasswordActivity.dismissLoadingDataDialog();
                mISafePasswordActivity.getIsExistReturnData(code, msg);
            }
        });
    }

    @Override
    public void checkSafePassword() {
        mISafePasswordBiz.checkSafePassword(mISafePasswordActivity.getUserId(), mISafePasswordActivity.getSafePassword(), new OnSafePasswordListener.OnCheckListener() {
            @Override
            public void onCheckSuccess(String code, String msg) {
                mISafePasswordActivity.getCheckReturnData(code, msg);
                mISafePasswordActivity.toNextActivity();
            }

            @Override
            public void onCheckFailed(String code, String msg) {
                mISafePasswordActivity.getCheckReturnData(code, msg);
            }
        });
    }

    @Override
    public void updateSafePassword() {
        mISafePasswordBiz.updateSafePassword(mISafePasswordActivity.getUserId(), mISafePasswordActivity.getNewSafePassword(), new OnSafePasswordListener.OnUpdateListener() {
            @Override
            public void onUpdateSuccess(String code, String msg) {
                mISafePasswordActivity.getUpdateReturnData(code, msg);
            }

            @Override
            public void onUpdateFailed(String code, String msg) {
                mISafePasswordActivity.getUpdateReturnData(code, msg);
            }
        });
    }
}
