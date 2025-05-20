public class Computer {
    private Processor cpu;
    private Memory ram;
    private Driver drive;
    private Display screen;
    private Keyboard kbd;
    private final String vendor;
    private final String name;
    private Computer next = null;
    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }
    public void setCpu(Processor cpu) {
        this.cpu = cpu;
    }
    public void setRam(Memory ram) {
        this.ram = ram;
    }
    public void setDrive(Driver drive) {
        this.drive = drive;
    }
    public void setScreen(Display screen) {
        this.screen = screen;
    }
    public void setKbd(Keyboard kbd) {
        this.kbd = kbd;
    }
    public void setNext(Computer next) {
        this.next = next;
    }
    public Processor getCpu() { return cpu; }
    public Memory getRam() {
        return ram;
    }
    public Driver getDrive() {
        return drive;
    }
    public Display getScreen() {
        return screen;
    }
    public Keyboard getKbd() { return kbd; }
    public Computer getNext() { return next; };
    public int getCommonWeight() {
        //int weight = 0;
        return cpu.getWeight() + ram.getWeight() +
               drive.getWeight() + screen.getWeight() + kbd.getWeight();

        //return weight;
    }
    public String getVendor() {
        return vendor;
    }
    public String getName() {
        return name;
    }
    public String toString() {

        String frec = "";
        switch (cpu.getFrequency()) {
            case ONE_THOUSAND_MEGAHERTZ ->                 frec = "1000 MHz";
            case ONE_THOUSAND_ONE_HUNDRED_MEGAHERTZ ->     frec = "1100 MHz";
            case ONE_THOUSAND_TWO_HUNDRED_MEGAHERTZ ->     frec = "1200 MHz";
            case ONE_THOUSAND_THREE_HUNDRED_MEGAHERTZ ->   frec = "1300 MHz";
            case ONE_THOUSAND_FOUR_HUNDRED_MEGAHERTZ ->    frec = "1400 MHz";
            case ONE_THOUSAND_FIVE_HUNDRED_MEGAHERTZ ->    frec = "1500 MHz";
            case ONE_THOUSAND_SIX_HUNDRED_MEGAHERTZ ->     frec = "1600 MHz";
            case ONE_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ ->   frec = "1700 MHz";
            case ONE_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ ->   frec = "1800 MHz";
            case ONE_THOUSAND_NINE_HUNDRED_MEGAHERTZ ->    frec = "1900 MHz";
            case TWO_THOUSAND_MEGAHERTZ ->                 frec = "2000 MHz";
            case TWO_THOUSAND_ONE_HUNDRED_MEGAHERTZ ->     frec = "2100 MHz";
            case TWO_THOUSAND_TWO_HUNDRED_MEGAHERTZ ->     frec = "2200 MHz";
            case TWO_THOUSAND_THREE_HUNDRED_MEGAHERTZ ->   frec = "2300 MHz";
            case TWO_THOUSAND_FOUR_HUNDRED_MEGAHERTZ ->    frec = "2400 MHz";
            case TWO_THOUSAND_FIVE_HUNDRED_MEGAHERTZ ->    frec = "2500 MHz";
            case TWO_THOUSAND_SIX_HUNDRED_MEGAHERTZ ->     frec = "2600 MHz";
            case TWO_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ ->   frec = "2700 MHz";
            case TWO_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ ->   frec = "2800 MHz";
            case TWO_THOUSAND_NINE_HUNDRED_MEGAHERTZ ->    frec = "2900 MHz";
            case THREE_THOUSAND_MEGAHERTZ ->               frec = "3000 MHz";
            case THREE_THOUSAND_ONE_HUNDRED_MEGAHERTZ ->   frec = "3100 MHz";
            case THREE_THOUSAND_TWO_HUNDRED_MEGAHERTZ ->   frec = "3200 MHz";
            case THREE_THOUSAND_THREE_HUNDRED_MEGAHERTZ -> frec = "3300 MHz";
            case THREE_THOUSAND_FOUR_HUNDRED_MEGAHERTZ ->  frec = "3400 MHz";
            case THREE_THOUSAND_FIVE_HUNDRED_MEGAHERTZ ->  frec = "3500 MHz";
            case THREE_THOUSAND_SIX_HUNDRED_MEGAHERTZ ->   frec = "3600 MHz";
            case THREE_THOUSAND_SEVEN_HUNDRED_MEGAHERTZ -> frec = "3700 MHz";
            case THREE_THOUSAND_EIGHT_HUNDRED_MEGAHERTZ -> frec = "3800 MHz";
            case THREE_THOUSAND_NINE_HUNDRED_MEGAHERTZ ->  frec = "3900 MHz";
            case FOUR_THOUSAND_MEGAHERTZ ->                frec = "4000 MHz";
        }
        String corecount = "";
        switch (cpu.getCorenumber()) {
            case ONE ->     corecount = "1 ядро";
            case TWO ->     corecount = "2 ядра";
            case THREE ->   corecount = "3 ядра";
            case FOUR ->    corecount = "4 ядра";
            case SIX ->     corecount = "6 ядер";
            case EIGHT ->   corecount = "8 ядер";
            case TEN ->     corecount = "10 ядер";
            case TWELVE ->  corecount = "12 ядер";
            case SIXTEEN -> corecount = "16 ядер";
        }
        String cpuVendor = "";
        switch (cpu.getVendor()) {
            case AMD ->   cpuVendor = "AMD  ";
            case IBM ->   cpuVendor = "IBM  ";
            case INTEL -> cpuVendor = "Intel";
            case CYRIX -> cpuVendor = "Cyrix";
            case VIA ->   cpuVendor = "Via  ";
        }
        String memType = "";
        switch (ram.getType()) {
            case DDR ->  memType = "DDR ";
            case DDR2 -> memType = "DDR2";
            case DDR3 -> memType = "DDR3";
            case DDR4 -> memType = "DDR4";
            case DDR5 -> memType = "DDR5";
        }
        String memVol = "";
        switch (ram.getVolume()) {
            case ONE_GIGABYTE ->     memVol = "1 Gb ";
            case TWO_GIGABYTE ->     memVol = "2 Gb ";
            case FOUR_GIGABYTE ->    memVol = "4 Gb ";
            case EIGHT_GIGABYTE ->   memVol = "8 Gb ";
            case SIXTEEN_GIGABYTE -> memVol = "16 Gb";
        }
        String drvTp = "";
        switch (drive.getType()) {
            case SSD -> drvTp = "SSD";
            case HDD -> drvTp = "HDD";
        }
        String drvVol = "";
        switch (drive.getVolume()) {
            case VOL_64GB ->   drvVol = "64 Gb ";
            case VOL_128GB ->  drvVol = "128 Gb";
            case VOL_256GB ->  drvVol = "256 Gb";
            case VOL_512GB ->  drvVol = "512 Gb";
            case VOL_1024GB -> drvVol = "1 Tb  ";
            case VOL_2048GB -> drvVol = "2 Tb  ";
        }
        String disDiag = "";
        switch (screen.getDiagonal()) {
            case D15INCH -> disDiag = "15 дюймов";
            case D17INCH -> disDiag = "17 дюймов";
            case D19INCH -> disDiag = "19 дюймов";
            case D21INCH -> disDiag = "21 дюйм  ";
            case D24INCH -> disDiag = "24 дюйма ";
            case D27INCH -> disDiag = "27 дюймов";
            case D29INCH -> disDiag = "29 дюймов";
            case D32INCH -> disDiag = "32 дюйма ";
            case D34INCH -> disDiag = "34 дюйма ";
        }
        String disTp = "";
        switch (screen.getType()) {
            case IPS -> disTp = "IPS";
            case TN ->  disTp = "TN ";
            case VA ->  disTp = "VA ";
        }
        String kbdTp = "";
        switch (kbd.getType()) {
            case WIRED ->    kbdTp = "проводная   ";
            case WIRELESS -> kbdTp = "беспроводная";
        }
        String kbdLt = "";
        switch (kbd.getLight()) {
            case YES -> kbdLt = "есть";
            case NO ->  kbdLt = "нет ";
        }
        String computer = "";
        computer += '\t'     + "производитель:    " + vendor + System.lineSeparator();
        computer += '\t'     + "название:         " + name + System.lineSeparator();
        computer += "\t\t"   + "процессор"          + System.lineSeparator();
        computer += "\t\t\t" + "производитель:    " + cpuVendor + System.lineSeparator();
        computer += "\t\t\t" + "частота:          " + frec + System.lineSeparator();
        computer += "\t\t\t" + "число ядер:       " + corecount + System.lineSeparator();
        computer += "\t\t\t" + "масса чипа:       " + cpu.getWeight() + " гр." + System.lineSeparator();
        computer += "\t\t"   + "память"             + System.lineSeparator();
        computer += "\t\t\t" + "тип модуля:       " + memType + System.lineSeparator();
        computer += "\t\t\t" + "ёмкость модуля:   " + memVol + System.lineSeparator();
        computer += "\t\t\t" + "масса модуля:     " + ram.getWeight() + " гр." + System.lineSeparator();
        computer += "\t\t"   + "накопитель"         + System.lineSeparator();
        computer += "\t\t\t" + "тип накопителя:   " + drvTp + System.lineSeparator();
        computer += "\t\t\t" + "объём накопителя: " + drvVol + System.lineSeparator();
        computer += "\t\t\t" + "масса накопителя: " + drive.getWeight() + " гр." + System.lineSeparator();
        computer += "\t\t"   + "монитор"            + System.lineSeparator();
        computer += "\t\t\t" + "тип монитора:     " + disTp + System.lineSeparator();
        computer += "\t\t\t" + "диагональ экрана: " + disDiag + System.lineSeparator();
        computer += "\t\t\t" + "масса монитора:   " + screen.getWeight() + " кг" + System.lineSeparator();
        computer += "\t\t"   + "клавиатура"         + System.lineSeparator();
        computer += "\t\t\t" + "тип клавиатуры:   " + kbdTp + System.lineSeparator();
        computer += "\t\t\t" + "подсветка:        " + kbdLt + System.lineSeparator();
        computer += "\t\t\t" + "масса клавиатуры: " + kbd.getWeight() + " гр." + System.lineSeparator();
        return computer;
    }
}