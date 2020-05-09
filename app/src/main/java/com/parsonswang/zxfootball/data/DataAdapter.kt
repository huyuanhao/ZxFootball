package com.parsonswang.zxfootball.data

import android.os.Build
import android.widget.ImageView
import android.widget.Switch
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.parsonswang.common.image.Imageloaders
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.HeaderTabTitle
import com.parsonswang.zxfootball.bean.NewsBean

/**
 *   @auther : Aleyn
 *   time   : 2019/11/08
 */
class DataAdapter : BaseQuickAdapter<HeaderTabTitle.TabInfo, BaseViewHolder>(R.layout.item_data) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun convert(helper: BaseViewHolder, item: HeaderTabTitle.TabInfo?) {
        with(helper!!) {
            setText(R.id.title, item?.name)
            val imageView = helper.getView<ImageView>(R.id.img)

            when(item?.id){
                1 -> {
                    setImageResource(R.id.img,R.mipmap.yc)
                }
                2 -> {
                    setImageResource(R.id.img,R.mipmap.xj)
                }
                3 -> {
                    setImageResource(R.id.img,R.mipmap.zc)
                }
                4 -> {
                    setImageResource(R.id.img,R.mipmap.dj)
                }
                5 -> {
                    setImageResource(R.id.img,R.mipmap.yj)
                }
                6 -> {
                    setImageResource(R.id.img,R.mipmap.fj)
                }
                else -> {}
            }
//            if (!item.picUrl.isNullOrEmpty()) {
//                if(!item.picUrl.startsWith("http")){
//                    Imageloaders.loadImage(mContext, "http:" + item.picUrl,imageView, 0)
//                }else {
//                    Imageloaders.loadImage(mContext, item.picUrl, imageView, 0)
//                }
//            }
        }
    }
}