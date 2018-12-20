package com.asparaw.weatherrecv.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asparaw.weatherrecv.R;
import com.asparaw.weatherrecv.controller.WeatherController;
import com.asparaw.weatherrecv.databean.OtherWeather;

import java.util.ArrayList;
import java.util.List;



public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder> {

    private static final String month = WeatherController.getInstance().getMonth();
    private static final String color[] = {"#FFECEC","#FFF3EE","#FFFFF4","#F0FFF0","#F3F3FA","#FFF7FF"};
    private List<OtherWeather> mDataList = new ArrayList<>();
    Context context;
//    //adapter构造函数直接传入数据
//    public RecTestAdapter(List<String> data) {
//        this.mDataList = data;
//    }
    MyViewHolder holder;
    public void addNewData(List<OtherWeather> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<OtherWeather> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Log.d("BIND", "ok ");
        OtherWeather status = mDataList.get(i);
/*
        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                    String tel = mDataList.get(position).getTel();
                    Toast.makeText(context, "show"+tel, Toast.LENGTH_SHORT).show();//

                }
            });
        }
*/
        //get month
        //
        viewHolder.textFengXiang.setText("风向 : "+ status.getFengxiang());
        //"风向 : 无持续风向"
        viewHolder.textFengLi.setText("风力 : "+status.getFengli());
        //"风力 : x级"
        viewHolder.textType.setText(status.getType());
        //
        viewHolder.textDate.setText(month+"月"+status.getDate());
        viewHolder.textLowHigh.setText(status.getLow()+" "+status.getHigh());
        viewHolder.itemView.setBackgroundColor(Color.parseColor(color[i]));
        //sample 低温 2℃ 高温 40℃
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemRootView;
        TextView textDate;
        TextView textLowHigh;
        TextView textFengXiang;
        TextView textType;
        TextView textFengLi;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRootView = itemView;
            textDate=itemView.findViewById(R.id.tv_date);
            textLowHigh=itemView.findViewById(R.id.tv_lowhigh);
            textFengXiang=itemView.findViewById(R.id.tv_fengxiang);
            textType=itemView.findViewById(R.id.tv_type);
            textFengLi=itemView.findViewById(R.id.tv_fengli);
        }
    }


}
