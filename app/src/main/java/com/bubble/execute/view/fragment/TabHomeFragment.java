package com.bubble.execute.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bubble.execute.R;
import com.bubble.execute.presenter.HomeTaskPresenter;
import com.bubble.execute.presenter.impl.IHomeTaskPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DeviceUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.NavigationController;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.view.activity.TaskEditActivity;
import com.bubble.execute.view.adapter.HomeTaskStepAdapter;
import com.bubble.execute.view.bean.TaskStepBean;
import com.bubble.execute.view.impl.ITabHomeFragment;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;


/**
 * @author 徐长策
 * Date: 2018/03/01
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

public class TabHomeFragment extends Fragment implements ITabHomeFragment {
    private Context mContext = getActivity();

    private ImageView mImageTitle, mImageLeft, mImageRight, mImageEdit;
    private TextView mTextTitle, mTextMotto;
    private RelativeLayout mLayoutIntoEdit;
    private RecyclerView mRecyclerTask;
    private SwipeRefreshLayout mRefreshLayout;

    private HomeTaskStepAdapter mHomeTaskStepAdapter;
    private List<TaskStepBean> mTaskStepBeans;
    private IHomeTaskPresenter mIHomeTaskPresenter;
    /**
     * 刷新加载状态, 今日寄语&&任务
     */
    private boolean isRefeshMottoStatus = false, isRefeshTaskStatus = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i("Fragment", "TabHomeFragment--onViewCreated");
        mImageTitle = view.findViewById(R.id.view_image_title);
        mImageLeft = view.findViewById(R.id.view_image_left);
        mImageRight = view.findViewById(R.id.view_image_right);
        mTextTitle = view.findViewById(R.id.view_text_title);
        mTextTitle.setVisibility(View.GONE);
        mImageLeft.setVisibility(View.GONE);
        mImageRight.setImageResource(R.drawable.icon_history);
        mImageRight.setVisibility(View.VISIBLE);
        mImageTitle.setVisibility(View.VISIBLE);
        mRefreshLayout = view.findViewById(R.id.swipe_home);
        mTextMotto = view.findViewById(R.id.text_motto_content);
        // 设置RecycleView
        mRecyclerTask = view.findViewById(R.id.recycler_home_task);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerTask.setLayoutManager(layoutManager);
        // Todo 注意该《TaskStepBean》Data，赋值的地方才需要加载，这个地方属于初始化的地方，必须拿到数据才能赋值，等功能出来后再做调整
        mHomeTaskStepAdapter = new HomeTaskStepAdapter(mContext, mTaskStepBeans);
        mRecyclerTask.setAdapter(mHomeTaskStepAdapter);
        // 进入编辑页面
        mLayoutIntoEdit = view.findViewById(R.id.layout_home_edit);
        mImageEdit = view.findViewById(R.id.image_home_edit);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i("Fragment", "TabHomeFragment--onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("Fragment", "TabHomeFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("Fragment", "TabHomeFragment--onCreateView");
        mTaskStepBeans = new ArrayList<TaskStepBean>();
        mIHomeTaskPresenter = new HomeTaskPresenter(getActivity(), this);
        View mHomeView = inflater.inflate(R.layout.fragment_home, container, false);
        return mHomeView;
    }

    @SuppressLint("CheckResult")
    private void initData() {
        loadHomeData();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadHomeData();
            }
        });

        RxView.clicks(mImageEdit).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtil.d("[TabHomeFragment]跳转到编辑页面");
                toTaskEditActivity();
            }
        });

        RxView.clicks(mImageRight).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtil.d("[TabHomeFragment]跳转到历史列表页面");
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("Fragment", "TabHomeFragment--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void loadHomeData() {
        mIHomeTaskPresenter.getHomeMottoData();
        mIHomeTaskPresenter.getHomeTaskData();
        mHomeTaskStepAdapter.notifyDataSetChanged();
    }

    @Override
    public void toTaskEditActivity() {
        NavigationController.getInstance().jumpTo(TaskEditActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getTodayDateText() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    @Override
    public String getUserId() {
        return SPManager.getUserID();
    }

    @Override
    public String getDeviceId() {
        return DeviceUtil.getAndroidID(Objects.requireNonNull(getActivity()));
    }

    @Override
    public void setHomeMottoContent(String mottoContent) {
        mTextMotto.setText(ConstantUtil.ToDBC(mottoContent));
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setHomeTaskContent(String taskContent, String taskStartTime, String taskEndTime, String taskStep) {
        JSONArray array = JSONArray.parseArray(taskStep);
        TaskStepBean taskStepBean;
        mTaskStepBeans.clear();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            taskStepBean = new TaskStepBean();
            taskStepBean.setTaskStep(jsonObject.getString("taskStep"));
            taskStepBean.setTaskContent(jsonObject.getString("taskContent"));
            taskStepBean.setTaskStatus(jsonObject.getString("taskStatus"));
            mTaskStepBeans.add(taskStepBean);
        }
        mHomeTaskStepAdapter.notifyDataSetChanged();
        for (int i = 0; i < mTaskStepBeans.size(); i++) {
            LogUtil.d("获取的列表内容【" + i + "】：" + mTaskStepBeans.get(i).getTaskStep() + " | " + mTaskStepBeans.get(i).getTaskContent() + " | " + mTaskStepBeans.get(i).getTaskStatus());
        }
    }
}
