package calc;

import java.text.DecimalFormat;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static calc.Divided.divide;

public class CalcTools {
    private static final double PRECISION = 1e-8;
    private Stack<Character> operation;
    private Stack<Double> number;
    private String str;
    private ExpreesionException ee;
    private char num[] = new char[40];
    private char getc;
    private double result;
    private boolean off;
    private boolean isDecimal;

    public CalcTools(String str) {
        off = false;
        str = str.replaceAll("[\\s]", "");//trim
        str = str.replaceAll(",","");
        str = str.replaceAll("÷", "/");
        str = str.replaceAll("＋", "+");
        str = str.replaceAll("×", "*");
        str = str.replaceAll("－", "-");
        str = str.replaceAll("X|x", "*");
        str = str.replaceAll("）", ")");
        str = str.replaceAll("（", "(");
        this.str = str;
        operation = new Stack<>();
        number = new Stack<>();
        try {
            check();
            this.str = str + '#';
            result = calc();
        } catch (ExpreesionException e) {
            e.printStackTrace();
            off = true;
        }
    }

    double calc() throws ExpreesionException {
        operation.push('#');
        getchar();
        boolean isNum = false;
        while (getc != '#' || operation.peek() != '#') {
            int i = 0;
            double a, b;
            char oper;
            while (Character.isDigit(getc) || getc == '.') {
                if (getc == '.' && !isDecimal) {
                    isDecimal = true;
                } else if (getc == '.') {
                    //ERROR
                    throw ee = new ExpreesionException("wrong expression: decimal error.too many \".\"?");
                }
                isNum = true;
                num[i++] = getc;
                getchar();
            }
            if (isNum) {
                isDecimal = false;
                isNum = false;
                num[i] = '\0';
                String temp = new String(num, 0, i);
                Double tmp = Double.parseDouble(temp);
                number.push(tmp);
            }

            switch (Precede(operation.peek(), getc)) {
                case '>':
                    oper = operation.peek();
                    operation.pop();
                    b = number.peek();
                    number.pop();
                    a = number.peek();
                    number.pop();
                    number.push(operate(a, oper, b));
                    break;
                case '=':
                    operation.pop();
                    getchar();
                    break;
                case '<':
                    operation.push(getc);
                    getchar();
                    break;
            }

        }
        return number.peek();
    }

    public String getResult() {
        if (off) {
            return "出错！";
        }
        String finres;
        DecimalFormat df = new DecimalFormat(",###.######");
        long resInt = Math.round(result);
        if (Math.abs(resInt - result) < PRECISION) {
            //格式化输出
            finres = df.format(resInt);//浮点转int
            //
        } else {
            //格式化输出
            finres = df.format(result);
            //
        }
        return finres;
    }

    void getchar() {
        getc = str.charAt(0);
        str = str.substring(1);
    }

    double operate(double a, char theta, double b) {
        if (theta == '+')
            return a + b;
        else if (theta == '-')
            return a - b;
        else if (theta == '*')
            return a * b;
        else if (theta == '/') {
            try {
                return divide(a, b);
            } catch (DividedZeroException e) {
                e.printStackTrace();
                off = true;
            }
        }
        return 0;
    }

    char Precede(char a, char b) throws ExpreesionException {
        if (a == '+' || a == '-') {
            if (b == '+' || b == '-' || b == ')' || b == '#')
                return '>';
            else
                return '<';
        } else if (a == '*' || a == '/') {
            if (b == '(')
                return '<';
            else
                return '>';
        } else if (a == '(') {
            if (b == '+' || b == '-' || b == '*' || b == '/' || b == '(')
                return '<';
            else if (b == ')')
                return '=';
            else {
                return 0;
            }
        } else if (a == ')') {
            if (b == '+' || b == '-' || b == '*' || b == '/' || b == ')' || b == '#')
                return '>';
            else if (b == '(') {
                //ERROR
                throw ee = new ExpreesionException("lack of (");

            }
        } else if (a == '#') {
            if (b == ')') {
                //ERROR
                throw ee = new ExpreesionException("lack of (");

            } else if (b == '#')
                return '=';
            else
                return '<';
        }
        throw ee = new ExpreesionException("at " + b);
    }

    void check() throws ExpreesionException {
        //check the expression
        String p;
        p = "\\+{2}|\\-{2}|\\*{2}|\\/{2}";
        Pattern pat = Pattern.compile(p);
        Matcher matcher = pat.matcher(str);
        if (matcher.find()) {
            throw ee = new ExpreesionException("calculator expression error");
        }
        p = "\\(";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        int numLeft = 0, numRight = 0;
        while (matcher.find()) {
            numLeft++;
        }
        p = "\\)";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        while (matcher.find()) {
            numRight++;
        }
        if (numRight > numLeft) {
            throw ee = new ExpreesionException("lack of (");
        } else if (numRight < numLeft) {
            throw ee = new ExpreesionException("lack of  )");
        }
        p = "[\\d]";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        if (!matcher.find()) {
            throw ee = new ExpreesionException("lack of numbers");
        }
        p = "\\+|\\-|\\*|\\/";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        if (!matcher.find()) {
            throw ee = new ExpreesionException("lack of operators: make sure your expression have +-*/ ");
        }

        char last = str.charAt(str.length() - 1);
        if (!(Character.isDigit(last) || last == ')')) {
            throw ee = new ExpreesionException("wrong expression");
        }
        p = "=";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        if (matcher.find()) {
            throw ee = new ExpreesionException("wrong expression: have \"=\"?");
        }
/*        p="\\.";
        pat = Pattern.compile(p);
        matcher = pat.matcher(str);
        if (matcher.find()){
            throw ee = new ExpreesionException("wrong expression : have decimals?");
        }*/
    }
}
