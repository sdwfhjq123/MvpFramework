package com.example.yinhao.mvpframework.main;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yinhao.mvpframework.R;
import com.example.yinhao.mvpframework.base.BaseActivity;
import com.example.yinhao.mvpframework.base.BasePresenter;

import butterknife.BindView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener {
    private MainContract.Presenter mPresenter;
    @BindView(R.id.btn_getVersion)
    Button mCheckButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mCheckButton.setOnClickListener(this);

        new MainPresenterImpl(this);
    }


    @Override
    public void onClick(View view) {
        mPresenter.getHxId();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public String getInfo() {
        return "imuser5";
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showMessage(String msg, int type) {
        Toast.makeText(this, msg, type).show();
    }

    @Override
    public void showError(String message) {

    }
}
