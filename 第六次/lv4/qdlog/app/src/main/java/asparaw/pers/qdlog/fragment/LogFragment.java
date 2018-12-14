package asparaw.pers.qdlog.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import asparaw.pers.qdlog.DataBean.Status;
import asparaw.pers.qdlog.R;
import asparaw.pers.qdlog.activity.MainActivity;
import asparaw.pers.qdlog.adapter.MyRecAdapter;

public class LogFragment extends Fragment {
    View basicView;
    ArrayList<Status> data = new ArrayList();
//
    //
    RecyclerView mRecyclerView;
    MyRecAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        basicView=inflater.inflate(R.layout.frag_rec,container,false);
        mRecyclerView = basicView.findViewById(R.id.review);
        setRec();
        return basicView;
    }
    public void setData(ArrayList<Status> data){
        //this.data = (ArrayList<Status>)data.clone();
        for (Status temp : data){
            String TAG  = "LOAD DATA";
            Log.d(TAG, "setData: "+temp.getName()+temp.getDate());
            this.data.add(temp);
        }

    }
    private void setRec() {
        mAdapter = new MyRecAdapter();
        mAdapter.setData(data);
        mRecyclerView.setAdapter(mAdapter);//给mRecyclerView设置adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//给mRecyclerView设置LayoutManager
    }

}
