package com.example.yinhao.mvpframework.main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yinhao.mvpframework.R;
import com.example.yinhao.mvpframework.base.BaseActivity;
import com.example.yinhao.mvpframework.bean.VersionBean;

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

        new MainPresenter(this);
    }

    @Override
    public void onClick(View view) {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            String versionName = pi.versionName;
            mPresenter.checkVersion(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
