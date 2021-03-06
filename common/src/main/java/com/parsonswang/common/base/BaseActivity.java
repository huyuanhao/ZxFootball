package com.parsonswang.common.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.transition.Fade;
import android.view.Window;

import com.parsonswang.common.R;
import com.parsonswang.common.swipeback.SwipeLayoutHelper;
import com.parsonswang.common.utils.BarUtils;

import java.lang.ref.WeakReference;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by parsonswang on 2017/10/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected SwipeLayoutHelper swipeLayoutHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
        }

        swipeLayoutHelper = new SwipeLayoutHelper(new WeakReference< Activity >(this));
        swipeLayoutHelper.onActivityCreated();

        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.colorCommonBackground));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (swipeLayoutHelper == null) {
            return;
        }
        swipeLayoutHelper.onPoseCreated();
    }

    private PromptDialog progressDialog = null;
    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new PromptDialog(this);
        }
        if (TextUtils.isEmpty(message)) {
            progressDialog.showLoading("加载中");
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
