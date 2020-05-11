package com.parsonswang.zxfootball.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.blankj.utilcode.util.CacheMemoryStaticUtils
import com.blankj.utilcode.util.CacheMemoryUtils
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.zxfootball.MainActivity
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.data.DataDetailActivity
import com.parsonswang.zxfootball.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author PC
 * @date 2020/05/09 17:25
 */

class LoginActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(!TextUtils.isEmpty(CacheDiskStaticUtils.getString("user"))){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        tv_register.setOnClickListener {
            val intent = Intent(this, RegistertActivity::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            val num = et_account.text.toString();
            val pass = et_password.text.toString()
            if(TextUtils.isEmpty(num)){
                toast(this,"账号不能为空！")
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass)){
                toast(this,"密码不能为空！")
                return@setOnClickListener
            }
            if(CacheDiskStaticUtils.getString(num) == null){
                toast(this,"账号或密码有误！")
            }else if(CacheDiskStaticUtils.getString(num).equals(pass)){
                CacheDiskStaticUtils.put("user",num)
                CacheDiskStaticUtils.put("password",pass)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else{
                toast(this,"账号或密码有误！")
            }
        }
    }
    fun toLogin(){

    }


}