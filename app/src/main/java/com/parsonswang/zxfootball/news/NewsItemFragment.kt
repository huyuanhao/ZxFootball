package com.parsonswang.zxfootball.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.parsonswang.common.base.BaseLazyLoadFragment
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.HeaderTabTitle
import com.parsonswang.zxfootball.bean.NewsBean
import com.parsonswang.zxfootball.utils.toast
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * @author PC
 * @date 2020/05/08 16:06
 */
class NewsItemFragment : BaseLazyLoadFragment(), NewsContract.INewsInfoView, BaseQuickAdapter.OnItemClickListener {
    var page = 1
    var presenter: NewsPresenter? = null
    var news_name: String? = null
    var newsAdapter: NewsListAdapter? = null

    companion object {
        public val NEWS_NAME = "news_name"
        fun newInstance(info: HeaderTabTitle.TabInfo): NewsItemFragment{
            var fragment = NewsItemFragment()
            val bundle = Bundle()
            bundle.putString(NEWS_NAME, info.name)
            fragment.setArguments(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = NewsPresenter(this)
        news_name = arguments?.getString("news_name")

        newsAdapter = NewsListAdapter()
        newsAdapter?.setOnItemClickListener(this)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        mRefreshLayout.run {
            setOnRefreshListener(OnRefreshListener {
                page = 1
                presenter?.getNewsInfos(page, news_name)
                finishRefresh()
            })
            setOnLoadmoreListener(OnLoadmoreListener {
                page++
                presenter?.getNewsInfos(page, news_name)
                finishLoadmore()
            })
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadData() {
        page = 1
        mRefreshLayout.autoRefresh()
    }

    override fun showNewsInfoList(bean: MutableList<NewsBean>?) {
        if (bean == null) {
            return
        }
        if (page == 1) {
            newsAdapter?.setNewData(bean)
        } else {
            newsAdapter?.addData(bean)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        context?.let { toast(it,"点击" + position) }
    }
}