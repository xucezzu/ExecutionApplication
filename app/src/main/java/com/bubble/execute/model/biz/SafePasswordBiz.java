package com.bubble.execute.model.biz;

import android.content.Context;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.SafePasswordDataRequest;
import com.bubble.execute.model.bean.SafePasswordDataResponse;
import com.bubble.execute.model.impl.ISafePasswordBiz;
import com.bubble.execute.model.listener.OnSafePasswordListener;
import com.bubble.execute.utils.ConstantUtil;
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
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class SafePasswordBiz implements ISafePasswordBiz {
    private Context mContext;

    /**
     * 存在安全密码
     */
    public static final String IS_EXIST_SAFE_PASSWORD_SUCCESS = "0";

    /**
     * 不存在安全密码
     */
    public static final String IS_EXIST_SAFE_PASSWORD_NO = "1";

    /**
     * 检测是否存在安全密码时，发现用户不存在
     */
    public static final String IS_EXIST_SAFE_PASSWORD_USER_NOT = "2";

    /**
     * 安全密码输入正确
     */
    public static final String IS_CHECK_SAFE_PASSWORD_SUCCESS = "0";

    /**
     * 安全密码输入错误
     */
    public static final String IS_CHECK_SAFE_PASSWORD_NO = "1";

    /**
     * 用户不存在
     */
    public static final String IS_CHECK_SAFE_PASSWORD_USER_NOT = "2";

    public SafePasswordBiz(Context context) {
        this.mContext = context;
    }

    /**
     * 是否存在安全密码的网络请求
     *
     * @param userId 用户ID
     */
    @Override
    public void isExistSafePassword(String userId, final OnSafePasswordListener.OnIsExistListener listener) {
        // 封装JSON数据
        SafePasswordDataRequest.IsExistSafePassword isExistSafePassword = new SafePasswordDataRequest.IsExistSafePassword();
        isExistSafePassword.setUserId(userId);
        Gson gson = new Gson();
        // 将JSON转化成String类型
        String isExistPasswordRequest = gson.toJson(isExistSafePassword);
        LogUtil.d("PostIsExistPasswordData: " + isExistPasswordRequest);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerUrl.BASE_URL)
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
                LogUtil.d("【isExistSafePassword】返回数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case IS_EXIST_SAFE_PASSWORD_SUCCESS:
                        listener.onIsExistSuccess(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case IS_EXIST_SAFE_PASSWORD_NO:
                        listener.onIsExistFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case IS_EXIST_SAFE_PASSWORD_USER_NOT:
                        listener.onIsExistFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<SafePasswordDataResponse.IsExistSafePassword> call, Throwable t) {
                LogUtil.d("【isExistSafePassword】网络请求失败！");
                listener.onIsExistFailed(ConstantUtil.NET_REQUEST_FAIL, Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }

    /**
     * 核对安全密码的网络请求
     *
     * @param userId           用户ID
     * @param userSafePassword 用户安全密码
     */
    @Override
    public void checkSafePassword(String userId, String userSafePassword, final OnSafePasswordListener.OnCheckListener listener) {
        // 封装JSON数据
        SafePasswordDataRequest.CheckSafePassword checkSafePassword = new SafePasswordDataRequest.CheckSafePassword();
        checkSafePassword.setUserId(userId);
        checkSafePassword.setUserSafePassword(userSafePassword);
        Gson gson = new Gson();
        // 将JSON转化成String类型
        String checkSafePasswordRequest = gson.toJson(checkSafePassword);
        LogUtil.d("PostCheckSafePasswordData: " + checkSafePasswordRequest);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestApi = retrofitLogin.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), checkSafePasswordRequest);
        Call<SafePasswordDataResponse.CheckSafePassword> call = requestApi.checkSafePasswordPostMsg(requestBody);
        //发送网络请求（异步）
        call.enqueue(new Callback<SafePasswordDataResponse.CheckSafePassword>() {
            @Override
            public void onResponse(Call<SafePasswordDataResponse.CheckSafePassword> call, Response<SafePasswordDataResponse.CheckSafePassword> response) {
                LogUtil.d("【checkSafePassword】网络请求成功！");
                LogUtil.d("【checkSafePassword】返回数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case IS_CHECK_SAFE_PASSWORD_SUCCESS:
                        listener.onCheckSuccess(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case IS_CHECK_SAFE_PASSWORD_NO:
                        listener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case IS_CHECK_SAFE_PASSWORD_USER_NOT:
                        listener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<SafePasswordDataResponse.CheckSafePassword> call, Throwable t) {
                LogUtil.d("【checkSafePassword】网络请求失败！");
                listener.onCheckFailed(ConstantUtil.NET_REQUEST_FAIL, Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }

    /**
     * 更新安全密码的网络请求
     *
     * @param userId           用户ID
     * @param userSafePassword 新安全密码
     */
    @Override
    public void updateSafePassword(String userId, String userSafePassword, final OnSafePasswordListener.OnUpdateListener listener) {
        // 封装JSON数据
        SafePasswordDataRequest.UpdateSafePassword updateSafePassword = new SafePasswordDataRequest.UpdateSafePassword();
        updateSafePassword.setUserId(userId);
        updateSafePassword.setUserSafePassword(userSafePassword);
        Gson gson = new Gson();
        // 将JSON转化成String类型
        String updateSafePasswordRequest = gson.toJson(updateSafePassword);
        LogUtil.d("PostUpdateSafePasswordData: " + updateSafePasswordRequest);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitLogin = new Retrofit.Builder()
                .baseUrl(ServerUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestApi = retrofitLogin.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), updateSafePasswordRequest);
        Call<SafePasswordDataResponse.UpdateSafePassword> call = requestApi.updateSafePasswordPostMsg(requestBody);
        //发送网络请求（异步）
        call.enqueue(new Callback<SafePasswordDataResponse.UpdateSafePassword>() {
            @Override
            public void onResponse(Call<SafePasswordDataResponse.UpdateSafePassword> call, Response<SafePasswordDataResponse.UpdateSafePassword> response) {
                LogUtil.d("【updateSafePassword】网络请求成功！");
            }

            @Override
            public void onFailure(Call<SafePasswordDataResponse.UpdateSafePassword> call, Throwable t) {
                LogUtil.d("【updateSafePassword】网络请求失败！");
            }
        });
    }
}
