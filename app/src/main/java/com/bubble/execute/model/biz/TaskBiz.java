package com.bubble.execute.model.biz;

import android.content.Context;
import android.text.TextUtils;

import com.bubble.execute.R;
import com.bubble.execute.model.bean.HomeDataRequest;
import com.bubble.execute.model.bean.HomeDataResponse;
import com.bubble.execute.model.impl.ITaskBiz;
import com.bubble.execute.model.listener.OnTaskListener;
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
 * Date：2018/12/23
 * 版权所有 © 徐长策
 */
public class TaskBiz implements ITaskBiz {
    private Context mContext;
    private static final String HOME_TASK_SUCCESS = "0", HOME_TASK_FAIL_NO_DOING = "1", HOME_TASK_FAIL_OUTTIME = "2";

    public TaskBiz(Context context) {
        this.mContext = context;
    }

    @Override
    public void getMottoData(String mottoDate, final OnTaskListener.OnTaskMottoListener listener) {
        // 封装JSON数据
        Gson gsonMotto = new Gson();
        HomeDataRequest.HomeDataMottoRequest homeDataMottoRequest = new HomeDataRequest.HomeDataMottoRequest();
        if (ConstantUtil.isLegalDate(mottoDate)) {
            homeDataMottoRequest.setMottoDate(mottoDate);
        } else {
            StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.home_motto_date_warn), R.style.AppDefaultToast).show();
            return;
        }
        // 将封装的JSON数据转换成String类型
        String homeDataMottoRequestJson = gsonMotto.toJson(homeDataMottoRequest);
        LogUtil.d("HomeDataMottoRequestJson封装结果: " + homeDataMottoRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitHomeMotto = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitHomeMotto.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), homeDataMottoRequestJson);
        Call<HomeDataResponse.HomeDataMottoResponse> call = requestAPI.getHomeMottoData(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<HomeDataResponse.HomeDataMottoResponse>() {
            @Override
            public void onResponse(Call<HomeDataResponse.HomeDataMottoResponse> call, Response<HomeDataResponse.HomeDataMottoResponse> response) {
                // 网络请求成功，处理返回的结果
                if (response.body() != null) {
                    LogUtil.d("【HomeMotto】网络请求成功");
                    LogUtil.d("【HomeMotto】返回的数据：" + response.body().toString());
                    listener.onSuccess(response.body().getErrCode(), response.body().getAlertMsg(), response.body().getReturnData());
                }
            }

            @Override
            public void onFailure(Call<HomeDataResponse.HomeDataMottoResponse> call, Throwable t) {
                // 网络请求失败
                StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.net_fail), R.style.AppDefaultToast).show();
            }
        });
    }

    @Override
    public void getHomeTaskData(String userId, String deviceId, final OnTaskListener.OnTaskHomeListener listener) {
        // 封装JSON数据
        Gson gson = new Gson();
        HomeDataRequest.HomeDataTaskRequest homeDataTaskRequest = new HomeDataRequest.HomeDataTaskRequest();
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(deviceId)) {
            homeDataTaskRequest.setUserId(userId);
            homeDataTaskRequest.setDeviceId(deviceId);
        } else {
            StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.home_task_date_warn), R.style.AppDefaultToast).show();
            return;
        }
        // 将封装的JSON数据转换成String类型
        String homeDataTaskRequestJson = gson.toJson(homeDataTaskRequest);
        LogUtil.d("HomeDataTaskRequestJson封装结果: " + homeDataTaskRequestJson);
        // 网络请求，开始创建 Retrofit 对象
        Retrofit retrofitHomeTask = new Retrofit.Builder()
                .baseUrl(ServerURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 创建网络请求接口实例
        RequestApi requestAPI = retrofitHomeTask.create(RequestApi.class);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), homeDataTaskRequestJson);
        Call<HomeDataResponse.HomeDataTaskResponse> call = requestAPI.getHomeTaskData(requestBody);
        // 发送网络请求（异步）
        call.enqueue(new Callback<HomeDataResponse.HomeDataTaskResponse>() {
            @Override
            public void onResponse(Call<HomeDataResponse.HomeDataTaskResponse> call, Response<HomeDataResponse.HomeDataTaskResponse> response) {
                if (response.body() != null) {
                    LogUtil.d("【HomeTask】网络请求成功");
                    LogUtil.d("【HomeTask】返回的数据：" + response.body().toString());
                    switch (response.body().getErrCode()) {
                        case HOME_TASK_SUCCESS:
                            listener.onSuccess(response.body().getErrCode(), response.body().getAlertMsg(), response.body().getHomeTaskData());
                            break;
                        case HOME_TASK_FAIL_NO_DOING:
                            listener.onFailed(response.body().getErrCode(), response.body().getAlertMsg());
                            break;
                        case HOME_TASK_FAIL_OUTTIME:
                            listener.onFailed(response.body().getErrCode(), response.body().getAlertMsg());
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeDataResponse.HomeDataTaskResponse> call, Throwable t) {
                // 网络请求失败
                StyleableToast.makeText(mContext, Util.getResourceString(mContext, R.string.net_fail), R.style.AppDefaultToast).show();
            }
        });
    }
}
