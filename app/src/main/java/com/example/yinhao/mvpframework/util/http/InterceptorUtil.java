package com.example.yinhao.mvpframework.util.http;

import android.util.Log;

import com.example.yinhao.mvpframework.constant.ConstantValue;
import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.TokenBean;
import com.example.yinhao.mvpframework.http.AppVersionService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class InterceptorUtil {
    public static String TAG = "----";

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                //在这里你可以做一些想做的事,比如token失效时,重新获取token或者添加header等等
                if (isTokenExpired(response)) {
                    String newToken = getNewToken();
                    //使用新的Token，创建新的请求
                    Request newRequest = chain.request()
                            .newBuilder()
                            .header("Authorization", newToken)
                            .build();
                    //重新请求
                    return chain.proceed(newRequest);
                }

                return chain.proceed(request);
            }
        };
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private static boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private static String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        AppVersionService service = RetrofitFactory.getInstence().API();
        retrofit2.Call<BaseResponse<TokenBean>> call = service.refreshToken(ConstantValue.REFRESH_TOKE);
        BaseResponse<TokenBean> tokenBean = call.execute().body();
        //TODO 请求完后将access_token  refresh_token save
        return tokenBean.getData().getAccess_token();
    }
}
