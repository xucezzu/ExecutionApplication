package com.bubble.execute.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/29
 * 版权所有 © 徐长策
 */
public class DateForWeekPikerDialog extends BottomSheetDialog {
    private Context mContext;
    private TextView mTextTitle, mTextMessage;
    private Button mButtonConfirm, mButtonCancel;
    private String mStringTitle, mStringMessage, mStringConfirm, mStringCancel;
    private OnConfirmClickListener mOnConfirmClickListener;
    private OnCancelClickListener mOnCancelClickListener;

    public DateForWeekPikerDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public DateForWeekPikerDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    /**
     * 设置按钮点击
     *
     * @param stringConfirm
     * @param confirmClickListener
     */
    public void setOnConfirmClickListener(String stringConfirm, OnConfirmClickListener confirmClickListener) {
        if (stringConfirm != null) {
            this.mStringConfirm = stringConfirm;
        }
        this.mOnConfirmClickListener = confirmClickListener;
    }

    public void setOnCancelClickListener(String stringCancel, OnCancelClickListener cancelClickListener){
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
        mButtonConfirm = findViewById(R.id.button_confirm);
        mButtonCancel = findViewById(R.id.button_cancel);
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

    public interface OnConfirmClickListener {
        /**
         * 点击确定
         */
        void onConfirmClick();
    }

    public interface OnCancelClickListener {
        /**
         * 点击取消
         */
        void onCancelClick();
    }
}
