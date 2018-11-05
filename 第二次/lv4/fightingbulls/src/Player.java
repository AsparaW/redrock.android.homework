public class Player {
    private static final int CARD_SUM = 5;
    public String name = "";

    private int cash = 500000;
    private int nowCard = 0;
    private int biggestPoint = 0;
    private int biggestIndex = 0;
    private Card[] card = new Card[CARD_SUM];
    private String state;
    private int wins = 0, loses = 0;
    private int testPoint;
    private long testScore;

    Player() {
        this.name = "无名氏";
    }

    public void setName(String name) {
        if (name.equals("")) {
            this.name = "无名氏";
        } else {
            this.name = name;
        }
    }

    public int getBiggestIndex() {
        return biggestIndex;
    }

    public void setCard(Card card) {
        this.card[nowCard] = card;
        nowCard++;
        for (int i = 0; i < nowCard; i++) {
            if (card.getComparePoint() >= biggestPoint) {
                biggestPoint = card.getComparePoint();
                biggestIndex = i;
            }
        }
        if (nowCard == 5) nowCard = 0;
    }//理牌，理出最大牌

    public Card getCard(int index) {
        return this.card[index];
    }

    public int getTestPoint() {
        return testPoint;
    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint;
    }

    public long getTestScore() {
        return testScore;
    }

    public void setTestScore(long testScore) {
        this.testScore = testScore;
    }

    public int getBiggestPoint() {
        return biggestPoint;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getWins() {
        return wins;
    }

    public void addWins() {
        this.wins++;
    }

    public int getLoses() {
        return loses;
    }

    public void addLoses() {
        this.loses++;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public void clearBiggestPoint() {
        this.biggestPoint = 0;
    }
}
