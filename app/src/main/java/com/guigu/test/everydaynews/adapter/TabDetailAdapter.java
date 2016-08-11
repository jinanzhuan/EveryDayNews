package com.guigu.test.everydaynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guigu.test.everydaynews.R;
import com.guigu.test.everydaynews.bean.TabDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuwei on 2016/8/11.
 */
public class TabDetailAdapter extends RecyclerView.Adapter<TabDetailAdapter.ViewHolder> {
    private Context mContext;
    private List<TabDetailBean.TabDetailPagerData.TopnewsData> mTopnews = new ArrayList<>();

    public TabDetailAdapter(Context context, List<TabDetailBean.TabDetailPagerData.TopnewsData> topnews) {
        mContext=context;
        if(topnews!=null && topnews.size()>0){
            mTopnews=topnews;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView=new ImageView(mContext);
        View view=View.inflate(mContext, R.layout.item_tab_detai,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = mTopnews.get(position).getUrl();
        if(url!=null){
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.home_scroll_default)
                    .crossFade()
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mTopnews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_top);
        }
    }
}
//    private Context mContext;
//    private List<TabDetailBean.TabDetailPagerData.TopnewsData> mTopnews=new ArrayList<>();
//    public TabDetailAdapter(Context context, List<TabDetailBean.TabDetailPagerData.TopnewsData> topnews) {
//        mContext=context;
//        if(topnews!=null && topnews.size()>0){
//            mTopnews=topnews;
//        }
//
//    }
//
//    @Override
//    public int getCount() {
//        return mTopnews.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view==object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView=new ImageView(mContext);
//
//        String url = mTopnews.get(position).getUrl();
//        if(url!=null){
//            Glide.with(mContext)
//                    .load(url)
//                    .centerCrop()
//                    .placeholder(R.drawable.home_scroll_default)
//                    .crossFade()
//                    .into(imageView);
//        }
//        container.addView(imageView);
//        return imageView;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
////        super.destroyItem(container, position, object);
//        container.removeView((View) object);
//    }
//}
