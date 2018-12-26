package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/26
 * 版权所有 © 徐长策
 */
public interface ITabHomeFragment {
    /**
     * 加载Home页面数据
     */
    void loadHomeData();

    /**
     * 跳转到任务编辑页面
     */
    void toTaskEditActivity();

    /**
     * 获取当前的日期
     *
     * @return
     */
    String getTodayDateText();

    /**
     * 获取UserID
     *
     * @return
     */
    String getUserId();

    /**
     * 获取DeviceID
     *
     * @return
     */
    String getDeviceId();

    /**
     * 设置今日寄语
     *
     * @param mottoContent
     */
    void setHomeMottoContent(String mottoContent);

    /**
     * 设置任务内容
     *
     * @param taskContent
     * @param taskStartTime
     * @param taskEndTime
     * @param taskStep
     */
    void setHomeTaskContent(String taskContent, String taskStartTime, String taskEndTime, String taskStep);
}
