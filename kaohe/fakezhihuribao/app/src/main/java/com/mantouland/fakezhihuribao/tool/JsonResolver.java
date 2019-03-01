package com.mantouland.fakezhihuribao.tool;

import android.util.Log;

import com.mantouland.fakezhihuribao.bean.Article;
import com.mantouland.fakezhihuribao.bean.BeforeNew;
import com.mantouland.fakezhihuribao.bean.Comment;
import com.mantouland.fakezhihuribao.bean.Extra;
import com.mantouland.fakezhihuribao.bean.HotNew;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonResolver {

    private static final JsonResolver instance = new JsonResolver();
    private static final String TAG = "jsonResolveTEST";
    public boolean netOK;

    public static JsonResolver getInstance(){
        return instance;
    }
    private JsonResolver() {
    }



/*
    void init(){
        netOK = false;
        try {
            fixNull();
            Log.d(TAG, "init: START");
            //dealer();
            Log.d(TAG, "init: OK");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "init: ERROR");
        }
    }
*/

    /**
     * json处理器1
     * 加载首次新闻数据
     * @throws JSONException
     */
   public HotNew infoDealer(String str) throws JSONException {
        //get status
        Log.d(TAG, "dealer: "+str);
        JSONObject jsonObject = new JSONObject(str);
        //get hotnews data
        HotNew tempHotNew=new HotNew();
        tempHotNew.setDate(jsonObject.getString("date"));
        JSONArray stories=jsonObject.getJSONArray("stories");
        JSONArray topStories=jsonObject.getJSONArray("top_stories");
        List tempStories=new ArrayList();
       Log.d(TAG, "infoDealer: OK1");
        for (int i = 0;i<stories.length();i++){
            HotNew.StoriesBean storiesBean=new HotNew.StoriesBean();
            storiesBean.setTitle(stories.getJSONObject(i).getString("title"));
            List<String> temp=new ArrayList<>();
            temp.add(stories.getJSONObject(i).getJSONArray("images").getString(0));
            storiesBean.setImages(temp);
            storiesBean.setId(stories.getJSONObject(i).getInt("id"));
            tempStories.add(storiesBean);
        }
        tempHotNew.setStories(tempStories);
       Log.d(TAG, "infoDealer: OK2");
        List tempTopStories=new ArrayList();
        for (int i = 0;i<topStories.length();i++){
            HotNew.TopStoriesBean storiesBean=new HotNew.TopStoriesBean();
            storiesBean.setTitle(topStories.getJSONObject(i).getString("title"));
            storiesBean.setImage(topStories.getJSONObject(i).getString("image"));
            storiesBean.setId(topStories.getJSONObject(i).getInt("id"));
            tempTopStories.add(storiesBean);
        }
        tempHotNew.setTop_stories(tempTopStories);
       Log.d(TAG, "infoDealer: "+tempHotNew.getTop_stories().size());

        return tempHotNew;
        //get first-loaded data
        //first get ended, users can visit
    }

    /**
     * json处理器2
     * 处理拖动后请求的json
     */
    public BeforeNew moreInfoDealer(String str) throws JSONException {
        BeforeNew beforeNew = new BeforeNew();
        JSONObject jsonObject=new JSONObject(str);
        beforeNew.setDate(jsonObject.getString("date"));
        Log.d(TAG, "moreInfoDealer: "+beforeNew.getDate());
        JSONArray jsonArray=jsonObject.getJSONArray("stories");
        List<BeforeNew.StoriesBean> storiesBeans=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            BeforeNew.StoriesBean temp = new BeforeNew.StoriesBean();
            temp.setId(jsonArray.getJSONObject(i).getInt("id"));
            Log.d(TAG, "moreInfoDealer: "+temp.getId());
            temp.setTitle(jsonArray.getJSONObject(i).getString("title"));
            List<String > tempImage=new ArrayList<>();
            tempImage.add(jsonArray.getJSONObject(i).getString("images"));
            temp.setImages(tempImage);
            storiesBeans.add(temp);
        }
        beforeNew.setStories(storiesBeans);
        return beforeNew;
    }

    /**
     * json处理器3
     * 加载文章的数据
     */
    public Article passageDealer(String str) throws JSONException {
        Article tempArticle =new Article();
        JSONObject jsonObject=new JSONObject(str);
        tempArticle.setBody(jsonObject.getString("body"));
        tempArticle.setImage(jsonObject.getString("image"));
        tempArticle.setId(jsonObject.getInt("id"));
        List<String >temp = new ArrayList<>();
        temp.add(jsonObject.getJSONArray("css").getString(0));
        tempArticle.setCss(temp);
        tempArticle.setTitle(jsonObject.getString("title"));
        return tempArticle;
    }

    /**
     * json 处理器4
     * 加载额外信息
     * @param str
     * @return
     */
    public Extra extraDealer(String str) throws JSONException {
        Extra extra=new Extra();
        JSONObject jsonObject=new JSONObject(str);
        extra.setComments(jsonObject.getInt("comments"));
        extra.setLong_comments(jsonObject.getInt("long_comments"));
        extra.setPopularity(jsonObject.getInt("popularity"));
        extra.setShort_comments(jsonObject.getInt("short_comments"));
        return extra;
    }

    public Comment commentDealer(String str) throws JSONException{
        Comment comment=new Comment();
        JSONObject jsonObject=new JSONObject(str);
        JSONArray jsonArray=jsonObject.getJSONArray("comments");
        List<Comment.CommentsBean> tempList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            Comment.CommentsBean temp=new Comment.CommentsBean();
            JSONObject tempJ=(JSONObject) jsonArray.get(i);
            temp.setAuthor(tempJ.getString("author"));
            Log.d(TAG, "commentDealer: "+temp.getAuthor());
            temp.setAvatar(tempJ.getString("avatar"));
            temp.setContent(tempJ.getString("content"));
            temp.setLikes(tempJ.getInt("likes"));
            temp.setTime(tempJ.getInt("time"));
            temp.setId(tempJ.getInt("id"));
            tempList.add(temp);
        }
        comment.setComments(tempList);
        return comment;
    }

}
