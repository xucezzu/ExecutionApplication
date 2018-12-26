package com.bubble.execute.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.bubble.execute.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class ConstantUtil {
    /**
     * 联网失败的返回码
     */
    public static final String NET_REQUEST_FAIL = "404";

    /**
     * 密码字段的最小长度
     */
    public static final int PASSWORD_LEN = 8;

    public static final String USER_LOGIN_TYPE_UN_UPDATE_DEVICE_ID = "0";
    public static final String USER_LOGIN_TYPE_UPDATE_DEVICE_ID = "1";

    /**
     * 进入LoginActivity传递的参数名称：从哪一个页面进入，0-闪屏页，1-安全密码页
     */
    public static String LOGIN_ACTIVITY_TYPE = "login_activity_type";

    /**
     * 进入PasswordActivity传递的参数名称：从哪一个页面进入，0-闪屏页，1-登录页面，2-设置页面
     */
    public static String PASSWORD_ACTIVITY_TYPE = "password_activity_type";

    /**
     * 进入MainActivity传递的参数名称：从哪一个页面进入，0-登录界面，1-安全密码页
     */
    public static String MAIN_ACTIVITY_TYPE = "main_activity_type";

    /**
     * PasswordActivity
     */
    public static String PASSWORD_ACTIVITY = "PasswordActivity";

    /**
     * 验证码发送的类型
     */
    public interface IdentifyCodeType {
        /**
         * 注册成功之后的验证
         */
        String TYPE_FROM_REGISTER = "0";

        /**
         * 忘记密码
         */
        String TYPE_FROM_FORGET_PASSWORD = "1";
    }

    public interface CheckMailType {
        /**
         * 不做任何操作，单纯验证邮箱
         */
        String TYPE_FROM_NONE = "0";

        /**
         * 忘记密码时验证邮箱，如果存在则发送验证码
         */
        String TYPE_FROM_FORGET_PASSWORD = "1";
    }

    public interface UserData {
        /**
         * 用户ID
         */
        String USER_ID = "User_ID";

        /**
         * 用户的账户
         */
        String USER_MAIL = "User_Mail";

        /**
         * 用户密码
         */
        String USER_PASSWORD = "User_Password";
    }

    public interface GlobalVariableKey {
        String INTENT_BUNDLE_NAME = "Intent_bundle_name";
    }

    /**
     * 用正则表达式验证邮箱是否符合标准
     */

    public static boolean isEmail(String mail) {
        String regexEmail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        return Pattern.matches(regexEmail, mail);
    }

    /**
     * 校验密码是否符合规则
     * 1.不能包含中文字符
     * 2.必须包含数字、字母、特殊字符中的至少两种    
     * 3.长度至少8位
     * 4.不能包含空格、制表符、换页符等空白字符
     */
    public static boolean checkPassword(Context context, String password) throws EmptyStackException {
        if (password == null || password.length() == 0) {
            StyleableToast.makeText(context, "密码字段不能为空", Toast.LENGTH_LONG, R.style.AppDefaultToast).show();
            return false;
        }
        // 包含中文字符正则表达式
        String containChinese = "[\u4E00-\u9FA5]|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】";
        Pattern pattern = Pattern.compile(containChinese);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            StyleableToast.makeText(context, "密码字段不能包含中文字符", Toast.LENGTH_LONG, R.style.AppDefaultToast).show();
            return false;
        }
        // 密码长度至少8位
        if (password.length() < PASSWORD_LEN) {
            StyleableToast.makeText(context, "密码字段长度不能少于8位", Toast.LENGTH_LONG, R.style.AppDefaultToast).show();
            return false;
        }
        // 密码中至少要包含两种类型字符
        String containLetter = "^.*[a-zA-Z]+.*$";
        String containNumber = "^.*[0-9]+.*$";
        String containSymbol = "^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$";
        boolean isLetterNumber = password.matches(containLetter) && password.matches(containNumber);
        boolean isLetterSymbol = password.matches(containLetter) && password.matches(containSymbol);
        boolean isNumberSymbol = password.matches(containNumber) && password.matches(containSymbol);
        if (isLetterNumber || isLetterSymbol || isNumberSymbol) {
            return true;
        } else {
            StyleableToast.makeText(context, "密码字段不能低于两种字符类型", Toast.LENGTH_LONG, R.style.AppDefaultToast).show();
            return false;
        }
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     * 2004-2-30 是无效的
     * 2003-2-29 是无效的
     *
     * @param sDate
     * @return
     */
    public static boolean isLegalDate(String sDate) {
        int legalLen = 10;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }

        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 转成全角字符
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i< c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }if (c[i]> 65280&& c[i]< 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    public static long dateToStamp(String date){
        long timeStamp = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd EEE HH:mm");
        try {
            timeStamp = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }
}
