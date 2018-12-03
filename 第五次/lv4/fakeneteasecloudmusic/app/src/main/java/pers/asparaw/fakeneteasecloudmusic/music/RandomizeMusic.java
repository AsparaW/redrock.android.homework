package pers.asparaw.fakeneteasecloudmusic.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizeMusic {

    ArrayList<Map<String, Object>> musicList;
    Map<String, Object> musicData;

    public ArrayList<Map<String, Object>> getMapData() {
        musicList.clear();
        int randomSize = new Random().nextInt(30);
        for (int i = 0; i < randomSize; i++) {
            musicData = new HashMap();
            musicData.clear();
            MusicData temp = new MusicData();
            temp.setName("我的第" + (i + 1) + "个歌单");
            temp.setTotalMusic(new Random().nextInt(1000));
            musicData.put("id", i + 1);
            musicData.put("name", temp.getName());
            musicData.put("content", "共" + temp.getTotalMusic() + "首");
            musicList.add(musicData);
        }
        return musicList;
    }

    public RandomizeMusic() {
        musicList = new ArrayList();
    }
}
