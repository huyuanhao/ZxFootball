package com.parsonswang.zxfootball.common.view;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.matches.MatchInfoListFragment;
import com.parsonswang.zxfootball.news.NewsItemFragment;

import java.util.List;

/**
 * Created by parsonswang on 2017/10/23.
 */

public class NewsHeaderTabAdapter extends FragmentPagerAdapter {

    private List<HeaderTabTitle.TabInfo> mDataBeanList;

//    public void setDataBeanList(List<HeaderTabTitle.TabInfo> dataBeens) {
//        mDataBeanList = dataBeens;
//        notifyDataSetChanged();
//    }

    public NewsHeaderTabAdapter(FragmentManager fm,List<HeaderTabTitle.TabInfo> dataBeens) {
        super(fm);
        mDataBeanList = dataBeens;
    }

    @Override
    public Fragment getItem(int position) {
        NewsItemFragment fragment = new NewsItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("news_name", mDataBeanList.get(position).getName());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataBeanList.get(position).getName();
    }

    @Override
    public int getCount() {
        return mDataBeanList == null ? 0 : mDataBeanList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
