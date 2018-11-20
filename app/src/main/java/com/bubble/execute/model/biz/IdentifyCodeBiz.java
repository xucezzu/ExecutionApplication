package com.bubble.execute.model.biz;

import android.content.Context;
import android.text.TextUtils;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.IdentifyCodeRequest;
import com.bubble.execute.model.bean.IdentifyCodeResponse;
import com.bubble.execute.model.impl.IIdentifyCodeBiz;
import com.bubble.execute.model.listener.OnIdentifyCodeListener;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.ServerURL;
import com.bubble.execute.utils.Util;
import com.google.gson.Gson;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

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
 * Date：2018/11/20
 * 版权所有 © 徐长策
 */
public class IdentifyCodeBiz implements IIdentifyCodeBiz {
    private Context mContext;

    /**
     * 0 --> 验证成功
     * 1 --> 验证失败
     * 2 --> 验证码失效
     * 3 --> 不存在该用户的验证码
     */
    private static final String CODE_CHECK_SUCCESS = "0";
    private static final String CODE_CHECK_FAILED = "1";
    private static final String CODE_CHECK_TIME_OUT = "2";
    private static final String CODE_CHECK_USER_NOT = "3";

    public IdentifyCodeBiz(Context context) {
        this.mContext = context;
    }

    @Override
    public void checkIdentify(String mail, String code, String type, final OnIdentifyCodeListener.OnCheckListener checkListener) {
        // 封装JSON数据
        IdentifyCodeRequest.CheckIdentifyCode checkIdentifyCode = new IdentifyCodeRequest.CheckIdentifyCode();
        if (!TextUtils.isEmpty(mail) && ConstantUtil.isEmail(mail)) {
            checkIdentifyCode.setUserMail(mail);
            checkIdentifyCode.setIdentifyCode(code);
            checkIdentifyCode.setTypeFrom(type);
        } else {
            StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.login_mail_error), R.style.AppDefaultToast).show();
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String checkIdentifyCodeRequestJson = gson.toJson(checkIdentifyCode);
        LogUtil.d("CheckIdentifyCodeRequestJsonData: " + checkIdentifyCodeRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitMail = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitMail.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), checkIdentifyCodeRequestJson);
        Call<IdentifyCodeResponse.CheckIdentifyCode> call = requestAPI.checkIdentifyCodePostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<IdentifyCodeResponse.CheckIdentifyCode>() {
            @Override
            public void onResponse(Call<IdentifyCodeResponse.CheckIdentifyCode> call, Response<IdentifyCodeResponse.CheckIdentifyCode> response) {
                // 网络请求成功，处理返回的结果
                LogUtil.d("【checkIdentifyCode】网络请求成功");
                LogUtil.d("【checkIdentifyCode】返回的数据：" + response.body().toString());
                switch (response.body().getErrCode()) {
                    case CODE_CHECK_SUCCESS:
                        checkListener.onCheckSuccess(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case CODE_CHECK_FAILED:
                        checkListener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case CODE_CHECK_TIME_OUT:
                        checkListener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    case CODE_CHECK_USER_NOT:
                        checkListener.onCheckFailed(response.body().getErrCode(), response.body().getAlertMsg());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<IdentifyCodeResponse.CheckIdentifyCode> call, Throwable t) {
                // 网络请求失败
                StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.net_fail), R.style.AppDefaultToast).show();
            }
        });
    }
}
