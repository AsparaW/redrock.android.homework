package com.mantouland.fakezhihuribao.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mantouland.fakezhihuribao.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHelper extends Thread {
    private static final HttpHelper instance = new HttpHelper();
    private static HttpHelper get;
    private static String temp;
    private static String url;

    public HttpHelper(){
        //do nothing
    }
    public static HttpHelper getInstance(){
        return instance;
    }
    @Override
    public void run(){
        URLRunner(url);
    }

    public static void setUrl(String url) {
        HttpHelper.url = url;
    }


    void URLRunner(String url) {
        temp="";
        URL u;
        HttpURLConnection httpURLConnection;
        BufferedReader bf;
        String readLine;
        try {
            u = new URL(url);
            httpURLConnection = (HttpURLConnection) u.openConnection();
            int responce = httpURLConnection.getResponseCode();
            if (responce == 200) {
                bf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                while ((readLine = bf.readLine()) != null) {
                    temp = temp + readLine + "\r\n";
                }
            } else {
                System.out.println(responce);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/*

    //手柄更新的作用
    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==111){
                imageView.setImageBitmap(bitmap);
            }
        };
    };
*/
private Bitmap bitmap;
        public Bitmap picRunner(String url) {

            try {

                Log.d("PPIC", "picRunner: "+url);
                URL u=new URL(url);
                InputStream inputStream=u.openStream();
                bitmap=BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    public static String getTemp() {
        return temp;
    }
}
