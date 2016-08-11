package com.guigu.test.everydaynews.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.guigu.test.everydaynews.base.BaseFragment;

/**
 * Created by shuwei on 2016/8/10.
 */
public class HomeFragment extends BaseFragment {
    private TextView textView;
    @Override
    protected View initView() {
        textView=new TextView(mContext);

        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("TAG", "首页页面数据被初始化了");
        textView.setGravity(Gravity.CENTER);
        textView.setText("我是主页面");
        textView.setTextSize(30);

    }
}
