package com.parsonswang.zxfootball.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
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
    companion object{
        var userMap:HashMap<String,String> ? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userMap = hashMapOf()

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
            if (userMap?.get(num)?.equals(pass)!!) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                toast(this,"账号或密码有误！")
            }
        }
    }
    fun toLogin(){

    }


}