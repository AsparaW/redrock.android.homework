import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner mySc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入执行的操作");
            System.out.println("1：默认开始");
            System.out.println("2：自定义开始");
            switch (mySc.nextLine()) {
                case "2":
                    while (true) {
                        System.out.println("请输入玩家数，输入0返回：");
                        int control = Integer.parseInt(mySc.nextLine());
                        if (control > 1) {
                            GameRound myGame = new GameRound(control);
                            System.out.println("是否打开给牌模式？ （Y/N）");
                            String temp=mySc.nextLine();
                            if (temp.equals("Y") || temp.equals("y")) {
                                myGame.setMode(1);
                                myGame.printOrder();
                                System.out.println("输入给牌");
                                myGame.newRoundSelect(mySc.nextLine());
                            }
                            else
                                myGame.newRound();
                            while (true) {
                                System.out.println("继续吗？（Y/N）");
                                temp=mySc.nextLine();
                                if (temp.equals("Y") || temp.equals("y"))
                                    if (myGame.mode==1){
                                        myGame.printOrder();
                                        System.out.println("输入给牌");
                                        myGame.newRoundSelect(mySc.nextLine());
                                    }
                                    else
                                        myGame.newRound();
                                else
                                    break;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case "1":
                    GameRound myGame = new GameRound(2);
                    myGame.newRound();
                    while (true) {
                        System.out.println("继续吗？（Y/N）");
                        String temp=mySc.nextLine();
                        if (temp.equals("Y") || temp.equals("y"))
                            myGame.newRound();
                        else
                            break;
                    }
                    break;
            }
        }
    }
}
