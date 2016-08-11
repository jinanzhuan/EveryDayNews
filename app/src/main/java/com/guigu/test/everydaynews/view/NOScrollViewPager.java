package com.guigu.test.everydaynews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by shuwei on 2016/7/30.
 */
//自定义不可以滑动的viewpager
public class NOScrollViewPager extends ViewPager {
    public NOScrollViewPager(Context context) {
        this(context, null);
    }

    public NOScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //把viewpager的滑动事件消化掉
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    //关于事件的拦截与反拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
