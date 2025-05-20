public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        char[][] alphabet = { {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'},
        {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'} };
        int x_array = alphabet.length;
        int y_array = alphabet[0].length;
        int i, j, k;
        //System.out.println(x_array);
        //System.out.println(y_array);
        System.out.println ("№  символ  код");
        int l = 1;
        for (k = 0; k < 0x10000; k++) {
            for (i = 0; i < x_array; i++) {
                for (j = 0; j < y_array; j++ ) {
                    if (alphabet[i][j] == (char) k) {
                        System.out.print(l + " - ");
                        System.out.print(alphabet[i][j] + " - ");
                        System.out.println(k);
                        l++;
                    }
                }
            }
        }

    }
}
