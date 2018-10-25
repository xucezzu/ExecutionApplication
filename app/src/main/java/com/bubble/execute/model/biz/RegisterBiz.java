package com.bubble.execute.model.biz;

import android.content.Context;
import android.text.TextUtils;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.RegisterDataRequest;
import com.bubble.execute.model.bean.RegisterDataResponse;
import com.bubble.execute.model.impl.IRegisterBiz;
import com.bubble.execute.model.listener.OnRegisterListener;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.ServerUrl;
import com.bubble.execute.utils.Util;
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
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public class RegisterBiz implements IRegisterBiz {
    private Context mContext;

    /**
     * 注册成功
     */
    private static final String REGISTER_SUCCESS = "0";

    /**
     * 注册失败，传入参数有误
     */
    private static final String REGISTER_FAILED_VALUE = "1";

    /**
     * 注册失败，账户已存在
     */
    private static final String REGISTER_FAILED_EXIST = "2";

    /**
     * 注册失败，密码格式不正确
     */
    private static final String REGISTER_FAILED_PASSWORD = "3";

    /**
     * 注册失败
     */
    private static final String REGISTER_FAILED = "4";

    public RegisterBiz(Context context) {
        this.mContext = context;
    }

    /**
     * @param mail             用户邮箱
     * @param password         用户登录密码
     * @param phoneDeviceId    用户设备ID
     * @param registerListener 用户登录结果监听
     */
    @Override
    public void register(String mail, String password, String phoneDeviceId, final OnRegisterListener registerListener) {
        // 封装JSON数据
        final RegisterDataRequest registerDataBean = new RegisterDataRequest();
        if (!TextUtils.isEmpty(mail) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(phoneDeviceId)) {
            registerDataBean.setMail(mail);
            registerDataBean.setPassword(password);
            registerDataBean.setPhoneDeviceId(phoneDeviceId);
        } else {
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String registerRequestJson = gson.toJson(registerDataBean);
        LogUtil.d("PostRegisterData: " + registerRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitLogin.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), registerRequestJson);
        Call<RegisterDataResponse> call = requestAPI.registerPostMsg(requestBody);
        call.enqueue(new Callback<RegisterDataResponse>() {
            @Override
            public void onResponse(Call<RegisterDataResponse> call, Response<RegisterDataResponse> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("【register】网络请求成功");
                LogUtil.d("【register】返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case REGISTER_SUCCESS:
                        registerListener.onRegisterSuccess(response.body().getAlertMsg());
                        break;
                    case REGISTER_FAILED_VALUE:
                        registerListener.onRegisterFailed(response.body().getAlertMsg());
                        break;
                    case REGISTER_FAILED_EXIST:
                        registerListener.onRegisterFailed(response.body().getAlertMsg());
                        break;
                    case REGISTER_FAILED_PASSWORD:
                        registerListener.onRegisterFailed(response.body().getAlertMsg());
                        break;
                    case REGISTER_FAILED:
                        registerListener.onRegisterFailed(response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<RegisterDataResponse> call, Throwable t) {
                // 网络请求失败
                LogUtil.d("【register】网络请求失败");
                registerListener.onRegisterFailed(Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }
}
