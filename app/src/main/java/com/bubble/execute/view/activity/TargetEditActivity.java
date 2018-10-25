package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.impl.ITargetEditActivityView;
import com.bubble.execute.widget.TargetDayEditText;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/9
 * 版权所有 © 徐长策
 */
@SuppressLint("Registered")
public class TargetEditActivity extends BaseActivity implements ITargetEditActivityView, View.OnClickListener {
    private static String TAG = TargetEditActivity.class.getName();

    private static final String EDIT_POINT = "●";
    private String endText = null;

    private ImageView mViewBack, mViewTitle, mViewRight;
    private TextView mTextTitle, mTextRight;
    private TargetDayEditText mEditTarget;
    private RelativeLayout mLayoutEdit;

    private InputMethodManager mInputMethodManager;

    private String testTextContent = "F&早晨五点半起床认真洗漱，煮一碗粥，然后去上班或做其他事情；F&早晨跑步10~15分钟，天气不好时可以选择做俯卧撑15分钟；F&要至少看书60分钟；F&务必喝至少2500ml水；F&手机娱乐时间不得多于60分钟；F&主动打扫家庭卫生；F&写一篇学习心得；F&不许喝饮料超过200ml；";

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_target_edit);
    }

    @Override
    public void initActivity() {
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert mInputMethodManager != null;
    }

    @Override
    public void initView() {
        mViewBack = findViewById(R.id.view_image_left);
        mViewRight = findViewById(R.id.view_image_right);
        mViewTitle = findViewById(R.id.view_image_title);
        mTextTitle = findViewById(R.id.view_text_title);
        mTextRight = findViewById(R.id.view_text_complete);
        mEditTarget = findViewById(R.id.edit_target);
        mLayoutEdit = findViewById(R.id.layout_edit);
        mTextRight.setVisibility(View.GONE);
        mViewTitle.setVisibility(View.GONE);
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(getResources().getText(R.string.target_day_edit_title));
        mViewRight.setVisibility(View.GONE);
        mViewBack.setOnClickListener(this);
        mTextRight.setOnClickListener(this);
        mLayoutEdit.setOnClickListener(this);
        mEditTarget.setOnClickListener(this);
        statusForNoneEditable(mEditTarget);
        mEditTarget.setText(formatTargetContentFromService(testTextContent));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initData() {
        // 监听回车键点击
        mEditTarget.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这样处理的方式是避免该判断执行两次，如果去掉“event.getAction() == KeyEvent.ACTION_UP”，则会执行两次
                // 原因，down和up占用了
                // 如果用户点击回车键则等于新增一条数据
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    LogUtil.d("点击了回车键");
                    int index = mEditTarget.getSelectionStart();
                    Editable editable = mEditTarget.getText();
                    editable.insert(index, EDIT_POINT);
                    return true;
                }
                return false;
            }
        });
        mEditTarget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mEditTarget.setOnKeyBoardHideListener(new TargetDayEditText.OnKeyBoardHideListener() {
            @Override
            public void onKeyHide(int keyCode, KeyEvent event) {
                LogUtil.d("点击实体返回键，键盘隐藏...");
            }
        });
        // 监听软键盘是否显示或隐藏，但是分屏不支持
        mLayoutEdit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mLayoutEdit.getWindowVisibleDisplayFrame(r);
                int screenHeight = mLayoutEdit.getRootView().getHeight();
                int heightDifference = screenHeight - (r.bottom);
                if (heightDifference > 200) {
                    //软键盘显示
                    LogUtil.d("键盘显示...");
                } else {
                    //软键盘隐藏
                    LogUtil.d("键盘隐藏...");
                }
            }
        });
        // 监听EditText的输入内容
        mEditTarget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.d("AfterTextChanged: " + "Editable: " + s);
                // 长度必须要大于0，因为点击
                if (s.length() > 0) {
                    if (" ".equals(s.toString().substring(s.length() - 1, s.length()))) {
                        // 不允许输入空格
                        s.delete(s.length() - 1, s.length());
                    } else if ("&".equals(s.toString().substring(s.length() - 1, s.length()))) {
                        // 为了保证条目分割，不允许出现 "F&", "f&", "T&", "t&"
                        if ("F".equals(s.toString().substring(s.length() - 2, s.length() - 1))
                                || "f".equals(s.toString().substring(s.length() - 2, s.length() - 1))
                                || "T".equals(s.toString().substring(s.length() - 2, s.length() - 1))
                                || "t".equals(s.toString().substring(s.length() - 2, s.length() - 1))) {
                            s.delete(s.length() - 2, s.length());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_image_left:
                finish();
                break;
            case R.id.view_text_complete:
                LogUtil.d("点击了完成按钮...");
                statusForNoneEditable(mEditTarget);
                break;
            case R.id.layout_edit:
                LogUtil.d("点击了LinerLayout...");
                statusForEditable(mEditTarget);
                break;
            case R.id.edit_target:
                LogUtil.d("点击了EditText...");
                statusForEditable(mEditTarget);
                break;
            default:
                break;
        }
    }

    @Override
    public String getTargetUploadData() {
        return formatTargetContentFromDevice(mEditTarget);
    }

    /**
     * EditText可编辑状态
     *
     * @param editText 传入的EditText控件
     */
    private void statusForEditable(EditText editText) {
        // 设置EditText可编辑
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        // 弹出软键盘
        mInputMethodManager.showSoftInput(editText, 0);
        // 显示光标
        editText.setCursorVisible(true);
        mTextRight.setVisibility(View.VISIBLE);
        LogUtil.d("编辑框中的数据长度：" + editText.getText().length());
        if (editText.getText().length() <= 0) {
            LogUtil.d("编辑框中无数据，弹出软键盘后，自动补点...");
            int index = editText.getSelectionStart();
            Editable editable = editText.getText();
            editable.insert(index, "●");
        }
    }

    /**
     * EditText不可编辑状态
     *
     * @param editText 传入的EditText控件
     */
    private void statusForNoneEditable(EditText editText) {
        // 设置EditText不可编辑
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        // 隐藏软键盘
        mInputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        // 隐藏EditText光标
        editText.setCursorVisible(false);
        mTextRight.setVisibility(View.GONE);
        LogUtil.d("编辑框中的数据长度：" + editText.getText().length());
        if (editText.getText().length() > 0) {
            endText = editText.getText().toString();
            LogUtil.d("编辑框的内容：\n" + endText + "\n");
            int textLen = editText.getText().length();
            if (textLen - 2 < 0) {
                editText.setText("");
            } else {
                for (int i = textLen; i > 1; i--) {
                    LogUtil.d("编辑框的第 " + i + " 个字符：" + endText.substring(i - 1, i));
                    if (EDIT_POINT.equals(endText.substring(i - 1, i))) {
                        LogUtil.d("该字符是“ ● ”");
                        if (textLen == i) {
                            LogUtil.d("该字符位置是最后一个");
                            editText.getText().delete(i - 2, i);
                        } else if ("\n".equals(endText.substring(i - 2, i - 1)) && "\n".equals(endText.substring(i, i + 1))) {
                            LogUtil.d("该字符位置是中间一个");
                            editText.getText().delete(i - 2, i);
                        }
                    }
                }
            }
        }
    }

    /**
     * 对从后台获取的数据格式化
     *
     * @param target 后台传过来的内容
     * @return 格式化后的数据
     */
    private String formatTargetContentFromService(String target) {
        String newTarget = target.replace("F&", "\n" + "●");
        return newTarget.replaceFirst("\n", "");
    }

    /**
     * 对要上传的数据进行格式化
     *
     * @param editText
     * @return
     */
    private String formatTargetContentFromDevice(EditText editText) {
        String confirmTarget = editText.getText().toString().replace("\n" + "●", "F&");
        return confirmTarget.replace("●", "F&");
    }
}