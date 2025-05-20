public class Cargo {
    //private Cargo preview = null;
    //private Cargo next = null;
    private final Dimensions dimension;
    private final int weight;
    private final String address;
    private final boolean isOrient;
    private final String regNum;
    private final boolean isGlass;

    public Cargo (/*Cargo next, int h, int l, int w*/ Dimensions dimension, int weight, String address, boolean isOrient, String regNum, boolean isGlass) {
        //this.next = next;
        this.dimension = dimension;//new Dimensions(h,l,w);
        this.weight = weight;
        this.address = address;
        this.isOrient = isOrient;
        this.regNum = regNum;
        this.isGlass = isGlass;
    }
    public Cargo setDimension(Dimensions newDimension) {
        return new Cargo(newDimension, this.weight, this.address, this.isOrient, this.regNum, this.isGlass);
    }
    public Cargo setWeight(int newWeight) {
        return new Cargo(this.dimension, newWeight, this.address, this.isOrient, this.regNum, this.isGlass);
    }
    public Cargo setAddress(String newAddress) {
        return new Cargo(this.dimension, this.weight, newAddress, this.isOrient, this.regNum,this.isGlass);
    }
    public Cargo setIsOrient(boolean newIsOrient) {
        return new Cargo(this.dimension, this.weight, this.address, newIsOrient, this.regNum, this.isGlass);
    }
    public Cargo setRegNum(String newRegNum) {
        return  new Cargo(this.dimension, this.weight, this.address, this.isOrient, newRegNum,this.isGlass);
    }
    public Cargo setIsGlass(boolean newIsGlass) {
        return new Cargo(this.dimension, this.weight, this.address, this.isOrient, this.regNum, newIsGlass);
    }
    public Dimensions getDimension() {
        return this.dimension;
    }

//    public Cargo getPreview () {
//        return preview;
//    }
    public void printOrder () {
        System.out.print("Высота: "); this.dimension.printHeight(); System.out.println();
        System.out.print("Длина:  "); this.dimension.printLength(); System.out.println();
        System.out.print("Ширина: "); this.dimension.printWidth();  System.out.println();
        System.out.print("Объём:  "); this.dimension.printVolume(); System.out.println();
        System.out.print("Масса:  "+ this.weight); System.out.println();
        System.out.print("Адрес:  " + this.address); System.out.println();
        String message = this.isOrient ? "не кантовать" : "можно переворачивать";
        System.out.print("Груз " + message);  System.out.println();
        System.out.print("Рег. № заказа: " + this.regNum);  System.out.println();
        message = this.isGlass ? "ОСТОРОЖНО - хрупкий груз " : "обычная транспортировка";
        System.out.print("Условия перевозки: " + message); System.out.println();
    }
//    public Cargo getNext () {
//        return next;
//    }
//    public void setNext(Cargo next) {
//        this.next = next;
//    }
}
