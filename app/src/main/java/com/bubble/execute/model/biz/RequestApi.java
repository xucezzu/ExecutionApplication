package com.bubble.execute.model.biz;

import com.bubble.execute.model.bean.LoginDataResponse;
import com.bubble.execute.model.bean.PasswordDataResponse;
import com.bubble.execute.model.bean.RegisterDataResponse;
import com.bubble.execute.model.bean.SafePasswordDataResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/22
 * 版权所有 © 徐长策
 */

public interface RequestApi {
    /**
     * 注册的接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_register/")
    Call<RegisterDataResponse> registerPostMsg(@Body RequestBody requestBody);

    /**
     * 登录的接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_login/")
    Call<LoginDataResponse> loginPostMsg(@Body RequestBody requestBody);

    /**
     * 是否存在安全密码接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("is_exist_safe_password/")
    Call<SafePasswordDataResponse.IsExistSafePassword> isExitSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 核对安全密码接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_safe_password/")
    Call<SafePasswordDataResponse.CheckSafePassword> checkSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 更新安全密码接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("update_safe_password/")
    Call<SafePasswordDataResponse.UpdateSafePassword> updateSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 核对密码接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_user_password/")
    Call<PasswordDataResponse.CheckPassword> checkPasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 更新密码接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("update_user_password/")
    Call<PasswordDataResponse.UpdatePassword> updatePasswordPostMsg(@Body RequestBody requestBody);
}
