package com.parsonswang.zxfootball.news

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.parsonswang.common.image.Imageloaders
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.NewsBean

/**
 *   @auther : Aleyn
 *   time   : 2019/11/08
 */
class NewsListAdapter : BaseQuickAdapter<NewsBean, BaseViewHolder>(R.layout.item_news_list) {

    override fun convert(helper: BaseViewHolder, item: NewsBean?) {
        with(helper!!) {
            setText(R.id.type, item!!.ctime)
            setText(R.id.title, item.title)
            setText(R.id.time, item.ctime)
            setText(R.id.auther, item.description)
            val imageView = helper.getView<ImageView>(R.id.img)
            if (!item.picUrl.isNullOrEmpty()) {
                if(!item.picUrl.startsWith("http")){
                    Imageloaders.loadImage(mContext, "http:" + item.picUrl,imageView, 0)
                }else {
                    Imageloaders.loadImage(mContext, item.picUrl, imageView, 0)
                }
            }
        }
    }
}