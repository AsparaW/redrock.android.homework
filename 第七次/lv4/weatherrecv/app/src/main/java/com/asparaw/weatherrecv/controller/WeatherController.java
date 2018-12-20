package com.asparaw.weatherrecv.controller;

import android.util.Log;

import com.asparaw.weatherrecv.databean.TestData;
import com.asparaw.weatherrecv.databean.WeatherData;
import com.asparaw.weatherrecv.httphelper.URLHelper;
import com.asparaw.weatherrecv.json.JsonResolver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherController {
    private static String jsonStr;
    private static String city="成都";
    private static final String apiUrl = "https://www.apiopen.top/weatherApi?city=";
    private static final String TAG = "WCTEST";
    private static final WeatherController instance = new WeatherController();
    private WeatherController(){

    }
    public static WeatherController getInstance(){
        return instance;
    }
    //访问http
    //访问失败 传给Activity
    //将拿到的String 解析

    //test

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        WeatherController.city = city;
    }

    public void fixFengLi(){
        int len = JsonResolver.getInstance().myWeatherData.size();
        for (int i = 0;i<len;i++){
            String model = JsonResolver.getInstance().myWeatherData.get(i).getFengli();
            String pattern = "(\\d)级";
            Pattern pat=Pattern.compile(pattern);
            Matcher mat = pat.matcher(model);
            if (mat.find()){
                model=mat.group(0);
            }
            JsonResolver.getInstance().myWeatherData.get(i).setFengli(model);
        }
    }
    public String getJson(){
        URLHelper.setUrl(apiUrl+city);
        new URLHelper().run();//
        jsonStr=URLHelper.getTemp();
        jsonStr=jsonStr.replaceAll("null","");
        return jsonStr;
    }
    public void refresh(){
        Log.d(TAG, "refresh: "+jsonStr);
        JsonResolver.getInstance().setStr(jsonStr);
        fixFengLi();
    }
    public void refreshTest(){
        Log.d(TAG, "refresh: "+TestData.getJsontest());
        JsonResolver.getInstance().setStr(TestData.getJsontest());
        fixFengLi();
    }
    public ArrayList getWeatherList(){
       return JsonResolver.getInstance().myWeatherData;
    }
    public WeatherData getWeatherData(){
        return JsonResolver.getInstance().myData;
    }

    public String getMonth(){
        String str;
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Date myDate = new Date(System.currentTimeMillis());
        str= format.format(myDate);
        return str;
    }
}
