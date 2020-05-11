package com.parsonswang.zxfootball.news;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.zxfootball.R;


/***
 * 文件名：WebActivity
 * 描述：便民服务加载网页的界面
 * 作者：chenhao
 * 时间：2017/11/30
 * 版权：v1.0
 */
public class WebActivity extends BaseActivity implements OnClickListener {
    private WebView webView;
    private ProgressBar pb;
    private WebSettings mWebSettings;
    private RelativeLayout error;
    private RelativeLayout toolBarBack,toolBarFresh,toolBarOff;
    private String title;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
//        initTitleBar(title, this, null);

        webView = findViewById(R.id.webview);
        pb = findViewById(R.id.progressBar);
        error =  findViewById(R.id.error);
        toolBarBack = findViewById(R.id.back);
        toolBarOff = findViewById(R.id.off);
        toolBarFresh = findViewById(R.id.fresh);

        //加载需要显示的网页
        webView.loadUrl(url);

        toolBarBack.setOnClickListener(this);
        toolBarOff.setOnClickListener(this);
        toolBarFresh.setOnClickListener(this);
        setUpView();

    }

    private void setUpView() {
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);    //允许加载javascript
        mWebSettings.setDomStorageEnabled(true);
        if ("微信网页版".equals(title)) {
            mWebSettings.setUserAgentString("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.108 Safari/537.36 UCBrowser/12.0.4.984");
        } else {
            mWebSettings.setAppCacheEnabled(true);// 设置缓存
        }
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 设置缓存模式,一共有四种模式
        mWebSettings.setSupportZoom(true);          //允许缩放
        mWebSettings.setBuiltInZoomControls(true);  //原网页基础上缩放
        mWebSettings.setDisplayZoomControls(false);
        mWebSettings.setUseWideViewPort(true);      //任意比例缩放
        mWebSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(webClient);  //设置Web视图
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if(newProgress==100){
                    pb.setVisibility(View.GONE);//加载完网页进度条消失
                }else{
                    pb.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pb.setProgress(newProgress);//设置进度值
                }

            }
        });
    }
    /***
     * 设置Web视图的方法
     */
    WebViewClient webClient = new WebViewClient(){//处理网页加载失败时
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            ShowErrorPage();
        }
        @Override
        public void onPageFinished(WebView view, String url) {//处理网页加载成功时
            if (!NetworkUtils.isConnected()){
                ShowErrorPage();
                return;
            }
            error.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    };
    /***
     * 显示加载失败时自定义的网页
     */
    protected void ShowErrorPage() {
        webView.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            if(webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (null == webView) {
                    return;
                }
                if(webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    webView.goBack();
                } else {
                    ToastUtils.showShort("不能再回退了");
                }
                break;
            case R.id.off:
                finish();
                break;
            case R.id.fresh:
                if (null != webView) {
                    webView.reload();
                }
                break;
        }
    }
}
