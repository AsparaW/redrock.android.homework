package com.mantouland.fakezhihuribao.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mantouland.fakezhihuribao.MainActivity;
import com.mantouland.fakezhihuribao.R;
import com.mantouland.fakezhihuribao.ViewPassage;
import com.mantouland.fakezhihuribao.bean.New;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {


    private List<New> mDataList = new ArrayList<>();
    Context context;
    //    //adapter构造函数直接传入数据
//    public RecTestAdapter(List<String> data) {
//        this.mDataList = data;
//    }
    MyViewHolder holder;
    public void addNewData(List<New> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<New> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, final int i) {
        Log.d("BIND", "ok ");
        final New status = mDataList.get(i);

        //
        viewHolder.imageView.setImageBitmap(status.getPic());
        viewHolder.textTitle.setText(status.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onItemClickListener.onItemClick(viewHolder.itemView,i);
                Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("webid",status.getId());
                bundle.putString("time",status.getTime());
                Intent intent=new Intent(v.getContext(),ViewPassage.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemRootView;
        TextView textTitle;
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRootView = itemView;
            textTitle=itemView.findViewById(R.id.titleNew);
            imageView=itemView.findViewById(R.id.titleImage);
        }
    }
/*

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(RecAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
*/

}
