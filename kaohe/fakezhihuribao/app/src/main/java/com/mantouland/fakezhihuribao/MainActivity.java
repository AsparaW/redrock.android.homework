package com.mantouland.fakezhihuribao;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mantouland.fakezhihuribao.adapter.PageAdapter;
import com.mantouland.fakezhihuribao.adapter.RecAdapter;
import com.mantouland.fakezhihuribao.bean.HotNew;
import com.mantouland.fakezhihuribao.bean.New;
import com.mantouland.fakezhihuribao.controller.NewsController;

import org.w3c.dom.Text;

import java.sql.NClob;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ASK_JSON_OVER =1;
    private static final int ASK_PIC_OVER =2;
    private static final int ASK_IMAGE_FIRST_OVER =3;
    private static final String TAG="MA";
    int nowNew=0;
    Toolbar toolbar;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    LinearLayout linearLayout;
    Menu menu;
    RecAdapter recAdapter;
    RecyclerView recyclerView;
    LinearLayout mainLinearLayout;
    NestedScrollView scrollView;

    private List<ImageView> mList=new ArrayList<>();
    private List<New> todayList=new ArrayList<>();
    private List<New> tempMore =new ArrayList<>();

    HotNew hotNew;
    boolean isPicStop=false;
    int PAGER_TIME=10000;
    int width;
    int height;
    HashMap<RecyclerView,Integer> dateMap;
    List<Bitmap> imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initURLHelper();//check network
        bindViews();
        setToolbar();
        askToday();
    }

    void initView(){
        setViewPager();
        autoPlayView();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void setToolbar(){
        this.setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toolbar.setOverflowIcon(getDrawable(R.drawable.ic_more_vert_black_24dp));
            }
            toolbar.setTitleTextColor(Color.WHITE);
        }
    }
    protected void bindViews(){
        toolbar=findViewById(R.id.toolbar);
        viewPager=findViewById(R.id.viewpager);
        linearLayout=findViewById(R.id.linearLayout);
        scrollView=findViewById(R.id.scrollview);
    }



    @SuppressLint("ClickableViewAccessibility")
    protected void setList(){

        mainLinearLayout=findViewById(R.id.mainll);

        LayoutInflater inflater = this.getLayoutInflater();
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.frag_main, null);
        mainLinearLayout.addView(view);
        recyclerView= (RecyclerView) view.getChildAt(1);
        TextView textView=(TextView)view.getChildAt(0);
        textView.setText("今日热闻");


        recAdapter.setData(todayList);

        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
        //
        //
        //
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:{
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        if(scrollView.getChildAt(0).getMeasuredHeight()<=scrollView.getScrollY()+scrollView.getHeight()){
                            askMore();
                        }else{
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    protected void setMoreList(){

        mainLinearLayout=findViewById(R.id.mainll);

        LayoutInflater inflater = this.getLayoutInflater();
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.frag_main, null);
        mainLinearLayout.addView(view);
        RecyclerView recyclerView= (RecyclerView) view.getChildAt(1);
        TextView textView=(TextView)view.getChildAt(0);
        textView.setText(tempMore.get(0).getTime());

        List<New> newList=tempMore;
        RecAdapter recAdapter=new RecAdapter();
        recAdapter.setContext(this);
        recAdapter.setData(newList);
        Log.d(TAG, "setMoreList: "+newList.get(0).getPic());
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);
        //
        //
        //
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch(event.getAction()){
                    case MotionEvent.ACTION_MOVE:{
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        if(scrollView.getChildAt(0).getMeasuredHeight()<=scrollView.getScrollY()+scrollView.getHeight()){
                            Log.d(TAG, "onTouch: ");
                            askMore();
                        }else{
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }




    private void autoPlayView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isPicStop){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(PAGER_TIME);
                }
            }
        }).start();
    }

    protected void setViewPager(){
        List<Bitmap> imgRes=imageRes;
        for (int i=0;i<imgRes.size();i++){
            ImageView imageView=new ImageView(this);
            imageView.setImageBitmap(imgRes.get(i));
            mList.add(imageView);
        }
        List<String> stringList= new ArrayList<>();
        for (int i=0;i<hotNew.getTop_stories().size();i++){
            stringList.add(hotNew.getTop_stories().get(i).getTitle());
        }
        List<Integer> id=new ArrayList<>();
        for (int i=0;i<hotNew.getTop_stories().size();i++){
            id.add(hotNew.getTop_stories().get(i).getId());
        }
        pagerAdapter=new PageAdapter(this,imgRes,stringList,id);
        Log.d(TAG, "setViewPager: "+pagerAdapter);
        viewPager.setAdapter(pagerAdapter);
        //
        //
        //


    }
    private void setRec() {
        recAdapter = new RecAdapter();
        recAdapter.setContext(this);
        setAskImageFirstOver();
    }


    private boolean checkPermission() {
        boolean hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED;
        return hasPermission;
    }

     private void requestPermission() {
        ActivityCompat.requestPermissions
                (this, new String[]{Manifest.permission.INTERNET}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
        }
    }

    protected void initURLHelper() {
        if (!checkPermission()) {
            requestPermission();
        }
    }

    void getScreenInfo() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    protected void askToday() {
        new Thread(runnableGetToday).start();
    }


    Runnable runnableGetToday = new Runnable() {
        @Override
        public void run() {
            hotNew=  NewsController.getInstance().getHotNew();
            //cover
            handler.sendEmptyMessage(ASK_JSON_OVER);

            }
    };

    protected void askMore() {
        NewsController.getInstance().dateMinus();
        new Thread(runnableGetMore).start();
    }


    Runnable runnableGetMore = new Runnable() {
        @Override
        public void run() {
           tempMore=NewsController.getInstance().getBeforeNew();
            //cover
            handler.sendEmptyMessage(4);

        }
    };


    protected void setAskImageFirstOver(){
        new Thread(runnableGetTodayImage).start();
    }
    Runnable runnableGetTodayImage = new Runnable() {
        @Override
        public void run() {
            todayList=NewsController.getInstance().hotToNews();
            //cover
            handler.sendEmptyMessage(ASK_IMAGE_FIRST_OVER);
        }
    };

    //


    Runnable runnableGetPic = new Runnable() {
        @Override
        public void run() {
            imageRes=  NewsController.getInstance().getPic();
            //cover
            handler.sendEmptyMessage(ASK_PIC_OVER);

        }
    };

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ASK_JSON_OVER) {
                askPic();
            } else if (msg.what == ASK_PIC_OVER) {
                initView();
                setRec();
            }else if (msg.what==ASK_IMAGE_FIRST_OVER){
                setList();
            }else if (msg.what==4){
                setMoreList();
            }
        }
    };
        public void askPic(){
            new Thread(runnableGetPic).start();
        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                Toast.makeText(this, "功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_setting:
                Toast.makeText(this, "功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }



public void getToday(){

}
public void getBefore(){

}

}
