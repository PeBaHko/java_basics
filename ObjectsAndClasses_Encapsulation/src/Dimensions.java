public class Dimensions {
    private final int height;
    private final int length;
    private final int width;

    Dimensions(int h, int l, int w) {
        this.height = h;
        this.length = l;
        this.width = w;
    }
    public int getHeight() {
        return this.height;
    }
    public int getLength() {
        return this.length;
    }
    public int getWidth() {
        return this.width;
    }
    public void printHeight () {
        System.out.print(this.height);
    }
    public void  printLength() {
        System.out.print(this.length);
    }
    public void printWidth() {
        System.out.print(this.width);
    }
    public void printVolume() {
        System.out.print(getVolume());
    }

    public int getVolume() {
        return height*length*width;
    }
}
