package com.example.yinhao.mvpframework.main;

import com.example.yinhao.mvpframework.base.BasePresenter;
import com.example.yinhao.mvpframework.base.BaseView;
import com.example.yinhao.mvpframework.bean.VersionBean;

public class MainContract {
    public interface View extends BaseView {
        //View效果就是展示下载进度框
        void showUpdateDialog(VersionBean bean);

        void showProgressDialog();

        void dissProgressDialog();

        void ShowToast(String message);
    }

    public interface Presenter extends BasePresenter<View> {
        //一般在首页我们会进行一个版本的更新（功能）
        void checkVersion(String currentVersion);
    }
}
