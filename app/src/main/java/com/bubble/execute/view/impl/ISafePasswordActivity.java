package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface ISafePasswordActivity {
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
     * 跳转到下一个页面
     * 根据登录结果返回值，来决定跳转
     */
    void toNextActivity();

    /**
     * 显示数据加载对话框
     */
    void showLoadingDataDialog();

    /**
     * 关闭数据加载对哈UK阿UN个
     */
    void dismissLoadingDataDialog();

    /**
     * 获取查询IsExistSafePassword接口返回的数据
     * @param code
     * @param msg
     */
    void getIsExistReturnData(String code, String msg);

    /**
     * 获取CheckSafePassword接口返回的数据
     * @param code
     * @param msg
     */
    void getCheckReturnData(String code, String msg);

    /**
     * 获取UpdateSafePassword接口返回的数据
     * @param code
     * @param msg
     */
    void getUpdateReturnData(String code, String msg);
}
