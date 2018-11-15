package game;

public interface Player {

    int getIndex();

    void setIndex(int index);

    String getPATH();

    boolean doIsCheat(Player player);

    void init(int maxPlayer, int index);

    String showScore();

    String showType();

    String getName();

    void addCoin(int coins);

    int getScore();

    void getOther(Player player, boolean selection);
}
