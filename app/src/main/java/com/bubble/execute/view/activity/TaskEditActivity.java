package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.DialogUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.Util;
import com.bubble.execute.view.adapter.TaskStepEditAdapter;
import com.bubble.execute.view.bean.TaskStepBean;
import com.bubble.execute.view.impl.ITaskEditActivity;
import com.bubble.execute.widget.ConfirmAndCancelDialog;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/26
 * 版权所有 © 徐长策
 */
@SuppressLint("Registered")
public class TaskEditActivity extends BaseActivity implements ITaskEditActivity {
    private Context mContext = TaskEditActivity.this;
    private TaskStepEditAdapter mTaskStepEditAdapter;
    private List<TaskStepBean> mTaskStepBeanData;
    private LayoutInflater mInflater;
    private View mViewHeader, mViewFooter;

    private ImageView mImageBack, mImageRight, mImageTitle;
    private TextView mTextTitle, mTextTimeStart, mTextTimeEnd;
    private RelativeLayout mLayoutTimeStart, mLayoutTimeEnd;
    private RecyclerView mRecyclerTaskAdd;
    private Button mButtonTaskDel, mButtonTaskAdd;
    private EditText mEditTaskContent;
    private LinearLayout mLayoutContent;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_task_edit);
    }

    @Override
    public void initActivity() {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void initView() {
        // 设置Title布局
        mImageBack = findViewById(R.id.view_image_left);
        mImageRight = findViewById(R.id.view_image_right);
        mImageTitle = findViewById(R.id.view_image_title);
        mTextTitle = findViewById(R.id.view_text_title);
        mImageRight.setVisibility(View.VISIBLE);
        mImageRight.setImageResource(R.drawable.icon_task_edit_submit);
        mImageTitle.setVisibility(View.GONE);
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(Util.getResourceString(mContext, R.string.task_edit_title));
        // 设置RecyclerView布局
        mLayoutContent = findViewById(R.id.layout_content);
        mRecyclerTaskAdd = findViewById(R.id.recycler_task_edit);
        // 设置RecyclerView
        initHeaderView();
        initFooterView();
        mTaskStepBeanData = new ArrayList<TaskStepBean>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerTaskAdd.setLayoutManager(layoutManager);
        mTaskStepEditAdapter = new TaskStepEditAdapter(mContext, mTaskStepBeanData);
        mTaskStepEditAdapter.setViewHeader(mViewHeader);
        mTaskStepEditAdapter.setViewFooter(mViewFooter);
        mRecyclerTaskAdd.setAdapter(mTaskStepEditAdapter);
        mRecyclerTaskAdd.setItemAnimator(new DefaultItemAnimator());
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        RxView.clicks(mImageBack).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        DialogUtil.getInstance().ConfirmAndCancelDialog(mContext,
                                Util.getResourceString(mContext, R.string.task_edit_step_back_title),
                                Util.getResourceString(mContext, R.string.task_edit_step_back_message),
                                Util.getResourceString(mContext, R.string.task_edit_step_confirm),
                                Util.getResourceString(mContext, R.string.task_edit_step_cancel),
                                true, false, new DialogUtil.ConfirmAndCancelCallback() {
                                    @Override
                                    public void onConfirmClick(ConfirmAndCancelDialog dialog) {
                                        finish();
                                    }

                                    @Override
                                    public void onCancelClick(ConfirmAndCancelDialog dialog) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
        RxView.clicks(mImageRight).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
        RxView.clicks(mLayoutTimeStart).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        DatePickDialog dialog = new DatePickDialog(mContext);
                        dialog.setYearLimt(50);
                        dialog.setTitle("选择开始时间");
                        dialog.setType(DateType.TYPE_ALL);
                        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                        dialog.setOnChangeLisener(null);
                        dialog.setOnSureLisener(new OnSureLisener() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onSure(Date date) {
                                LogUtil.d("开始时间：" + date);
                                String dateTimeStart = new SimpleDateFormat("yyyy/MM/dd EEE HH:mm").format(date);
                                LogUtil.d("格式化时间：" + dateTimeStart);
                                mTextTimeStart.setText(dateTimeStart);
                            }
                        });
                        dialog.show();
                    }
                });
        RxView.clicks(mLayoutTimeEnd).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        DatePickDialog dialog = new DatePickDialog(mContext);
                        dialog.setYearLimt(50);
                        dialog.setTitle("选择结束时间");
                        dialog.setType(DateType.TYPE_ALL);
                        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                        dialog.setOnChangeLisener(null);
                        dialog.setOnSureLisener(new OnSureLisener() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onSure(Date date) {
                                LogUtil.d("结束时间：" + date);
                                String dateTimeEnd = new SimpleDateFormat("yyyy/MM/dd EEE HH:mm").format(date);
                                LogUtil.d("格式化时间：" + dateTimeEnd);
                                mTextTimeEnd.setText(dateTimeEnd);
                            }
                        });
                        dialog.show();
                    }
                });
        RxView.clicks(mButtonTaskDel).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
        RxView.clicks(mButtonTaskAdd).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        if (mTaskStepEditAdapter.getViewHeader() != null) {
                            mTaskStepEditAdapter.addData(mTaskStepBeanData.size());
                        }

                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            DialogUtil.getInstance().ConfirmAndCancelDialog(mContext,
                    Util.getResourceString(mContext, R.string.task_edit_step_back_title),
                    Util.getResourceString(mContext, R.string.task_edit_step_back_message),
                    Util.getResourceString(mContext, R.string.task_edit_step_confirm),
                    Util.getResourceString(mContext, R.string.task_edit_step_cancel),
                    true, false, new DialogUtil.ConfirmAndCancelCallback() {
                        @Override
                        public void onConfirmClick(ConfirmAndCancelDialog dialog) {
                            finish();
                        }

                        @Override
                        public void onCancelClick(ConfirmAndCancelDialog dialog) {
                            dialog.dismiss();
                        }
                    });
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 添加HeaderView
     */
    private void initHeaderView() {
        mViewHeader = mInflater.inflate(R.layout.item_task_edit_header, mLayoutContent, false);
        mEditTaskContent = mViewHeader.findViewById(R.id.edit_task_content);
        mLayoutTimeStart = mViewHeader.findViewById(R.id.layout_task_edit_time_start);
        mLayoutTimeEnd = mViewHeader.findViewById(R.id.layout_task_edit_time_end);
        mTextTimeStart = mViewHeader.findViewById(R.id.text_task_edit_time_start);
        mTextTimeEnd = mViewHeader.findViewById(R.id.text_task_edit_time_end);
    }

    /**
     * 添加FooterView
     */
    private void initFooterView() {
        mViewFooter = mInflater.inflate(R.layout.item_task_edit_footer, mLayoutContent, false);
        mButtonTaskDel = mViewFooter.findViewById(R.id.button_task_edit_del);
        mButtonTaskAdd = mViewFooter.findViewById(R.id.button_task_edit_add);
    }

    @Override
    public void uploadTaskData() {

    }

    @Override
    public void updataTaskData() {

    }
}
