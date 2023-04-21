package exercise2;

import java.util.Scanner;

/**
 * <h1> Строка наоборот</h1>
 * Программа, в которой все переданные во входную строку аргументы выводятся
 * на экран в обратной порядке.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class ReverseString {
    /**
     * Этот метод меняет символы строки в обратном порядке.
     * @param str - строковый параметр метода reverseString.
     * @return возвращает строку, символы которой расположены
     * в обратном порядке.
     * @see java.lang.String
     */
    public static String reverseString(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            result.insert(0, str.charAt(i));
        }
        return result.toString();
    }

    /**
     * Это основной метод, использующий метод {@code reverseString}.
     * @param args не используется.
     */
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = scanner.nextLine();
        System.out.print("Перевернутая строка: " + reverseString(string));
    }
}