package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.DialogUtil;
import com.bubble.execute.utils.NavigationController;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.utils.Util;
import com.bubble.execute.view.impl.ISettingActivity;
import com.bubble.execute.widget.ConfirmAndCancelDialog;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/10/29
 * 版权所有 © 徐长策
 */
@SuppressLint("Registered")
public class SettingActivity extends BaseActivity implements ISettingActivity {
    private Context mContext = SettingActivity.this;
    private ImageView mImageBack, mImageRight, mImageTitle;
    private TextView mTextTitle;
    private Button mButtonLogout;
    private RelativeLayout mLayoutResetPassword;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initActivity() {

    }

    @Override
    public void initView() {
        mImageBack = findViewById(R.id.view_image_left);
        mImageRight = findViewById(R.id.view_image_right);
        mImageTitle = findViewById(R.id.view_image_title);
        mTextTitle = findViewById(R.id.view_text_title);
        mImageRight.setVisibility(View.GONE);
        mImageTitle.setVisibility(View.GONE);
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(Util.getResourceString(mContext, R.string.setting_title));
        mButtonLogout = findViewById(R.id.button_setting_logout);
        mLayoutResetPassword = findViewById(R.id.layout_setting_password);
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        RxView.clicks(mImageBack).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        finish();
                    }
                });

        RxView.clicks(mButtonLogout).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        userLogout();
                    }
                });

        RxView.clicks(mLayoutResetPassword).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        toResetPasswordActivity();
                    }
                });
    }

    @Override
    public void userLogout() {
        DialogUtil.getInstance().ConfirmAndCancelDialog(mContext,
                Util.getResourceString(mContext, R.string.dialog_title_warn),
                Util.getResourceString(mContext, R.string.setting_logout_dialog_message),
                Util.getResourceString(mContext, R.string.setting_logout_dialog_confirm),
                Util.getResourceString(mContext, R.string.setting_logout_dialog_cancel),
                true, true,
                new DialogUtil.ConfirmAndCancelCallback() {
                    @Override
                    public void onConfirmClick(ConfirmAndCancelDialog dialog) {
                        SPManager.removeAllData();
                        NavigationController.getInstance().jumpTo(LoginActivity.class);
                        NavigationController.getInstance().clear();
                        finish();
                    }

                    @Override
                    public void onCancelClick(ConfirmAndCancelDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void toResetPasswordActivity() {
        NavigationController.getInstance().jumpTo(ResetPasswordActivity.class);
    }
}
