public class Memory {
    private final MemType type;
    private final MemVolume volume;
    private final  int weight;
    public Memory(MemType type, MemVolume volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }
    public MemType getType() { return type; }
    public MemVolume getVolume() { return volume; }
    public int getWeight() {
        return weight;
    }
}