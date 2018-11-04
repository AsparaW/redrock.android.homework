import java.util.Random;

public class Card {
    Random weightSetter = new Random();
    private String suit;
    private String face;
    private int point;
    private int comparePoint;
    private double weight;

    Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
        switch (face) {
            case "A":
                point = 1;
                break;
            case "J":
                point = 10;
                break;
            case "Q":
                point = 10;
                break;
            case "K":
                point = 10;
                break;
            default:
                point = Integer.parseInt(face);
        }
    }

    public int getPoint() {
        return point;
    }

    public int getComparePoint() {
        return comparePoint;
    }

    public void setComparePoint(int comparePoint) {
        this.comparePoint = comparePoint;
    }

    public String getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    public void randomizeWeight() {
        weight = weightSetter.nextDouble();
    }

    public double getWeight() {
        return weight;
    }
}
