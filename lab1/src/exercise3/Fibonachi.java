package exercise3;

import java.util.Arrays;

/**
 * <h1> Числа Фибоначчи</h1>
 * Программа, вычисляющая числа Фибоначчи.
 * <p>
 * Числа Фибоначчи – последовательность чисел,
 * в которой каждое следующее число равно сумме двух предыдущих.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Fibonachi {
    /**
     * Этот метод создает последовательность чисел Фибоначчи.
     * @return возвращает массив чисел,
     * состоящий из последовательности Фибоначчи.
     */
    public int[] getFibonachi() {
        int[] array = new int[10];
        array[0] = 1;
        array[1] = 1;

        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 2] + array[i - 1];
        }
        return array;
    }

    /**
     * Это основной метод, использующий метод {@code getFibonachi}.
     * @param args не используется.
     */
    public static void main(String [] args) {
        Fibonachi obj = new Fibonachi();
        int[] numbersFibonachi = obj.getFibonachi();

        System.out.println("Последовательность Фибоначчи: "
                + Arrays.toString(numbersFibonachi));
    }
}