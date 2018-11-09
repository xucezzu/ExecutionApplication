package com.bubble.execute.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;

import static android.support.v4.content.ContextCompat.getDrawable;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/5
 * 版权所有 © 徐长策
 */

public class CancelEditText extends AppCompatEditText implements TextWatcher {
    private Drawable mDrawableClear;

    public CancelEditText(Context context) {
        super(context);
        init();
    }

    public CancelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CancelEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setClearDrawable();
    }

    @Override
    public void afterTextChanged(Editable s) {
        setClearDrawable();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 重写点击事件，划定点击的范围
        // getCompoundDrawables()是一个四个值的数组，分别代表左上右下（left/top/right/bottom），故getCompoundDrawables()[2]代表右边的图片
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                // 弄明白每个值代表的含义
                // event.getX()  -->  点击的位置距离控件X轴原点的距离
                // getWidth()  -->  获取控件的宽度
                // getTotalPaddingRight()  -->  获取图标左边缘至控件右边缘的距离
                // getPaddingRight()  -->  获取图标右边缘至控件右边缘的距离
                LogUtil.d("event.getX() : " + event.getX() + " getWidth() : " + getWidth() + " getTotalPaddingRight() : " + getTotalPaddingRight() + " getPaddingRight() : " + getPaddingRight());
                boolean touchable = (event.getX() > (getWidth() - getTotalPaddingRight())) && (event.getX() < (getWidth() - getPaddingRight()));
                if (touchable) {
                    this.setText("");
                }
            }
            this.setFocusable(true);
            this.setFocusableInTouchMode(true);
            this.requestFocus();
        }
        return super.onTouchEvent(event);
    }

    private void init() {
        mDrawableClear = getCompoundDrawables()[2];
        mDrawableClear = getDrawable(getContext(), R.drawable.icon_exit_delete);
        assert mDrawableClear != null;
        mDrawableClear.setBounds(0, 0,
                (int) (mDrawableClear.getIntrinsicWidth() * 0.25),
                (int) (mDrawableClear.getIntrinsicHeight() * 0.25));
        setClearDrawable();
        addTextChangedListener(this);
    }

    /**
     * 设置删除图片的显示
     * length(),是TextView自带的方法,判断内容有无
     */
    private void setClearDrawable() {
        if (length() < 1) {
            setClearIconVisible(false);
        } else {
            setClearIconVisible(true);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     */
    protected void setClearIconVisible(boolean visible) {
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1],
                visible ? mDrawableClear : null,
                getCompoundDrawables()[3]);
    }
}
