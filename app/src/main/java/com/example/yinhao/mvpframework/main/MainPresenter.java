package com.example.yinhao.mvpframework.main;

import android.annotation.SuppressLint;

import com.example.yinhao.mvpframework.ConstantValue;
import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.VersionBean;
import com.example.yinhao.mvpframework.http.AppVersionService;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    @SuppressLint("RestrictedApi")
    public MainPresenter(MainContract.View view) {
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void checkVersion(String currentVersion) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
//                        .addHeader("Authorization", token)
                        .build();

                return chain.proceed(request);
            }
        });
        OkHttpClient httpClient = client.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        AppVersionService service = retrofit.create(AppVersionService.class);
        service.getTask(String.valueOf(0))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<VersionBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!d.isDisposed()) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(BaseResponse<VersionBean> versionBeanBaseResponse) {
                        if (versionBeanBaseResponse != null) {
                            if (versionBeanBaseResponse.getCode() == 200) {
                                if (versionBeanBaseResponse.getData() != null) {
                                    String version = versionBeanBaseResponse.getData().getVersion();
                                    mView.showToast(version);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgressDialog();
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissProgressDialog();
                    }
                });

    }

    @Override
    public void start() {

    }
}
