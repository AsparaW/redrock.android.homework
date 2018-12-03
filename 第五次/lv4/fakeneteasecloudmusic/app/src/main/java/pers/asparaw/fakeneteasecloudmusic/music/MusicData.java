package pers.asparaw.fakeneteasecloudmusic.music;

import android.app.Application;

public class MusicData extends Application {
    private String name;
    private int totalMusic;

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalMusic(int totalMusic) {
        this.totalMusic = totalMusic;
    }

    public String getName() {
        return name;
    }

    public int getTotalMusic() {
        return totalMusic;
    }
}
