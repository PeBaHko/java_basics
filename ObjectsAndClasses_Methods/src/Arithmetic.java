public class Arithmetic {
    private final int a;
    private final int b;
    public Arithmetic(int i, int j) {
        a = i;
        b = j;
    }
    public int sum() {
        return a+b;
    }
    public int big() {
        return Math.max(a, b);
        // if (a > b) { return a } else { return b }
    }
    public int lit() {
        return Math.min(a, b);
        // if (a < b) { return a } else { return b }
    }
    public int multi() {
        return a*b;
    }

}
