public class Main {

    public static void main(String[] args) {
        //Arithmetic arcObj = new Arithmetic(5, 2);
        //System.out.println("Сумма: " + arcObj.sum());
        //System.out.println("Произведение: " + arcObj.multi());
        //System.out.println("Большее число: " + arcObj.big());
        //System.out.println("Меньшее число: " + arcObj.lit());

        Basket basket = new Basket();
        Basket basket1 = new Basket();
        basket.clear();
        basket1.clear();
        basket.add("Milk", 40);
        basket1.add("Milk", 50, 3);
        //basket.print("Milk");

        basket.add("Bread", 30, 2);
        basket1.add("Bread", 20);
        //basket.print("Bread");

        basket.add("Meat", 900, 1, 400);
        basket1.add("Chicken", 200, 4, 800);
        basket.print("Meat");
        basket1.print("Bread");
        //basket.print("Milk");
        System.out.println();
        System.out.println("Общая стоимость товаров в первой корзине:  " + basket.getTotalPrice());
        System.out.println("Общая количество товаров в первой корзине: " + basket.getTotalCount());
        System.out.println("Общий вес товаров первой корзины:          " + basket.getTotalWeight());
        System.out.println("Общая стоимость товаров во второй корзине: " + basket1.getTotalPrice());
        System.out.println("Общая количество товаров в первой корзине: " + basket1.getTotalCount());
        System.out.println("Общий вес товаров второй корзины:          " + basket1.getTotalWeight());
        System.out.println("Число корзин:                              " + Basket.getCount());
        System.out.println("Средняя стоимость товара:                  " + String.format("%.2f", Basket.getAverageFullPrice()));
        System.out.println("средняя стоимость корзины:                 " + Basket.getAverageBasket());
    }
}