package com.solutions.vasilieva;

import com.nixsolutions.ppp.strings.StringUtils;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class StringsHomeTasks implements StringUtils {
    /**
     * Функция проверяет, что ip соответствует формату NNN.NNN.NNN.NNN<br>
     * Тоесть, что ip состоит из четырёх цифр разделённых точкой<br>
     * Каждое фракция должна быть в промежутке от 0 до 255<br>
     * В начале {@code ip} и конце возможно будут пробелы. <br>
     * IP фракции от 0 до 127 преобразуются в байт от 0 до 127
     * IP фракции от 128 до 255 преобразуются в байт от -128 до -1
     *
     * @param ip Ip адрес
     * @return {@code ip} в виде массива {@code byte} или, в случае ошибки,
     * {@code null}.
     */

    @Override
    public byte[] ip2Bytes(String ip) {
        String tr = ip.trim();
        String[] str = tr.split("\\.");
        int[] intStr = new int[str.length];
        byte[] ipByte = new byte[str.length];
        //int i = 0;
        if (str.length != 4) {
            return null;
        } else {
            for (int i = 0; i < str.length; i++) {
                intStr[i] = Integer.parseInt(str[i]);
            }
            for (int j = 0; j < intStr.length; j++) {
                if ((intStr[j] >= 0) && (intStr[j] <= 255)) {
                    ipByte[j] = (byte) intStr[j];
                } else return null;
            }
            return ipByte;
        }
    }


    /**
     * Функция конвертирует ip в NNN.NNN.NNN.NNN формат,
     * где каждая группа разделённая точкой будет состоять из трёх чисел<br>
     * Перед конвертацией необходимо удостовериться, что ip адрес, который передан в метод имеет правильный формат<br>
     * Тоесть, что ip состоит из четырёх цифр разделённых точкой<br>
     * Каждое число должно быть в промежутке от 0 до 255<br>
     * В начале {@code ip} и конце возможно будут пробелы. <br>
     * Пример конвертации: "127.0.0.1" -&gt; "127.000.000.001"
     *
     * @param ip
     * @return
     */

    @Override
    public String convertIp(String ip) {

        return null;
    }

    /**
     * Функция Создает строку всех символов английского алфавита "A..Z", где
     * четные буквы upper case, а нечетные в low case.
     *
     * @return
     */

    @Override
    public StringBuilder alphabet() {
        char[] alph = new char[26];
        StringBuilder sb = new StringBuilder();
        for( char i ='a'; i<='z';i++){
            sb.append(i);
        }
        for( int j =0; j< alph.length; j++){
            if( j % 2 != 0){
                sb.replace(j,j+1,sb.substring(j,j+1).toUpperCase());
            }
        }
        return sb;
    }

    /**
     * Функция преобразует url в массив составляющих <br>
     * &lt;схема&gt;://[&lt;логин&gt;:&lt;пароль&gt;@&lt;хост&gt;:&lt;порт&gt;/&lt;путь&gt;?&lt;параметры&gt;#&lt;якорь&gt;<br>
     * Во входной строке значение любая состовлящая может отсутствовать, в этом случае соответсвующее значение в выходном
     * массиве должно быть {@code null}
     *
     * @param uri строка содержащая идентификатор ресурса
     * @param uri
     * @return
     */

    @Override
    public String[] uri2Array(String uri) {
        ArrayList<String> urlArr = new ArrayList<>();
        try {
            URI url = new URI(uri);
            urlArr.add(url.getScheme());
            String[] user = url.getUserInfo() != null ? url.getUserInfo().split(":") : null;
            if(user != null){
                urlArr.add(url.getUserInfo().split(":")[0]);
                urlArr.add(url.getUserInfo().split(":")[1]);
            }else{
                urlArr.add(null);
                urlArr.add(null);
            }
            urlArr.add(url.getHost());
            urlArr.add(String.valueOf(url.getPort()));
            urlArr.add(url.getPath());
            urlArr.add(url.getQuery());
            urlArr.add(url.getFragment());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] result = new String[urlArr.size()];

        return urlArr.toArray(result);
    }

    /**
     * Функция преобразует словосочетание в CamelCase независимо от оригинального регистра букв<br>
     * Исключением служит первая буква. Её регистр нужно оставить нетронутым<br>
     * Разделителем слов служат пробелы и запятые<br>
     * Вначале и вконце строки возможны дополнительные пробелы<br>
     * Пример: "HeLlO jAvA, WoRlD" -&gt; "HelloJavaWorld"
     *
     * @param str
     * @return
     */

    @Override
    public String toCamelCase(String str) {
        String tr = str.trim();
        String[] result = str.split(",\\s+| ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length(); j++) {
                sb.append(str.substring(j, j + 1).toUpperCase() + str.substring(1, i + 1));
            }
        }
        return sb.toString();
    }

    /**
     * Функция преобразует CamelCase строку в словосочетания в нижнем регистре<br>
     * Исключением служит первая буква. Её регистр нужно оставить нетронутым<br>
     * Пример: "HelloJavaWorld" -&gt; "Hello java world"
     *
     * @param camelStr
     * @return
     */

    @Override
    public String fromCamelCase(String camelStr) {
        ArrayList<Integer> ind = new ArrayList<>();
        ind.add(0);
        for( int i = 0; i < camelStr.length(); i++) {
            if (camelStr.substring(i, i + 1).equals(camelStr.substring(i, i + 1).toUpperCase())) {
                ind.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ind.size() - 1; i++) {
            if (i == 0) {
                sb.append(camelStr.substring(0, 1).toUpperCase() + camelStr.substring(1, ind.get(i + 1)));
                continue;
            }
            sb.append(" " + camelStr.substring(ind.get(i), ind.get(i + 1)).toLowerCase());
            if (i == ind.size() - 2) {
                sb.append(" " + camelStr.substring(ind.get(i + 1)).toLowerCase());
            }
        }
        return sb.toString();
    }
}
