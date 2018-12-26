package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.bean.HomeDataResponse;
import com.bubble.execute.model.biz.TaskBiz;
import com.bubble.execute.model.impl.ITaskBiz;
import com.bubble.execute.model.listener.OnTaskListener;
import com.bubble.execute.presenter.impl.IHomeTaskPresenter;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.impl.ITabHomeFragment;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/24
 * 版权所有 © 徐长策
 */
public class HomeTaskPresenter implements IHomeTaskPresenter {
    private Context mContext;
    private ITabHomeFragment mITabHomeFragment;
    private ITaskBiz mITaskBiz;

    public HomeTaskPresenter(Context context, ITabHomeFragment tabHomeFragment) {
        this.mContext = context;
        this.mITabHomeFragment = tabHomeFragment;
        this.mITaskBiz = new TaskBiz(mContext);
    }

    @Override
    public void getHomeMottoData() {
        mITaskBiz.getMottoData(mITabHomeFragment.getTodayDateText(), new OnTaskListener.OnTaskMottoListener() {

            @Override
            public void onSuccess(String errCode, String alertMsg, HomeDataResponse.HomeDataMottoResponse.ReturnData returnData) {
                LogUtil.d("获取今日寄语内容成功：" + returnData.getMottoContent());
                mITabHomeFragment.setHomeMottoContent(returnData.getMottoContent());
            }

            @Override
            public void onFailed(String errCode, String alertMsg) {
                LogUtil.d("获取今日寄语内容失败。。。");
                mITabHomeFragment.setHomeMottoContent("生活的本质就是简简单单，追求健康、幸福、快乐、充实，要想得到这些，就需要把生活中遇到的一切化繁为简，愿Execute APP 陪你简简单单度过每一天！");
            }
        });
    }

    @Override
    public void getHomeTaskData() {
        mITaskBiz.getHomeTaskData(mITabHomeFragment.getUserId(), mITabHomeFragment.getDeviceId(), new OnTaskListener.OnTaskHomeListener() {
            @Override
            public void onSuccess(String errCode, String alertMsg, HomeDataResponse.HomeDataTaskResponse.HomeTaskData returnData) {
                LogUtil.d("获取首页任务内容：" + returnData.getTaskContent() + "\n" + returnData.getTaskStartTime() + "\n" + returnData.getTaskEndTime() + "\n" + returnData.getTaskStepContent());
                mITabHomeFragment.setHomeTaskContent(returnData.getTaskContent(), returnData.getTaskStartTime(), returnData.getTaskEndTime(), returnData.getTaskStepContent());
            }

            @Override
            public void onFailed(String errCode, String alertMsg) {

            }
        });
    }
}
