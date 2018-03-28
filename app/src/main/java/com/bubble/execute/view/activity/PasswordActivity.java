package com.bubble.execute.view.activity;

import android.content.Intent;
import android.text.InputType;

import com.bubble.execute.R;
import com.bubble.execute.presenter.PasswordPresenter;
import com.bubble.execute.presenter.impl.IPasswordPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DialogUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.view.impl.IPasswordActivityView;
import com.bubble.execute.widget.PasswordEditText;
import com.bubble.execute.widget.PasswordKeyboard;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/5
 * 版权所有 © 徐长策
 */

public class PasswordActivity extends BaseActivity implements IPasswordActivityView {
    /**
     * 页面跳转类型值
     */
    private int typeFromActivity = 0;
    private static final int isFromSplash = 0, isFromLogin = 1, isFromSetting = 2;

    private PasswordEditText mPasswordEdit;
    private PasswordKeyboard mPasswordKeyboard;
    private IPasswordPresenter mIPasswordPresenter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_password);
    }

    @Override
    public void initActivity() {
        // 获取从上一个页面传递的值
        Intent intent = getIntent();
        typeFromActivity = intent.getIntExtra(ConstantUtil.PASSWORD_ACTIVITY_TYPE, 0);
        mIPasswordPresenter = new PasswordPresenter(PasswordActivity.this, this);
    }

    @Override
    public void initView() {
        mPasswordEdit = getViewById(R.id.password_edit_text);
        mPasswordEdit.setInputType(InputType.TYPE_NULL);
        mPasswordKeyboard = getViewById(R.id.keyboard);
        mPasswordKeyboard.setPreviewEnabled(false);
        mPasswordKeyboard.setIOnKeyboardListener(new PasswordKeyboard.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                LogUtil.d("输入的数字为：" + text);
                mPasswordEdit.append(text);
            }

            @Override
            public void onDeleteKeyEvent() {
                int start = mPasswordEdit.length() - 1;
                if (start >= 0) {
                    mPasswordEdit.getText().delete(start, start + 1);
                }
            }

            @Override
            public void onCancelKeyEvent() {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        //根绝值的不同是否选择去判断用户是否含有安全密码
        switch (typeFromActivity) {
            case isFromSplash:
                break;
            case isFromLogin:
                break;
            case isFromSetting:
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserId() {
        return SPManager.getUserID();
    }

    @Override
    public String getSafePassword() {
        return null;
    }

    @Override
    public String getNewSafePassword() {
        return null;
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
        intent.putExtra(ConstantUtil.MAIN_ACTIVITY_TYPE, 1);
        startActivity(intent);
        finish();
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
        intent.putExtra(ConstantUtil.LOGIN_ACTIVITY_TYPE, 1);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoadingDataDialog() {
        DialogUtil.getInstance().showProgressDialog(PasswordActivity.this);
    }

    @Override
    public void dismissLoadingDataDialog() {
        DialogUtil.getInstance().dismissProgressDialog();
    }
}
