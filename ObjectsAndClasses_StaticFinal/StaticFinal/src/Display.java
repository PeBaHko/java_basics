public class Display {
    private final DisDiag diagonal;
    private final DisplayType type;
    private final int weight;
    public Display(DisDiag diagonal, DisplayType type, int weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }
    public DisDiag getDiagonal() { return diagonal; }
    public DisplayType getType() { return type; }
    public int getWeight() {
        return weight;
    }
}