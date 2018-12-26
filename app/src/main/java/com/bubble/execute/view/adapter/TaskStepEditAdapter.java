package com.bubble.execute.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.Util;
import com.bubble.execute.view.bean.TaskStepBean;

import java.util.List;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/26
 * 版权所有 © 徐长策
 */
public class TaskStepEditAdapter extends RecyclerView.Adapter<TaskStepEditAdapter.TaskStepEditHolder> {
    /**
     * RecyclerView展示的类型
     */
    private static final int VIEW_TYPE_HEADER = 0, VIEW_TYPE_NORMAL = 1, VIEW_TYPE_FOOTER = 2;
    private Context mContext;
    private TaskStepBean mTaskStepBean;
    private List<TaskStepBean> mTaskStepBeans;
    private View mViewHeader, mViewFooter;

    public TaskStepEditAdapter(Context context, List<TaskStepBean> data) {
        this.mContext = context;
        this.mTaskStepBeans = data;
    }

    public void setViewHeader(View viewHeader) {
        mViewHeader = viewHeader;
    }

    public View getViewHeader() {
        return mViewHeader;
    }

    public void setViewFooter(View viewFooter) {
        mViewFooter = viewFooter;
    }

    public View getViewFooter() {
        return mViewFooter;
    }

    @NonNull
    @Override
    public TaskStepEditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * 走进科学：
         * 01 --> parent指的是RecyclerView
         * 02 --> LayoutInflater简单解释：
         * LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；
         * 对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入，如果已经载入，则可以使用[View].findViewById()来找到对应的控件；
         */
        LogUtil.d("父View的名称：" + parent);
        if (getViewHeader() != null && viewType == VIEW_TYPE_HEADER) {
            return new TaskStepEditAdapter.TaskStepEditHolder(mViewHeader);
        }
        if (getViewFooter() != null && viewType == VIEW_TYPE_FOOTER) {
            return new TaskStepEditAdapter.TaskStepEditHolder(mViewFooter);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_edit, parent, false);
        return new TaskStepEditAdapter.TaskStepEditHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskStepEditHolder holder, int position) {
        /**
         * RecyclerView的Position包含了Header与Footer，所以mTaskStepBeans的Position应减去1
         */
        LogUtil.d("onBindViewHolder：" + position + " 该位置的类型：" + getItemViewType(position));
        if (VIEW_TYPE_NORMAL == getItemViewType(position)) {
            holder.mTextStep.setText(Util.getResourceString(mContext, R.string.home_task_step_item_left) + mTaskStepBeans.get(position - 1).getTaskStep());
        }
    }

    @Override
    public int getItemCount() {
        if (mViewHeader == null && mViewFooter == null) {
            return mTaskStepBeans.size();
        } else if (mViewHeader == null || mViewFooter == null) {
            return mTaskStepBeans.size() + 1;
        } else {
            return mTaskStepBeans.size() + 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getViewHeader() == null && getViewFooter() == null) {
            return VIEW_TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return VIEW_TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_NORMAL;
    }

    /**
     * 添加Item
     *
     * @param position
     */
    public void addData(int position) {
        // 在List中添加数据，并展示到列表中
        mTaskStepBean = new TaskStepBean();
        mTaskStepBean.setTaskStep(String.valueOf(position + 1));
        mTaskStepBean.setTaskContent("");
        mTaskStepBean.setTaskStatus("0");
        mTaskStepBeans.add(position, mTaskStepBean);
        // 根据是否添加HeaderView来区分刷新的位置
        if (getViewHeader() != null) {
            notifyItemInserted(position + 1);
        } else {
            notifyItemInserted(position);
        }
    }

    class TaskStepEditHolder extends RecyclerView.ViewHolder {
        private TextView mTextStep;
        private EditText mEditStepContent;

        TaskStepEditHolder(View itemView) {
            super(itemView);
            if (itemView == mViewHeader) {
                return;
            }
            if (itemView == mViewFooter) {
                return;
            }
            mTextStep = itemView.findViewById(R.id.text_item_home_task_left);
            mEditStepContent = itemView.findViewById(R.id.edit_task);
            mEditStepContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            mEditStepContent.setSingleLine(false);
            mEditStepContent.setHorizontallyScrolling(false);
        }
    }
}
