package pers.asparaw.fakeneteasecloudmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import pers.asparaw.fakeneteasecloudmusic.music.RandomizeMusic;

public class ActivityList extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        doSth();
    }

    void doSth() {
        lv = findViewById(R.id.music_list);
        lv.setAdapter(null);
        RandomizeMusic r = new RandomizeMusic();
        //android.support.v7.widget.RecyclerView.Adapter adapter =
        SimpleAdapter adapter = new SimpleAdapter(this, new RandomizeMusic().getMapData(), R.layout.item_musiclist, new String[]{"name", "content"}, new int[]{R.id.item_name, R.id.item_content});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT);
            }
        });

    }
}
