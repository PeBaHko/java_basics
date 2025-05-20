public class CargoLink {
    private CargoLink preview = null;
    private Cargo current = null;
    private CargoLink next = null;
    CargoLink(CargoLink preview, Cargo current) {
        this.preview = preview;
        this.current = current;
    }
    public CargoLink getPreview() {
        return preview;
    }
    public Cargo getCurrent() {
        return current;
    }
    public CargoLink getNext() {
        return next;
    }
    public void setPreview(CargoLink preview) {
        this.preview = preview;
    }
    public void setCurrent(Cargo current) {
        this.current = current;
    }
    public void setNext(CargoLink next) {
        this.next = next;
    }
}
