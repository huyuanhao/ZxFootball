package com.parsonswang.zxfootball.price

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.CacheDiskStaticUtils
import com.parsonswang.zxfootball.MainActivity
import com.parsonswang.zxfootball.R
import kotlinx.android.synthetic.main.fragment_datas.*
import kotlinx.android.synthetic.main.fragment_price.*
import kotlinx.android.synthetic.main.fragment_price.titlebar

/**
 * Created by parsonswang on 2017/10/13.
 */
class PriceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titlebar.setTitle("我的")
        titlebar.hideRightArrow()
        var user = CacheDiskStaticUtils.getString("user")
        name.setText(user)
        settingLl.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
        myInfoLl.setOnClickListener{
            val intent = Intent(activity, MyInfoActivity::class.java)
            startActivity(intent)
        }
    }
}