package com.parsonswang.zxfootball.common.mvp;

/**
 * Created by wangchun on 2017/12/30.
 */

public interface IBaseView {

    default void showLoding(){};

    default void hideLoding(){};

    default void showExceptionView(){};
}
