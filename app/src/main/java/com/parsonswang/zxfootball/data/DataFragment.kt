package com.parsonswang.zxfootball.data

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.parsonswang.zxfootball.R
import com.parsonswang.zxfootball.bean.HeaderTabTitle
import com.parsonswang.zxfootball.matches.detail.MatchDetailActivity
import com.parsonswang.zxfootball.news.NewsContract
import com.parsonswang.zxfootball.news.NewsPresenter
import kotlinx.android.synthetic.main.fragment_datas.*
import timber.log.Timber

/**
 * Created by parsonswang on 2017/10/13.
 */
class DataFragment : Fragment(), NewsContract.NewsView,BaseQuickAdapter.OnItemClickListener {
    var dataAdapter:DataAdapter ? = null
    var presenter : NewsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("DataFragment---onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("DataFragment---onCreateView")
        return inflater.inflate(R.layout.fragment_datas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titlebar.setTitle("赛事库")
        titlebar.hideRightArrow()
        dataAdapter = DataAdapter()
        dataAdapter?.setOnItemClickListener(this)
        recyclerView.run {
            layoutManager = GridLayoutManager(context,3)
            adapter = dataAdapter
        }
        presenter = NewsPresenter(this)
        presenter!!.start()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val intent = Intent(activity, DataDetailActivity::class.java)
        intent.putExtra("news_name", dataAdapter?.getItem(position)?.name)
        intent.putExtra("id", dataAdapter?.getItem(position)?.id.toString())
        activity?.startActivity(intent)
    }

    override fun showHeaderTabTitle(headerTabTitle: HeaderTabTitle?) {
        val dataBeanList = headerTabTitle!!.data
        dataAdapter?.setNewData(dataBeanList)
    }
}