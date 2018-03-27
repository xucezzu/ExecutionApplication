package com.bubble.execute.utils;

import android.media.MediaCodec;

import java.util.regex.Pattern;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class ConstantUtil {
    public static final String USER_LOGIN_TYPE_UNUPDATE_DEVICEID = "0";
    public static final String USER_LOGIN_TYPE_UPDATE_DEVICEID = "1";

    /**
     * 进入LoginActivity传递的参数名称：从哪一个页面进入，0-闪屏页，1-安全密码页
     */
    public static String LOGIN_ACTIVITY_TYPE = "login_activity_type";

    public interface UserData{
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

    /**
     * 用正则表达式验证邮箱是否符合标准
     */
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    public static boolean isEmail(String mail){
        return Pattern.matches(REGEX_EMAIL, mail);
    }
}
