package com.guigu.test.everydaynews.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.base.BaseFragment;
import com.guigu.test.everydaynews.fragment.GovaffairFragment;
import com.guigu.test.everydaynews.fragment.HomeFragment;
import com.guigu.test.everydaynews.fragment.NewsCenterFragment;
import com.guigu.test.everydaynews.fragment.SettingFragment;
import com.guigu.test.everydaynews.fragment.SmartServiceFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RadioGroup mRadioGroup;
    //装5个页面
    private ArrayList<BaseFragment> baseFragments;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    public TextView tvTitle;
    public ImageButton ibMenu;
    public ImageButton ibBack;
    public ImageButton ibTextsize;
    public ImageButton ibShare;
    public FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中常用框架
        mRadioGroup.check(R.id.rb_home);
    }

    private void initData() {
        //初始化fragment，并放入到集合中
        baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());//主页面
        baseFragments.add(new NewsCenterFragment());//新闻中心
        baseFragments.add(new SmartServiceFragment());//智慧服务
        baseFragments.add(new GovaffairFragment());//政要指南
        baseFragments.add(new SettingFragment());//设置


    }

    private void initView() {
        mRadioGroup = (RadioGroup)findViewById(R.id.rg_main);
        flContent= (FrameLayout) findViewById(R.id.fl_content);
        tvTitle = (TextView)findViewById(R.id.tv_title);
        ibMenu = (ImageButton)findViewById(R.id.ib_menu);
        ibBack = (ImageButton)findViewById(R.id.ib_back);
        ibTextsize = (ImageButton)findViewById(R.id.ib_textsize);
        ibShare = (ImageButton)findViewById(R.id.ib_share);
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home :
                    position=0;
                    tvTitle.setText("主页面");
                    break;
                case R.id.rb_news :
                    position=1;
                    tvTitle.setText("新闻页面");
                    break;
                case R.id.rb_smartservice :
                    position=2;
                    tvTitle.setText("智慧页面");
                    break;
                case R.id.rb_govaffair:
                    position=3;
                    tvTitle.setText("政要页面");
                    break;
                case R.id.rb_setting :
                    position=4;
                    tvTitle.setText("设置页面");
                    break;
                default:
                    position=0;
                    tvTitle.setText("主页面");
                    break;
            }
            BaseFragment to = getFragment();

            switchFrament(mContent,to);
        }
    }

    /**
     *
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }

    }

    /**
     * 根据位置得到对应的Fragment
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = baseFragments.get(position);
        return fragment;
    }
}
