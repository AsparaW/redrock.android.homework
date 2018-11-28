package calc;

public class ExpreesionException extends Exception {
    ExpreesionException(String msg) {
        super("表达式错误！" + msg);
    }
}
