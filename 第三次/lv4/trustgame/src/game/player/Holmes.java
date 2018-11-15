package game.player;

import game.Player;

public class Holmes implements Player {
    private static final String PATH = "file:./res/Players/3.png";
    private String type = "福尔摩星儿";
    private boolean otherSelection[];
    private String name = "";
    private boolean isRepeater[];
    private boolean isCheater[];
    private boolean[] test = {false, true, false, false};
    private int index;
    private int now[];
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
        if (now[player.getIndex()] == 4) {
            now[player.getIndex()] = -1;
            if (otherSelection[player.getIndex()]) {
                isRepeater[player.getIndex()] = true;
            } else {
                isCheater[player.getIndex()] = true;
            }
        }

        if (isRepeater[player.getIndex()]) {
            return otherSelection[player.getIndex()];
        } else if (isCheater[player.getIndex()]) {
            return true;
        } else {
            now[player.getIndex()]++;
            return test[now[player.getIndex()] - 1];
        }
    }

    @Override
    public void getOther(Player player, boolean selection) {
        setOtherSelection(player.getIndex(), selection);

    }

    public void setOtherSelection(int index, boolean selection) {
        otherSelection[index] = selection;
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
        otherSelection = new boolean[maxPlayer];
        isRepeater = new boolean[maxPlayer];
        isCheater = new boolean[maxPlayer];
        now = new int[maxPlayer];
        for (int i = 0; i < otherSelection.length; i++) {
            otherSelection[i] = false;
            isRepeater[i] = false;
            isCheater[i] = false;
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
