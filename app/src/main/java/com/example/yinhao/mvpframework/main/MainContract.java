package com.example.yinhao.mvpframework.main;

import com.example.yinhao.mvpframework.base.BasePresenter;
import com.example.yinhao.mvpframework.base.BaseView;

public class MainContract {
    public interface View extends BaseView<Presenter> {
        void showProgressDialog();

        void dismissProgressDialog();

        void showToast(String msg);
    }

    public interface Presenter extends BasePresenter {
        //一般在首页我们会进行一个版本的更新（功能）
        void checkVersion(String currentVersion);
    }
}
