package com.bubble.execute.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/5
 * 版权所有 © 徐长策
 */
public class ConfirmDialog extends Dialog {
    private int mTheme = R.style.ConfirmCancelDialog;
    private Context mContext;
    private TextView mTextTitle, mTextMessage;
    private Button mButtonConfirm;
    private String mStringTitle, mStringMessage, mStringConfirm;
    /**
     * 对话框中间文字是否居中显示
     */
    private boolean mBooleanIsCenter = true;
    private ConfirmDialog.onConfirmOkClickListener mOnConfirmOkClickListener;

    public ConfirmDialog(@NonNull Context context, boolean booleanIsCenter) {
        super(context);
        this.mContext = context;
        this.mBooleanIsCenter = booleanIsCenter;
    }

    public ConfirmDialog(@NonNull Context context, int themeResId, boolean booleanIsCenter) {
        super(context, themeResId);
        this.mContext = context;
        this.mTheme = themeResId;
        this.mBooleanIsCenter = booleanIsCenter;
    }

    /**
     * 设置对话框Title
     *
     * @param title
     */
    public void SetTitleText(String title) {
        this.mStringTitle = title;
    }

    /**
     * 设置对话框中间内容
     *
     * @param message
     */
    public void SetMessageText(String message) {
        this.mStringMessage = message;
    }

    /**
     * 设置按钮点击
     *
     * @param stringConfirm
     * @param confirmOkClickListener
     */
    public void SetOnConfirmOkClickListenter(String stringConfirm, ConfirmDialog.onConfirmOkClickListener confirmOkClickListener) {
        if (stringConfirm != null) {
            this.mStringConfirm = stringConfirm;
        }
        this.mOnConfirmOkClickListener = confirmOkClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm_dialog);

        mTextTitle = findViewById(R.id.dialog_text_title);
        mTextMessage = findViewById(R.id.dialog_text_message);
        mButtonConfirm = findViewById(R.id.button_confirm);
        if (mBooleanIsCenter) {
            mTextMessage.setGravity(Gravity.CENTER);
        }
        if (mStringTitle != null) {
            mTextTitle.setText(mStringTitle);
        }
        if (mStringMessage != null) {
            mTextMessage.setText(mStringMessage);
        }
        if (mStringConfirm != null) {
            mButtonConfirm.setText(mStringConfirm);
        }
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnConfirmOkClickListener != null) {
                    mOnConfirmOkClickListener.onConfirmOkClick();
                }
            }
        });
    }

    public interface onConfirmOkClickListener {
        void onConfirmOkClick();
    }
}
