package game.player;

import game.Player;

public class TestInit implements Player {
    private static final String PATH = "file:./res/Players/unknown.png";
    private String type = "添加测试角色";
    private String name = "";
    private int index;
    private int now;
    private int score;

    public int getScore() {
        return score;
    }

    public String getPATH() {
        return PATH;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean doIsCheat(Player player) {
        return true;
    }

    @Override
    public void getOther(Player player, boolean selection) {

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
