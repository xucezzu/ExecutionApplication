package com.bubble.execute.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.view.bean.TargetDayBean;

import java.util.List;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/5
 * 版权所有 © 徐长策
 */
public class TargetDayAdapter extends RecyclerView.Adapter<TargetDayAdapter.VH> {
    /**
     * 创建ViewHolder
     */
    public static class VH extends RecyclerView.ViewHolder {
        CheckBox mCheckTargetDay;
        TextView mTextTargetDay;
        public VH(View itemView) {
            super(itemView);
            mCheckTargetDay = itemView.findViewById(R.id.check_target);
            mTextTargetDay = itemView.findViewById(R.id.text_target_content);
        }
    }

    /**
     * 声明TargetDayBean数据类型的列表
     */
    private List<TargetDayBean> mDataTargetDay;

    public TargetDayAdapter(List<TargetDayBean> data) {
        this.mDataTargetDay = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_target_day, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mCheckTargetDay.setChecked(mDataTargetDay.get(position).getIsChecked());
        holder.mTextTargetDay.setText(mDataTargetDay.get(position).getTargetDayContent());
        holder.mCheckTargetDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataTargetDay.size();
    }
}
