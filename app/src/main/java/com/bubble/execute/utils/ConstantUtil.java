package com.bubble.execute.utils;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class ConstantUtil {
    public static final String USER_LOGIN_TYPE_UNUPDATA_DEVICEID = "0";
    public static final String USER_LOGIN_TYPE_UPDATA_DEVICEID = "1";

    public interface UserData{
        /**
         * 用户的账户
         */
        String USER_MAIL = "User_Mail";

        /**
         * 用户密码
         */
        String USER_PASSWORD = "User_Password";
    }
}
