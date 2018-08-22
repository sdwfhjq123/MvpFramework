package com.example.yinhao.mvpframework.http;

import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.UserBean;
import com.example.yinhao.mvpframework.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppVersionService {
    @GET("/appnew/updateVersion")
    Observable<BaseResponse<VersionBean>> getTask(@Query("type") String type);

    @FormUrlEncoded
    @POST("/friend/getIMUserInfo")
    Observable<BaseResponse<UserBean>> getUserInfo(@Field("searchVal") String username);
}
