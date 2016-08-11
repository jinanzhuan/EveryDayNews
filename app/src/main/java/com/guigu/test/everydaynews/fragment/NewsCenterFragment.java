package com.guigu.test.everydaynews.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.activity.MainActivity;
import com.guigu.test.everydaynews.adapter.NewsCenterAdapter;
import com.guigu.test.everydaynews.base.BaseFragment;
import com.guigu.test.everydaynews.base.BaseNewsFragment;
import com.guigu.test.everydaynews.bean.NewsCenterBean;
import com.guigu.test.everydaynews.utils.CacheUtils;
import com.guigu.test.everydaynews.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by shuwei on 2016/8/10.
 */
public class NewsCenterFragment extends BaseFragment {
    private static final String TAG = "TAG";
    private TabLayout indicator;
    private ImageButton ib_next_tab;
    private ViewPager viewpager;
    private List<NewsCenterBean.NewsCenterPagerData.ChildrenData> datas;
    private List<BaseNewsFragment> fragments;
    private NewsCenterAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.newscenter_fragment, null);
        indicator = (TabLayout) view.findViewById(R.id.indicator);
        ib_next_tab = (ImageButton) view.findViewById(R.id.ib_next_tab);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);

        //设置滚动模式
        indicator.setTabMode(TabLayout.MODE_SCROLLABLE);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e("TAG", "新闻页面数据被初始化了");

        String saveJson = CacheUtils.getString(mContext, Constants.NEWSCENTER_URL);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }
        getDataFromNet();

    }

    /**
     * 使用okhttp-utils的get请求网络文本数据
     */
    public void getDataFromNet() {

        OkHttpUtils
                .get()
                .url(Constants.NEWSCENTER_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }


    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            super.onBefore(request, id);
        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            super.inProgress(progress, total, id);
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "onError="+e.getMessage());

        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete"+response);
            CacheUtils.putString(mContext, Constants.NEWSCENTER_URL, response);
            processData(response);
        }
    }

    private void processData(String json) {
        NewsCenterBean bean=parseJson(json);
        datas = bean.getData().get(0).getChildren();
        if(datas !=null && datas.size()>0){
            fragments = new ArrayList<>();
            TabDetailFragment tabDetailFragment=null;
            for(int i = 0; i < datas.size(); i++) {
                tabDetailFragment=new TabDetailFragment();
                Bundle args=new Bundle();
                args.putString("url", datas.get(i).getUrl());
                tabDetailFragment.setArguments(args);
                fragments.add(tabDetailFragment);
            }

            MainActivity mainActivity= (MainActivity) mContext;
            adapter = new NewsCenterAdapter(mainActivity.getSupportFragmentManager(),fragments,datas);
            viewpager.setAdapter(adapter);
 /*------    -----------indicator和viewpager进行绑定----------------------------*/
            indicator.setupWithViewPager(viewpager);
        }
    }

    private NewsCenterBean parseJson(String json) {
        return new Gson().fromJson(json,NewsCenterBean.class);
    }
}
