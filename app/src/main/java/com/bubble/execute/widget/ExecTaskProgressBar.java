package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/8/1
 * 版权所有 © 徐长策
 * 注：
 * 1：重写onMeasure()方法，为了自定义View的尺寸规则，如果不重写自定义View的尺寸默认和父控件一样
 */
@SuppressLint("ViewConstructor")
public class ExecTaskProgressBar extends ProgressBar {
    public static final int DEFAULT_UNREACHED_COLOR = 0xFF912CEE;
    public static final int DEFAULT_REACHED_COLOR = 0xFF54FF9F;
    /**
     * 定义全局Context
     */
    private Context mContext;
    /**
     * 空心画笔
     */
    private Paint mPaintStroke;
    /**
     * 控件的主体颜色
     */
    private int mViewColor;
    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;
    /**
     * 未到达进度条颜色
     */
    private int unreachedColor;
    /**
     * 已到达进度条颜色
     */
    private int reachedColor;

    public ExecTaskProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs);
    }

    public ExecTaskProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        @SuppressLint({"Recycle", "CustomViewStyleable"})
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ExecTaskProgressBarView, 0, 0);
        mViewColor = typedArray.getColor(R.styleable.ExecTaskProgressBarView_etpBackgroundColor, Color.TRANSPARENT);

        mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintStroke.setColor(mViewColor);
        mPaintStroke.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintStroke.setStrokeWidth(10);

        unreachedColor = DEFAULT_UNREACHED_COLOR;
        reachedColor = DEFAULT_REACHED_COLOR;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        // 获取画布的宽高
//        int width = getWidth();
//        int height = getHeight();
//        // 获取进度条的实际宽高
//        int lineWidth = width - getPaddingLeft() - getPaddingRight();
//        int lineHeight = height - getPaddingTop() - getPaddingBottom();
//        // 获取当前进度
//        float ratio = getProgress() * 1.0f / getMax();
//        // 获取未完成进度大小
//        int unreachedWidth = (int) (lineWidth * (1 - ratio));
//        // 获取已完成进度大小
//        int reachedWidth = lineWidth - unreachedWidth;
//        // 绘制已完成进度条，设置画笔颜色和大小
//        mPaintStroke.setColor(reachedColor);
//        mPaintStroke.setStrokeWidth(lineHeight);
//        // 计算已完成进度条起点和终点的坐标
//        int startX = getPaddingLeft();
//        int startY = getHeight() / 2;
//        int stopX = startX + reachedWidth;
//        int stopY = startY;
//        // 画线
//        canvas.drawLine(startX, startY, stopX, stopY, mPaintStroke);
//        // 设置画笔颜色
//        mPaintStroke.setColor(unreachedColor);
//
//        startX = getPaddingLeft() + reachedWidth;
//        stopX = width - getPaddingRight();
//        canvas.drawLine(startX, startY, stopX, stopY, mPaintStroke);

        // 绘制实心的条形图
        int leftFill = 167;
        int bottomFill = 100;
        int topFill = 100;

        RectF rectFill = new RectF(leftFill, topFill, 300, bottomFill);
        canvas.drawRoundRect(rectFill, 5, 5, mPaintStroke);

    }
}
