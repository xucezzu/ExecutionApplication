package com.bubble.execute.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.bubble.execute.view.activity.BaseActivity;

import java.util.ArrayList;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/10/29
 * 版权所有 © 徐长策
 */

public class NavigationController implements Application.ActivityLifecycleCallbacks {
    private static final ArrayList<Activity> activities = new ArrayList<>();

    private static class SingletonHolder {
        private static final NavigationController INSTANCE = new NavigationController();
    }

    private NavigationController() {

    }

    public static NavigationController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void add(Activity activity) {
        activities.add(activity);
    }

    public int size() {
        return activities.size();
    }

    public void pop() {
        activities.get(activities.size() - 1).finish();
        activities.remove(activities.size() - 1);
    }

    private void popToRoot() {
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = activities.get(i);
            if (activity.isTaskRoot() || (activity instanceof BaseActivity && ((BaseActivity) activity).isRoot())) {
                break;
            }

            activity.finish();
            activities.remove(i);
        }
    }

    public Activity get(int index) {
        Activity activity = null;
        if (index < activities.size()) {
            activity = activities.get(index);
        }
        return activity;
    }

    public boolean jumpTo(Activity activity) {
        boolean succeed = false;
        if (activities.contains(activity)) {
            int i = activities.indexOf(activity) + 1;
            for (; i < activities.size(); ) {
                activities.get(i).finish();
                activities.remove(i);
            }
            succeed = true;
        }
        return succeed;
    }

    public void jumpTo(Class activityClass) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            LogUtil.d("当前Activity：" + currentActivity.getLocalClassName());
            currentActivity.startActivity(new Intent(currentActivity, activityClass));
        }
    }

    public void jumpTo(Class activityClass, Bundle bundle, boolean needFinish) {
        Activity currentActivity = getCurrentActivity();
        Intent i = new Intent(currentActivity, activityClass);
        i.putExtra(ConstantUtil.GlobalVariableKey.INTENT_BUNDLE_NAME, bundle);
        currentActivity.startActivity(i);
        if (needFinish) {
            currentActivity.finish();
        }
    }

    public void jumpToForSingleTask(Class activityClass) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Intent intent = new Intent(currentActivity, activityClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            currentActivity.startActivity(intent);
        }
    }

    public boolean isContains(Activity activity) {
        return activities.contains(activity);
    }

    public void clear() {
        LogUtil.d("Activity Size: " + activities.size());
        for (int i = activities.size() - 1; i >= 0; i--) {
            LogUtil.d("Activity Name: " + activities.get(i).getComponentName());
            activities.get(i).finish();
            activities.remove(i);
        }
    }

    public void clear(int step) {
        LogUtil.d("Activity Size: " + activities.size());
        for (int i = activities.size() - 1; i >= activities.size() - step; i--) {
            activities.get(i).finish();
            activities.remove(i);
        }
    }

    /**
     * 回退几次，参数填几
     *
     * @param YouWantBackTimes 退回的步数
     */
    public void backWithTimes(int YouWantBackTimes) {
        if (YouWantBackTimes >= activities.size()) {
            popToRoot();
        } else {
            int backTimes = YouWantBackTimes - 1;
            int count = activities.size() - 1;
            for (int i = count; i >= count - backTimes; i--) {
                activities.get(i).finish();
                activities.remove(i);
            }
        }
    }


    private Activity getCurrentActivity() {
        Activity activity = null;
        if (!activities.isEmpty()) {
            activity = activities.get(activities.size() - 1);
        }
        return activity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}
