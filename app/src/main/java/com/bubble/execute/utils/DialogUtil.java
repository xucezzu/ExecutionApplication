package com.bubble.execute.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/28
 * 版权所有 © 徐长策
 */
public class DialogUtil {
    private static DialogUtil instance = new DialogUtil();

    private DialogUtil() {

    }

    public static DialogUtil getInstance() {
        return instance;
    }

    @SuppressLint("StaticFieldLeak")
    private static MaterialDialog dialog;

    /**
     * 显示数据加载的对话框，固定格式，不包含Title
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
