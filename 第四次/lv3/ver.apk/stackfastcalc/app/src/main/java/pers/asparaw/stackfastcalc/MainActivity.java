package pers.asparaw.stackfastcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import calc.CalcTools;

public class MainActivity extends AppCompatActivity {

    //init btn
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;
    private Button btn_00;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_multi;
    private Button btn_divide;
    private Button btn_left;
    private Button btn_right;
    private Button btn_clear;
    private Button btn_equals;
    private Button btn_dot;
    private Button btn_back;
    private EditText txt_box;
    //over


    String str="";
    //string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    public void bindView(){
        btn_0=findViewById(R.id.btn_zero);
        btn_00=findViewById(R.id.btn_zzero);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5= findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7= findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus=findViewById(R.id.btn_minus);
        btn_multi=findViewById(R.id.btn_multiply);
        btn_divide=findViewById(R.id.btn_divide);
        btn_left =findViewById(R.id.btn_left);
        btn_right=findViewById(R.id.btn_right);
        btn_clear=findViewById(R.id.btn_C);
        btn_equals =findViewById(R.id.btn_start);
        btn_dot=findViewById(R.id.btn_dot);
        btn_back = findViewById(R.id.backspace);

        txt_box = findViewById(R.id.edit);

        btn_0.setOnClickListener(listener);
        btn_00.setOnClickListener(listener);
        btn_1.setOnClickListener(listener);
        btn_2.setOnClickListener(listener);
        btn_3.setOnClickListener(listener);
        btn_4.setOnClickListener(listener);
        btn_5.setOnClickListener(listener);
        btn_6.setOnClickListener(listener);
        btn_7.setOnClickListener(listener);
        btn_8 .setOnClickListener(listener);
        btn_9 .setOnClickListener(listener);
        btn_plus.setOnClickListener(listener);
        btn_minus.setOnClickListener(listener);
        btn_multi.setOnClickListener(listener);
        btn_divide.setOnClickListener(listener);
        btn_left .setOnClickListener(listener);
        btn_right.setOnClickListener(listener);
        btn_clear.setOnClickListener(listener);
        btn_equals .setOnClickListener(listener);
        btn_dot.setOnClickListener(listener);
        btn_back.setOnClickListener(listener);

    }

    public void refresh(){
        txt_box.setText(str);
        txt_box.setSelection(str.length());
    }

    public View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            refresh();
            switch (v.getId()) {
                case R.id.btn_zero:
                    str=str+"0";
                    break;
                case R.id.btn_zzero:
                    str=str+"00";
                    break;
                case R.id.btn_1:
                    str=str+"1";
                    break;
                case R.id.btn_2:
                    str=str+"2";
                    break;
                case R.id.btn_3:
                    str=str+"3";
                    break;
                case R.id.btn_4:
                    str=str+"4";
                    break;
                case R.id.btn_5:
                    str=str+"5";
                    break;
                case R.id.btn_6:
                    str=str+"6";
                    break;
                case R.id.btn_7:
                    str=str+"7";
                    break;
                case R.id.btn_8:
                    str=str+"8";
                    break;
                case R.id.btn_9:
                    str=str+"9";
                    break;
                case R.id.btn_C:
                    clear();
                    break;
                case R.id.btn_dot:
                    str=str+".";
                    break;
                case R.id.btn_plus:
                    str=str+" ＋ ";
                    break;
                case R.id.btn_minus:
                    str=str+" － ";
                    break;
                case R.id.btn_multiply:
                    str=str+" × ";
                    break;
                case R.id.btn_divide:
                    str=str+" ÷ ";
                    break;
                case R.id.btn_start:
                    start();
                    refresh();
                    break;
                case R.id.btn_left:
                    str=str+"(";
                    break;
                case R.id.btn_right:
                    str=str+")";
                    break;
                case R.id.backspace:
                    if (str.length()>0){
                        str=str.substring(0,str.length()-1);
                    }
                    break;
            }
            refresh();
        }

    };

    private void start(){
        str = txt_box.getText().toString();
        str = (new CalcTools(str).getResult());
    }
    private void clear(){
        str="";
    }
}
