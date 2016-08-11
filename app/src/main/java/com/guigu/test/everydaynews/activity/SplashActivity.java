package com.guigu.test.everydaynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.utils.CacheUtils;

public class SplashActivity extends Activity {
    private RelativeLayout rl_splashn_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl_splashn_root = (RelativeLayout)findViewById(R.id.rl_splashn_root);

        //旋转动画
        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);

        //透明动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true);

        //拉伸动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);

        rl_splashn_root.startAnimation(set);

        //设置动画监听
        set.setAnimationListener(new SplashAnimationListener());

    }

    private class SplashAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            boolean isStartMainActivity = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
            if (isStartMainActivity) {//如果已经启动过主页面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            } else {//没有启动过主页面
                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
            }
            //关闭当前页面
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
