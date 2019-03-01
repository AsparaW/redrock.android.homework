package com.mantouland.fakezhihuribao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mantouland.fakezhihuribao.R;

import java.util.List;

public class PageAdapter extends PagerAdapter {
    private Context context;
    private List<Bitmap> pics;
    private List<String> titles;

    public PageAdapter(Context context, List<Bitmap> pics,List titles) {
        this.context = context;
        this.pics = pics;
        this.titles=titles;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
/*        ImageView imageView = new ImageView(context);
        container.addView(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(pics[position%pics.length]);

        return imageView;*/
        View view = View.inflate(context, R.layout.item_page,null);
        TextView tv = view.findViewById(R.id.pageTitle);
        ImageView iv = view.findViewById(R.id.pageImage);
        iv.setImageBitmap(pics.get(position%pics.size()));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        tv.setText(titles.get(position%titles.size()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}