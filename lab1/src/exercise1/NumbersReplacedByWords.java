package exercise1;

/**
 * <h1> Замена чисел на слова</h1>
 * Программа, в которой перебираются числа от 1 до 500 и выводятся на экран.
 * Если число делится на 5, то вместо него выводится слово fizz, если на 7,
 * то buzz. Если число делится на 5 и на 7, то выводить слово fizzbuzz.
 * @author Ilya Polshchikov
 * @since 1.0
 */

public class NumbersReplacedByWords {
    /**
     * Этот метод используется для сортировки и дальнейшей замены: числа,
     * которые целочисленно делятся на определенное значение,
     * заменяются на слова.
     * @param number - переменная числа.
     * @return возвращает определенное значение типа String.
     * @see java.lang.String
     */
    public static String replacingNumbersWithWords(int number) {
        if ((number % 5) == 0) {
            if ((number % 7) == 0) {
                return "fizzbuzz";
            } else {
                return "fizz";
            }
        } else if (number % 7 == 0) {
            return "buzz";
        } else {
            return String.valueOf(number);
        }
    }

    /**
     * Это основной метод, использующий метод
     * {@code replacingNumbersWithWords}.
     * @param args не используется.
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 500; i++) {
            System.out.println(replacingNumbersWithWords(i));
        }
    }
}

