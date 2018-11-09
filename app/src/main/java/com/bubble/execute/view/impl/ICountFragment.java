package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/10/30
 * 版权所有 © 徐长策
 */
public interface ICountFragment {
    /**
     * 跳转到设置页面
     */
    void toSettingActivity();

    /**
     * 跳转到消息页面
     */
    void toMessageActivity();

    /**
     * 跳转到用户详情页面
     */
    void toUserActivity();

    /**
     * 跳转到历史数据页面
     * 可根据不同的类型展示不同的URL
     */
    void toHistoryDataActivity();
    void toFeedbackActivity();
    void checkUpdate();
    void shareOther();
}
