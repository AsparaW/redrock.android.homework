package game.player;

import game.Player;

public class Gang implements Player {
    private static final String PATH = "file:./res/Players/6.png";
    private String type = "黑帮老铁";
    private String name = "";
    private int index;
    private boolean untrusted[];
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
        if (untrusted[player.getIndex()]) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void getOther(Player player, boolean selection) {
        if (selection) {
            untrusted[player.getIndex()] = true;
        }
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
        untrusted = new boolean[maxPlayer];
        for (int i = 0; i < untrusted.length; i++) {
            untrusted[i] = false;
        }
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
