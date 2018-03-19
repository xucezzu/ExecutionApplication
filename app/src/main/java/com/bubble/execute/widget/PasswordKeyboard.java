package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;

import java.util.List;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/6
 * 版权所有 © 徐长策
 */

public class PasswordKeyboard extends KeyboardView implements KeyboardView.OnKeyboardActionListener {
    /**
     * 用于区分左下角取消按键
     */
    private static final int KEYCODE_NEW_CANCEL = -10;
    /**
     * 删除按键的背景颜色
     */
    private int mDeleteBackgroundColor;
    /**
     * 取消按键的背景颜色
     */
    private int mCancelBackgroundColor;
    /**
     * 定义一个矩形画删除按键背景
     */
    private Rect mDeleteDrawRect;
    /**
     * 定义一个矩形画取消按键背景
     */
    private Rect mCancelDrawRect;
    /**
     * 定义一个实心画笔
     */
    private Paint mPaintFill;
    private Drawable mDeleteDrawable;

    private IOnKeyboardListener mOnKeyboardListener;

    public PasswordKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PasswordKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PasswordKeyboardView, 0, 0);
        mDeleteDrawable = a.getDrawable(R.styleable.PasswordKeyboardView_pkvDeleteDrawable);
        mDeleteBackgroundColor = a.getColor(R.styleable.PasswordKeyboardView_pkvDeleteBackgroundColor, Color.TRANSPARENT);
        mCancelBackgroundColor = a.getColor(R.styleable.PasswordKeyboardView_pkvCancelBackgroundColor, Color.TRANSPARENT);
        a.recycle();

        // 设置软键盘按键的布局
        Keyboard keyboard = new Keyboard(context, R.xml.keyboard_number);
        setKeyboard(keyboard);
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(this);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 遍历所有按键
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            // 如果是左下角空白的按键，重画按键的背景
            if (key.codes[0] == KEYCODE_NEW_CANCEL) {
                drawKeyBackground(key, canvas, mCancelBackgroundColor);
                drawCancelButton(key, canvas);
            }
            // 如果是右下角的删除按键，重画背景，并且绘制删除的图标
            else if (key.codes[0] == Keyboard.KEYCODE_DELETE) {
                drawKeyBackground(key, canvas, mDeleteBackgroundColor);
                drawDeleteButton(key, canvas);
            }
        }
    }

    /**
     * 绘制按键的背景
     */
    private void drawKeyBackground(Keyboard.Key key, Canvas canvas, int color) {
        ColorDrawable drawable = new ColorDrawable(color);
        drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    /**
     * 绘制删除按键
     */
    private void drawDeleteButton(Keyboard.Key key, Canvas canvas) {
        if (mDeleteDrawable == null) {
            return;
        }

        // 计算删除图标绘制的坐标
        if (mDeleteDrawRect == null || mDeleteDrawRect.isEmpty()) {
            int intrinsicWidth = mDeleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = mDeleteDrawable.getIntrinsicHeight();
            int drawWidth = intrinsicWidth;
            int drawHeight = intrinsicHeight;

            // 限制图标的大小，防止图标超出按键
            if (drawWidth > key.width) {
                drawWidth = key.width;
                drawHeight = drawWidth * intrinsicHeight / intrinsicWidth;
            }
            if (drawHeight > key.height) {
                drawHeight = key.height;
                drawWidth = drawHeight * intrinsicWidth / intrinsicHeight;
            }

            // 获取删除图标绘制的坐标
            int left = key.x + (key.width - drawWidth) / 2;
            int top = key.y + (key.height - drawHeight) / 2;
            mDeleteDrawRect = new Rect(left, top, left + drawWidth, top + drawHeight);
        }

        // 绘制删除的图标
        if (mDeleteDrawRect != null && !mDeleteDrawRect.isEmpty()) {
            mDeleteDrawable.setBounds(mDeleteDrawRect.left + 60, mDeleteDrawRect.top + 55, mDeleteDrawRect.right - 60, mDeleteDrawRect.bottom - 55);
            mDeleteDrawable.draw(canvas);
        }
    }

    /**
     * 绘制取消按键
     */
    private void drawCancelButton(Keyboard.Key key, Canvas canvas) {
        // 需要根据字体大小来设置
        int left = key.x + key.width / 2 - 48;
        int top = key.y + key.height / 2 + 24;
        // KEY-X: 0   Left:159     KEY-Y:723     Top:838     KEY—WIDTH: 329    KEY-HEIGHT: 240
        LogUtil.d("KEY-X: " + key.x + " Left:" + left + " KEY-Y:" + key.y + " Top:" + top + " KEY—WIDTH: " + key.width + " KEY-HEIGHT: " + key.height);
        mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFill.setColor(getContext().getResources().getColor(R.color.colorBlack));
        mPaintFill.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintFill.setStrokeWidth(0);
        mPaintFill.setTextSize(48);
        canvas.drawText("取消", left, top, mPaintFill);
    }

    @Override
    public void onPress(int primaryCode) {
        // 处理按键的点击事件
        if (primaryCode == Keyboard.KEYCODE_DELETE) {
            // 点击删除按键
            LogUtil.d("Code: " + primaryCode + " 点击了删除按钮");
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onDeleteKeyEvent();
            }
        } else if (primaryCode == KEYCODE_NEW_CANCEL) {
            // 点击取消按键
            LogUtil.d("Code: " + primaryCode + " 点击了取消按钮");
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onCancelKeyEvent();
            }
        } else {
            // 点击了非左右下角按键的其他按键
            LogUtil.d("Code: " + primaryCode + " 点击了其他按钮");
            if (mOnKeyboardListener != null) {
                mOnKeyboardListener.onInsertKeyEvent(Character.toString((char) primaryCode));
            }
        }
    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    /**
     * 设置键盘的监听事件。
     *
     * @param listener 监听事件
     */
    public void setIOnKeyboardListener(IOnKeyboardListener listener) {
        this.mOnKeyboardListener = listener;
    }

    public interface IOnKeyboardListener {
        /**
         * 点击数字键
         * @param text
         */
        void onInsertKeyEvent(String text);

        /**
         * 点击删除键
         */
        void onDeleteKeyEvent();

        /**
         * 点击取消键
         */
        void onCancelKeyEvent();
    }
}
