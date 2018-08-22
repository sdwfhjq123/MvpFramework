package com.example.yinhao.mvpframework.http;

import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppVersionService {
    @GET("/appnew/updateVersion")
    Observable<BaseResponse<VersionBean>> getTask(@Query("type") int type);
}
