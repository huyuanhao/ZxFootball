package com.parsonswang.zxfootball;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.glidebitmappool.GlideBitmapPool;
import com.parsonswang.zxfootball.core.ApplicationContextHolder;
import com.parsonswang.zxfootball.core.CrashReportInit;
import com.parsonswang.zxfootball.core.LogInit;
import com.parsonswang.zxfootball.core.MtaInit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.tencent.bugly.crashreport.CrashReport;

import timber.log.Timber;


/**
 * Created by parsonswang on 2017/10/13.
 */

public class ZxApplication extends Application {

    public static Application appInstance;
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new BallPulseFooter(context);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        LogInit.initLog();

        MtaInit.initMta(appInstance);

        CrashReportInit.initCrashReport();

        Stetho.initializeWithDefaults(this);

        CrashReport.initCrashReport(getApplicationContext(), "ce5d70e35c", false);

        GlideBitmapPool.initialize(10 * 1024 * 1024) ;

        Timber.i("---onCreate---");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Timber.i("---attachBaseContext---");
        ApplicationContextHolder.setContext(base);
    }
}
