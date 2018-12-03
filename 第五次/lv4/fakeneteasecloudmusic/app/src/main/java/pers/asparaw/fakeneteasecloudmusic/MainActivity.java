package pers.asparaw.fakeneteasecloudmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnLogin;
    Button btnReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);
        btnReg = findViewById(R.id.btn_reg);

        btnLogin.setOnClickListener(listener);
        btnReg.setOnClickListener(listener);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    intent = new Intent(MainActivity.this, ActivityLogin.class);
                    Log.d("FLAG", "onClick: ");
                    startActivity(intent);
                    break;
                case R.id.btn_reg:
                    intent = new Intent(MainActivity.this, ActivityRegister.class);
                    startActivity(intent);
                    break;
            }
        }
    };


}
