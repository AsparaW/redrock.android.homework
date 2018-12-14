package asparaw.pers.qdlog.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import asparaw.pers.qdlog.DataBean.Status;
import asparaw.pers.qdlog.R;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder> {
    private List<Status> mDataList = new ArrayList<>();

//    //adapter构造函数直接传入数据
//    public RecTestAdapter(List<String> data) {
//        this.mDataList = data;
//    }

    public void addNewData(List<Status> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }


    public void setData(List<Status> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        Status status = mDataList.get(i);
        viewHolder.Avatar.setImageResource(status.getResID());
        viewHolder.dataname.setText(status.getName());
        viewHolder.dataTime.setText(status.getTime());
        viewHolder.dataDate.setText(status.getDate());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemRootView;
        ImageView Avatar;
        TextView dataname;
        TextView dataTime;
        TextView dataDate;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemRootView = itemView;
            Avatar = itemView.findViewById(R.id.imageView);
            dataDate = itemView.findViewById(R.id.dataDate);
            dataTime = itemView.findViewById(R.id.datatime);
            dataname = itemView.findViewById(R.id.dataname);

        }
    }


}
