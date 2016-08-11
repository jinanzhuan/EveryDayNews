package com.guigu.test.everydaynews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.view.HorizontalScrollViewPager;

/**
 * Created by shuwei on 2016/8/10.
 */
public class BaseNewsFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;
    public HorizontalScrollViewPager viewPager;
    public TextView tv_title;
    public LinearLayout ll_point_group;
    public RecyclerView listview;
    public MaterialRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 强制子类重写，实现子类特有的ui
     * @return
     */
    protected View initView(){
        View view = View.inflate(mContext, R.layout.base_news_fragment, null);
        refreshLayout= (MaterialRefreshLayout) view.findViewById(R.id.refreshLayout);
        listview= (RecyclerView) view.findViewById(R.id.listView);

        View topnewsView = View.inflate(mContext, R.layout.topnews, null);
        viewPager = (HorizontalScrollViewPager) topnewsView.findViewById(R.id.viewpager);
        tv_title = (TextView) topnewsView.findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) topnewsView.findViewById(R.id.ll_point_group);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected void initData() {

    }
}
