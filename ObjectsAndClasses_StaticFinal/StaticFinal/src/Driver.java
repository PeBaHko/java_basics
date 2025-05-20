public class Driver {
    private final DriveType type;
    private final DriveVolume volume;
    private final int weight;
    public Driver(DriveType type, DriveVolume volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }
    public DriveType getType() { return type; }
    public DriveVolume getVolume() { return volume; }
    public int getWeight() {
        return weight;
    }
}