package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/10
 * 版权所有 © 徐长策
 */
@SuppressLint("AppCompatCustomView")
public class TargetDayEditText extends EditText {
    private OnKeyBoardHideListener onKeyBoardHideListener;

    public TargetDayEditText(Context context) {
        super(context);
    }

    public TargetDayEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TargetDayEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnKeyBoardHideListener(OnKeyBoardHideListener onKeyBoardHideListener) {
        this.onKeyBoardHideListener = onKeyBoardHideListener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            super.onKeyPreIme(keyCode, event);
            onKeyBoardHideListener.onKeyHide(keyCode, event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public interface OnKeyBoardHideListener {
        /**
         * 隐藏
         * @param keyCode
         * @param event
         */
        void onKeyHide(int keyCode, KeyEvent event);
    }
}
