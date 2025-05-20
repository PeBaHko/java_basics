public class Processor {
    private final CpuFixClk frequency;
    private final CpuCoreCount corenumber;
    private final CpuVendor vendor;
    private final int weight;
    public  Processor(CpuFixClk frequency, CpuCoreCount corenumber, CpuVendor vendor, int weight) {
        this.frequency = frequency;
        this.corenumber = corenumber;
        this.vendor = vendor;
        this.weight = weight;
    //public  Processor(Processor cpu, Memory mem, Driver drv, Driver scr, Keyboard kbd) {
    }
    public CpuFixClk getFrequency() { return frequency; }
    public CpuCoreCount getCorenumber() { return corenumber; }
    public CpuVendor getVendor() { return vendor; }
    public int getWeight() {
        return weight;
    }
}
