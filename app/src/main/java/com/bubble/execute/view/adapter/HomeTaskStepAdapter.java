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
        LogUtil.d("Position: " + position + mTaskStepBeans.get(position).getTaskStep());
        holder.mTextStepNumber.setText("Step." + mTaskStepBeans.get(position).getTaskStep());
        holder.mTextStepContent.setEnabled(false);
        holder.mTextStepContent.setText(ConstantUtil.ToDBC(mTaskStepBeans.get(position).getTaskContent()));
    }

    @Override
    public int getItemCount() {
        return mTaskStepBeans.size();
    }

    class TaskStepHolder extends RecyclerView.ViewHolder {
        private TextView mTextStepNumber;
        private TextView mTextStepContent;

        TaskStepHolder(View itemView) {
            super(itemView);
            mTextStepNumber = itemView.findViewById(R.id.text_item_home_task_left);
            mTextStepContent = itemView.findViewById(R.id.task_content);
        }
    }
}
