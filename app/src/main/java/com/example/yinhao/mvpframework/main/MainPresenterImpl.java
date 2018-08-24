package com.example.yinhao.mvpframework.main;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.yinhao.mvpframework.constant.ConstantValue;
import com.example.yinhao.mvpframework.base.BaseResponse;
import com.example.yinhao.mvpframework.bean.UserBean;
import com.example.yinhao.mvpframework.http.AppVersionService;
import com.example.yinhao.mvpframework.inter.Callback;
import com.example.yinhao.mvpframework.util.http.RetrofitFactory;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainPresenterImpl implements MainContract.Presenter {
    private MainContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    @SuppressLint("RestrictedApi")
    public MainPresenterImpl(MainContract.View view) {
        mView = checkNotNull(view);
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getHxId() {
        AppVersionService service = RetrofitFactory.getInstence().API();
        service.getUserInfo(mView.getInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!d.isDisposed()) {
                            mView.showLoading(true);
                        }
                    }

                    @Override
                    public void onNext(BaseResponse<UserBean> versionBeanBaseResponse) {
                        if (versionBeanBaseResponse.getCode() == 200) {
                            mView.showMessage(versionBeanBaseResponse.getData().getHxId(), 0);
                        } else {
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showLoading(false);
                    }

                    @Override
                    public void onComplete() {
                        mView.showLoading(true);
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

}
