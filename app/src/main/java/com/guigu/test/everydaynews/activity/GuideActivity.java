package com.guigu.test.everydaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.adapter.GuideAdapter;
import com.guigu.test.everydaynews.utils.CacheUtils;
import com.guigu.test.everydaynews.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements View.OnClickListener {

    public static final String START_MAIN = "START_MAIN";

    private ViewPager viewpager;
    private Button btnStartMain;
    private LinearLayout llStartIcon;
    private int[] ids;
    private List<ImageView> imageViews;
    private List<ImageView> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();

        initData();

        initListener();
    }

    private void initListener() {
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initData() {
        //得到集合数据
        ids = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        //绘制圆点
        ImageView imageView=null;
        imageViews = new ArrayList<>();
        points = new ArrayList<>();
        for(int i = 0; i < ids.length; i++) {
            imageView=new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);

            //有几张图片设置几个点
            imageView=new ImageView(this);
            imageView.setBackgroundResource(R.drawable.point_bg_selector);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(DensityUtils.dip2px(this,10),DensityUtils.dip2px(this,10));
            if(i!=0){
                params.leftMargin=DensityUtils.dip2px(this,10);
            }
            imageView.setLayoutParams(params);
            imageView.setEnabled(false);
            points.add(imageView);
            llStartIcon.addView(imageView);
        }
        //设置适配器
        GuideAdapter adapter = new GuideAdapter(this, imageViews);
        viewpager.setAdapter(adapter);
        checkPointStatus(0);
        btnStartMain.setVisibility(View.GONE);

    }


    /**
     *初始化view
     */
    private void initViews() {
        viewpager = (ViewPager)findViewById( R.id.viewpager );
        btnStartMain = (Button)findViewById( R.id.btn_start_main );
        llStartIcon = (LinearLayout)findViewById( R.id.ll_start_icon );

        btnStartMain.setOnClickListener( this );
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        if (v == btnStartMain) {
            CacheUtils.putBoolean(this, START_MAIN, true);
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            checkPointStatus(position);
            if (position == imageViews.size() - 1) {
                btnStartMain.setVisibility(View.VISIBLE);
            } else {
                btnStartMain.setVisibility(View.GONE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void checkPointStatus(int position) {
        for(int i = 0; i < points.size(); i++) {
          if(i==position){
              points.get(i).setEnabled(true);
          }else {
              points.get(i).setEnabled(false);
          }
        }
    }
}
