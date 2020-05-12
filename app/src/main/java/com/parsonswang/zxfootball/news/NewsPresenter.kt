package com.parsonswang.zxfootball.news

import android.os.Parcel
import android.os.Parcelable
import com.parsonswang.common.network.JsonCallback
import com.parsonswang.common.utils.JsonObjectMap
import com.parsonswang.common.utils.StringUtils
import com.parsonswang.zxfootball.bean.HeaderTabTitle
import com.parsonswang.zxfootball.bean.News
import com.parsonswang.zxfootball.common.mvp.AbsPresenter
import com.parsonswang.zxfootball.common.utils.ConfigUtil
import okhttp3.Call
import timber.log.Timber

/**
 * @author PC
 * @date 2020/05/08 14:39
 */
class NewsPresenter() : AbsPresenter(), NewsContract.INewsPresenter {
    var model: NewsModel? = null
    var newsView: NewsContract.NewsView? = null
    var newsInfoView: NewsContract.INewsInfoView? = null

    constructor(newsView: NewsContract.NewsView) : this() {
        this.newsView = newsView
        this.model = NewsModel()
    }
    constructor(newsView: NewsContract.INewsInfoView):this() {
        this.newsInfoView = newsView
        this.model = NewsModel()
    }

    public override fun start() {
        getHeaderTabTitle()
    }

    override fun stop() {
    }

    override fun getNewsInfos(page: Int, word: String?) {
        model?.getNewsList(page, word, object : JsonCallback<News>() {
            override fun onSuccess(t: News?) {
                newsInfoView?.showNewsInfoList(t?.newslist)
            }

            override fun onFail(call: Call?, reson: String?) {
            }

        })
    }

    private fun getHeaderTabTitle() {
        val leagueConfig = ConfigUtil.getOnlineConfig("league_config", "{\"data\":[{ \"name\":\"英超\", \"id\": 1 },{ \"name\":\"中超\", \"id\": 3 },{ \"name\":\"西甲\", \"id\": 2 },{ \"name\":\"意甲\", \"id\": 5 },{ \"name\":\"德甲\", \"id\": 4 },{ \"name\":\"法甲\", \"id\": 6 }]}")
        Timber.i("leagueConfig|: $leagueConfig")
        if (StringUtils.isEmptyString(leagueConfig)) {
            newsView?.showHeaderTabTitle(null)
        } else {
            val headerTabTitle: HeaderTabTitle = JsonObjectMap.getInstance().fromJson(leagueConfig, HeaderTabTitle::class.java)
            newsView?.showHeaderTabTitle(headerTabTitle)
        }
    }

}