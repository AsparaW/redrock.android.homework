package sample;


import game.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static final String FILE_PATH = "src/game/player/";
    private static final String CLASS_PATH_PREFIX = "game.player.";

    protected static List<Player> players = new ArrayList<>();
    protected static HashMap<Player, Integer> gameData = new HashMap<>();

    // init class
    private static void createPlayer() {
        File dir = new File(FILE_PATH);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            try {
                String className = file.getName().replace(".java", "");
                Class<? extends Player> clazz = (Class<? extends Player>) Class.forName(CLASS_PATH_PREFIX + className);
                Player newPlayer = clazz.newInstance();
                players.add(newPlayer);
            } catch (Exception ignored) {
            }
        }
    }

    //start
    public static void main(String[] args) {
        createPlayer();
        System.out.println("已加载角色：");
        for (Player temp : players)
            System.out.println(temp.getClass());
        UI myUI = new UI();
        myUI.startUI();
    }


}
