package calc;

public class DividedZeroException extends Exception {
    public DividedZeroException(String msg) {
        super("错误！不能除0！" + msg);
    }
}
