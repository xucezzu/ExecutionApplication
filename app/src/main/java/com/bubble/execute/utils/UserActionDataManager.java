package com.bubble.execute.utils;

import com.bubble.execute.thread.DefaultExecutorSupplier;
import com.bubble.execute.thread.task.ActionDataTask;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/1
 * 版权所有 © 徐长策
 * 用户的操作信息数据管理类，避免使用Android自带Log类，防止用户日志数据被Logcat命令获取到。
 * 1：创建本地文件
 * 2：获取数据并写入文件
 * 3：上传文件到数据库
 */
public class UserActionDataManager {
    /**
     * 用volatile关键字修饰一个共享变量，保证修改的值立刻被更新
     */
    private static volatile UserActionDataManager sManager = null;

    private UserActionDataManager(){}

    /**
     * 双重检测
     * @return
     */
    public static UserActionDataManager getInstance(){
        if (sManager == null) {
            synchronized (UserActionDataManager.class) {
                if (sManager == null) {
                    sManager = new UserActionDataManager();
                }
            }
        }
        return sManager;
    }

    /**
     * 创建用户操作数据保存的文件
     * [DeviceID]_action_data_[time].txt
     * 其中：
     * DeviceID为设备 DeviceID;
     * time的命名方式如：201812011435;
     */
    public void createActionDataFile(){

    }

    /**
     * 把用户的操作数据写入文件
     * 具体过程是：
     * 先把数据加密，然后写入文件
     * @param fileName
     * @param actionData
     */
    public void inputActionData(String fileName, String actionData){
        // 文件的写入需要在子线程中完成
        DefaultExecutorSupplier.getInstance().forLightWeightBackgroundTasks().submit(new ActionDataTask(fileName, actionData));
    }

    /**
     * 上传该文件到制定的服务器
     * 需要对接具体的Model，实现一个Biz类
     * @param fileName
     */
    public void postActionData(String fileName){

    }
}
