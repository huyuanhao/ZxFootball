package com.parsonswang.zxfootball.price

import android.os.Bundle
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.zxfootball.R
import kotlinx.android.synthetic.main.activity_dada_detail.*

/**
 * @author PC
 * @date 2020/05/11 16:22
 */
class MyInfoActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titlebar.setTitle("资料")
        titlebar.hideRightArrow()

        setContentView(R.layout.activity_my_info)
    }
}