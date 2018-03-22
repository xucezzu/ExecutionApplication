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
    @Override
    public void login(String mail, String password, String phoneDeviceId, String userLoginType, OnLoginListener loginListener) {
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
                // 处理返回的结果
                LogUtil.d("返回的数据：" + response.body().toString());
            }

            @Override
            public void onFailure(Call<LoginDataResponse> call, Throwable t) {
                // 网络请求失败
            }
        });
    }
}
