package com.mantouland.fakezhihuribao.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mantouland.fakezhihuribao.R;
import com.mantouland.fakezhihuribao.ViewPassage;

import java.util.List;

public class PageAdapter extends PagerAdapter {
    private Context context;
    private List<Bitmap> pics;
    private List<String> titles;
    private List<Integer> id;

    public PageAdapter(Context context, List<Bitmap> pics,List titles,List<Integer> id) {
        this.context = context;
        this.pics = pics;
        this.titles=titles;
        this.id=id;
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

        View view = View.inflate(context, R.layout.item_page,null);
        TextView tv = view.findViewById(R.id.pageTitle);
        ImageView iv = view.findViewById(R.id.pageImage);
        iv.setImageBitmap(pics.get(position%pics.size()));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        tv.setText(titles.get(position%titles.size()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onItemClickListener.onItemClick(viewHolder.itemView,i);
                Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("webid",id.get(position%pics.size()));
                Intent intent=new Intent(v.getContext(),ViewPassage.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}