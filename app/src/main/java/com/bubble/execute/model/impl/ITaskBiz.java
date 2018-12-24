package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnTaskListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/22
 * 版权所有 © 徐长策
 */
public interface ITaskBiz {
    /**
     * 获取首页的“今日寄语”
     *
     * @param mottoDate
     * @param listener
     */
    void getMottoData(String mottoDate, OnTaskListener.OnTaskMottoListener listener);

    /**
     * 获取首页任务数据
     *
     * @param userId
     * @param deviceId
     * @param listener
     */
    void getHomeTaskData(String userId, String deviceId, OnTaskListener.OnTaskHomeListener listener);
}
