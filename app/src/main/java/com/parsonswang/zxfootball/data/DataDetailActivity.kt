package com.parsonswang.zxfootball.data

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.parsonswang.common.base.BaseActivity
import com.parsonswang.common.utils.DateUtils
import com.parsonswang.common.utils.UIUtils
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.MatchesBean
import com.parsonswang.zxfootball.bean.MatchesBean.MatchInfo
import com.parsonswang.zxfootball.common.view.CommonRecyclerViewDivider
import com.parsonswang.zxfootball.common.view.MatchScoreInfoView
import com.parsonswang.zxfootball.matches.MatchContract.IMatchInfoView
import com.parsonswang.zxfootball.matches.MatchInfoAdapter
import com.parsonswang.zxfootball.matches.MatchPresenter
import com.parsonswang.zxfootball.matches.detail.MatchDetailActivity
import com.parsonswang.zxfootball.utils.toast
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import kotlinx.android.synthetic.main.activity_dada_detail.*
import timber.log.Timber
import java.util.*

/**
 * @author PC
 * @date 2020/05/09 16:24
 */
class DataDetailActivity : BaseActivity(),  BaseQuickAdapter.OnItemClickListener, IMatchInfoView,
        MatchScoreInfoView.OnItemClickListener<MatchInfo> {
    var page = 1
    var presenter: MatchPresenter? = null
    var news_name: String? = null
    var dataId :String?= null
    var mRollbackMonth:Int = 0
    var newsAdapter: MatchInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dada_detail)

        presenter = MatchPresenter(this)
        news_name = intent.getStringExtra("news_name")
        dataId = intent.getStringExtra("id")

        titlebar.setTitle(news_name)
        titlebar.hideRightArrow()

        newsAdapter = MatchInfoAdapter(this)
        newsAdapter?.setOnItemClickListener(this)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonRecyclerViewDivider(
                    context,
                    LinearLayoutManager.VERTICAL,
                    UIUtils.dip2px(context, 14.4f),
                    Color.parseColor("#232C30")))
            adapter = newsAdapter
        }

        mRefreshLayout.run {
            setOnRefreshListener {
                page = 1
                mRollbackMonth = 0
                presenter?.getMatchInfos(dataId, getSpecifyDateParams())
                finishRefresh()
            }
            setOnLoadmoreListener(OnLoadmoreListener {
                page++
                mRollbackMonth++
                presenter?.getMatchInfos(dataId, news_name)
                finishLoadmore()
            })
        }

        mRefreshLayout.autoRefresh()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        toast(this,"点击" + position)
    }

    override fun onItemClick(t: MatchInfo?) {
        MatchDetailActivity.actionStart(this, t?.getId().toString())
    }

    override fun onFail(message: String?) {
        mRefreshLayout.finishRefresh()
        mRefreshLayout.finishLoadmore()
    }

    override fun showMatchInfoList(bean: MatchesBean?) {
        if (bean == null) {
            return
        }
        if (page == 1) {
            newsAdapter?.clearData()
        }
        newsAdapter?.addAll(bean.datas)
        newsAdapter?.notifyDataSetChanged()
    }

    private fun getSpecifyDateParams(): String? {
        val str = DateUtils.date2String(DateUtils.getSomeMonthOfFirstDay(Date(), mRollbackMonth))
        Timber.e(str)
        val str1 = DateUtils.date2String(DateUtils.getSomeMonthOfLastDay(Date(), mRollbackMonth))
        Timber.e(str1)
        return "$str+至+$str1"
    }
}