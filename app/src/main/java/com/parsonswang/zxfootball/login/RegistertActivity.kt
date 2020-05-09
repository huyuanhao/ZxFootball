package com.parsonswang.zxfootball.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.blankj.utilcode.util.CacheMemoryStaticUtils
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author PC
 * @date 2020/05/09 17:25
 */

class RegistertActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setText("注册")

        tv_register.visibility = View.GONE
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
            if(CacheDiskStaticUtils.getString(num)!=null){
                toast(this,"该账号已被注册！")
                return@setOnClickListener
            }
            toast(this,"注册成功！")
            CacheDiskStaticUtils.put(num,pass)
            finish()
        }
    }
    fun toLogin(){

    }


}