package com.junerver.happyread.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by junerver on 2016/10/25.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        int layout = getLayoutId();
        if (layout == 0) {
            throw new IllegalStateException("Please specify root layout resource id for " + getClass().getSimpleName());
        } else {
            setContentView(layout);
            ButterKnife.bind(this);
            initData();
            initView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mProgressDialog = new ProgressDialog(this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                mProgressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
            }
            mProgressDialog.setMessage("请稍等...");
        }

    }

    /**
     * @param msg toast的提示信息
     */
    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开进度条dialog
     */
    public void showProgress() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 关闭进度条dialog
     */
    public void closeProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 视图初始化
     */
    protected abstract void initView();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    /**
     * @return  布局ID
     */
    protected abstract int getLayoutId();
}
