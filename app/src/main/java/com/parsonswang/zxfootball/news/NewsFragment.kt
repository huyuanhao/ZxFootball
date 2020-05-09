package com.parsonswang.zxfootball.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.HeaderTabTitle
import com.parsonswang.zxfootball.common.view.CommonHeaderTabAdapter
import com.parsonswang.zxfootball.common.view.NewsHeaderTabAdapter
import kotlinx.android.synthetic.main.fragment_datas.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.titlebar

/**
 * @author PC
 * @date 2020/05/08 14:23
 */
class NewsFragment : Fragment(), NewsContract.NewsView {
    var mCommonHeaderTabAdapter: NewsHeaderTabAdapter? = null;
    var presenter : NewsPresenter ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titlebar.setTitle("新闻")
        titlebar.hideRightArrow()
        tabs?.selectedTextColor = activity!!.resources.getColor(R.color.colorPrimary)

        presenter = NewsPresenter(this)
        presenter!!.start()
    }

    override fun showHeaderTabTitle(headerTabTitle: HeaderTabTitle?) {
        val dataBeanList = headerTabTitle!!.data
        mCommonHeaderTabAdapter = NewsHeaderTabAdapter(childFragmentManager,dataBeanList)
        mVpPager.setAdapter(mCommonHeaderTabAdapter)
        tabs!!.setViewPager(mVpPager)
    }

}