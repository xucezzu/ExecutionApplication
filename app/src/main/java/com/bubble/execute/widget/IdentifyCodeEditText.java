package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.bubble.execute.R;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/10/26
 * 版权所有 © 徐长策
 */
@SuppressLint("AppCompatCustomView")
public class IdentifyCodeEditText extends EditText {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 一个密码所占的宽度
     */
    private int mPasswordItemWidth;
    /**
     * 密码的个数默认为4位数
     */
    private int mPasswordNumber = 6;
    /**
     * 背景边框颜色
     */
    private int mBgColor = Color.parseColor("#d1d2d6");
    /**
     * 下划线颜色: 黑色
     */
    private int mUnderLineColor = Color.parseColor("#000000");
    /**
     * 背景边框大小
     */
    private int mBgSize = 1;
    /**
     * 背景边框圆角大小
     */
    private int mBgCorner = 0;
    /**
     * 分割线的颜色
     */
    private int mDivisionLineColor = mBgColor;
    /**
     * 分割线的大小
     */
    private int mDivisionLineSize = 1;
    /**
     * 密码圆点的颜色
     */
    private int mPasswordColor = mDivisionLineColor;
    /**
     * 密码圆点的半径大小
     */
    private int mPasswordRadius = 4;
    public IdentifyCodeEditText(Context context) {
        super(context);
    }

    public IdentifyCodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAttributeSet(context, attrs);
        // 设置输入模式是密码
        setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        // 不显示光标
        setCursorVisible(false);
    }

    /**
     * 初始化属性
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IdentifyCodeEditText);
        // 获取大小
        mDivisionLineSize = (int) array.getDimension(R.styleable.IdentifyCodeEditText_codeDivisionLineSize, dip2px(mDivisionLineSize));
        mBgSize = (int) array.getDimension(R.styleable.IdentifyCodeEditText_codeBgSize, dip2px(mBgSize));
        mBgCorner = (int) array.getDimension(R.styleable.IdentifyCodeEditText_codeBgCorner, 0);
        // 获取颜色
        mBgColor = array.getColor(R.styleable.IdentifyCodeEditText_codeBgColor, mBgColor);
        mDivisionLineColor = array.getColor(R.styleable.IdentifyCodeEditText_codeDivisionLineColor, mDivisionLineColor);
        mPasswordColor = array.getColor(R.styleable.IdentifyCodeEditText_codeColor, Color.BLACK);
        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    /**
     * dip 转 px
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int passwordWidth = getWidth() - (mPasswordNumber - 1) * mDivisionLineSize;
        mPasswordItemWidth = passwordWidth / mPasswordNumber;
        // 绘制背景
//        drawBg(canvas);
        // 绘制分割线
//        drawDivisionLine(canvas);
        //绘制下划线
        drawUnderLine(canvas);
        // 绘制密码
        drawHidePassword(canvas);
    }

    /**
     * 绘制输入框的下划线
     */
    private void drawUnderLine(Canvas canvas) {
        mPaint.setColor(mUnderLineColor);
        // 设置画笔为实心
        mPaint.setStyle(Paint.Style.FILL);
        // 下划线的粗细
        int underLineSize = 4;
        mPaint.setStrokeWidth(underLineSize);
        int everySize = getWidth() / 6;
        // 每条线的左右间隔距离
        int everySpace = 30;
        for (int i = mPasswordNumber; i > 0; i--) {
            canvas.drawLine(getWidth() - i * everySize + everySpace, getHeight(), getWidth() - (i-1) * everySize - everySpace, getHeight(), mPaint);
        }

    }

    /**
     * 绘制背景
     */
    private void drawBg(Canvas canvas) {
        mPaint.setColor(mBgColor);
        // 设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBgSize);
        RectF rectF = new RectF(mBgSize, mBgSize, getWidth() - mBgSize, getHeight() - mBgSize);
        // 如果没有设置圆角，就画矩形
        if (mBgCorner == 0) {
            canvas.drawRect(rectF, mPaint);
        } else {
            // 如果有设置圆角就画圆矩形
            canvas.drawRoundRect(rectF, mBgCorner, mBgCorner, mPaint);
        }
    }

    /**
     * 绘制隐藏的密码
     */
    private void drawHidePassword(Canvas canvas) {
        int passwordLength = getText().length();
        mPaint.setColor(mPasswordColor);
        // 设置画笔为实心
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < passwordLength; i++) {
            int cx = i * mDivisionLineSize + i * mPasswordItemWidth + mPasswordItemWidth / 2 + mBgSize;
            canvas.drawCircle(cx, getHeight() / 2, mPasswordRadius, mPaint);
        }
    }

    /**
     * 绘制分割线
     */
    private void drawDivisionLine(Canvas canvas) {
        mPaint.setStrokeWidth(mDivisionLineSize);
        mPaint.setColor(mDivisionLineColor);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            int startX = (i + 1) * mDivisionLineSize + (i + 1) * mPasswordItemWidth + mBgSize;
            canvas.drawLine(startX, mBgSize, startX, getHeight() - mBgSize, mPaint);
        }
    }
}
