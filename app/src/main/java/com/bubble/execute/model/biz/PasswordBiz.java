package com.bubble.execute.model.biz;

import android.content.Context;
import android.text.TextUtils;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.PasswordDataRequest;
import com.bubble.execute.model.bean.PasswordDataResponse;
import com.bubble.execute.model.impl.IPasswordBiz;
import com.bubble.execute.model.listener.OnPasswordListener;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.ServerURL;
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
 * Date：2018/11/7
 * 版权所有 © 徐长策
 */
public class PasswordBiz implements IPasswordBiz {
    private Context mContext;

    /**
     * 密码核对成功
     */
    private static final String CHECK_PASSWORD_SUCCESS = "0";

    /**
     * 密码核对失败
     */
    private static final String CHECK_PASSWORD_FAILD = "1";

    /**
     * 密码核对失败，用户不存在
     */
    private static final String CHECK_PASSWORD_NOT_USER = "2";

    /**
     * 密码更新成功
     */
    private static final String UPDATE_PASSWORD_SUCCESS = "0";

    /**
     * 密码更新失败
     */
    private static final String UPDATE_PASSWORD_FAILD = "1";

    /**
     * 密码更新失败，用户不存在
     */
    private static final String UPDATE_PASSWORD_NOT_USER = "2";

    /**
     * 密码重置成功
     */
    private static final String RESET_PASSWORD_SUCCESS = "0";

    /**
     * 密码重置失败
     */
    private static final String RESET_PASSWORD_FAILD = "1";

    /**
     * 密码重置失败，用户不存在
     */
    private static final String RESET_PASSWORD_NOT_MAIL = "2";

    public PasswordBiz(Context context) {
        this.mContext = context;
    }

    @Override
    public void checkUserPassword(String userId, String userPassword, final OnPasswordListener.OnCheckPasswordListener checkPasswordListener) {
        // 封装JSON数据
        PasswordDataRequest.CheckPassword checkPassword = new PasswordDataRequest.CheckPassword();
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userPassword)) {
            checkPassword.setUserId(userId);
            checkPassword.setUserPassword(userPassword);
        } else {
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String checkPasswordRequestJson = gson.toJson(checkPassword);
        LogUtil.d("PostCheckPasswordData: " + checkPasswordRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitCheckUserPassword = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitCheckUserPassword.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), checkPasswordRequestJson);
        Call<PasswordDataResponse.CheckPassword> call = requestAPI.checkPasswordPostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<PasswordDataResponse.CheckPassword>() {

            @Override
            public void onResponse(Call<PasswordDataResponse.CheckPassword> call, Response<PasswordDataResponse.CheckPassword> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("【checkPassword】网络请求成功");
                LogUtil.d("【checkPassword】返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case CHECK_PASSWORD_SUCCESS:
                        checkPasswordListener.onCheckSuccess(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case CHECK_PASSWORD_FAILD:
                        checkPasswordListener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case CHECK_PASSWORD_NOT_USER:
                        checkPasswordListener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<PasswordDataResponse.CheckPassword> call, Throwable t) {
                // 网络请求失败
                LogUtil.d("【checkPassword】网络请求失败");
                checkPasswordListener.onCheckFailed(ConstantUtil.NET_REQUEST_FAIL, Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }

    @Override
    public void updateUserPassword(String userId, String userNewPassword, String userNewAgainPassword, final OnPasswordListener.OnUpdatePasswordListener updatePasswordListener) {
        // 封装JSON数据
        PasswordDataRequest.UpdatePassword updatePassword = new PasswordDataRequest.UpdatePassword();
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userNewPassword) && !TextUtils.isEmpty(userNewAgainPassword)) {
            updatePassword.setUserId(userId);
            updatePassword.setUserNewPassword(userNewPassword);
            updatePassword.setUserNewAgainPassword(userNewAgainPassword);
        } else {
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String updatePasswordRequestJson = gson.toJson(updatePassword);
        LogUtil.d("PostUpdatePasswordData: " + updatePasswordRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitUpdatePassword = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitUpdatePassword.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), updatePasswordRequestJson);
        Call<PasswordDataResponse.UpdatePassword> call = requestAPI.updatePasswordPostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<PasswordDataResponse.UpdatePassword>() {

            @Override
            public void onResponse(Call<PasswordDataResponse.UpdatePassword> call, Response<PasswordDataResponse.UpdatePassword> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("【updatePassword】网络请求成功");
                LogUtil.d("【updatePassword】返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case UPDATE_PASSWORD_SUCCESS:
                        updatePasswordListener.onUpdateSuccess(response.body().getErrCode(), response.body().getAlertMsg(), response.body().getNewPassword());
                        break;
                    case UPDATE_PASSWORD_FAILD:
                        updatePasswordListener.onUpdateFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case UPDATE_PASSWORD_NOT_USER:
                        updatePasswordListener.onUpdateFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<PasswordDataResponse.UpdatePassword> call, Throwable t) {
                // 网络请求失败
                LogUtil.d("【login】网络请求失败");
                updatePasswordListener.onUpdateFailed(ConstantUtil.NET_REQUEST_FAIL, Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }

    @Override
    public void resetUserPassword(String userMail, String userNewPassword, String userNewAgainPassword, final OnPasswordListener.OnResetPasswordListener resetPasswordListener) {
        // 封装JSON数据
        PasswordDataRequest.ResetPassword resetPassword = new PasswordDataRequest.ResetPassword();
        if (!TextUtils.isEmpty(userMail) && ConstantUtil.isEmail(userMail) && !TextUtils.isEmpty(userNewPassword) && !TextUtils.isEmpty(userNewAgainPassword)) {
            resetPassword.setUserMail(userMail);
            resetPassword.setUserNewPassword(userNewPassword);
            resetPassword.setUserNewAgainPassword(userNewAgainPassword);
        } else {
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String resetPasswordRequestJson = gson.toJson(resetPassword);
        LogUtil.d("PostUpdatePasswordData: " + resetPasswordRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitResetPassword = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitResetPassword.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), resetPasswordRequestJson);
        Call<PasswordDataResponse.ResetPassword> call = requestAPI.resetPasswordPostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<PasswordDataResponse.ResetPassword>() {

            @Override
            public void onResponse(Call<PasswordDataResponse.ResetPassword> call, Response<PasswordDataResponse.ResetPassword> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("【resetPassword】网络请求成功");
                LogUtil.d("【resetPassword】返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case RESET_PASSWORD_SUCCESS:
                        resetPasswordListener.onResetSuccess(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case RESET_PASSWORD_FAILD:
                        resetPasswordListener.onResetFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case RESET_PASSWORD_NOT_MAIL:
                        resetPasswordListener.onResetFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<PasswordDataResponse.ResetPassword> call, Throwable t) {
                // 网络请求失败
                LogUtil.d("【login】网络请求失败");
                resetPasswordListener.onResetFailed(ConstantUtil.NET_REQUEST_FAIL, Util.getResourceString(mContext, R.string.net_fail));
            }
        });
    }
}
