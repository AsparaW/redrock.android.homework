package game.player;

import game.Player;

public class Pink implements Player {
    private static final String PATH = "file:./res/Players/2.png";
    private String type = "万年小粉红";
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
        return false;
    }

    @Override
    public void getOther(Player player, boolean selection) {
        //DO_NOTHING
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
