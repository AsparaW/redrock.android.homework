package com.mantouland.fakezhihuribao.bean;

import android.graphics.Bitmap;

public class New {
    String Title;
    Bitmap Pic;
    String url;
    int id;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getPic() {
        return Pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setPic(Bitmap pic) {
        Pic = pic;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

