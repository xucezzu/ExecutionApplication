package com.bubble.execute.view.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;

import com.bubble.execute.R;
import com.bubble.execute.model.biz.SafePasswordBiz;
import com.bubble.execute.presenter.PasswordPresenter;
import com.bubble.execute.presenter.impl.IPasswordPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DialogUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.SpManager;
import com.bubble.execute.view.impl.IPasswordActivityView;
import com.bubble.execute.widget.PasswordEditText;
import com.bubble.execute.widget.PasswordKeyboard;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

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
    private static final int IS_FROM_SPLASH = 0, IS_FROM_LOGIN = 1, IS_FROM_SETTING = 2, IS_FROM_SCREEN = 3;

    /**
     * 密码长度
     */
    private static final int SAFE_PASS_LEN = 4;

    /**
     * 通过密码输入框得到的密码
     */
    private String checkSafePassword = "";
    private String updateSafePassword = "";

    /**
     * 设置密码时需要验证
     */
    private String setSafePasswordOne = "";
    private String setSafePasswordTwo = "";

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
        // 密码输入框
        mPasswordEdit = getViewById(R.id.password_edit_text);
        mPasswordEdit.setInputType(InputType.TYPE_NULL);
        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtil.d("BeforeTextChanged: " + "Start: " + start + "Count: " + count + "After: " + after + "CharSequence: " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.d("OnTextChanged: " + "Start: " + start + "Count: " + count + "Before: " + before + "CharSequence: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.d("AfterTextChanged: " + "Editable: " + s);
                if (SAFE_PASS_LEN == s.length()) {
                    if (typeFromActivity == IS_FROM_SPLASH || typeFromActivity == IS_FROM_SCREEN) {
                        // 核对安全密码
                        checkSafePassword = s.toString();
                        mIPasswordPresenter.checkSafePassword();
                        mPasswordEdit.setText("");
                    } else if (typeFromActivity == IS_FROM_LOGIN || typeFromActivity == IS_FROM_SETTING) {
                        // 修改安全密码

                    }
                }
            }
        });
        // 密码键盘
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
            case IS_FROM_SPLASH:
                // 从闪屏页面进入，首先需要验证是否存在安全密码
                showLoadingDataDialog();
                mIPasswordPresenter.isExistSafePassword();
                break;

            case IS_FROM_LOGIN:
                break;

            case IS_FROM_SETTING:
                // 从设置页面进入修改安全密码，需要先核对原密码的正确性
                break;

            case IS_FROM_SCREEN:
                break;

            default:
                break;
        }
    }

    @Override
    public String getUserId() {
        return SpManager.getUserID();
    }

    @Override
    public String getSafePassword() {
        return checkSafePassword;
    }

    @Override
    public String getNewSafePassword() {
        return updateSafePassword;
    }

    @Override
    public void toNextActivity() {
        switch (typeFromActivity){
            case IS_FROM_SPLASH:
                Intent intentMain = new Intent(PasswordActivity.this, MainActivity.class);
                intentMain.putExtra(ConstantUtil.MAIN_ACTIVITY_TYPE, 1);
                startActivity(intentMain);
                finish();
                break;

            case IS_FROM_SCREEN:
                LogUtil.d("从屏幕解锁页面进入，登录成功后直接 Finish() 密码页面");
                finish();
                break;

            default:
                break;
        }

    }

    @Override
    public void showLoadingDataDialog() {
        DialogUtil.getInstance().showProgressDialog(PasswordActivity.this);
    }

    @Override
    public void dismissLoadingDataDialog() {
        DialogUtil.getInstance().dismissProgressDialog();
    }

    /**
     * 获取查询IsExistSafePassword接口返回的数据
     *
     * @param code
     * @param msg
     */
    @Override
    public void getIsExistReturnData(String code, String msg) {
        if (!SafePasswordBiz.IS_EXIST_SAFE_PASSWORD_SUCCESS.equals(code)) {
            // 如果不是验证成功，则要弹出TOAST
            showToastMsg(msg);
        }
    }

    /**
     * 获取CheckSafePassword接口返回的数据
     *
     * @param code
     * @param msg
     */
    @Override
    public void getCheckReturnData(String code, String msg) {
        switch (code) {
            case SafePasswordBiz.IS_CHECK_SAFE_PASSWORD_SUCCESS:
                break;
            case SafePasswordBiz.IS_CHECK_SAFE_PASSWORD_NO:
                showToastMsg(msg);
                break;
            case SafePasswordBiz.IS_CHECK_SAFE_PASSWORD_USER_NOT:
                showToastMsg(msg);
                break;
            default:
                break;
        }
    }

    /**
     * 获取UpdateSafePassword接口返回的数据
     *
     * @param code
     * @param msg
     */
    @Override
    public void getUpdateReturnData(String code, String msg) {

    }

    /**
     * 弹出TOAST
     *
     * @param msg 消息内容
     */
    public void showToastMsg(String msg) {
        StyleableToast.makeText(PasswordActivity.this, msg, R.style.AppDefaultToast).show();
    }

    /**
     * 忘记密码，跳转到登录页面
     */
    public void toLoginActivity() {
        Intent intent = new Intent(PasswordActivity.this, LoginActivity.class);
        intent.putExtra(ConstantUtil.LOGIN_ACTIVITY_TYPE, 1);
        startActivity(intent);
        finish();
    }
}
