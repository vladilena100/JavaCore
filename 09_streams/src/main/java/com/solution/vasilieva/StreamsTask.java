package com.solution.vasilieva;

import com.nixsolutions.ppp.streamoptional.StringTransformUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс реализующий методы интерфейса StringTransformUtils
 * демонстрирует использование Stream API
 *
 * @author Vasilieva Vladilena
 * @since 06.06.21
 */

public class StreamsTask implements StringTransformUtils {
    /**
     * Функция ищет уникальные строки из {@code strings},
     * капитализирует их(превращает все буквы в большие)
     * и возвращает в виде списка
     * Пример:
     * strings = ["foo", "bar", "foo", "baz"];
     * Вернет:
     * ["FOO", "BAR", "BAZ"]
     *
     * @param strings массив строк
     * @return Список строк
     */
    @Override
    public List<String> findDistinctToUpperCase(String[] strings) {

        return Arrays.stream(strings)
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Функция считает сколько отрицательных чисел в списке чисел {@code integers}.
     *
     * @param integers список Integer чисел
     * @return количество отрицательных чисел в списке
     */
    @Override
    public long countNegativeIntegers(List<Integer> integers) {
        return integers.stream()
                .filter(s -> s < 0)
                .count();
    }

    /**
     * Функция считает сколько раз в списке слов(строк) {@code words} встречается определенное слово(строка) {@code wordToCount}.
     *
     * @param words       список строк
     * @param wordToCount строка, количество повторений которой нужно найти
     * @return количество {@code wordToCount} в списке
     */
    @Override
    public long countWordsInList(List<String> words, String wordToCount) {

        return words.stream()
                .filter(s -> s.contains(wordToCount))
                .count();
    }

    /**
     * Функция превращает список массивов строк {@code stringsArrays} в список всех уникальных строк.
     * Пример:
     * stringsArrays = [ ["foo", "bar", "baz"], ["foo", "bar", "fuz"] ]
     * Вернет:
     * ["foo", "bar", "baz", "fuz"]
     *
     * @param stringsArrays список массивов строк
     * @return Список строк
     */
    @Override
    public List<String> toDistinctList(List<String[]> stringsArrays) {

        return stringsArrays.stream()
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Функция проверяет, ялвяется ли длинна всех строк в списке {@code strings} больше
     * чем заданный параметр {@code numberOfCharacters}
     * Пример:
     * strings = ["test", "tonystark", "diobrando"],
     * numberOfCharacters = 3, функция вернет true
     * но если numberOfCharacters = 4, функция вернет false
     *
     * @param strings            список строк
     * @param numberOfCharacters количество символов
     * @return boolean значение
     * @throws IllegalArgumentException если {@code numberOfCharacters} - отрицательное число
     */
    @Override
    public boolean isAllStringsLongerThen(List<String> strings, long numberOfCharacters) {

        if (numberOfCharacters < 0) {
            throw new IllegalArgumentException("число отрицательное" + numberOfCharacters);
        }
        return strings.stream()
                .allMatch((s) -> s.length() > numberOfCharacters);

    }

    /**
     * Функция принимает массив целых чисел {@code numbers} и возвращает 5 самых больших чисел
     * в порядке возростания.
     * Пример:
     * numbers = [1, 2, 3, 4, 9, 8, 7, 6, 5]
     * Вернет [5, 6, 7, 8, 9]
     *
     * @param numbers массив целых чисел
     * @return список самых больших чисел
     * @throws IllegalArgumentException если длинна {@code numbers} меньше чем 5
     */
    @Override
    public List<Integer> getMaxFiveNumbers(int[] numbers) {

        if (numbers.length < 5) {
            throw new IllegalArgumentException("количество элементов меньше 5");
        }
        return Arrays.stream(numbers)
                .sorted()
                .skip(numbers.length - 5)
                .boxed()
                .collect(Collectors.toList());

    }

    /**
     * Функция принимает массив целых чисел {@code numbers} и возвращает их в виде одной строки,
     * где каждое число разделено запятой. Если массив пустой - вернет пустую строку.
     * Пример:
     * numbers = [1, 53, 63, 23]
     * вернет строку "1,53,63,23"
     *
     * @param numbers массив целых чисел
     * @return строка со всеми числами массива, разделенными запятой
     */
    @Override
    public String getStringOfNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .map(s -> String.valueOf(s))
                .collect(Collectors.joining(","));
    }

    /**
     * Функция принимает список строк {@code strings} и возвращает строку с первыми символами этих строк.
     * Если список пустой - вернуть пустую строку.
     * Функция должна использовать {@code Stream.reduce(..)}
     * Пример:
     * strings = ["serial", "number", "of", "turbo"]
     * вернет строку "snot"
     *
     * @param strings список строк
     * @return строку с первыми символами каждой строки из {@code strings}
     */
    @Override
    public String getFirstCharactersAsString(List<String> strings) {
        return strings.stream()
                .map(s -> Character.toString(s.charAt(0)))
                .collect(Collectors.joining());
    }

    /**
     * Функция принимает список строк {@code strings} и возвращает {@code Map}, в котором
     * ключ это длинна строки, а значение - список строк с такой длинной строки.
     * Пример:
     * strings = ["Irene", "Wendy", "Seulgi", "Joy", "Yeri", "Red", "Velvet"]
     * вернет Map = {3=[Joy, Red], 4=[Yeri], 5=[Irene, Wendy], 6=[Seulgi, Velvet]}
     *
     * @param strings список строк
     * @return {@code Map}, в котором ключ это длинна строки, а значение - список строк с такой длинной строки
     */
    @Override
    public Map<Integer, List<String>> groupByLength(List<String> strings) {

        Set<Integer> keys = new HashSet<Integer>();

        Map<Integer, List<String>> result = new HashMap<>();

        return strings.stream()
                .collect(Collectors
                        .groupingBy(String::length,
                                Collectors.toList()));
    }
}
