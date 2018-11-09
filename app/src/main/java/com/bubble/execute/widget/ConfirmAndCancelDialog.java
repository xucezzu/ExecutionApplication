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
public class ConfirmAndCancelDialog extends Dialog {
    private int mTheme = R.style.ConfirmCancelDialog;
    private Context mContext;
    private TextView mTextTitle, mTextMessage;
    private Button mButtonConfirm, mButtonCancel;
    private String mStringTitle, mStringMessage, mStringConfirm, mStringCancel;
    /**
     * 对话框中间文字是否居中显示
     */
    private boolean mBooleanIsCenter = true;
    private onConfirmClickListener mOnConfirmClickListener;
    private onCancelClickListener mOnCancelClickListener;

    public ConfirmAndCancelDialog(@NonNull Context context, boolean booleanIsCenter) {
        super(context);
        this.mContext = context;
        this.mBooleanIsCenter = booleanIsCenter;
    }

    public ConfirmAndCancelDialog(@NonNull Context context, int themeResId, boolean booleanIsCenter) {
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
    public void setTitleText(String title) {
        this.mStringTitle = title;
    }

    /**
     * 设置对话框中间内容
     *
     * @param message
     */
    public void setMessageText(String message) {
        this.mStringMessage = message;
    }

    /**
     * 设置按钮点击
     *
     * @param stringConfirm
     * @param confirmOkClickListener
     */
    public void setOnConfirmClickListenter(String stringConfirm, onConfirmClickListener confirmOkClickListener) {
        if (stringConfirm != null) {
            this.mStringConfirm = stringConfirm;
        }
        this.mOnConfirmClickListener = confirmOkClickListener;
    }

    public void setOnCancelClickListener(String stringCancel, onCancelClickListener cancelClickListener){
        if (stringCancel != null) {
            this.mStringCancel = stringCancel;
        }
        this.mOnCancelClickListener = cancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm_cancel_dialog);

        mTextTitle = findViewById(R.id.dialog_text_title);
        mTextMessage = findViewById(R.id.dialog_text_message);
        mButtonConfirm = findViewById(R.id.button_confirm);
        mButtonCancel = findViewById(R.id.button_cancel);
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
                if (mOnConfirmClickListener != null) {
                    mOnConfirmClickListener.onConfirmClick();
                }
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCancelClickListener != null) {
                    mOnCancelClickListener.onCancelClick();
                }
            }
        });
    }

    public interface onConfirmClickListener {
        void onConfirmClick();
    }

    public interface onCancelClickListener {
        void onCancelClick();
    }
}
