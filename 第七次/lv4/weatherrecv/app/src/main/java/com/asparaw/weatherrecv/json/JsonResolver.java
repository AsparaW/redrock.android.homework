package com.asparaw.weatherrecv.json;

import android.util.Log;

import com.asparaw.weatherrecv.databean.OtherWeather;
import com.asparaw.weatherrecv.databean.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonResolver {
    private static final JsonResolver instance = new JsonResolver();
    private static final String TAG = "jsonResolveTEST";
    private String str;
    public boolean netOK;
    public ArrayList<OtherWeather> myWeatherData=new ArrayList<>();
    public WeatherData myData=new WeatherData();

    public static JsonResolver getInstance(){
        return instance;
    }
    private JsonResolver() {
    }

    public void setStr(String str) {
        this.str = str;
        Log.d(TAG, "setStr: "+this.str);
        Log.d(TAG, "setStr: SETOVER");
        init();
    }
    void init(){
        myWeatherData.clear();
        netOK = false;
        try {
            fixNull();
            Log.d(TAG, "init: START");
            dealer();
            Log.d(TAG, "init: OK");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "init: ERROR");
        }
    }

    void dealer() throws JSONException {
        //get status
        Log.d(TAG, "dealer: "+str);
        JSONObject jsonObject = new JSONObject(str);
        //JSONArray jsonArray = new JSONArray(str);
        Log.d(TAG, "dealer: getstatus");
        int status = jsonObject.getInt("code");
        if (status == 200) {
            netOK = true;
        } else {
            return;
        }
        //get weather main data
        //get yesterday
        int nowIndex = 1;
        Log.d(TAG, "dealer: yesterday");
        JSONObject jData = jsonObject.getJSONObject("data");
        JSONObject jYesterday = jData.getJSONObject("yesterday");

        //add data
        Log.d(TAG, "dealer: add data");
        OtherWeather tempWeatherData = new OtherWeather(nowIndex++);
        // added data;
        Log.d(TAG, "dealer: setdata");
        tempWeatherData.setDate(jYesterday.getString("date"));
        tempWeatherData.setFengli(jYesterday.getString("fl"));
        tempWeatherData.setFengxiang(jYesterday.getString("fx"));
        tempWeatherData.setHigh(jYesterday.getString("high"));
        tempWeatherData.setLow(jYesterday.getString("low"));
        tempWeatherData.setType(jYesterday.getString("type"));
        myWeatherData.add(tempWeatherData);
        //get other day
        Log.d(TAG, "dealer: get other day");
        JSONArray jForecast = jData.getJSONArray("forecast");
        for (int i = 0; i < jForecast.length(); i++) {
            tempWeatherData=new OtherWeather(nowIndex++);
            JSONObject record = jForecast.getJSONObject(i);
            tempWeatherData.setType(record.getString("type"));
            tempWeatherData.setLow(record.getString("low"));
            tempWeatherData.setHigh(record.getString("high"));
            tempWeatherData.setFengxiang(record.getString("fengxiang"));
            tempWeatherData.setFengli(record.getString("fengli"));
            tempWeatherData.setDate(record.getString("date"));
            //tempWeatherData.setIndex(nowIndex++);
            myWeatherData.add(tempWeatherData);
        }
        //add weather data
        Log.d(TAG, "dealer: get weather data");
        String city = jData.getString("city");
        int aqi = jData.getInt("aqi");
        String ganmao = jData.getString("ganmao");
        int wendu = jData.getInt("wendu");
        Log.d(TAG, "dealer: set weather data");
        myData.setAqi(String.valueOf(aqi));
        myData.setWendu(String.valueOf(wendu));
        myData.setCity(city);
        myData.setGanmao(ganmao);
        //over
    }
    void fixNull(){
        str=str.replaceAll("null","");
    }
}
