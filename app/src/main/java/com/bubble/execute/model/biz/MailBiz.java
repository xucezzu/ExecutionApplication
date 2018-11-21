package com.bubble.execute.model.biz;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.MailDataRequest;
import com.bubble.execute.model.bean.MailDataResponse;
import com.bubble.execute.model.impl.IMailBiz;
import com.bubble.execute.model.listener.OnMailListener;
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
 * Date：2018/11/15
 * 版权所有 © 徐长策
 */
public class MailBiz implements IMailBiz {
    private Context mContext;

    /**
     * 邮箱存在
     */
    private static final String MAIL_EXIST = "0";

    /**
     * 邮箱不存在
     */
    private static final String MAIL_NOT_EXIST = "1";

    public MailBiz(Context context) {
        this.mContext = context;
    }

    @Override
    public void checkMail(String mail, String type, final OnMailListener mailListener) {
        // 封装JSON数据
        MailDataRequest.CheckMail checkMail = new MailDataRequest.CheckMail();
        if (!TextUtils.isEmpty(mail) && ConstantUtil.isEmail(mail)) {
            checkMail.setUserMail(mail);
            checkMail.setMailType(type);
        } else {
            StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.login_mail_error), R.style.AppDefaultToast).show();
            return;
        }
        Gson gson = new Gson();
        // 将JSON数据转换为String类型
        String checkMailRequestJson = gson.toJson(checkMail);
        LogUtil.d("PostCheckMailData: " + checkMailRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitMail = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitMail.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), checkMailRequestJson);
        Call<MailDataResponse.CheckMail> call = requestAPI.checkMailPostMsg(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<MailDataResponse.CheckMail>() {
            @Override
            public void onResponse(Call<MailDataResponse.CheckMail> call, Response<MailDataResponse.CheckMail> response) {
                // 网络请求成功，处理返回的结果
                if (response.body() != null) {
                    LogUtil.d("【checkMail】网络请求成功");
                    LogUtil.d("【checkMail】返回的数据：" + response.body().toString());
                    switch (response.body().getErrCode()) {
                        case MAIL_EXIST:
                            mailListener.mailHasExist(response.body().getErrCode(), response.body().getAlertMsg());
                            break;
                        case MAIL_NOT_EXIST:
                            mailListener.mailNotExist(response.body().getErrCode(), response.body().getAlertMsg());
                            break;
                        default:
                            break;
                    }
                } else {
                    // 服务器错误
                    StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.server_error), Toast.LENGTH_LONG, R.style.AppDefaultToast).show();
                }
            }

            @Override
            public void onFailure(Call<MailDataResponse.CheckMail> call, Throwable t) {
                // 网络请求失败
                StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.net_fail), R.style.AppDefaultToast).show();
            }
        });
    }
}
