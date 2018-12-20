package com.asparaw.weatherrecv;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asparaw.weatherrecv.adapter.MyRecAdapter;
import com.asparaw.weatherrecv.controller.WeatherController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int ASK_JSON_OVER = 1;
    TextView textCity;
    TextView textNowTemp;
    TextView textDate;
    TextView textAQI;
    TextView textGanMao;
    MyRecAdapter mAdapter;
    RecyclerView myRecView;

    View rootWeatherView;
    View itemView;

    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initURLHelper();
        bindViews();
        getScreenInfo();
        askURL();
    }

    protected void bindViews() {
        textAQI = findViewById(R.id.tv_aqi);
        textDate = findViewById(R.id.tv_thisdate);
        textGanMao = findViewById(R.id.tv_ganmao);
        textNowTemp = findViewById(R.id.tv_nowtemp);
        textCity = findViewById(R.id.tv_city);
        myRecView = findViewById(R.id.rv);
        rootWeatherView = findViewById(R.id.div_main);
        rootWeatherView.setOnClickListener(mainListener);
    }

    protected void askURL() {
        new Thread(runnableGetWeather).start();
    }

    protected void initView() {
        WeatherController.getInstance().refresh();
        textCity.setText(WeatherController.getInstance().getWeatherData().getCity());
        textAQI.setText("AQI : " + WeatherController.getInstance().getWeatherData().getAqi());
        //get time
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());
        String strDate = format.format(curDate);
        textDate.setText(strDate);
        textGanMao.setText(WeatherController.getInstance().getWeatherData().getGanmao());
        textNowTemp.setText(WeatherController.getInstance().getWeatherData().getWendu() + "℃");
        setRec();
    }

    private void setRec() {
        mAdapter = new MyRecAdapter();
        mAdapter.setContext(this);
        mAdapter.setData(WeatherController.getInstance().getWeatherList());
        MyRecAdapter ma = new MyRecAdapter();
        myRecView.setAdapter(mAdapter);//给mRecyclerView设置adapter
        myRecView.setLayoutManager(new LinearLayoutManager(this));//给mRecyclerView设置LayoutManager
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

    void dynamicListView() {

    }

    // Something following is internet thread receiver and listener
    Runnable runnableGetWeather = new Runnable() {
        @Override
        public void run() {
            WeatherController.getInstance().getJson();
            //initView();
            handler.sendEmptyMessage(ASK_JSON_OVER);
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ASK_JSON_OVER) {
                initView();
            }

        }
    };

    View.OnClickListener mainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.div_main:
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("请输入城市名称");
                    final EditText myET = new EditText(MainActivity.this);
                    myET.setText(WeatherController.getCity());
                    dialog.setView(myET);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("Main Activity", "onClick: OK");
                            Toast.makeText(MainActivity.this, "读取中", Toast.LENGTH_SHORT).show();
                            WeatherController.setCity(myET.getText().toString());
                            askURL();
                        }
                    });
                    dialog.setNegativeButton("取消", null);
                    dialog.show();
                    break;
            }
        }
    };
}
