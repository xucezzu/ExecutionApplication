package com.bubble.execute.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.bean.TaskStepBean;

import java.util.List;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/25
 * 版权所有 © 徐长策
 */
public class HomeTaskStepAdapter extends RecyclerView.Adapter<HomeTaskStepAdapter.TaskStepHolder> {
    private Context mContext;
    private List<TaskStepBean> mTaskStepBeans;
    private OnTaskClickListener mOnTaskClickListener;
    private static final String STEP_STATUS_NOT_COMPLETE = "0", STEP_STATUS_HAS_COMPLETE = "1";

    public HomeTaskStepAdapter(Context context, List<TaskStepBean> data) {
        this.mContext = context;
        this.mTaskStepBeans = data;
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        mOnTaskClickListener = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskStepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_home, parent, false);
        return new TaskStepHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull final TaskStepHolder holder, int position) {
        LogUtil.d("Position: " + position + mTaskStepBeans.get(position).getTaskStep());
        holder.mTextStepNumber.setText("Step." + mTaskStepBeans.get(position).getTaskStep());
        holder.mTextStepContent.setEnabled(false);
        holder.mTextStepContent.setText(ConstantUtil.ToDBC(mTaskStepBeans.get(position).getTaskContent()));
        if (STEP_STATUS_NOT_COMPLETE.equals(mTaskStepBeans.get(position).getTaskStatus())) {
            holder.mTextStepStatus.setText("未完成");
            holder.mTextStepStatus.setTextColor(mContext.getResources().getColor(R.color.colorGray));
        } else if (STEP_STATUS_HAS_COMPLETE.equals(mTaskStepBeans.get(position).getTaskStatus())) {
            holder.mTextStepStatus.setText("已完成");
            holder.mTextStepStatus.setTextColor(mContext.getResources().getColor(R.color.colorHasComplete));
        }
        if (mOnTaskClickListener != null) {
            holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int viewPosition = holder.getLayoutPosition();
                    mOnTaskClickListener.onClick(viewPosition);
                }
            });

            holder.mLayoutItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int viewPosition = holder.getLayoutPosition();
                    mOnTaskClickListener.onLongClick(viewPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTaskStepBeans.size();
    }

    class TaskStepHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLayoutItem;
        private TextView mTextStepNumber;
        private TextView mTextStepContent;
        private TextView mTextStepStatus;

        TaskStepHolder(View itemView) {
            super(itemView);
            mLayoutItem = itemView.findViewById(R.id.layout_home_task);
            mTextStepNumber = itemView.findViewById(R.id.text_item_home_task_left);
            mTextStepContent = itemView.findViewById(R.id.task_content);
            mTextStepStatus = itemView.findViewById(R.id.text_item_home_task_left_status);
        }
    }

    interface OnTaskClickListener {
        /**
         * 点击Star
         *
         * @param position
         */
        void onClick(int position);

        /**
         * 长摁Star
         *
         * @param position
         */
        void onLongClick(int position);
    }
}
