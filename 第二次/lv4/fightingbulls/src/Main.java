import java.util.Scanner;

public class Main {
    private static final String ASK_FOR_MODE = "是否打开给牌模式？ （Y/N）";
    private static final String ASK_FOR_GIVE = "输入给牌（格式：牌1序号，牌2序号，...，牌玩家数*5序号）";
    private static final String ASK_FOR_CONTINUE = "继续吗？（Y/N）";
    private static final String ASK_PLAYERS = "请输入玩家数，输入0返回：";
    private static final String ASK_NAME = "请输入每个玩家的名字，可以不输入";
    private static final String[] WELCOME = {"请输入执行的操作",
            "1：快速双人开始",
            "2：自定义开始（支持自定义名字，给牌，自定义玩家数量）",
            "3：快速开始100回合"};

    public static void main(String[] args) {
        Scanner mySc = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < WELCOME.length; i++) {
                System.out.println(WELCOME[i]);
            }
            //Show welcome message
            String tmp = mySc.nextLine();
            if (tmp.equals("3")) {
                GameRound myGame = new GameRound(2);
                myGame.rounds(100);
                while (true) {
                    System.out.println(ASK_FOR_CONTINUE);
                    String temp = mySc.nextLine();
                    if (temp.equals("Y") || temp.equals("y"))
                        myGame.newRound();
                    else
                        break;
                }
            }
            if (tmp.equals("2")) {

                System.out.println(ASK_PLAYERS);
                int control = Integer.parseInt(mySc.nextLine());
                if (control > 1) {
                    GameRound myGame = new GameRound(control);
                    if (myGame.mode != -1) {
                        System.out.println(ASK_NAME);
                        for (int i = 0; i < control; i++) {
                            System.out.println("请输入玩家" + (i + 1) + "的名字：");
                            String tempStr = mySc.nextLine();
                            myGame.player[i].setName(tempStr);
                        }
                        System.out.println(ASK_FOR_MODE);
                        String temp = mySc.nextLine();
                        if (temp.equals("Y") || temp.equals("y")) {
                            myGame.setMode(1);
                            myGame.printOrder();
                            System.out.println(ASK_FOR_GIVE);
                            myGame.newRoundSelect(mySc.nextLine());
                        } else
                            myGame.newRound();
                        while (true) {
                            System.out.println(ASK_FOR_CONTINUE);
                            temp = mySc.nextLine();
                            if (temp.equals("Y") || temp.equals("y"))
                                if (myGame.mode == 1) {
                                    myGame.printOrder();
                                    System.out.println(ASK_FOR_GIVE);
                                    myGame.newRoundSelect(mySc.nextLine());
                                } else
                                    myGame.newRound();
                            else
                                break;
                        }
                    }
                }
            }
            if (tmp.equals("1")) {
                GameRound myGame = new GameRound(2);
                myGame.newRound();
                while (true) {
                    System.out.println(ASK_FOR_CONTINUE);
                    String temp = mySc.nextLine();
                    if (temp.equals("Y") || temp.equals("y"))
                        myGame.newRound();
                    else
                        break;
                }
            }
        }
    }
}
