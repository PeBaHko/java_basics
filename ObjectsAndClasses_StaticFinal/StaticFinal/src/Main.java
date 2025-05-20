import java.lang.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Computer firstPC = null;
        Computer lastPC = null;
        Computer currentPC;
        Scanner scan = new Scanner(System.in);
        String variantString;
        int number;
        while (true) {
            System.out.println("Главное меню" + System.lineSeparator() +
                               "   1 - собрать новый компьютер" + System.lineSeparator() +
                               "   2 - посмотреть список готовых конфигураций" + System.lineSeparator() +
                               "   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) break;
                switch (number) {
                    case 1 -> {
                        lastPC = newConfig(lastPC);
                        if(firstPC == null) { firstPC = lastPC; }

                    }
                    case 2 ->  listConfig(firstPC);
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
            }
        }
    }
    private static Computer newConfig(Computer lastPC) {
        Computer nextConfig = null;
        Computer currentConfig = null;
        Scanner scan = new Scanner(System.in);
        String variantString;
        int number;
        boolean configIsCreated = false;

        //производитель сборки
        String vendor = "";
        while (true) {
            System.out.println("Новая сборка:");
            System.out.println("Выберите производителя компьютера:");
            System.out.println("   1 - DELL");
            System.out.println("   2 - HP");
            System.out.println("   3 - LENOVO");
            System.out.println("   3 - MSI");
            System.out.println("   4 - TOSHIBA");
            System.out.println("   5 - HIPERPC");
            System.out.println("   6 - INTEL");
            System.out.println("   7 - IBM");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { vendor = "Dell      "; }
                    case 2 -> { vendor = "HP        "; }
                    case 3 -> { vendor = "Lenovo    "; }
                    case 4 -> { vendor = "MSI       "; }
                    case 5 -> { vendor = "Toshiba   "; }
                    case 6 -> { vendor = "Intel     "; }
                    case 7 -> { vendor = "IBM       "; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }// else { continue; }
        }

        //название сборки
        String name = "";
        while (true) {
            configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Выберите название компьютера:");
            System.out.println("   1 - домашний ПК");
            System.out.println("   2 - учебный ПК");
            System.out.println("   3 - офисный ПК");
            System.out.println("   4 - игровой ПК");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { name = "домашний ПК"; }
                    case 2 -> { name = "учебный ПК "; }
                    case 3 -> { name = "офисный ПК "; }
                    case 4 -> { name = "игровой ПК "; }
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        //производитель процессора
        CpuVendor cpuVendor = null;
        while (true) {
            configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Выберите производителя процессора:");
            System.out.println("   1 - Intel");
            System.out.println("   2 - AMD");
            System.out.println("   3 - IBM");
            System.out.println("   4 - VIA");
            System.out.println("   5 - Cyrix");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { cpuVendor = CpuVendor.INTEL; }
                    case 2 -> { cpuVendor = CpuVendor.AMD; }
                    case 3 -> { cpuVendor = CpuVendor.IBM; }
                    case 4 -> { cpuVendor = CpuVendor.VIA; }
                    case 5 -> { cpuVendor = CpuVendor.CYRIX; }
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        //частота процессора
        CpuFixClk frequency = null;
        while (true) {
            configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Введите частоту процесора в мегагерцах диапазоне от 1 ГГц до 4 ГГц с шагом в 100 МГц:");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                else if ((number<1000)||(number>4000)) {
                    System.out.println("Частота вне диапазона");
                } else if ((number % 100)!=0) {
                    System.out.println("Частота не кратна ста МГц");
                } else {
                    configIsCreated = true;
                    switch (number/100) {
                        case 10 -> { frequency = CpuFixClk.ONE_THOUSAND_MEGAHERTZ; }
                        case 11 -> { frequency = CpuFixClk.ONE_THOUSAND_ONE_HUNDRED_MEGAHERTZ; }
                        case 12 -> { frequency = CpuFixClk.ONE_THOUSAND_TWO_HUNDRED_MEGAHERTZ; }
                        case 13 -> { frequency = CpuFixClk.ONE_THOUSAND_THREE_HUNDRED_MEGAHERTZ; }
                        case 14 -> { frequency = CpuFixClk.ONE_THOUSAND_FOUR_HUNDRED_MEGAHERTZ; }
                        case 15 -> { frequency = CpuFixClk.ONE_THOUSAND_FIVE_HUNDRED_MEGAHERTZ; }
                        case 16 -> { frequency = CpuFixClk.ONE_THOUSAND_SIX_HUNDRED_MEGAHERTZ; }
                        case 17 -> { frequency = CpuFixClk.ONE_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ; }
                        case 18 -> { frequency = CpuFixClk.ONE_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ; }
                        case 19 -> { frequency = CpuFixClk.ONE_THOUSAND_NINE_HUNDRED_MEGAHERTZ; }
                        case 20 -> { frequency = CpuFixClk.TWO_THOUSAND_MEGAHERTZ; }
                        case 21 -> { frequency = CpuFixClk.TWO_THOUSAND_ONE_HUNDRED_MEGAHERTZ; }
                        case 22 -> { frequency = CpuFixClk.TWO_THOUSAND_TWO_HUNDRED_MEGAHERTZ; }
                        case 23 -> { frequency = CpuFixClk.TWO_THOUSAND_THREE_HUNDRED_MEGAHERTZ; }
                        case 24 -> { frequency = CpuFixClk.TWO_THOUSAND_FOUR_HUNDRED_MEGAHERTZ; }
                        case 25 -> { frequency = CpuFixClk.TWO_THOUSAND_FIVE_HUNDRED_MEGAHERTZ; }
                        case 26 -> { frequency = CpuFixClk.TWO_THOUSAND_SIX_HUNDRED_MEGAHERTZ; }
                        case 27 -> { frequency = CpuFixClk.TWO_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ; }
                        case 28 -> { frequency = CpuFixClk.TWO_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ; }
                        case 29 -> { frequency = CpuFixClk.TWO_THOUSAND_NINE_HUNDRED_MEGAHERTZ; }
                        case 30 -> { frequency = CpuFixClk.THREE_THOUSAND_MEGAHERTZ; }
                        case 31 -> { frequency = CpuFixClk.THREE_THOUSAND_ONE_HUNDRED_MEGAHERTZ; }
                        case 32 -> { frequency = CpuFixClk.THREE_THOUSAND_TWO_HUNDRED_MEGAHERTZ; }
                        case 33 -> { frequency = CpuFixClk.THREE_THOUSAND_THREE_HUNDRED_MEGAHERTZ; }
                        case 34 -> { frequency = CpuFixClk.THREE_THOUSAND_FOUR_HUNDRED_MEGAHERTZ; }
                        case 35 -> { frequency = CpuFixClk.THREE_THOUSAND_FIVE_HUNDRED_MEGAHERTZ; }
                        case 36 -> { frequency = CpuFixClk.THREE_THOUSAND_SIX_HUNDRED_MEGAHERTZ; }
                        case 37 -> { frequency = CpuFixClk.THREE_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ; }
                        case 38 -> { frequency = CpuFixClk.THREE_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ; }
                        case 39 -> { frequency = CpuFixClk.THREE_THOUSAND_NINE_HUNDRED_MEGAHERTZ; }
                        case 40 -> { frequency = CpuFixClk.FOUR_THOUSAND_MEGAHERTZ; }
                        default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                    }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        //число ядер процессора
        CpuCoreCount corenumber = null;
        while (true) {
            configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Выберите количество ядер:");
            System.out.println("   1; 2; 3; 4; 6; 8; 10; 12; 16. ");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { corenumber = CpuCoreCount.ONE; }
                    case 2 -> { corenumber = CpuCoreCount.TWO; }
                    case 3 -> { corenumber = CpuCoreCount.THREE; }
                    case 4 -> { corenumber = CpuCoreCount.FOUR; }
                    case 6 -> { corenumber = CpuCoreCount.SIX; }
                    case 8 -> { corenumber = CpuCoreCount.EIGHT; }
                    case 10 -> { corenumber = CpuCoreCount.TEN; }
                    case 12 -> { corenumber = CpuCoreCount.TWELVE; }
                    case 16 -> { corenumber = CpuCoreCount.SIXTEEN; }
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // масса процессора
        int weightCpu = 0;
        while (true) {
            //configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Введите массу процессора в диапазоне от 1 до 99 граммов");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                else if (number < 0) {
                    configIsCreated = false;
                    System.out.println("Масса не может быть отрицательной!!!");
                    System.out.println();
                } else if (number > 99) {
                    configIsCreated = false;
                    System.out.println("Введённое число превышает максимально допустимое значение");
                    System.out.println();
                } else {
                    configIsCreated = true;
                    weightCpu = number;
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        //  тип памяти
        MemType memType = null;
        while (true) {
            System.out.println("Новая сборка:");
            System.out.println("Выберите тип используемой памяти:");
            System.out.println("   1 - DDR");
            System.out.println("   2 - DDR2");
            System.out.println("   3 - DDR3");
            System.out.println("   4 - DDR4");
            System.out.println("   5 - DDR5");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { memType = MemType.DDR; }
                    case 2 -> { memType = MemType.DDR2; }
                    case 3 -> { memType = MemType.DDR3; }
                    case 4 -> { memType = MemType.DDR4; }
                    case 5 -> { memType = MemType.DDR5; }
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // объём памяти
        MemVolume memVolume = null;
        while (true) {
            System.out.println("Новая сборка:");
            System.out.println("Выберите объём модуля памяти:");
            System.out.println("   1 - 1 Гигабайт");
            System.out.println("   2 - 2 Гигабайта");
            System.out.println("   3 - 4 Гигабайта");
            System.out.println("   4 - 8 Гигабайт");
            System.out.println("   5 - 16 Гигабайт");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { memVolume = MemVolume.ONE_GIGABYTE; }
                    case 2 -> { memVolume = MemVolume.TWO_GIGABYTE; }
                    case 3 -> { memVolume = MemVolume.FOUR_GIGABYTE; }
                    case 4 -> { memVolume = MemVolume.EIGHT_GIGABYTE; }
                    case 5 -> { memVolume = MemVolume.SIXTEEN_GIGABYTE; }
                    default -> { System.out.println("Неизвестный вариант"); System.out.println(); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // вес модуля
        int weightMem = 0;
        while (true) {
            //configIsCreated = false;
            System.out.println("Новая сборка:");
            System.out.println("Введите массу модуля памяти в диапазоне от 1 до 99 граммов");
            System.out.println("   0 - выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                else if (number < 0) {
                    configIsCreated = false;
                    System.out.println("Масса не может быть отрицательной!!!");
                    System.out.println();
                } else if (number > 99) {
                    configIsCreated = false;
                    System.out.println("Введённое число превышает максимально допустимое значение");
                    System.out.println();
                } else {
                    configIsCreated = true;
                    weightMem = number;
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда");
                System.out.println();
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // тип накопителя
        DriveType drvTp = null;
        while(true) {
            System.out.println("Новая сборка:");
            System.out.println("Выберите тип накопителя:");
            System.out.println("   1 - HDD");
            System.out.println("   2 - SSD");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch(number) {
                    case 1 -> { drvTp = DriveType.HDD; }
                    case 2 -> { drvTp = DriveType.SSD; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // объём накопителя
        DriveVolume drvVol = null;
        while (true) {
            System.out.println("Новая сборка: дисковый накопитель");
            System.out.println("Выберите объём накопителя");
            System.out.println("   1 - 64 Гб");
            System.out.println("   2 - 128 Гб");
            System.out.println("   3 - 256 Гб");
            System.out.println("   4 - 512 Гб");
            System.out.println("   5 - 1 Тб");
            System.out.println("   6 - 2 Тб");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { drvVol = DriveVolume.VOL_64GB; }
                    case 2 -> { drvVol = DriveVolume.VOL_128GB; }
                    case 3 -> { drvVol = DriveVolume.VOL_256GB; }
                    case 4 -> { drvVol = DriveVolume.VOL_512GB; }
                    case 5 -> { drvVol = DriveVolume.VOL_1024GB; }
                    case 6 -> { drvVol = DriveVolume.VOL_2048GB; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // вес накопителя
        int weightDrv = 0;
        while (true) {
            System.out.println("Новая сборка:");
            System.out.println("Введите массу модуля памяти в диапазоне от 10 до 999 граммов");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                else if (number < 0) {
                    configIsCreated = false;
                    System.out.println("Масса не может быть отрицательной!!!" +
                            System.lineSeparator());
                } else if (number > 999) {
                    configIsCreated = false;
                    System.out.println("Введённое число превышает максимально допустимое значение" +
                            System.lineSeparator());
                } else if (number < 10) {
                    configIsCreated = false;
                    System.out.println("Введённое число ниже минимально допустимоге значения" +
                            System.lineSeparator());
                } else {
                    configIsCreated = true;
                    weightDrv = number;
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // диагональ дисплея
        DisDiag disDiag = null;
        while (true) {
            System.out.println("Новая сборка: Дисплей");
            System.out.println("Выберите диагональ экрана дисплея");
            System.out.println("   1 - 15 дюймов");
            System.out.println("   2 - 17 дюймов");
            System.out.println("   3 - 19 дюймов");
            System.out.println("   4 - 21 дюйм");
            System.out.println("   5 - 24 дюйма");
            System.out.println("   6 - 27 дюймов");
            System.out.println("   7 - 29 дюймов");
            System.out.println("   8 - 32 дюйма");
            System.out.println("   9 - 34 дюйма");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { disDiag = DisDiag.D15INCH; }
                    case 2 -> { disDiag = DisDiag.D17INCH; }
                    case 3 -> { disDiag = DisDiag.D19INCH; }
                    case 4 -> { disDiag = DisDiag.D21INCH; }
                    case 5 -> { disDiag = DisDiag.D24INCH; }
                    case 6 -> { disDiag = DisDiag.D27INCH; }
                    case 7 -> { disDiag = DisDiag.D29INCH; }
                    case 8 -> { disDiag = DisDiag.D32INCH; }
                    case 9 -> { disDiag = DisDiag.D34INCH; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // тип матрицы
        DisplayType disType = null;
        while (true) {
            System.out.println("Новая сборка: Дисплей");
            System.out.println("Выберите тип матрицы дисплея");
            System.out.println("   1 - IPS");
            System.out.println("   2 - TN");
            System.out.println("   3 - VA");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { disType = DisplayType.IPS; }
                    case 2 -> { disType = DisplayType.TN; }
                    case 3 -> { disType = DisplayType.VA; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // вес монитора
        int weightMon = 0;
        while (true) {
            System.out.println("Новая сборка: Дисплей");
            System.out.println("Введите массу дисплея к кг в диапазоне от 1 до 9 кг");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null;
                } else if (number < 0) {
                    configIsCreated = false;
                    System.out.println("Масса не может быть отрицательной!!!" +
                            System.lineSeparator());
                } else if (( number < 1 ) || ( number > 9 )) {
                    configIsCreated = false;
                    System.out.println("Введённое значение за пределам допустимого диапазона" +
                            System.lineSeparator());
                } else {
                    weightMon = number;
                    configIsCreated = true;
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // тип клавиатуры
        KbdType keyTp = null;
        while (true) {
            System.out.println("Новая сборка: Клавиатура");
            System.out.println("Выберите тип клавиатуры");
            System.out.println("   1 - проводная");
            System.out.println("   2 - беспроводная");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { keyTp = KbdType.WIRED; }
                    case 2 -> { keyTp = KbdType.WIRELESS; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        // наличие подстветки
        KbdLight keyLt = null;
        while (true) {
            System.out.println("Новая сборка: Клавиатура");
            System.out.println("Выберите наличие подсветки клавиатуры");
            System.out.println("   1 - есть");
            System.out.println("   2 - нет");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                configIsCreated = true;
                switch (number) {
                    case 1 -> { keyLt = KbdLight.YES; }
                    case 2 -> { keyLt = KbdLight.NO; }
                    default -> { System.out.println("Неизвестный вариант" + System.lineSeparator()); configIsCreated = false; }
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }
        // вес клавиатуры
        int weightKbd = 0;
        while (true) {
            System.out.println("Новая сборка: Клавиатура");
            System.out.println("Введите массу клавиатуры в диапазоне от 100 до 1000 грамм");
            System.out.println("   0 - Выход");
            variantString = scan.nextLine();
            try {
                number = Integer.parseInt(variantString);
                if (number == 0) { return null; }
                else if (number < 0) {
                    configIsCreated = false;
                    System.out.println("Масса не может быть отрицательной!!!" +
                            System.lineSeparator());
                } else if (number > 1000) {
                    configIsCreated = false;
                    System.out.println("Введённое число превышает предельно допустимое значение" +
                            System.lineSeparator());
                } else if (number < 100) {
                    configIsCreated = false;
                    System.out.println("Введённое число меньше минимально допустимго значения" +
                            System.lineSeparator());
                } else {
                    weightKbd = number;
                    configIsCreated = true;
                }
            } catch (NumberFormatException error) {
                System.out.println("Некорректная команда" + System.lineSeparator());
                configIsCreated = false;
            }
            if (configIsCreated) { break; }
        }

        //Processor cpu = null;
        //Memory ram = null;
        //Driver drv = null;
        //Display screen = null;
        //Keyboard kbd = null;


        if (configIsCreated) {
            currentConfig = new Computer(vendor, name);
            if (lastPC != null) {
                lastPC.setNext(currentConfig);
            }
            //currentConfig.setNext(lastPC);
            currentConfig.setCpu(new Processor(frequency, corenumber, cpuVendor, weightCpu));
            currentConfig.setRam(new Memory(memType, memVolume, weightMem));
            currentConfig.setDrive(new Driver(drvTp, drvVol, weightDrv));
            currentConfig.setScreen(new Display(disDiag, disType, weightMon));
            currentConfig.setKbd(new Keyboard(keyTp, keyLt, weightKbd));
            //lastPC.setNext(currentConfig);
            return currentConfig;
        } else { currentConfig = null; return null;
        }

    }
    private static void listConfig(Computer config) {
        int configCount = 1;
        if (config == null) {
            System.out.println("Список пуст" + System.lineSeparator());
        } else {
            System.out.println("Список готовых конфигураций");
            while (config != null) {
                System.out.println("ПК № " + configCount);
                System.out.println(config.toString());
                configCount++;
                config = config.getNext();
            }
        }
    }
}