package com.example.yinhao.mvpframework.http;

import com.example.yinhao.mvpframework.constant.ConstantValue;
import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.TokenBean;
import com.example.yinhao.mvpframework.bean.UserBean;
import com.example.yinhao.mvpframework.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AppVersionService {
    @GET("/appnew/updateVersion")
    Observable<BaseResponse<VersionBean>> getTask(@Query("type") String type);

    @Headers("Authorization:"+ ConstantValue.TOKE)
    @FormUrlEncoded
    @POST("/friend/getIMUserInfo")
//    @Headers({"Anthorization:" + ConstantValue.TOKE})
    Observable<BaseResponse<UserBean>> getUserInfo(@Field("searchVal") String username);

    @FormUrlEncoded
    @POST("/appnew/refresh_token")
    Call<BaseResponse<TokenBean>> refreshToken(@Field("refresh_token") String refreshToken);
}
