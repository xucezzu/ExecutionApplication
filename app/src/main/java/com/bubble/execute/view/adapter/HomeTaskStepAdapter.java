package com.bubble.execute.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.Util;
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

    public HomeTaskStepAdapter(Context context, List<TaskStepBean> data){
        this.mContext = context;
        this.mTaskStepBeans = data;
    }

    @NonNull
    @Override
    public TaskStepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_home, parent, false);
        return new TaskStepHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskStepHolder holder, int position) {
        holder.mTextStep.setText(Util.getResourceString(mContext, R.string.home_task_step_item_left) + mTaskStepBeans.get(position).getTaskStep());
    }

    @Override
    public int getItemCount() {
        return mTaskStepBeans.size();
    }

    class TaskStepHolder extends RecyclerView.ViewHolder {
        private TextView mTextStep;

        TaskStepHolder(View itemView) {
            super(itemView);
            mTextStep = itemView.findViewById(R.id.text_item_home_task_left);
        }
    }
}
