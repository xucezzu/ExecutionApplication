package com.bubble.execute.model.biz;

import com.bubble.execute.model.bean.LoginDataRequest;
import com.bubble.execute.model.bean.LoginDataResponse;
import com.bubble.execute.model.impl.ILoginBiz;
import com.bubble.execute.model.listener.OnLoginListener;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.ServerURL;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/22
 * 版权所有 © 徐长策
 */

public class LoginBiz implements ILoginBiz {
    /**
     * 登录成功
     */
    private static final String LOGIN_SUCCESS = "0";

    /**
     * 参数值不正确
     */
    private static final String LOGIN_FAILED_VALUE = "1";

    /**
     * 账户信息不对
     */
    private static final String LOGIN_FAILED_NO_USER = "2";

    /**
     * 密码错误
     */
    private static final String LOGIN_FAILED_PASSWORD = "3";

    /**
     * 在其他设备登录
     */
    private static final String LOGIN_FAILED_DEVICE = "4";

    /**
     * 登录操作
     *
     * @param mail          用户邮箱
     * @param password      用户登录密码
     * @param phoneDeviceId 用户设备ID
     * @param userLoginType 用户登录类型
     * @param loginListener 用户登录结果监听
     */

    @Override
    public void login(String mail, String password, String phoneDeviceId, String userLoginType, final OnLoginListener loginListener) {
        // 封装JSON数据
        LoginDataRequest loginDataBean = new LoginDataRequest();
        loginDataBean.setMail(mail);
        loginDataBean.setPassword(password);
        loginDataBean.setPhoneDeviceId(phoneDeviceId);
        loginDataBean.setUserLoginType(userLoginType);
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String loginRequestJson = gson.toJson(loginDataBean);
        LogUtil.d("PostLoginData: " + loginRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestAPI requestAPI = retrofitLogin.create(RequestAPI.class);
        //
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), loginRequestJson);
        Call<LoginDataResponse> call = requestAPI.loginPostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<LoginDataResponse>() {
            @Override
            public void onResponse(Call<LoginDataResponse> call, Response<LoginDataResponse> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()){
                    case LOGIN_SUCCESS:
                        loginListener.onLoginSuccess(response.body().getAlertMsg());
                        break;
                    case LOGIN_FAILED_VALUE:
                        loginListener.onLoginFailed(response.body().getAlertMsg());
                        break;
                    case LOGIN_FAILED_NO_USER:
                        loginListener.onLoginFailed(response.body().getAlertMsg());
                        break;
                    case LOGIN_FAILED_PASSWORD:
                        loginListener.onLoginFailed(response.body().getAlertMsg());
                        break;
                    case LOGIN_FAILED_DEVICE:
                        loginListener.onLoginFailed(response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<LoginDataResponse> call, Throwable t) {
                // 网络请求失败
            }
        });
    }
}
