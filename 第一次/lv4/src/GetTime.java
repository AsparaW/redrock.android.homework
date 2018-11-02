public class GetTime {

    private static final long NORMAL = 31536000L;
    private static final long LEAP = 31622400L;
    private static final long HUNDRED = 3155673600L;
    private static final long FOURHUNDRED = 12622780800L;
    private static final long FOUR = 126230400L;
    private static final long MONTH31 = 2678400L;
    private static final long MONTH30 = 2592000L;
    private static final long MONTH29 = 2505600L;
    private static final long MONTH28 = 2419200L;
    private static final long DAY = 86400L;
    private static final long HOUR = 3600L;
    private static final long MINUTE = 60L;
    public long gmt;
    private long ctime;
    private long year, month, day, hour, min, sec, small;

    GetTime() {
        this.ctime = ctime;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.small = small;
        this.gmt = gmt;
        gmt = 8;
    }

    void showWelcome() {

        if (gmt >= 0) System.out.println("当前GMT：+" + gmt);
        else System.out.println("当前GMT：" + gmt);
        System.out.println("请输入操作：");
        System.out.println("1：改变GMT");
        System.out.println("2：当前时间戳");
        System.out.println("3：时间戳转换为时间");
        System.out.println("4：时间转换为时间戳");
        System.out.println("exit：关闭");
        System.out.println();
    }

    void changeGMT(String input) {
        input.trim();
        String strn = input.substring(1);
        gmt = Integer.parseInt(strn);
        if (input.charAt(0) == '-') {
            gmt = -gmt;
        }
        System.out.println("操作成功");
    }


    void printMillis() {
        System.out.println("当前时间：");
        printConvert(ctime / 1000);
    }

    void convert(long time) {


        long bigtime = time;
        bigtime += gmt * 3600;
        year = 1970;
        month = 1;
        day = 1;
        hour = 0;
        min = 0;
        sec = 0;
        boolean isleap = false;
        year += 400 * (bigtime / FOURHUNDRED);
        bigtime = bigtime % FOURHUNDRED;
        year += 100 * (bigtime / HUNDRED);
        bigtime = bigtime % HUNDRED;

        year += 4 * (bigtime / FOUR);
        bigtime = bigtime % FOUR;

        for (long i = year; ; i++) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                isleap = true;
            } else isleap = false;
            if (isleap) {
                if (bigtime < LEAP) break;
                else {
                    bigtime -= LEAP;
                    year++;
                }
            } else {
                if (bigtime < NORMAL) break;
                else {
                    bigtime -= NORMAL;
                    year++;
                }
            }
        }

        for (long i = month; ; i++) {
            long nexttime = 0;
            if (isleap) {
                if (month == 2) nexttime = MONTH29;
                else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    nexttime = MONTH31;
                else if (month == 4 || month == 6 || month == 9 || month == 11) nexttime = MONTH30;
            } else {
                if (month == 2) nexttime = MONTH28;
                else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                    nexttime = MONTH31;
                else if (month == 4 || month == 6 || month == 9 || month == 11) nexttime = MONTH30;
            }
            if (bigtime < nexttime)
                break;
            else {
                bigtime -= nexttime;
                month++;
            }
        }
        for (long i = day; ; i++) {
            if (bigtime < DAY) break;
            else {
                bigtime -= DAY;
                day++;
            }
        }
        for (long i = hour; ; i++) {
            if (bigtime < HOUR) break;
            else {
                bigtime -= HOUR;
                hour++;
            }
        }
        for (long i = min; ; i++) {
            if (bigtime < MINUTE) break;
            else {
                bigtime -= MINUTE;
                min++;
            }
        }
        sec = bigtime;

    }

    void printConvert(long thetime) {
        ctime = System.currentTimeMillis() / 1000;
        convert(ctime);
        System.out.println(ctime + "<------>" + year + "年" + month + "月" + day + "日" + hour + "时" + min + "分" + sec + "秒");
    }

    void convertBack(String tmp) {
        String strarry[] = new String[6];
        strarry = tmp.split("-");
        Long toyear = Long.parseLong(strarry[0]);
        Long tomonth = Long.parseLong(strarry[1]);
        Long today = Long.parseLong(strarry[2]);
        Long tohour = Long.parseLong(strarry[3]);
        Long tomin = Long.parseLong(strarry[4]);
        Long tosec = Long.parseLong(strarry[5]);
        year = 1970;
        month = 1;
        day = 1;
        hour = 0;
        min = 0;
        sec = 0;
        small = 0;
        Long res = -gmt * 3600L;
        boolean isleap = false;
        ///
        Long tmpfourhundred = toyear - year;
        res += tmpfourhundred / 400 * FOURHUNDRED;
        year = toyear - tmpfourhundred % 400;

        Long tmphundred = toyear - year;
        res += tmphundred / 100 * HUNDRED;
        year = toyear - tmphundred % 100;

        Long tmpfour = toyear - year;
        res += tmpfour / 4 * FOUR;
        year = toyear - tmpfour % 4;
        for (long i = year; i < toyear; i++) {

            if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
                isleap = true;
            } else isleap = false;
            if (isleap) {
                res += LEAP;
            } else {
                res += NORMAL;
            }

        }

        if ((toyear % 4 == 0 && toyear % 100 != 0) || toyear % 400 == 0) {
            isleap = true;
        } else isleap = false;

        for (long i = month; i < tomonth; i++) {
            long nexttime = 0;
            if (isleap) {
                if (i == 2) nexttime = MONTH29;
                else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
                    nexttime = MONTH31;
                else if (i == 4 || i == 6 || i == 9 || i == 11) nexttime = MONTH30;
            } else {
                if (i == 2) nexttime = MONTH28;
                else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
                    nexttime = MONTH31;
                else if (i == 4 || i == 6 || i == 9 || i == 11) nexttime = MONTH30;
            }
            res += nexttime;
        }
        for (long i = day; i < today; i++) {
            res += DAY;
        }
        for (long i = hour; i < tohour; i++) {
            res += HOUR;
        }
        for (long i = min; i < tomin; i++) {
            res += MINUTE;
        }
        res += tosec;

        ///
        System.out.println(toyear + "年" + tomonth + "月" + today + "日" + tohour + "时" + tomin + "分" + tosec + "秒" + "<------>" + res);
    }

    void inputPrintConvert(String tmp) {

        long thetime;
        thetime = Long.parseLong(tmp);
        convert(thetime);
        System.out.println(thetime + "<------>" + year + "年" + month + "月" + day + "日" + hour + "时" + min + "分" + sec + "秒");
    }

}
