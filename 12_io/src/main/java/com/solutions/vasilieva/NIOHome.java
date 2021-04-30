package com.solutions.vasilieva;

import com.nixsolutions.ppp.io.NIOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class NIOHome implements NIOUtils {

    /**
     * Функция ищет текст в файле, согласно следующим правилам. В файле
     * расположен текст и числа типа Integer(положительные и отрицательные)
     * разделитель пробел. Между разделителями не более 10 байт.<br>
     * Если функция считывает число N, она должна начать следующее считываение
     * через N байт, относительно текущей позиции. Для положительных чисел
     * позицию прибавляем, для отрицательных вычитаем. Если функция после
     * очередного числа считывает текст, то она возвращает это значение.
     *
     * @param file путь к файлу
     * @param offset начальный сдвиг в файле
     * @return найденный в файле текст.
     * @throws IllegalArgumentException
     */
    @Override
    public String searchText(Path file, int offset) throws IllegalArgumentException {
        return null;
    }

    /**
     * Функция ищет все файлы с расширением {@code ext} в {@code folder}
     * директории и поддиректориях и возвращает их полные пути в виде массива
     * строк. В случае если extention == null, считаем что это пустая строка и
     * ищем все файлы.
     *
     * @param folder корневая директория для поиска
     * @param ext расширение файла
     * @return
     * @throws IllegalArgumentException
     */

    @Override
    public String[] search(Path folder, String ext) throws IllegalArgumentException {

        File file = new File(String.valueOf(folder.toFile()));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException();
        }
        ArrayList<String> pathList = new ArrayList<>();

        if (ext.equals(" ") || ext == null) {
            for (File everyFile : file.listFiles()) {
                if (everyFile.isDirectory()) {
                    String[] temp = search(Path.of(everyFile.getPath()), ext);
                    for (String r : temp) {
                        pathList.add(r);
                    }
                } else {
                    pathList.add(everyFile.getPath());
                }
            }
            String[] result = new String[pathList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = pathList.get(i);
            }
            //LOGGER.info("Res: " + Arrays.toString(result));
            return result;
        } else {
            for (File everyFile : file.listFiles()) {
                if (everyFile.isDirectory()) {
                    String[] temp = search(Path.of(everyFile.getPath()), ext);
                    for (String r : temp) {
                        pathList.add(r);
                    }
                } else {
                    if (everyFile.getName().contains(ext)) {
                        pathList.add(everyFile.getPath());
                    }
                }
            }
            String[] result = new String[pathList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = pathList.get(i);
            }
            //LOGGER.info("Res: " + Arrays.toString(result));
            return result;
        }
    }
}
