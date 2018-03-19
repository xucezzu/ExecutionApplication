package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;

/**
 * @author 徐长策
 * Date: 2018/02/28
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

@SuppressLint("AppCompatCustomView")
public class ExecRadioButton extends RadioButton{
    private int mDrawableSize;

    public ExecRadioButton(Context context) {
        super(context);
    }

    public ExecRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableLeft = null;
        Drawable drawableRight = null;
        Drawable drawableTop = null;
        Drawable drawableBottom = null;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExecRadioButton);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            LogUtil.d("attr:" + attr);
            switch (attr) {
                case R.styleable.ExecRadioButton_drawableSize:
                    mDrawableSize = typedArray.getDimensionPixelSize(R.styleable.ExecRadioButton_drawableSize, 50);
                    break;
                case R.styleable.ExecRadioButton_drawableTop:
                    drawableTop = typedArray.getDrawable(attr);
                    break;
                case R.styleable.ExecRadioButton_drawableRight:
                    drawableRight = typedArray.getDrawable(attr);
                    break;
                case R.styleable.ExecRadioButton_drawableBottom:
                    drawableBottom = typedArray.getDrawable(attr);
                    break;
                case R.styleable.ExecRadioButton_drawableLeft:
                    drawableLeft = typedArray.getDrawable(attr);
                    break;
                default:
                    break;
            }
        }

        typedArray.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public ExecRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
}
