package pers.asparaw.fakeneteasecloudmusic;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pers.asparaw.fakeneteasecloudmusic.data.Security;
import pers.asparaw.fakeneteasecloudmusic.dbstarter.DBTool;
import pers.asparaw.fakeneteasecloudmusic.dbstarter.DBUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ActivityLogin extends AppCompatActivity {

    private static final int ACCESS_OPEN = 0;
    private static final int WRONG_PWD = 1;
    private static final int DB_READ_ERROR = 2;
    private static final int NO_USER = 3;

    Button buttonLogin;
    EditText editTel;
    EditText editPasswd;
    DBUtil dbUtil;
    App info;
    android.support.v7.widget.Toolbar tb;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbUtil = new DBUtil(this, "User.db", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        info = (App) this.getApplication();
        getById();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void getById() {
        buttonLogin = findViewById(R.id.btn_login);
        editTel = findViewById(R.id.edit_tel);
        editPasswd = findViewById(R.id.edit_pwd);
        buttonLogin.setOnClickListener(listener);

        tb = findViewById(R.id.toolbar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id = ", "onClick: " + v.getId());
                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    //do the login
                    //using global var
                    String name = editTel.getText().toString();
                    String password = editPasswd.getText().toString();
                    info.setName(name);
                    info.setPassword(password);
                    doLogin(info.getName(), info.getPassword());
                    break;
            }
        }
    };

    void doLogin(String name, String password) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();
        DBTool dbTool = new DBTool(db);
        int dbOK = dbTool.selectData(name, new Security().getMD5(password));
        //dbOK STATUS
        //what you seen is as clear,isn't it?
        if (dbOK == ACCESS_OPEN) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            gotoList();
        } else if (dbOK == WRONG_PWD) {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        } else if (dbOK == DB_READ_ERROR) {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        } else if (dbOK == NO_USER) {
            Toast.makeText(this, "无此用户", Toast.LENGTH_SHORT).show();
        }
    }

    void gotoList() {
        Intent intent = new Intent(this, ActivityList.class);
        startActivity(intent);
    }
}
