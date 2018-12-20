package com.asparaw.weatherrecv.httphelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLHelper extends Thread {
    private static final URLHelper instance = new URLHelper();
    private static URLHelper get;
    private static String temp;
    private static String url;

    public URLHelper(){
        //do nothing
    }
    public static URLHelper getInstance(){
        return instance;
    }
    @Override
    public void run(){
        URLRunner(url);
    }

    public static void setUrl(String url) {
        URLHelper.url = url;
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

    public static String getTemp() {
        return temp;
    }
}
