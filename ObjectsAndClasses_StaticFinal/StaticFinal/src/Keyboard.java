public class Keyboard {
    private final KbdType type;
    private final KbdLight light;
    private  final int weight;
    public Keyboard(KbdType type, KbdLight light, int weight) {
        this.type = type;
        this.light = light;
        this.weight = weight;
    }
    public KbdType getType() { return type; }
    public KbdLight getLight() { return light; }
    public int getWeight() {
        return weight;
    }
}
