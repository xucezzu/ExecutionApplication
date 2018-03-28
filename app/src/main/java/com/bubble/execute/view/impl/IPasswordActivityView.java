package com.bubble.execute.view.impl;

/**
 * @Author：徐长策 E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface IPasswordActivityView {
    /**
     * 获取到用户的ID
     * @return
     */
    String getUserId();

    /**
     * 获取到用户输入的安全密码
     * @return
     */
    String getSafePassword();

    /**
     * 获取到用户输入的新安全密码
     * @return
     */
    String getNewSafePassword();

    /**
     * 跳转到主页面
     */
    void toMainActivity();

    /**
     * 跳转到登录注册界面
     */
    void toLoginActivity();

    /**
     * 显示数据加载对话框
     */
    void showLoadingDataDialog();

    /**
     * 关闭数据加载对哈UK阿UN个
     */
    void dismissLoadingDataDialog();
}
