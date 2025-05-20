import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static final Scanner scan = new Scanner(System.in);
    private static CargoLink firstOrder = null;
    private static CargoLink currentOrder = null;
    private static CargoLink lastOrder = null;
    private static String selectString;
    private static int select;
    /*
    try {
    } catch (NumberFormatException error) {
    }
     */
    private static void elevator() {
        int floor;
        Elevator elevator = new Elevator(-3, 26);
        //scan = new Scanner(System.in);
        String floorString;
        while(true) {
            System.out.println("\nCurrent floor: " + elevator.getCurrentFloor());
            System.out.println("Назад: 0");
            System.out.print("Введите номер этажа: ");
            floorString = scan.nextLine();
            try {
                floor = Integer.parseInt(floorString);
                if (floor == 0) {
                    break;
                }
                if (floor!= elevator.getCurrentFloor()) {
                elevator.move(floor);
                } else {
                    System.out.println("Лифт уже на этом этаже");
                }
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
    }
    private static void newOrder() {
        int height;
        int length;
        int width;
        int weight;
        String address;
        boolean isOrient;
        String regNum;
        boolean isGlass;

        System.out.println("Создание нового заказа");
        System.out.println("Ввод параметров:");
        System.out.println("1 - размер груза");
        while (true) {
            System.out.println("Введите высоту: ");
            selectString = scan.nextLine();
            try {
                height = Integer.parseInt(selectString);
                break;
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
        while (true) {
            System.out.println("Введите длину: ");
            selectString = scan.nextLine();
            try {
                length = Integer.parseInt(selectString);
                break;
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
        while (true) {
            System.out.println("Введите ширину: ");
            selectString = scan.nextLine();
            try {
                width = Integer.parseInt(selectString);
                break;
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
        while (true) {
            System.out.println("Введите массу груза: ");
            selectString = scan.nextLine();
            try {
                weight = Integer.parseInt(selectString);
                break;
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
        System.out.println("2 - получатель");
        System.out.println("Введите адрес доставки: ");
        address = scan.nextLine();

        System.out.println("3 - положение груза");
        System.out.println("Груз можно переворачивить (Y/N): ");
        selectString = scan.nextLine();
        while (true) {
            if (Objects.equals(selectString, "Y") || Objects.equals(selectString, "y")) {
                isOrient = false;
                break;
            } else if (Objects.equals(selectString, "N") || Objects.equals(selectString, "n")) {
                isOrient = true;
                break;
            } else {
                System.out.println("Incorrect input");
            }
        }
        System.out.println("4 - Регистрационный номер");
        System.out.println("Ввод: ");
        regNum = scan.nextLine();

        System.out.println("5 - Хрупкий груз");
        System.out.println("Ввод (Y/N): ");
        selectString = scan.nextLine();
        while (true) {
            if (Objects.equals(selectString, "Y") || Objects.equals(selectString, "y")) {
                isGlass = true;
                break;
            } else if (Objects.equals(selectString, "N") || Objects.equals(selectString, "n")) {
                isGlass = false;
                break;
            } else {
                System.out.println("Incorrect input");
            }
        }
        if (firstOrder == null) {
            System.out.println("In to works 12");
            firstOrder = new CargoLink(null, new Cargo(new Dimensions(height, length, width), weight, address, isOrient, regNum, isGlass));
            lastOrder = firstOrder;
        } else {System.out.println("In to works 22");
            lastOrder.setNext(new CargoLink(lastOrder, new Cargo(new Dimensions(height, length, width), weight, address, isOrient, regNum, isGlass)));
            lastOrder = lastOrder.getNext();
        }


    }
    private static CargoLink getCurrentOrder(int numOfOrder) {
        CargoLink currentOrder;
        if(firstOrder == null) return null;
        //currentOrder++;
        currentOrder = firstOrder;
        int currentNum = 0;
        while (true) {
            currentNum++;
            if (currentNum == numOfOrder) {
                return currentOrder;
            } else {
                currentOrder = currentOrder.getNext();
                if (currentOrder == null) return null;
            }
        }
    }
    private static Dimensions updateDimension () {
        String inputDimension;
        int input;
        int height;
        int length;
        int width;
        System.out.println("задайте новые габариты: ");
        while (true) {
            System.out.println("1 - введите высоту: ");
            System.out.println("0 - выход");
            inputDimension = scan.nextLine();
            try {
                height = Integer.parseInt(inputDimension);
                if (height==0) return null;//break;
                if (height<0) {
                    System.out.println("Incorrect input");
                    continue;
                }
                while (true) {
                    System.out.println("1 - введите длину: ");
                    System.out.println("0 - выход");
                    inputDimension = scan.nextLine();
                    try {
                        length = Integer.parseInt(inputDimension);
                        if (length==0) return null;//break;
                        if (length<0) {
                            System.out.println("Incorrect input");
                            continue;
                        }
                        while (true) {
                            System.out.println("1 - введите ширину: ");
                            System.out.println("0 - выход");
                            inputDimension = scan.nextLine();
                            try {
                                width = Integer.parseInt(inputDimension);
                                if (width==0) return null;//break;;
                                if (width<0) {
                                    System.out.println("Incorrect input");
                                    continue;
                                }
                                return new Dimensions(height, length, width);
                            } catch (NumberFormatException error) {
                                System.out.println("Incorrect input");
                            }
                        }
                    } catch (NumberFormatException error) {
                        System.out.println("Incorrect input");
                        //continue;
                    }
                }//new Dimensions(height, length, width);
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
                //continue;
            }
        }
    };
    private static int updateWeight () {
        String inputNewWeight;
        int newWeight;
        while (true) {
            System.out.println("Введите массу груза: ");
            inputNewWeight = scan.nextLine();
            try {
                newWeight = Integer.parseInt(selectString);
                if (newWeight == 0) {
                    return 0;
                } else if (newWeight < 0) {
                    System.out.println("Неверный ввод");
                } else {
                    return newWeight;
                }
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
        //*****

    };
    private static String updateAddress () {
        String inputNewAdres = "";
        while (true) {
            System.out.println("введите новый адрес: ");
            System.out.println("0 - выход");
            inputNewAdres = scan.nextLine();
            if (Objects.equals(inputNewAdres, "") ) {
                System.out.println("Incorrect input");
                continue;
            } else if (Objects.equals(inputNewAdres, "0")) {
                return "0";
            } else {
                return inputNewAdres;
            }
        }
    };
    private static byte updateIsOrient () {
        String inputNewOrient = "";
        //while (true) {
            System.out.println("Груз можно переворачивить? (Y/N): ");
            System.out.println("Выход - (любая клавиша)");
            inputNewOrient = scan.nextLine();
            //while (true) {
                if (Objects.equals(inputNewOrient, "Y") || Objects.equals(inputNewOrient, "y")) {
                    return 1;
                    //isOrient = false;
                    //break;
                } else if (Objects.equals(inputNewOrient, "N") || Objects.equals(inputNewOrient, "n")) {
                    return -1;
                    //isOrient = true;
                    //break;
                } else {
                    return 0;
                    //System.out.println("Incorrect input");
                }
            //}

        //}
        // 1 - можно крутить
        // 0 - выход
        // -1 - нельзя крутить
        //return 0;
    };
    private static String updateRegNum () {
        String inputNewRegMum = "";
        System.out.println("введите новый рег. номер: ");
        inputNewRegMum = scan.nextLine();
        return inputNewRegMum;
    };
    private static byte updateIsGlass () {
        String inputNewGlass;
        System.out.println("Груз хрупкий? (Y/N): ");
        System.out.println("Выход - (любая клавиша)");
        inputNewGlass = scan.nextLine();
        //while (true) {
            if (Objects.equals(inputNewGlass, "Y") || Objects.equals(inputNewGlass, "y")) {
                return 1;
                //isGlass = true;
                //break;
            } else if (Objects.equals(inputNewGlass, "N") || Objects.equals(inputNewGlass, "n")) {
                return -1;
                //isGlass = false;
                //break;
            } else {
                return 0;
                //System.out.println("Incorrect input");
            }
        //}
        //  1 - хрупкий
        // -1 - твёрдый
        //  0 - выход
        //return 0;
    };
    private static void updateOrder() {
        Dimensions newDimension = null;
        int newWeight = 0;
        String newAddress = "";
        byte newOrient = 0;
        String newRegNum = "";
        byte newIsGlass = 0;
        boolean done = false;
        boolean cancel = false;
        if (firstOrder == null) {
            System.out.println("Список заказов пуст");
        } else {
            String selectParameter = "";
            int numbParameter = 0;
            System.out.println("Назад: 0");
            listOfOrders();
            while (true) {
                System.out.println("Введите номер заказа: ");
                System.out.println("0 - Назад");
                selectString = scan.nextLine();
                cancel = false;
                try {
                    select = Integer.parseInt(selectString);
                    if (select==0) {
                        System.out.println("Отмена операции");
                        break;
                    }
                    if (select<0) { System.out.println("неверный ввод"); continue; };
                    currentOrder = getCurrentOrder(select);
                    if (currentOrder == null) {
                        System.out.println("заказ не найден");
                        continue;
                    }
                    while (true) {
                        System.out.println("Выберите изменяемый параметр: ");
                        System.out.println("1 - габариты: ");
                        System.out.println("2 - вес: ");
                        System.out.println("3 - адрес назначения: ");
                        System.out.println("4 - положение груза: ");
                        System.out.println("5 - регистрационный номер: ");
                        System.out.println("6 - хрупкость груза: ");
                        System.out.println("0 - Назад");
                        selectParameter = scan.nextLine();
                        try {
                            numbParameter = Integer.parseInt(selectParameter);
                            if (numbParameter==0) break;
                            switch (numbParameter) {
                                case 1 -> {
                                    cancel = true;
                                    newDimension = updateDimension();
                                    if (newDimension != null)  {
                                        lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setDimension(newDimension)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                    System.out.println("1 - габариты: ");
                                }
                                case 2 -> {
                                    cancel = true;
                                    newWeight = updateWeight();
                                    if (newWeight != 0) {
                                        lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setWeight(newWeight)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                    System.out.println("2 - вес: ");
                                }
                                case 3 -> {
                                    cancel = true;
                                    newAddress = updateAddress();
                                    if (!Objects.equals(newAddress, "0")) {
                                        lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setAddress(newAddress)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                    System.out.println("3 - адрес: "); }
                                case 4 -> {
                                    cancel = true;
                                    newOrient = updateIsOrient();
                                    if (newOrient != 0) {
                                        if (newOrient == 1) lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setIsOrient(false)));
                                        if (newOrient == -1) lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setIsOrient(true)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                    System.out.println("4 - положение: ");
                                }
                                case 5 -> {
                                    cancel = true;
                                    newRegNum = updateRegNum();
                                    if (!Objects.equals(newRegNum, "")) {
                                        lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setRegNum(newRegNum)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                    System.out.println("5 - номер: ");
                                }
                                case 6 -> {
                                    cancel = true;
                                    newIsGlass = updateIsGlass();
                                    System.out.println("6 - хрупкость: ");
                                    if (newIsGlass != 0) {
                                        if (newIsGlass == 1) lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setIsGlass(true)));
                                        if (newIsGlass == -1) lastOrder.setNext(new CargoLink(lastOrder, currentOrder.getCurrent().setIsGlass(false)));
                                        lastOrder.getNext().setPreview(lastOrder);
                                        lastOrder = lastOrder.getNext();
                                        done = true;
                                    }
                                }
                                default -> {
                                    System.out.println("вариант не найден");
                                    cancel = false;
                                }
                            }
                            if (cancel) break;
                        } catch (NumberFormatException error) {
                            System.out.println("Incorrect input");
                        }
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Incorrect input");
                }
                if (done) break;
            }
        }
    }
    private static void cancelOrder() {
        System.out.println("In to works 3");
        int counterOrder = 0;
        if (firstOrder == null) {
            System.out.println("Список заказов пуст");
        } else {
            listOfOrders();
            while(true) {
                System.out.println("Введите номер заказа");
                System.out.println("Назад: 0");
                //String selectString;
                selectString = scan.nextLine();
                try {
                    select = Integer.parseInt(selectString);
                    if (select == 0) {
                        System.out.println("Отмена операции");
                        break;
                    } else {
                        currentOrder = firstOrder;
                        counterOrder = 1;
                        while (true) {
                            if (counterOrder == select) {
                                if (counterOrder == 1) {
                                    if (currentOrder.getNext() == null) {
                                        firstOrder = null;
                                        break;
                                    } else {
                                        firstOrder = firstOrder.getNext();
                                        firstOrder.setPreview(null);
                                        break;
                                    }
                                } else {
                                    if (currentOrder.getNext() == null) {
                                        currentOrder.getPreview().setNext(null);
                                        break;
                                    } else {
                                        currentOrder.getPreview().setNext(currentOrder.getNext());
                                        currentOrder.getNext().setPreview(currentOrder.getPreview());
                                        break;
                                    }
                                }
                            } else {
                                if (currentOrder.getNext() == null) {
                                    System.out.println("Заказ не найден");
                                    break;
                                } else {
                                    currentOrder = currentOrder.getNext();
                                    counterOrder++;
                                }
                            }
                        }
                        break;
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Incorrect input");
                }
            }
        }

    }
    //private static int numOfOrder = 0;
    private static void printCurrentOrder(CargoLink currentOrder) {
        System.out.println();
    }
    private static void listOfOrders() {
        int numOfOrder;
        if(firstOrder==null) {
            System.out.println("Список заказов пуст");
        } else {
            currentOrder = firstOrder;
            numOfOrder = 1;
            while (currentOrder != null) {
                System.out.println("Заказ № " + numOfOrder);
                currentOrder.getCurrent().printOrder();
                //printCurrentOrder(currentOrder);
                //System.out.println(currentOrder);
                //currentOrder.printOrder();
                currentOrder = currentOrder.getNext();
                numOfOrder++;
                System.out.println();
            };
            //System.out.println("Назад: 0");
        }
    }
    private static void delivery() {
        //String selectString;
        //scan = new Scanner(System.in);
        //int select;
        boolean backTo = false;
        while (true) {
            System.out.println("Сделать заказ: 1");
            System.out.println("Изменить заказ: 2");
            System.out.println("Отменить заказ: 3");
            System.out.println("Посмотреть список заказов: 4");
            System.out.println("Назад: 0");
            selectString = scan.nextLine();
            try {
                select = Integer.parseInt(selectString);
                switch (select) {
                    case 1 -> newOrder();
                    case 2 -> updateOrder();
                    case 3 -> cancelOrder();
                    case 4 -> listOfOrders();
                    case 0 ->  backTo = true;//break;
                    default -> System.out.println("Select not Exist (order)");
                }
                if (backTo) { break; }// else { continue; }
            /*            switch()
            if(select==1) {
                newOrder();
            } else if (select==2) {
                updateOrder();
            } else if (select==3) {
                cancelOrder();
            } else if (select==4) {
                listOfOrders();
            }else if (select==0) break;
            else System.out.println("Select not Exist (order)");*/
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input (order)");
            }
        }
    }
    public static void main(String[] args) {
        //String selectString;
        //scan = new Scanner(System.in);
        //int select;
        while(true) {
            System.out.println("Ехать на лифте: 1");
            System.out.println("Заказать доставку: 2");
            System.out.println("Выход: 0");
            selectString = scan.nextLine();
            try {
                select = Integer.parseInt(selectString);
                if(select==1) {
                    elevator();
                } else if (select==2) {
                    delivery();
                } else if (select==0) break;
                  else System.out.println("Select not Exist");
            } catch (NumberFormatException error) {
                System.out.println("Incorrect input");
            }
        }
    }
}
