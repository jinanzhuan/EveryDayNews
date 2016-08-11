package com.guigu.test.everydaynews;

import android.app.Application;

/**
 * Created by shuwei on 2016/8/10.
 */
public class NewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();




        //必须调用初始化
//        OkHttpUtils.init(this);

    }
}
