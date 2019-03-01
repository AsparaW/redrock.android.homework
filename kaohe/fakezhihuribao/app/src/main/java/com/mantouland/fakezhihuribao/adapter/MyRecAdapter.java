package com.mantouland.fakezhihuribao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mantouland.fakezhihuribao.R;
import com.mantouland.fakezhihuribao.bean.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder> {


    private List<Comment.CommentsBean> mDataList = new ArrayList<>();
    Context context;

    MyViewHolder holder;
    public void addNewData(List<Comment.CommentsBean> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<Comment.CommentsBean> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        Log.d("TEST333", "setData: "+dataList.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("", "onCreateViewHolder: ");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
         MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        Log.d("BIND123123", "ok ");
         Comment.CommentsBean status= mDataList.get(i);

        //
        //viewHolder.imageView.setImageBitmap(status.getPic());

        Log.d("SETLIKE", "onBindViewHolder: ");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date curDate = new Date(status.getTime()*1000L);
        String date = sDateFormat.format(curDate);
        viewHolder.textContent.setText(status.getContent().toString());
        viewHolder.textTime.setText(date);
        viewHolder.textName.setText(status.getAuthor().toString());
        viewHolder.textLikes.setText(Integer.toString(status.getLikes()));

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemRootView;
        TextView textName;
        TextView textTime;
        TextView textContent;
        TextView textLikes;
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRootView = itemView;
            textName=itemView.findViewById(R.id.thenametext);
            textTime=itemView.findViewById(R.id.thetimetext);
            textContent=itemView.findViewById(R.id.words1);
            textLikes=itemView.findViewById(R.id.likestext);
        }
    }


}
