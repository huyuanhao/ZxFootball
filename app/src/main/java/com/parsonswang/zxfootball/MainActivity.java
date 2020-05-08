package com.parsonswang.zxfootball;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.zxfootball.common.utils.BottomNavagationHelper;
import com.parsonswang.zxfootball.data.DataFragment;
import com.parsonswang.zxfootball.matches.MatchesFragment;
import com.parsonswang.zxfootball.news.NewsFragment;
import com.parsonswang.zxfootball.news.NewsFragment1;
import com.parsonswang.zxfootball.price.PriceFragment;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_NEWS = "news";
    private static final String TAG_MATCHFRAGMENT = "match";
    private static final String TAG_DATAFRAGMENT = "data";
    private static final String TAG_PRICEFRAGMENT = "price";

    private int mCurrMenuItemId;
    private Fragment mCurrFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final int itemId = item.getItemId();
            selectFragment(itemId);
//            switch (itemId) {
//                case R.id.navigation_match:
//                    Timber.i("navigation_match click");
////                    switchFragment(itemId, TAG_MATCHFRAGMENT);
//                    selectFragment(itemId);
//                    return true;
//                case R.id.navigation_news:
//                    Timber.i("navigation_news click");
////                    switchFragment(itemId, TAG_NEWS);
//                    selectFragment(2);
//                    return true;
//                case R.id.navigation_data:
//                    Timber.i("navigation_data click");
////                    switchFragment(itemId, TAG_DATAFRAGMENT);
//                    selectFragment(3);
//                    return true;
//                case R.id.navigation_price:
//                    Timber.i("navigation_price click");
////                    switchFragment(itemId, TAG_PRICEFRAGMENT);
//                    selectFragment(4);
//                    return true;
//            }
            return false;
        }
    };

    private void switchFragment(int menuItemId, String tag) {
        //如果已经是当前Fragment了也就不用添加了
        if (mCurrMenuItemId == menuItemId) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager == null) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction == null) {
            return;
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        //刚开始就没有该fragment
        if (fragment == null) {
            fragment = createFragment(menuItemId);
            if (fragment == null && !isFinishing()) {
                transaction.commitAllowingStateLoss();
                return;
            }

            transaction.add(R.id.mFlContent, fragment, tag);
        } else {
            transaction.replace(R.id.mFlContent, fragment, tag);
        }

        //隐藏当前fragment
        if (mCurrFragment != null) {
            transaction.hide(mCurrFragment);
        }

        transaction.show(fragment);
        if (!isFinishing()) {
            transaction.commitAllowingStateLoss();
        }

        mCurrFragment = fragment;
        mCurrMenuItemId = menuItemId;

    }

    private Fragment createFragment(int menuItemId) {
        switch (menuItemId) {
            case R.id.navigation_news:
                Timber.i(TAG, "navigation_news createFragment");
                return new NewsFragment();
            case R.id.navigation_match:
                Timber.i(TAG, "navigation_match createFragment");
                return new MatchesFragment();
            case R.id.navigation_data:
                Timber.i(TAG, "navigation_data createFragment");
                return new DataFragment();
            case R.id.navigation_price:
                Timber.i(TAG, "navigation_price createFragment");
                return new PriceFragment();
        }
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeLayoutHelper.getSwipeBackLayout().setEnable(false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavagationHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        switchFragment(R.id.navigation_match, TAG_MATCHFRAGMENT);
        selectFragment(R.id.navigation_match);
    }

    private Fragment fragment1,fragment2,fragment3,fragment4;
    public void selectFragment(int itemId){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (itemId){
            case R.id.navigation_match:
                if(fragment1 == null) {
                    fragment1 = new MatchesFragment();
                }
                addOrShowFragment(fragmentTransaction, fragment1);
                break;
            case R.id.navigation_news:
                if(fragment2 == null) {
                    fragment2 = new NewsFragment();
                }
                addOrShowFragment(fragmentTransaction, fragment2);
                break;
            case R.id.navigation_data:
                if(fragment3 == null) {
                    fragment3 = new DataFragment();
                }
                addOrShowFragment(fragmentTransaction, fragment3);
                break;
            case R.id.navigation_price:
                if(fragment4 == null) {
                    fragment4 = new PriceFragment();
                }
                addOrShowFragment(fragmentTransaction, fragment4);
                break;
            default:
        }
    }
    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private Fragment currentFragment;
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if(currentFragment != null){
            transaction.hide(currentFragment);
        }
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.add(R.id.mFlContent, fragment).commit();
        } else {
            transaction.show(fragment).commit();
        }
        currentFragment = fragment;
    }
}
