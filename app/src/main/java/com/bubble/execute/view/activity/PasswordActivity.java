package com.bubble.execute.view.activity;

import android.text.InputType;

import com.bubble.execute.R;
import com.bubble.execute.widget.PasswordEditText;
import com.bubble.execute.widget.PasswordKeyboard;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/5
 * 版权所有 © 徐长策
 */

public class PasswordActivity extends BaseActivity{
    private PasswordEditText mPasswordEdit;
    private PasswordKeyboard mPasswordKeyboard;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_password);
    }

    @Override
    public void initActivity() {

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

    }
}
