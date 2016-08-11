package com.guigu.test.everydaynews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by shuwei on 2016/8/1.
 */
//水平方向的viewPager
public class HorizontalScrollViewPager extends ViewPager {
    public HorizontalScrollViewPager(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private float startX, startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //先拦截给自己
                getParent().requestDisallowInterceptTouchEvent(true);
                //记录其实坐标
                startX = ev.getRawX();
                startY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //1.来到新的坐标
                float endX = ev.getRawX();
                float endY = ev.getRawY();
                //2
                float distanceX = endX - startX;
                float distanceY = endY - startY;
                //3.判断移动方向

                    //水平移动
//                    (2)水平方向滑动
//                    a.如果是第0个位置，并且滑动方向是从左到右滑动（endX-startX>0时）
//                    getparent().requstDisallowInterceptTounchEvent(false);

//                    b.如果是页面的最后一个位置，并且滑动方向是从右向左滑动
//                    getparent().requstDisallowInterceptTounchEvent(false);
//                    c.其他中间部分
//                    getparent().requstDisallowInterceptTounchEvent(true);
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    Log.e("TAG", "getCurrentItem="+getCurrentItem());
                    if (getCurrentItem() == 0 && distanceX > 0) {
                        Log.e("TAG", "第一个不拦截");
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (getCurrentItem() == getAdapter().getCount() - 1 && distanceX < 0) {
                        Log.e("TAG", "最后一个不拦截");
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        Log.e("TAG", "其他的进行拦截");
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

                } else {
                    Log.e("TAG", "垂直方向不进行拦截");
                    //垂直移动
                    getParent().requestDisallowInterceptTouchEvent(false);
                }


                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
