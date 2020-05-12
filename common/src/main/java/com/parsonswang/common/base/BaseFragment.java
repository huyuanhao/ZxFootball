package com.parsonswang.common.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by parsonswang on 2018/2/6.
 */

public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private ProgressDialog progressDialog = null;
    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String message) {
        Activity activity = getActivity();
        if(activity == null){
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
        }
        if (TextUtils.isEmpty(message)) {
            progressDialog.show();
//            progressDialog.showLoading("加载中",false);
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
