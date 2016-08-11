package com.guigu.test.everydaynews.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.guigu.test.everydaynews.adapter.TabDetailAdapter;
import com.guigu.test.everydaynews.base.BaseNewsFragment;
import com.guigu.test.everydaynews.bean.TabDetailBean;
import com.guigu.test.everydaynews.utils.CacheUtils;
import com.guigu.test.everydaynews.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by shuwei on 2016/8/10.
 */
public class TabDetailFragment extends BaseNewsFragment {
    private List<TabDetailBean.TabDetailPagerData.TopnewsData> topnews;
    private TabDetailAdapter adapter;
    private String url;

    @Override
    protected void initData() {
        super.initData();

        Bundle bundle = this.getArguments();
        String url=bundle.getString("url","");

        url = Constants.BASE_URL+url;
        Log.e("TAG", "url==" + url);
        String saveJson = CacheUtils.getString(mContext, Constants.TABDETAIL_URL);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(url)
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
            Log.e("TAG", "onResponse：complete"+response);

            switch (id) {
                case 100:
                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                    break;
            }

            CacheUtils.putString(mContext, Constants.TABDETAIL_URL, response);
            processData(response);
        }
    }

    private void processData(String json) {
        TabDetailBean bean=parseJson(json);
        topnews=bean.getData().getTopnews();

        adapter = new TabDetailAdapter(mContext, topnews);
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
    }

    private TabDetailBean parseJson(String json) {
        return new Gson().fromJson(json,TabDetailBean.class);
    }
}
