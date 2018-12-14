package asparaw.pers.qdlog.DataBean;

import asparaw.pers.qdlog.R;

public class Status {
    private static final int TYPE_IN = 0;
    private static final int TYPE_OUT=1;
    private static final int TYPE_MISS = 2;
    private static final int MAGIC_NUM =  11 ;
    private String  name;
    private String date;
    private String time;
    private int resID;
    private int type;

    public Status(String name,String date,int type){
        resID= R.drawable.ic_person_black_24dp;
        this.name=name;
        this.date=date;
        this.type=type;
        this.time=date.substring(0,MAGIC_NUM);
        this.date=date.substring(MAGIC_NUM);
    }
    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getResID() {
        return resID;
    }

    public int getType() {
        return type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
