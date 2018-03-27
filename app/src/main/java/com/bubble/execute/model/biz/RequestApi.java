package com.bubble.execute.model.biz;

import com.bubble.execute.model.bean.LoginDataResponse;
import com.bubble.execute.model.bean.SafePasswordDataResponse;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/22
 * 版权所有 © 徐长策
 */

public interface RequestApi {
    /**
     * 登录的接口
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_login/")
    Call<LoginDataResponse> loginPostMsg(@Body RequestBody requestBody);

    /**
     * 是否存在安全密码
     * @param requestBody 上传的数据
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8", "Accept:application/json"})
    @POST("user_login/")
    Call<SafePasswordDataResponse.IsExistSafePassword> isExitSafePasswordPostMsg(@Body RequestBody requestBody);
}
