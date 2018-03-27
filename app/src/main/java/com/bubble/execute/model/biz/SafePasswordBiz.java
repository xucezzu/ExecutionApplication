package com.bubble.execute.model.biz;

import android.content.Context;

import com.bubble.execute.model.bean.SafePasswordDataRequest;
import com.bubble.execute.model.bean.SafePasswordDataResponse;
import com.bubble.execute.model.impl.ISafePasswordBiz;
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
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class SafePasswordBiz implements ISafePasswordBiz{
    private Context mContext;

    public SafePasswordBiz(Context context){
        this.mContext = context;
    }

    @Override
    public void isExistSafePassword(String userId) {
        // 封装JSON数据
        SafePasswordDataRequest.IsExistSafePassword isExistSafePassword = new SafePasswordDataRequest.IsExistSafePassword();
        isExistSafePassword.setUserId(userId);
        Gson gson = new Gson();
        // 将JSON转化成String类型
        String isExistPasswordRequest = gson.toJson(isExistSafePassword);
        LogUtil.d("PostLoginData: " + isExistPasswordRequest);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestApi = retrofitLogin.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), isExistPasswordRequest);
        Call<SafePasswordDataResponse.IsExistSafePassword> call = requestApi.isExitSafePasswordPostMsg(requestBody);
        //发送网络请求（异步）
        call.enqueue(new Callback<SafePasswordDataResponse.IsExistSafePassword>() {
            @Override
            public void onResponse(Call<SafePasswordDataResponse.IsExistSafePassword> call, Response<SafePasswordDataResponse.IsExistSafePassword> response) {
                LogUtil.d("【isExistSafePassword】网络请求成功！");

            }

            @Override
            public void onFailure(Call<SafePasswordDataResponse.IsExistSafePassword> call, Throwable t) {
                LogUtil.d("【isExistSafePassword】网络请求失败！");
            }
        });
    }

    @Override
    public void checkSafePassword(String userId, String userSafePassword) {

    }

    @Override
    public void updateSafePassword(String userId, String userSafePassword) {

    }
}
