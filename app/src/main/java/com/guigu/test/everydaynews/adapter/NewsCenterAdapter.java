package com.guigu.test.everydaynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guigu.test.everydaynews.base.BaseNewsFragment;
import com.guigu.test.everydaynews.bean.NewsCenterBean;

import java.util.List;

/**
 * Created by shuwei on 2016/8/10.
 */
public class NewsCenterAdapter extends FragmentPagerAdapter {
    private List<BaseNewsFragment> fragments;
    List<NewsCenterBean.NewsCenterPagerData.ChildrenData> mDatas;

    public NewsCenterAdapter(FragmentManager fm, List<BaseNewsFragment> fragments, List<NewsCenterBean.NewsCenterPagerData.ChildrenData> datas) {
        super(fm);
        this.fragments=fragments;
        this.mDatas=datas;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).getTitle();
    }
}
