public class GameRound {
    private static final String SUIT[] = {"黑桃", "梅花", "红心", "方块"};
    private static final String FACE[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final String SUIT_ORDER[] = {"方块", "梅花", "红心", "黑桃"};//牌买来是黑桃梅花的顺序，但是比大小是按照黑红梅方的顺序
    private static final String[] STATES = {"炸弹", "五小", "五花", "四花", "牛牛", "有牛", "无牛"};
    private static final String[] STATES_WUXIAO_STATE = {"", "", "", "", "", "5点", "6点", "7点", "8点", "9点", "10点"};
    private static final String[] STATES_DANNIU_STATE = {"", "牛一", "牛二", "牛三", "牛四", "牛五", "牛六", "牛七", "牛八", "牛九"};
    private static final int CARD_LENGTH = 52;
    private static final int CARDS_PER_PLAYER = 5;
    private static final long E14 = 100000000000000L;
    private static final long E11 = 100000000000L;

    private static int maxplayer;
    private static int nowRound = 0;
    public int mode = 0;
    private int players;
    private Card[] card = new Card[CARD_LENGTH];
    private Player[] player;
    private int nextIndex = 0;
    private long[] score;


    GameRound(int players) {
        this.players = players;
        maxplayer = CARD_LENGTH / CARDS_PER_PLAYER;
        if (players <= maxplayer) {
            createCard();
            createPlayers();
        } else {
            System.out.println("玩家数过多！无法开始游戏！");
            mode = -1;
        }
    }

    public static int getNowRound() {
        return nowRound;
    }

    public void rounds(int rounds) {
        for (int i = 0; i < rounds; i++) {
            newRound();
        }
    }

    public void newRound() {
        shuffle();
        printOrder();
        giveAllPlayersCards();
        calculate();
        nowRound++;
    }

    public void newRoundSelect(String module) {
        System.out.println("选牌模式已开");
        giveAllPlayersCards(module);
        calculate();
        nowRound++;
    }

    public void sortNumber(long[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    long temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void createCard() {
        for (int i = 0; i < FACE.length; i++) {
            for (int j = 0; j < SUIT.length; j++) {
                card[nextIndex] = new Card(FACE[i], SUIT[j]);
                //
                int tempPoint = 0;
                for (int k = 0; k < SUIT_ORDER.length; k++) {
                    if (card[nextIndex].getSuit().equals(SUIT_ORDER[k])) {
                        tempPoint += k;
                        break;
                    }
                }
                for (int k = 0; k < FACE.length; k++) {
                    if (card[nextIndex].getFace().equals(FACE[k])) {
                        tempPoint += SUIT.length * k;
                        break;
                    }
                }
                card[nextIndex].setComparePoint(tempPoint);
                //预处理牌型大小
                nextIndex++;
            }
        }
        System.out.println("全新扑克：");
        printOrder();
    }

    private void createPlayers() {
        player = new Player[players];
        score = new long[players];
        for (int i = 0; i < players; i++) {
            player[i] = new Player();
        }
    }

    public void shuffle() {
        //init
        for (int i = 0; i < CARD_LENGTH; i++) {
            card[i].randomizeWeight();
        }
        sortWeight();
        System.out.println("------已洗牌------");
    }

    private void sortWeight() {
        for (int i = 0; i < card.length - 1; i++) {
            for (int j = 0; j < card.length - 1 - i; j++) {
                if (card[j].getWeight() > card[j + 1].getWeight()) {
                    Card temp = card[j];
                    card[j] = card[j + 1];
                    card[j + 1] = temp;
                }
            }
        }
    }

    public void printOrder() {
        for (int i = 0; i < CARD_LENGTH; i++) {
            System.out.println("card " + (i + 1) + " = " + card[i].getSuit() + " " + card[i].getFace() + "    weight = " + card[i].getWeight());
        }
    }

    private void giveAllPlayersCards() {
        for (int i = 0; i < players * CARDS_PER_PLAYER; i++) {
            player[i % players].setCard(card[i]);
            System.out.println("玩家" + (i % players + 1) + " " + player[i % players].name + " " + "拿到了" + "card " + (i + 1) + " = " + card[i].getSuit() + " " + card[i].getFace());
        }
    }

    private void giveAllPlayersCards(String indexStr) {
        System.out.println("indexStr = " + indexStr);
        String[] tempStr ;
        tempStr = indexStr.split(",");
        System.out.println("tempStr = " + tempStr);
        for (int i = 0; i < players * CARDS_PER_PLAYER; i++) {
            player[i / CARDS_PER_PLAYER].setCard(card[Integer.parseInt(tempStr[i]) - 1]);
            System.out.println("玩家" + (i / CARDS_PER_PLAYER + 1) + " " + player[i / CARDS_PER_PLAYER].name + " " + "拿到了" + "card " + Integer.parseInt(tempStr[i]) + " = " + card[Integer.parseInt(tempStr[i]) - 1].getSuit() + " " + card[Integer.parseInt(tempStr[i]) - 1].getFace());
        }
    }

    private void calculate() {
        for (int nowPlayer = 0; nowPlayer < players; nowPlayer++) {

            Card[] tempCard = new Card[CARDS_PER_PLAYER];
            int suitSum[] = new int[SUIT.length];
            int faceSum[] = new int[FACE.length];

            for (int i = 0; i < CARDS_PER_PLAYER; i++) {
                tempCard[i] = player[nowPlayer].getCard(i);
                String tempSuit = tempCard[i].getSuit();
                String tempFace = tempCard[i].getFace();

                for (int j = 0; j < SUIT.length; j++) {
                    suitSum[j] = 0;
                }

                for (int j = 0; j < SUIT.length; j++) {
                    if (tempSuit.equals(SUIT[j])) {
                        suitSum[j]++;
                        break;
                    }
                }

                for (int j = 0; j < FACE.length; j++) {
                    if (tempFace.equals(FACE[j])) {
                        faceSum[j]++;
                        break;
                    }
                }

            }

            //牌型计算

            boolean setOverFlag = false;
            int cases = 0;
            for (; ; cases++) {
                if (cases == 0) {
                    for (int j = 0; j < FACE.length; j++) {
                        if (faceSum[j] >= 4) {
                            player[nowPlayer].setState(STATES[cases]);
                            setOverFlag = true;
                            break;
                        }
                    }
                    if (setOverFlag) {
                        break;
                    }
                }
                //炸弹
                else if (cases == 1) {
                    boolean isWuXiao = true;
                    int sum = 0;
                    for (int j = 0; j < 5; j++) {
                        sum += faceSum[j] * (j + 1);
                    }
                    for (int j = 5; j < FACE.length; j++) {
                        if (faceSum[j] != 0) {
                            isWuXiao = false;
                            break;
                        }
                    }
                    if (sum <= 10 && isWuXiao == true) {
                        player[nowPlayer].setState(STATES[cases]);
                        player[nowPlayer].setTestPoint(sum);
                        break;
                    }
                }
                //五小
                else if (cases == 2) {
                    int sum = faceSum[10] + faceSum[11] + faceSum[12];// JQK牌总数
                    if (sum == 5) {
                        player[nowPlayer].setState(STATES[cases]);
                        break;
                    }
                }
                //五花
                else if (cases == 3) {
                    int sum = faceSum[10] + faceSum[11] + faceSum[12];// JQK牌总数
                    if (sum == 4 && faceSum[9] == 1) {
                        player[nowPlayer].setState(STATES[cases]);
                        break;
                    }
                }
                //四花
                else if (cases == 4) {
                    for (int i = 0; i < CARDS_PER_PLAYER - 2; i++) {
                        for (int j = i + 1; j < CARDS_PER_PLAYER - 1; j++) {
                            for (int k = j + 1; k < CARDS_PER_PLAYER; k++) { //抽3张
                                if ((tempCard[i].getPoint() + tempCard[j].getPoint() + tempCard[k].getPoint()) % 10 == 0) {
                                    int sum = 0;//记录剩下的牌的点数大小
                                    for (int w = 0; w < 5; w++) {
                                        if (w != i && w != j && w != k) {
                                            sum += tempCard[w].getPoint();
                                        }
                                    }
                                    if (sum % 10 == 0) {
                                        player[nowPlayer].setState(STATES[cases]);
                                        setOverFlag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (setOverFlag) {
                        break;
                    }
                }
                //牛牛
                else if (cases == 5) {
                    boolean isHaveBulls = false;
                    int biggestPoint = 0;
                    for (int i = 0; i < CARDS_PER_PLAYER - 2; i++) {
                        for (int j = i + 1; j < CARDS_PER_PLAYER - 1; j++) {
                            for (int k = j + 1; k < CARDS_PER_PLAYER; k++) { //抽3张
                                if ((tempCard[i].getPoint() + tempCard[j].getPoint() + tempCard[k].getPoint()) % 10 == 0) {
                                    int sum = 0;//记录剩下的牌的点数大小
                                    isHaveBulls = true;
                                    for (int w = 0; w < 5; w++) {
                                        if (w != i && w != j && w != k) {
                                            sum += tempCard[w].getPoint();
                                        }
                                    }
                                    sum = sum % 10;
                                    if (sum > biggestPoint) {
                                        biggestPoint = sum;
                                    }
                                }
                            }
                        }
                    }
                    if (isHaveBulls) {
                        player[nowPlayer].setState(STATES[cases]);
                        player[nowPlayer].setTestPoint(biggestPoint);
                        break;
                    }
                }
                //牛X
                else if (cases == 6) {
                    player[nowPlayer].setState(STATES[cases]);
                    break;
                }
                //没牛
            }
            //牌最大价值生成
            long nowScore = 0;
            switch (cases) {
                case 6:
                    nowScore = 0;
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
                case 5:
                    nowScore = (long) 1e2;
                    nowScore = 100 * player[nowPlayer].getTestPoint();
                    //最大只能加900
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    //最大只能加51
                    break;
                case 4:
                    nowScore = (long) 1e5;
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
                case 3:
                    nowScore = (long) 1e7;
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
                case 2:
                    nowScore = (long) 1e9;
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
                case 1:
                    nowScore = E11;
                    nowScore = 100 * player[nowPlayer].getTestPoint();
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
                case 0:
                    nowScore = E14;
                    nowScore += (long) player[nowPlayer].getBiggestPoint();
                    break;
            }
            player[nowPlayer].setTestScore(nowScore);
            score[nowPlayer] = nowScore;

/*          虚拟分计算，按照虚拟分比较，方便直接排序
            虚拟分计算公式：
            斗牛的规则有7个级别，大吃小。而每种牌最大的分支情况
            要比较2次，比如单牛和五小，先算点数，再算单张，点数
            最大情况五小是5-10，而单牛有1-9种情况，取10种，也就
            是说，同类的牌最多有 牌总数（52）*10种情况（简化，
            实际肯定没这么多）。取每次100的大小，为下
            0-1e2-1e5-1e7-1e9-1e11-1e14----->MAX=1e16 可以存在
            long long 类型中。
            另外要注意循环过后存放的牌型大小加了1*/

        }
        //全部玩家的牌最大价值生成完毕，开始比较
        sortNumber(score);
        for (int rank = 0; rank < players; rank++) {
            for (int nowPlayer = 0; nowPlayer < players; nowPlayer++) {
                if (player[nowPlayer].getTestScore() == score[rank]) {
                    System.out.println("第" + (rank + 1) + "名 ： 玩家" + (nowPlayer + 1) + " " + player[nowPlayer].name);
                    System.out.print("该玩家的牌为 ：");
                    for (int nowCard = 0; nowCard < CARDS_PER_PLAYER; nowCard++) {
                        System.out.print(player[nowPlayer].getCard(nowCard).getSuit() + player[nowPlayer].getCard(nowCard).getFace());
                    }
                    System.out.print("   最大牌 ： " + player[nowPlayer].getCard(player[nowPlayer].getBiggestIndex()).getSuit() + player[nowPlayer].getCard(player[nowPlayer].getBiggestIndex()).getFace());
                    System.out.println();
                    if (player[nowPlayer].getState().equals(STATES[5])) {
                        System.out.println("牌型为 " + player[nowPlayer].getState() + "：" + STATES_DANNIU_STATE[player[nowPlayer].getTestPoint()]);
                    } else if (player[nowPlayer].getState().equals(STATES[1])) {
                        System.out.println("牌型为 " + player[nowPlayer].getState() + "：" + STATES_WUXIAO_STATE[player[nowPlayer].getTestPoint()]);
                    } else {
                        System.out.println("牌型为 " + player[nowPlayer].getState());
                    }
                }
            }
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
