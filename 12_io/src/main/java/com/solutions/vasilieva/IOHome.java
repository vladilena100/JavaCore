package com.solutions.vasilieva;

import com.nixsolutions.ppp.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

public class IOHome implements IOUtils {

    /**
     * Функция сжимает {@code fileName} и ложит результат с расширением ".gzip"
     * в директорию {@code folder}, для сжатия потока используется
     * {@link java.util.zip.GZIPOutputStream GZIPOutputStream}
     *
     * @param fileName путь к файлу
     * @param folder   директория для сохранения сжатого файла
     * @return полный путь к новому файлу, включая имя файла.
     * @throws IllegalArgumentException
     */

    @Override
    public String gzip(String fileName, String folder) throws IllegalArgumentException {
        if (fileName == null || fileName.length() == 0) {
            return fileName;
        }

        BufferedWriter writer = null;
        File file = new File("gzipTest.gzip");

        try {
            GZIPOutputStream zip = new GZIPOutputStream(new FileOutputStream(file));

            writer = new BufferedWriter(new OutputStreamWriter(zip, "UTF-8"));

            writer.append(fileName);

            if (writer != null) {
                writer.close();
            }
            new File(folder);
        } catch (IOException e) {
        }
        return file.getAbsolutePath();
    }


    /**
     * Функция ищет в текстовом {@code fileName} все вхождения {@code mark} и
     * возвращает количество строк, в которых встречается {@code mark}.
     *
     * @param fileName путь к файлу
     * @param mark     текст для поиска
     * @return количество строк, в которых встречается {@code mark}.
     * @throws IllegalArgumentException
     */

    @Override
    public Integer searchText(String fileName, String mark) throws IllegalArgumentException {
        File file = new File(fileName);
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String themap = sc.nextLine();
                if (themap.contains(mark)) {
                    count++;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    /**
     * Функция ищет все файлы с расширением {@code ext} в {@code folder}
     * директории и поддиректориях и возвращает их полные пути в виде массива
     * строк. В случае если extention == null считаем что это пустая строка и
     * ищем все файлы.
     *
     * @param folder корневая директория для поиска
     * @param ext    расширение файла
     * @return список найденных файлов с абсолютными путями.
     * @throws IllegalArgumentException
     */

    @Override
    public String[] search(File folder, String ext)
            throws IllegalArgumentException {
        final List<String> PATHS = new ArrayList<>();
        File[] files = folder.listFiles();
        String fileName;
        boolean extensionFile;
        if (files != null) {
            for (File entry : files) {
                if (entry.isFile()) {
                    if (ext == null) {
                        PATHS.add(entry.getAbsolutePath());
                    } else {
                        fileName = entry.getName();
                        extensionFile = fileName.endsWith(ext);
                        if (extensionFile) {
                            PATHS.add(entry.getAbsolutePath());
                        }
                    }
                } else {
                    search(entry, ext);
                }
            }
        }
        return PATHS.toArray(new String[0]);
    }
}
