package com.mantouland.fakezhihuribao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;


import com.mantouland.fakezhihuribao.adapter.MyRecAdapter;
import com.mantouland.fakezhihuribao.bean.Comment;
import com.mantouland.fakezhihuribao.controller.NewsController;

public class CommentView extends AppCompatActivity {

    Comment shortComment;
    Comment longComment;
    RecyclerView longRec;
    RecyclerView shortRec;
    int id;
    String TAG="CV";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");
        Log.d(TAG, "onCreate: "+id);
        setRunnableGetComment();
        Log.d(TAG, "onCreate: ");
    }

    void initView(){
        Log.d(TAG, "initView: okg");
        longRec=findViewById(R.id.rvlong);
        shortRec=findViewById(R.id.rvshort);
        MyRecAdapter recAdapterShort = new MyRecAdapter();
        MyRecAdapter recAdapterLong = new MyRecAdapter();
        recAdapterLong.setContext(this);
        recAdapterShort.setContext(this);
        Log.d(TAG, "initView: GETCO"+shortComment.getComments().size());
        Log.d(TAG, "initView: GETCO"+longComment.getComments().size());
        recAdapterShort.setData(shortComment.getComments());
        recAdapterLong.setData(longComment.getComments());
        longRec.setLayoutManager(new LinearLayoutManager(this));
        shortRec.setLayoutManager(new LinearLayoutManager(this));
        longRec.setAdapter(recAdapterLong);
        shortRec.setAdapter(recAdapterShort);
    }
    protected void setRunnableGetComment(){
        new Thread(runnableGetComment).start();
    }
    Runnable runnableGetComment = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "run: "+id);
            shortComment=  NewsController.getInstance().getShortComment(Integer.toString(id));
            longComment =NewsController.getInstance().getLongComment(Integer.toString(id));

            //cover
            handler.sendEmptyMessage(1);

        }
    };
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                initView();
                //
            }
        }
    };
}
