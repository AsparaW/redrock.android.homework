package com.mantouland.fakezhihuribao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mantouland.fakezhihuribao.controller.NewsController;
import com.mantouland.fakezhihuribao.tool.HttpHelper;

import java.sql.NClob;
import java.util.ArrayList;
import java.util.List;

public class ViewPassage extends AppCompatActivity {

    private ViewPager vpArticle;
    private MyPagerAdapter myAdapter;
    private List<View> mListViews;
    android.support.v7.widget.Toolbar toolbar;
    private static final String TAG = "VP";
    int webid;
    String tempWeb;
    String tempCss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_passage);

        //set toolbar
        toolbar=findViewById(R.id.webtoolbar);
        this.setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        webid = bundle.getInt("webid");
        String url=NewsController.getApiUrlDetail()+webid;
        mListViews = new ArrayList<>();

        bindView();
        HttpHelper.setUrl(url);
        setPassageGet();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_like:
                break;
            case R.id.item_comment:
                Bundle bundle = new Bundle();
                Intent intent3 = getIntent();
                Bundle bundle2 = intent3.getExtras();
                int id = bundle2.getInt("webid");
                Log.d(TAG, "onOptionsItemSelected: "+id);
                bundle.putInt("id",id);
                Intent intent=new Intent(this,CommentView.class);
                intent.putExtras(bundle);
                this.startActivity(intent);
                break;
            case R.id.item_star:
                break;
            default:
        }
        return true;
    }
    void bindView(){
        toolbar=findViewById(R.id.webtoolbar );
    }


    private void addView(List<View> viewList) {

        WebView webView = new WebView(this);
        //webView.loadUrl(url);
        //tempWeb = tempWeb.replace("<img","<img style=\"max-width:100%;height:auto");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        //webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setDefaultFontSize(32);

        //webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);


        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示

        webView.loadDataWithBaseURL(tempCss, tempWeb , "text/html", "UTF-8", null);



        viewList.add(webView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    protected void setPassageGet(){
        new Thread(passageGet).start();
    }
    Runnable passageGet = new Runnable() {
        @Override
        public void run() {
            HttpHelper.getInstance().run();
            tempWeb=NewsController.getInstance().getArticle(Integer.toString(webid)).getBody();
            String cssUrl=NewsController.getInstance().getArticle(Integer.toString(webid)).getCss().get(0);
            cssUrl=cssUrl.replace("http","https");
            Log.d(TAG, "run: "+cssUrl);
            HttpHelper.setUrl(cssUrl);
            HttpHelper.getInstance().run();
            Log.d(TAG, "run: "+HttpHelper.getTemp());
            tempCss=HttpHelper.getTemp();
            Log.d(TAG, "run:CSS "+tempCss);
            handler.sendEmptyMessage(1);
        }
    };

    void okGet(){
        addView(mListViews);
        myAdapter = new MyPagerAdapter();
        vpArticle = findViewById(R.id.webv);
        vpArticle.setAdapter(myAdapter);
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                okGet();
                Log.d(TAG, "handleMessage: OKGET");
                Log.d(TAG, "handleMessage: OKGET"+tempWeb);
            }
        }
    };


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }
    }
}

