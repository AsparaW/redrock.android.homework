import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GetTime mytime = new GetTime();
        Scanner sc = new Scanner(System.in);
        do {
            mytime.showWelcome();
            String temp = sc.nextLine();
            if (temp.equals("1")) {
                System.out.println("请输入您当前的GMT : 格式 +8 +0 -5");
                System.out.print("GMT");
                String input = sc.nextLine();
                mytime.changeGMT(input);

            } else if (temp.equals("2")) {
                mytime.printMillis();
            } else if (temp.equals("3")) {
                System.out.println("输入格式 xxxxxxxx(无毫秒)");
                String tmp = sc.nextLine();
                mytime.inputPrintConvert(tmp);
            } else if (temp.equals("4")) {
                System.out.println("输入格式 1970-1-1-12-30-57 当前GMT：" + mytime.gmt);
                String tmp = sc.nextLine();
                mytime.convertBack(tmp);
            } else if (temp.equals("exit")) break;
            else {
                System.out.println("操作无效");
            }
        } while (true);
        sc.close();
    }
}
