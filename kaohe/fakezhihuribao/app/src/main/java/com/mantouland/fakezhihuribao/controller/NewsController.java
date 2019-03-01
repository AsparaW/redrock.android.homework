package com.mantouland.fakezhihuribao.controller;

import android.graphics.Bitmap;
import android.util.Log;

import com.mantouland.fakezhihuribao.bean.Article;
import com.mantouland.fakezhihuribao.bean.Comment;
import com.mantouland.fakezhihuribao.bean.Extra;
import com.mantouland.fakezhihuribao.bean.HotNew;
import com.mantouland.fakezhihuribao.bean.New;
import com.mantouland.fakezhihuribao.tool.HttpHelper;
import com.mantouland.fakezhihuribao.tool.JsonResolver;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsController {
    private static String jsonStr;

    String date;

    HotNew hotNew=new HotNew();
    private static final String apiUrl = "https://news-at.zhihu.com/api/4/news/latest";
    private static final String apiUrlDetail ="https://news-at.zhihu.com/api/4/news/";
    private static final String apiUrlExtra="https://news-at.zhihu.com/api/4/story-extra/";
    private static final String apiUrlBefore="https://news-at.zhihu.com/api/4/news/before/";
    private static final String apiUrlComment="https://news-at.zhihu.com/api/4/story/";
    private static final String apiUrlShortSuf="/short-comments";
    private static final String apiUrlLongSuf="/long-comments";

    private static final String TAG = "NEWSTEST";
    private static final int PICSIZE=5;
    private static final NewsController instance = new NewsController();
    private NewsController(){
            setDate();
    }
    public void setDate(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());
        date = sDateFormat.format(curDate);
    }
    public void dateMinus(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis()- 86400000L);
        date = sDateFormat.format(curDate);
    }
    public static NewsController getInstance(){
        return instance;
    }

    public static String getApiUrlDetail() {
        return apiUrlDetail;
    }

    public HotNew getHotNew (){
        HttpHelper.setUrl(apiUrl);
        new HttpHelper().run();
        try {
            hotNew=JsonResolver.getInstance().infoDealer(HttpHelper.getTemp());
        } catch (JSONException e) {
            Log.e(TAG, "init: JSONWRONG");
            e.printStackTrace();
        }
        Log.d(TAG, "getPic: "+hotNew.getTop_stories().get(0));
        return hotNew;
    }
    public List<Bitmap> getPic(){
        List<Bitmap> temp = new ArrayList<>();
        for (int i=0;i<PICSIZE;i++){
            Bitmap bitmap;
            Log.d(TAG, "getPic: "+hotNew.getTop_stories().get(i));
            bitmap=HttpHelper.getInstance().picRunner(hotNew.getTop_stories().get(i).getImage());
            temp.add(bitmap);
        }
        return temp;
    }
    public List<New> hotToNews(){
        List<New> tempList=new ArrayList<>();
        for (int i=0;i<hotNew.getStories().size();i++){
            New temp=new New();
            temp.setPic(HttpHelper.getInstance().picRunner(hotNew.getStories().get(i).getImages().get(0)));
            temp.setTitle(hotNew.getStories().get(i).getTitle());
            temp.setId(hotNew.getStories().get(i).getId());
            temp.setTime(hotNew.getDate());
            tempList.add(temp);
            Log.d(TAG, "hotToNews: "+i+"to"+hotNew.getTop_stories().size());
        }
        return tempList;
    }
    public Article getArticle(String add){
        HttpHelper.setUrl(apiUrlDetail+add);
        Article tempArticle = new Article();
        new HttpHelper().run();
        try {
            tempArticle = JsonResolver.getInstance().passageDealer(HttpHelper.getTemp());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempArticle;
    }
    public Extra getExtra(String add){
        HttpHelper.setUrl(apiUrlDetail+add);
        Extra tempExtra = new Extra();
        new HttpHelper().run();
        try {
            tempExtra=JsonResolver.getInstance().extraDealer(HttpHelper.getTemp());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempExtra;
    }

    public Comment getLongComment(String id){
        HttpHelper.setUrl(apiUrlComment+id+apiUrlLongSuf);
        Log.d(TAG, "getLongComment: "+apiUrlComment+id+apiUrlLongSuf);
        Comment tempComment = new Comment();
        new HttpHelper().run();
        try {
            tempComment=JsonResolver.getInstance().commentDealer(HttpHelper.getTemp());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempComment;
    }
    public Comment getShortComment(String id){
        HttpHelper.setUrl(apiUrlComment+id+apiUrlShortSuf);
        Log.d(TAG, "getShortComment: "+apiUrlComment+id+apiUrlShortSuf);
        Comment tempComment = new Comment();
        new HttpHelper().run();
        try {
            tempComment=JsonResolver.getInstance().commentDealer(HttpHelper.getTemp());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempComment;
    }

}
