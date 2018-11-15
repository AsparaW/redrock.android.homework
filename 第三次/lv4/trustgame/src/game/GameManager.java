package game;


import javafx.scene.image.Image;
import sample.UI;

import java.util.*;

public class GameManager {
    private static final int MAX_PLAYER = 25;
    private static final int START_MONEY = 300;

    private static double wrongChance = 0.05;
    private static int competionRound = 10;
    private static int rounds;
    private static int breedAndKill = 5;
    private static int playerNum;
    private static int allTrustCoin = 2;
    private static int allCheatCoin = 0;
    private static int cheatCoin = 3;
    private static int trustCoin = -1;
    private List<Player> player = new ArrayList<Player>();
    private HashMap<Player, Integer> gameData;
    private HashMap<Player, Integer> score;

    public GameManager(int allTrustCoin, int allCheatCoin, int cheatCoin, int trustCoin, int breedAndKill, int rounds, int competionRound, double wrongChance) {
        this.allTrustCoin = allTrustCoin;
        this.allCheatCoin = allCheatCoin;
        this.cheatCoin = cheatCoin;
        this.trustCoin = trustCoin;
        this.breedAndKill = breedAndKill;
        this.rounds = rounds;
        this.wrongChance = wrongChance;
        this.competionRound = competionRound;
    }
    public GameManager(){
        //DO NOTHING
    }

    public void Change(int allTrustCoin, int allCheatCoin, int cheatCoin, int trustCoin, int breedAndKill, int rounds, int competionRound, double wrongChance) {
        this.allTrustCoin = allTrustCoin;
        this.allCheatCoin = allCheatCoin;
        this.cheatCoin = cheatCoin;
        this.trustCoin = trustCoin;
        this.breedAndKill = breedAndKill;
        this.rounds = rounds;
        this.wrongChance = wrongChance;
        this.competionRound = competionRound;
    }



    // 游戏开始
    public void init(HashMap player) {


        //add player
        Set<HashMap.Entry<Player, Integer>> tempSet = player.entrySet();
        Iterator<HashMap.Entry<Player, Integer>> it = tempSet.iterator();

        int nowPlayer = 0;
        while (it.hasNext()) {
            Map.Entry<Player, Integer> entry = it.next();

            int tempNum = entry.getValue();
            for (int i = 0; i < tempNum; i++) {
                Class<? extends Player> temp = entry.getKey().getClass();

                try {
                    Player tempPlayer = temp.newInstance();
                    tempPlayer.init(MAX_PLAYER, nowPlayer);
                    tempPlayer.addCoin(START_MONEY);
                    Image tmpImage = new Image(tempPlayer.getPATH());
                    UI.picBox[nowPlayer].setImage(tmpImage);
                    this.player.add(tempPlayer);


                } catch (Exception ignored) {

                }

                nowPlayer++;
            }
        }


        //add player over
        score = new HashMap<>();

        for (Player tempPlayer : this.player) {
            score.put(tempPlayer, tempPlayer.getScore());

        }
        playerNum = this.player.size();

        //playerNum = player.length;
        for (int i = 0; i < competionRound; i++)
            Round(rounds);
    }


    public void Round(int rounds) {
        while (rounds != 0) {
            System.out.println("NOW ROUND:" + (this.rounds - rounds));

            for (int i = 0; i < playerNum; i++) {
                for (int j = i + 1; j < playerNum; j++) {
                    boolean leftChoice, rightChoice;
                    leftChoice = player.get(i).doIsCheat(player.get(j));
                    rightChoice = player.get(j).doIsCheat(player.get(i));
                    Random r = new Random();
                    double ran = r.nextDouble();
                    if (ran < wrongChance) {
                        leftChoice = !leftChoice;
                    }
                    ran = r.nextDouble();
                    if (ran < wrongChance) {
                        rightChoice = !rightChoice;
                    }
                    player.get(i).getOther(player.get(j), rightChoice);
                    player.get(j).getOther(player.get(i), leftChoice);
                    System.out.println(player.get(i).getName() + leftChoice);
                    System.out.println(player.get(j).getName() + rightChoice);
                    if (leftChoice && rightChoice) {
                        player.get(i).addCoin(allCheatCoin);
                        player.get(j).addCoin(allCheatCoin);
                    } else if (leftChoice && !rightChoice) {
                        player.get(i).addCoin(cheatCoin);
                        player.get(j).addCoin(trustCoin);
                    } else if (!leftChoice && rightChoice) {
                        player.get(j).addCoin(cheatCoin);
                        player.get(i).addCoin(trustCoin);
                    } else if (!leftChoice && !rightChoice) {
                        player.get(i).addCoin(allTrustCoin);
                        player.get(j).addCoin(allTrustCoin);
                    }
                    //refresh
                    score.replace(player.get(i), player.get(i).getScore());
                    score.replace(player.get(j), player.get(j).getScore());
                    System.out.println(Integer.toString(player.get(i).getScore()));
                    System.out.println(Integer.toString(player.get(j).getScore()));
                    System.out.println("OK");
                    //Thread Sleep


                    //TS over
/*                    Main.myUI.scoreText[i].setText(Integer.toString(player.get(i).getScore()));
                    Main.myUI.;*/
                    UI.scoreText[i].setText(Integer.toString(player.get(i).getScore()));
                    UI.scoreText[j].setText(Integer.toString(player.get(j).getScore()));
/*                    Platform.runLater(() -> )));
                    Platform.runLater(() -> UI.scoreText[b].setText(Integer.toString(player.get(b).getScore())));*/

                }
            }
            rounds--;

/*            ////////////////////////////
// breed
            Player sortPlayer[] = new Player[playerNum];
            int sortScore[] = new int[playerNum];


            for (int i = 0; i < playerNum; i++) {
                sortPlayer[i] = player.get(i);
                sortScore[i] = player.get(i).getScore();
            }

            for (int i = 0; i < sortScore.length - 1; i++) {
                for (int j = 0; j < sortScore.length - 1 - i; j++) {
                    if (sortScore[j] > sortScore[j + 1]) {
                        int temp = sortScore[j];
                        sortScore[j] = sortScore[j + 1];
                        sortScore[j + 1] = temp;
                        Player tempP = sortPlayer[j];
                        sortPlayer[j] = sortPlayer[j + 1];
                        sortPlayer[j + 1] = tempP;
                    }
                }
            }

            for (Player temp : sortPlayer) {
                System.out.println(temp.getName());
                System.out.println(temp.getScore());
            }


            //kill
            int killIndex[] = new int[breedAndKill];
            for (int i = 0; i < breedAndKill; i++) {
                Player temp = sortPlayer[i];
                killIndex[i] = temp.getIndex();
                player.remove(temp);
            }
            //breed
            for (int i = playerNum - breedAndKill; i < playerNum; i++) {
                Player temp = sortPlayer[i];
                int coin = sortPlayer[i].getScore();

                Class<? extends Player> clazz = temp.getClass();
                try {

                    Player tempPlayer = clazz.newInstance();
                    tempPlayer.init(MAX_PLAYER, killIndex[-playerNum + i + breedAndKill]);
                    tempPlayer.addCoin(coin);
                    this.player.add(tempPlayer);

                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }

            // breed over
            int nowPlayer = 0;
            for (Player temp : this.player) {
                System.out.println(temp.getName());
                Image tmpImage = new Image(temp.getPATH());
                UI.picBox[nowPlayer].setImage(tmpImage);
                nowPlayer++;
            }
            //refresh UI
////////////////////////////*/


        }

////////////////////////////
// breed
        Player sortPlayer[] = new Player[playerNum];
        int sortScore[] = new int[playerNum];


        for (int i = 0; i < playerNum; i++) {
            sortPlayer[i] = player.get(i);
            sortScore[i] = player.get(i).getScore();
        }

        for (int i = 0; i < sortScore.length - 1; i++) {
            for (int j = 0; j < sortScore.length - 1 - i; j++) {
                if (sortScore[j] > sortScore[j + 1]) {
                    int temp = sortScore[j];
                    sortScore[j] = sortScore[j + 1];
                    sortScore[j + 1] = temp;
                    Player tempP = sortPlayer[j];
                    sortPlayer[j] = sortPlayer[j + 1];
                    sortPlayer[j + 1] = tempP;
                }
            }
        }

        for (Player temp : sortPlayer) {
            System.out.println(temp.getName());
            System.out.println(temp.getScore());
        }


        //kill
        int killIndex[] = new int[breedAndKill];
        for (int i = 0; i < breedAndKill; i++) {
            Player temp = sortPlayer[i];
            killIndex[i] = temp.getIndex();
            player.remove(temp);
        }
        //breed
        for (int i = playerNum - breedAndKill; i < playerNum; i++) {
            Player temp = sortPlayer[i];
            int coin = sortPlayer[i].getScore();

            Class<? extends Player> clazz = temp.getClass();
            try {

                Player tempPlayer = clazz.newInstance();
                tempPlayer.init(MAX_PLAYER, killIndex[-playerNum + i + breedAndKill]);
                tempPlayer.addCoin(coin);
                this.player.add(tempPlayer);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        // breed over
        int nowPlayer = 0;
        for (Player temp : this.player) {
            System.out.println(temp.getName());
            Image tmpImage = new Image(temp.getPATH());
            UI.picBox[nowPlayer].setImage(tmpImage);
            nowPlayer++;
        }
        //refresh UI
////////////////////////////

    }
}
