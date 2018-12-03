package pers.asparaw.fakeneteasecloudmusic.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PwdCheck {
    String pwd;
    String hashPwd;

    public PwdCheck(String pwd) {
        this.pwd = pwd;
    }

    public boolean Check() {
        //密码6-15位
        //必须有字母和数字
        boolean isOK = false;
        int len = pwd.length();
        if (len >= 6 && len <= 15) {
            String pattern;
            pattern = "[\\d]";
            Pattern r = Pattern.compile(pattern);
            Matcher matcher = r.matcher(pwd);
            if (matcher.find()) {
                pattern = "[a-zA-Z]";
                r = Pattern.compile(pattern);
                matcher = r.matcher(pwd);
                if (matcher.find()) {
                    isOK = true;
                }
            }
        }
        return isOK;
    }

}
