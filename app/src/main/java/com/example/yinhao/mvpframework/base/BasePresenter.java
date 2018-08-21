package com.example.yinhao.mvpframework.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
