public class Basket {

    private static int count = 0;  //  общий счётчик создвнных корзин
    private String items = "";
    private int totalPrice = 0; // общая стоимость всех товаров в корзине
    private int totalCount = 0; // общее количество товаров в корзине
    private int limit;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items += items;
        this.totalPrice = totalPrice;
        addPriceAndThing(totalPrice, 1);
        totalCount += 1;
    }

    // my work

    private static int fullPrice = 0; // общая стоимость всех товаров во всех корзинах
    private static int fullThing = 0; // общее количество товаров во всех корзинах
    private  static int fullBasket = 0;
    //private static void addPriceAndThing (int totalPrice) {     }
    private static void addPriceAndThing (int totalPrice, int count) {
        fullPrice += totalPrice;
        //addPriceAndThing(totalPrice*count);
        fullThing += count;
    }
    public static double getAverageFullPrice() {
        double a = fullPrice;
        a = a / fullThing;
        return (a);
    }
    public static double getAverageBasket() {
        return ((double) (fullPrice / count));
    }

    private double totalWeight = 0;  // общая масс всех товаров в корзине
    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight += weight;
    }
    public double getTotalWeight() {
        return totalWeight;
    }
    // end of my work

    public static int getCount() {
        return count;
    }

    public static void increaseCount(int count) {
        Basket.count += count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occurred :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price;
        totalPrice += count * price;
        totalCount += count;
        addPriceAndThing((count*price), count);
    }

    public void clear() {
        items = "";
        if (totalPrice != 0) { fullPrice -= totalPrice; }
        if (totalCount != 0) { fullThing -= totalCount; }
        totalPrice = 0;
        totalCount = 0;
        totalWeight = 0;
        //addPriceAndThing(-totalPrice,-count);

    }

    public int getTotalPrice() {
        return totalPrice;
    }
    public int getTotalCount() { return totalCount; }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.print(System.lineSeparator() + "Корзина: " + title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}