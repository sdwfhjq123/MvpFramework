package com.example.yinhao.mvpframework.main;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yinhao.mvpframework.R;
import com.example.yinhao.mvpframework.base.BaseActivity;
import com.example.yinhao.mvpframework.bean.VersionBean;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, View.OnClickListener {
    @BindView(R.id.btn_getVersion)
    Button mCheckButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MainPresenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void init() {
        mCheckButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showUpdateDialog(VersionBean bean) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dissProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void ShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
