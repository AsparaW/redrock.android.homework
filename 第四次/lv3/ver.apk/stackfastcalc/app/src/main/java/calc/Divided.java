package calc;

public class Divided {
    public static double divide(double a, double b) throws DividedZeroException {
        DividedZeroException dze = new DividedZeroException("at " + Double.toString(a) + " / " + Double.toString(b));
        if (b == 0) {
            throw dze;
        }
        return a / b;
    }
}
