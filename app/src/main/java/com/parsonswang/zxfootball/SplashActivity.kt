package com.parsonswang.zxfootball

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.common.network.JsonCallback
import com.parsonswang.zxfootball.common.Constant
import com.parsonswang.zxfootball.common.data.DataFetchFactory
import com.parsonswang.zxfootball.login.LoginActivity
import com.parsonswang.zxfootball.news.WebActivity
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import okhttp3.Call
import java.util.*


/**
 * Created by parsonswang on 2017/10/12.
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(Action<List<String?>> { permissions: List<String?>? ->
                    getUrl()
                })
                .onDenied(Action<List<String?>> { permissions: List<String?>? ->
                    finish()
                })
                .start()
    }

    fun getUrl(){
            val paramsMap = LinkedHashMap<String, Any>()
            DataFetchFactory.getInstance()
                    .getDataFetcher(Constant.NetworkProtocolConstant.GET_BALL_URL, DataFetchFactory.DATA_FETCH_TYPE_JSON)
                    .fetchData(object : JsonCallback<UrlBean>() {
                        override fun onSuccess(t: UrlBean?) {
                            if(t?.code ==0){
                                val intent = Intent(this@SplashActivity, WebActivity::class.java)
                                intent.putExtra("url", "https://sj.qq.com/myapp/detail.htm?apkName=com.qixiao.qrxs")
                                startActivity(intent)
                                finish()
                            }else{
                                startHome()
                            }
                        }

                        override fun onFail(call: Call?, reson: String?) {
                            startHome()
                        }

                    }, paramsMap)
        }

    fun startHome(){
        if(!TextUtils.isEmpty(CacheDiskStaticUtils.getString("user"))){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

data class UrlBean(val result:String,val code:Int)