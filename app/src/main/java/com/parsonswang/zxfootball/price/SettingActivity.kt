package com.parsonswang.zxfootball.price

import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.blankj.utilcode.util.CacheMemoryUtils
import com.blankj.utilcode.util.Utils
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.login.LoginActivity
import kotlinx.android.synthetic.main.activity_dada_detail.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.titlebar

/**
 * @author PC
 * @date 2020/05/11 16:22
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        titlebar.setTitle("设置")
        titlebar.hideRightArrow()
        tvCacheSize.text = CacheDiskStaticUtils.getCacheSize().toString()
        rlClear.setOnClickListener {
            CacheDiskStaticUtils.clear()
            tvCacheSize.text = CacheDiskStaticUtils.getCacheSize().toString()
        }

        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        tvVersion.text = "版本号 v$versionName"


        btnLoginOut.setOnClickListener {
            CacheDiskStaticUtils.remove("user")
            CacheDiskStaticUtils.remove("password")
            ActivityUtils.finishAllActivities()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}