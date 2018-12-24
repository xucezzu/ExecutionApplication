package com.bubble.execute.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bubble.execute.R;
import com.bubble.execute.widget.ConfirmAndCancelDialog;
import com.bubble.execute.widget.ConfirmDialog;
import com.bubble.execute.widget.DateForWeekPikerDialog;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/28
 * 版权所有 © 徐长策
 */
public class DialogUtil {
    /**
     * 用volatile关键字修饰一个共享变量，保证修改的值立刻被更新
     */
    private static volatile DialogUtil sDialogUtil = null;

    private DialogUtil() {
    }

    /**
     * 双重检测
     *
     * @return
     */
    public static DialogUtil getInstance() {
        if (sDialogUtil == null) {
            synchronized (DialogUtil.class) {
                if (sDialogUtil == null) {
                    sDialogUtil = new DialogUtil();
                }
            }
        }
        return sDialogUtil;
    }

    /**
     * MaterialDialog
     */
    @SuppressLint("StaticFieldLeak")
    private static MaterialDialog dialog;

    /**
     * 只有确定按钮的Dialog
     */
    @SuppressLint("StaticFieldLeak")
    private static ConfirmDialog confirmDialog;

    /**
     * 带有取消和确定的Dialog
     */
    @SuppressLint("StaticFieldLeak")
    private static ConfirmAndCancelDialog confirmAndCancelDialog;
    private static DateForWeekPikerDialog dateForWeekPikerDialog;

    public interface ConfirmAndCancelCallback {
        /**
         * 点击提交按钮
         *
         * @param dialog
         */
        void onConfirmClick(ConfirmAndCancelDialog dialog);

        /**
         * 点击取消按钮
         *
         * @param dialog
         */
        void onCancelClick(ConfirmAndCancelDialog dialog);
    }

    public void ConfirmAndCancelDialog(Context context, String title, String message, String textConfirm, String textCancel, boolean isCenter, boolean isBackWork, final ConfirmAndCancelCallback confirmAndCancelCallback) {
        confirmAndCancelDialog = new ConfirmAndCancelDialog(context, isCenter);
        confirmAndCancelDialog.setCanceledOnTouchOutside(false);
        confirmAndCancelDialog.setTitleText(title);
        confirmAndCancelDialog.setMessageText(message);
        confirmAndCancelDialog.setCancelable(isBackWork);
        confirmAndCancelDialog.setOnConfirmClickListener(textConfirm, new ConfirmAndCancelDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClick() {
                if (confirmAndCancelCallback != null) {
                    confirmAndCancelCallback.onConfirmClick(confirmAndCancelDialog);
                }
                confirmAndCancelDialog.dismiss();
            }
        });
        confirmAndCancelDialog.setOnCancelClickListener(textCancel, new ConfirmAndCancelDialog.OnCancelClickListener() {
            @Override
            public void onCancelClick() {
                if (confirmAndCancelCallback != null) {
                    confirmAndCancelCallback.onCancelClick(confirmAndCancelDialog);
                }
                confirmAndCancelDialog.dismiss();
            }
        });
        confirmAndCancelDialog.show();
    }

    public interface ExecWeekPikerButtonCallback {

    }

    public void DateForWeekDialog(){

    }

    /**
     * 显示数据加载的对话框，固定格式，不包含Title
     *
     * @param context
     */
    public void showProgressDialog(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .content(R.string.dialog_content_loading_data)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false);

        dialog = builder.build();
        dialog.show();
    }

    /**
     * 显示数据加载的对话框，固定格式，包含Title
     *
     * @param context
     */
    public void showTitleProgressDialog(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_warn)
                .content(R.string.dialog_content_loading_data)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false);

        dialog = builder.build();
        dialog.show();
    }

    /**
     * 显示数据加载对话框，字体可变
     *
     * @param context
     * @param resString
     */
    public void showProgressDialogWithMsg(Context context, int resString) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_warn)
                .content(resString)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false);

        dialog = builder.build();
        dialog.show();
    }

    /**
     * 关闭对话框
     */
    public void dismissProgressDialog() {
        dialog.dismiss();
    }
}
