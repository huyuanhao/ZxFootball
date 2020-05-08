package com.parsonswang.zxfootball.news;

import com.parsonswang.zxfootball.bean.GoalPlayers;
import com.parsonswang.zxfootball.bean.HeaderTabTitle;
import com.parsonswang.zxfootball.bean.MatchDetailHeaderInfoBean;
import com.parsonswang.zxfootball.bean.MatchPlayerStatInfo;
import com.parsonswang.zxfootball.bean.MatchStatBean;
import com.parsonswang.zxfootball.bean.MatchSummary;
import com.parsonswang.zxfootball.bean.MatchesBean;
import com.parsonswang.zxfootball.bean.News;
import com.parsonswang.zxfootball.bean.NewsBean;
import com.parsonswang.zxfootball.common.mvp.IBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun on 2017/10/22.
 */

public class NewsContract {

    public interface NewsView {
         void showHeaderTabTitle(HeaderTabTitle headerTabTitle);
    }

    public interface INewsInfoView {
         void showNewsInfoList(List<NewsBean> bean);
    }


    public interface INewsPresenter {

        void getNewsInfos(int page, String word);

    }
}
