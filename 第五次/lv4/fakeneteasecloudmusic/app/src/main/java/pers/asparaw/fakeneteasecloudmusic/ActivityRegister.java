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

import pers.asparaw.fakeneteasecloudmusic.data.PwdCheck;
import pers.asparaw.fakeneteasecloudmusic.data.Security;
import pers.asparaw.fakeneteasecloudmusic.data.TelCheck;
import pers.asparaw.fakeneteasecloudmusic.dbstarter.DBTool;
import pers.asparaw.fakeneteasecloudmusic.dbstarter.DBUtil;

public class ActivityRegister extends AppCompatActivity {
    Button buttonReg;
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
        setContentView(R.layout.activity_register2);
        info = (App) this.getApplication();
        getById();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void getById() {
        buttonReg = findViewById(R.id.btn_reg);
        editTel = findViewById(R.id.edit_tel);
        editPasswd = findViewById(R.id.edit_pwd);
        buttonReg.setOnClickListener(listener);

        tb = findViewById(R.id.toolbar);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id = ", "onClick: " + v.getId());
                Intent intent = new Intent(ActivityRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_reg:
                    //do the reg
                    String name = editTel.getText().toString();
                    String password = editPasswd.getText().toString();
                    info.setName(name);
                    info.setPassword(password);
                    doReg(info.getName(), info.getPassword());
                    break;
            }
        }
    };

    void doReg(String name, String password) {

        PwdCheck pwdCheck = new PwdCheck(password);
        TelCheck telCheck = new TelCheck(name);
        boolean telOK = telCheck.check();
        boolean pwdOK = pwdCheck.Check();
        if (pwdOK && telOK) {
            SQLiteDatabase db = dbUtil.getWritableDatabase();
            DBTool dbTool = new DBTool(db);
            dbTool.createInfoTable();
            boolean dbOK = dbTool.addData(name, new Security().getMD5(password));
            if (dbOK) {
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ActivityList.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "已存在帐号！", Toast.LENGTH_SHORT).show();
            }
        } else if (!telOK) {
            Toast.makeText(getApplicationContext(), "注册失败，请输入11位手机号", Toast.LENGTH_SHORT).show();
        } else if (!pwdOK) {
            Toast.makeText(getApplicationContext(), "注册失败，请确认密码有字母和数字，并且长度在6-15个字以内", Toast.LENGTH_SHORT).show();

        }
    }

}

