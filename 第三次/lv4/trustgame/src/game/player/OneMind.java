package game.player;

import game.Player;

public class OneMind implements Player {
    private static final String PATH = "file:./res/Players/4.png";
    private String type = "一根筋";
    private boolean otherSelection[];
    private String name = "";
    private int index;
    private boolean last;
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
        if (otherSelection[player.getIndex()]) {
            last = (last == true) ? false : true;
        }
        return last;
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
        for (int i = 0; i < otherSelection.length; i++)
            otherSelection[i] = false;
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
