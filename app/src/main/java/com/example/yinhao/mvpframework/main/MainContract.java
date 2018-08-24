package com.example.yinhao.mvpframework.main;

import com.example.yinhao.mvpframework.base.BasePresenter;
import com.example.yinhao.mvpframework.base.BaseView;
import com.example.yinhao.mvpframework.bean.UserBean;
import com.example.yinhao.mvpframework.inter.Callback;

public class MainContract {
    public interface View extends BaseView<Presenter> {
        String getInfo();
    }

    public interface Presenter extends BasePresenter {
        //一般在首页我们会进行一个版本的更新（功能）
        void getHxId();
    }

}
