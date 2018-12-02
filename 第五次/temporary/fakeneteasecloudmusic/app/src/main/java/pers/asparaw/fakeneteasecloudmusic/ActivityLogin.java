package pers.asparaw.fakeneteasecloudmusic;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    Button buttonLogin;
    EditText editTel;
    EditText editPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getById();
    }

    protected void getById(){
        buttonLogin= findViewById(R.id.btn_login);
        editTel=findViewById(R.id.edit_tel);
        editPasswd=findViewById(R.id.edit_pwd);
    }
    protected void listen(){
        Listener listener = new View.OnClickListener(){}
    }
}
