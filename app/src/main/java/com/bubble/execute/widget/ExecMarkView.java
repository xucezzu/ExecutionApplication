package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bubble.execute.R;
import com.bubble.execute.thread.Priority;
import com.bubble.execute.thread.PriorityRunnable;
import com.bubble.execute.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/18
 * 版权所有 © 徐长策
 */
public class ExecMarkView extends LinearLayout {
    private Context mContext;
    private int srcEmpty, srcFill, starNum;
    private int viewWidth;
    private RecyclerView mRecyclerMark;
    private ExecMarkViewAdapter mExecMarkViewAdapter;
    private List<Boolean> mListStarStatus;

    public ExecMarkView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ExecMarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        // 引入自定义控件属性值
        initAttributeSet(mContext, attrs);
    }

    public ExecMarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initAttributeSet(Context context, @Nullable AttributeSet attrs) {
        @SuppressLint("Recycle") TypedArray execMarkType = context.obtainStyledAttributes(attrs, R.styleable.ExecMarkView);
        srcEmpty = execMarkType.getResourceId(R.styleable.ExecMarkView_srcEmpty, R.drawable.icon_star_empty);
        srcFill = execMarkType.getResourceId(R.styleable.ExecMarkView_srcFill, R.drawable.icon_star_fill);
        starNum = execMarkType.getInteger(R.styleable.ExecMarkView_starNum, 5);
        execMarkType.recycle();
        LogUtil.d("【initAttributeSet】控件Item个数：" + starNum);
        mListStarStatus = new ArrayList<Boolean>();
        for (int i = 0; i < starNum; i++) {
            if (i == 0) {
                mListStarStatus.add(true);
            } else if (i == 1) {
                mListStarStatus.add(true);
            } else {
                mListStarStatus.add(false);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取控件的宽度
        LogUtil.d("【onMeasure】控件的宽度：" + getMeasuredWidth());
        viewWidth = getMeasuredWidth();
        // 自定义View引入已存在的布局界面
        initLayoutView(mContext);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void initLayoutView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_exec_mark, this);
        mRecyclerMark = findViewById(R.id.recycler_mark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerMark.setLayoutManager(layoutManager);
        mExecMarkViewAdapter = new ExecMarkViewAdapter();
        mRecyclerMark.setAdapter(mExecMarkViewAdapter);

        mExecMarkViewAdapter.setOnStarClickListener(new OnStarClickListener() {
            @Override
            public void onClick(int position) {
                LogUtil.d("【initLayoutView - ONClick】点击了Item, 位置是：" + position);
                mListStarStatus.clear();
                for (int i = 0; i < starNum; i++) {
                    mListStarStatus.add(false);
                }
                for (int i = 0; i <= position; i++) {
                    mListStarStatus.set(i, true);
                }
                LogUtil.d("【initLayoutView - ONClick】List: " + mListStarStatus);
                mExecMarkViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }

    class ExecMarkViewAdapter extends RecyclerView.Adapter<ExecMarkViewHolder> {
        private OnStarClickListener mOnStarClickListener;

        public void setOnStarClickListener(OnStarClickListener onStarClickListener) {
            this.mOnStarClickListener = onStarClickListener;
        }

        public ExecMarkViewAdapter() {

        }

        @NonNull
        @Override
        public ExecMarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LogUtil.d("【onCreateViewHolder】加载Item");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mark_star, parent, false);
            return new ExecMarkViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ExecMarkViewHolder holder, int position) {
            // 根据星星的个数来设置Item的宽度
            LogUtil.d("【onBindViewHolder】控件Item设置的宽度: " + viewWidth + " Position: " + position);
            ViewGroup.LayoutParams params = holder.mLayoutStar.getLayoutParams();
            params.width = viewWidth / starNum;
            holder.mLayoutStar.setLayoutParams(params);

            if (mOnStarClickListener != null) {
                holder.mImageStar.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int viewPosition = holder.getLayoutPosition();
                        mOnStarClickListener.onClick(viewPosition);
                    }
                });
            }
            LogUtil.d("【onBindViewHolder】控件Item设置的布尔值: " + mListStarStatus.get(position));
            if (!mListStarStatus.get(position)) {
                holder.mImageStar.setImageResource(R.drawable.icon_star_empty);
            } else {
                holder.mImageStar.setImageResource(R.drawable.icon_star_fill);
            }
        }

        @Override
        public int getItemCount() {
            return mListStarStatus.size();
        }
    }

    /**
     * ViewHolder，来设置每个Item，每个小星t星
     */
    class ExecMarkViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLayoutStar;
        private ImageView mImageStar;

        public ExecMarkViewHolder(View itemView) {
            super(itemView);
            mLayoutStar = itemView.findViewById(R.id.layout_mark_star);
            mImageStar = itemView.findViewById(R.id.image_star);
        }
    }

    interface OnStarClickListener {
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

    class ViewThread extends PriorityRunnable{

        public ViewThread(Priority priority) {
            super(Priority.IMMEDIATE);
        }

        @Override
        public void run() {
            super.run();
            postInvalidate();
        }
    }
}
