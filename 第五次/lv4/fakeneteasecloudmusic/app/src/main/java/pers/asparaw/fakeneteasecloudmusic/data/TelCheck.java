package pers.asparaw.fakeneteasecloudmusic.data;

import android.util.Log;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelCheck {
    String tel;

    public TelCheck(String tel) {
        this.tel = tel;
    }

    public boolean check() {
        //手机号 ,11位数字
        Log.d("TC", "check: " + tel);
        boolean isOK = false;
        String pat = "(\\d{11})";
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(tel);
        if (matcher.find()) {
            isOK = true;
        }
        return isOK;
    }
}
