package com.guigu.test.everydaynews.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuwei on 2016/8/10.
 */
public class GuideAdapter extends PagerAdapter{
    private Context mContext;
    private List<ImageView> mImageViews=new ArrayList<>();
    public GuideAdapter(Context context, List<ImageView> imageViews) {
        mContext=context;
        if(imageViews!=null && imageViews.size()>0){
            mImageViews.clear();
            mImageViews.addAll(imageViews);
        }
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
