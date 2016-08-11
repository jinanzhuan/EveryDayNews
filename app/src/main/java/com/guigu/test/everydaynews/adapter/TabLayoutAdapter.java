package com.guigu.test.everydaynews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.guigu.test.everydaynews.bean.NewsCenterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuwei on 2016/8/10.
 */
public class TabLayoutAdapter extends PagerAdapter {
    private Context mContext;
    private List<NewsCenterBean.NewsCenterPagerData> mDatas=new ArrayList<>();
    public TabLayoutAdapter(Context context, List<NewsCenterBean.NewsCenterPagerData> datas) {
        mContext=context;
        if(datas!=null && datas.size()>0){
            mDatas.clear();
            mDatas.addAll(datas);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
