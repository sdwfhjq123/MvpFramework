package com.example.yinhao.mvpframework.base;

public interface BaseView<P extends BasePresenter> {

    void setPresenter(P presenter);

    void showLoading(boolean pullToRefresh);

    void showContent();

    void showMessage(String msg, int type);

    void showError(String message);
}
