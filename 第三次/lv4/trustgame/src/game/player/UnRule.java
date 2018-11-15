package game.player;

import game.Player;

import java.util.Random;

public class UnRule implements Player {
    private static final String PATH = "file:./res/Players/8.png";
    private String type = "胡乱来";
    private String name = "";
    private int index;
    private int now;
    private int score;

    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public String getPATH() {
        return PATH;
    }

    @Override
    public boolean doIsCheat(Player player) {
        Random r = new Random();
        return r.nextBoolean();
    }

    @Override
    public void getOther(Player player, boolean selection) {
        //DO NOTHING
    }

    @Override
    public String showScore() {
        return Integer.toString(score);
    }

    @Override
    public String showType() {
        return type.toString();
    }

    @Override
    public void init(int maxPlayer, int index) {

        now = 0;
        score = 0;
        this.index = index;
        name = type + Integer.toString(index);
    }

    @Override
    public void addCoin(int coins) {
        score += coins;
    }

    @Override
    public String getName() {
        return name;
    }
}
