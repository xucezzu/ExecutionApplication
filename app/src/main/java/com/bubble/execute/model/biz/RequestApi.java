package com.bubble.execute.model.biz;

import com.bubble.execute.model.bean.HomeDataResponse;
import com.bubble.execute.model.bean.IdentifyCodeResponse;
import com.bubble.execute.model.bean.LoginDataResponse;
import com.bubble.execute.model.bean.MailDataResponse;
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
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_register/")
    Call<RegisterDataResponse> registerPostMsg(@Body RequestBody requestBody);

    /**
     * 登录的接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_login/")
    Call<LoginDataResponse> loginPostMsg(@Body RequestBody requestBody);

    /**
     * 是否存在安全密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("is_exist_safe_password/")
    Call<SafePasswordDataResponse.IsExistSafePassword> isExitSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 核对安全密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_safe_password/")
    Call<SafePasswordDataResponse.CheckSafePassword> checkSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 更新安全密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("update_safe_password/")
    Call<SafePasswordDataResponse.UpdateSafePassword> updateSafePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 核对密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_user_password/")
    Call<PasswordDataResponse.CheckPassword> checkPasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 更新密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("update_user_password/")
    Call<PasswordDataResponse.UpdatePassword> updatePasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 重置密码接口
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("reset_user_password/")
    Call<PasswordDataResponse.ResetPassword> resetPasswordPostMsg(@Body RequestBody requestBody);

    /**
     * 核对邮箱
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_user_mail/")
    Call<MailDataResponse.CheckMail> checkMailPostMsg(@Body RequestBody requestBody);

    /**
     * 核对验证码
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("check_identify_code/")
    Call<IdentifyCodeResponse.CheckIdentifyCode> checkIdentifyCodePostMsg(@Body RequestBody requestBody);

    /**
     * 获取首页今日寄语
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("get_today_motto/")
    Call<HomeDataResponse.HomeDataMottoResponse> getHomeMottoData(@Body RequestBody requestBody);

    /**
     * 获取首页任务数据
     *
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("get_doing_task/")
    Call<HomeDataResponse.HomeDataTaskResponse> getHomeTaskData(@Body RequestBody requestBody);
}
