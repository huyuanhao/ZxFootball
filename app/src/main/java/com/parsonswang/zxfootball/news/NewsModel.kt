package com.parsonswang.zxfootball.news

import com.parsonswang.common.network.JsonCallback
import com.parsonswang.zxfootball.bean.News
import com.parsonswang.zxfootball.common.Constant
import com.parsonswang.zxfootball.common.data.DataFetchFactory
import java.util.*

/**
 * @author PC
 * @date 2020/05/08 14:53
 */
class NewsModel {
    fun getNewsList(page:Int, word:String?,callback: JsonCallback<News>){
        val paramsMap = LinkedHashMap<String, Any>()
        paramsMap.put("page", page.toString())
        if(word!=null) {
            paramsMap.put("word", word)
        }
        paramsMap.put("key","bcdbd7c08ca1c1e30364282c95ec2b07")
        DataFetchFactory.getInstance()
                .getDataFetcher(Constant.NetworkProtocolConstant.NEW_LIST_URL, DataFetchFactory.DATA_FETCH_TYPE_JSON)
                .fetchData(callback, paramsMap)
    }
}