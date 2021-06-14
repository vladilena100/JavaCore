package com.solution.vasilieva;

import com.nixsolutions.ppp.reflection.Ignore;
import com.nixsolutions.ppp.reflection.Info;
import com.nixsolutions.ppp.reflection.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс имплементирует и реализует методы интерфейса ReflectionUtil
 *
 * @author Vasilieva Vladilena
 * @since 13.06.21
 */

public class Reflection implements ReflectionUtil {

    private static final Logger LOG = LogManager.getLogger(Reflection.class);

    boolean baseElemField (Field field) {
        return BASE_TYPES.contains(field.getType().getTypeName());
    }

    /**
     * Метод приведения произвольного обьекта к строке в JSON подобном формате:
     * {key_1 : value_1, key_2 : value_2, key_3 : {key_4 : value_4 }, key_5 : value_5}.
     * Обьект может состоять из базовых и составных обьектов/елементов.
     * Базовый обьект - это обьект с одним из типов: java.lang.String, long, int, double, boolean.
     * В результирующей строке он должен быть представлен как key : value, где key - имя поля, value - его значение
     * Составной обьект - это обьект сформированный из базовых или других составных обьектов.
     * Составной обьект в результирующей строке находиться между символам '{' и '}'.
     * В результирующую строку должны попасть только поля обекта помеченные аннотацией @Info.
     *
     * @param object
     * @return
     */
    @Override
    public String toString(Object object) {

        Map<Object, Object> result = new HashMap<>();
        Class<?> clas = object.getClass();

        Arrays.stream(clas.getDeclaredFields())
                .filter(s -> s.isAnnotationPresent(Info.class))
                .forEach(s -> {
                    s.setAccessible(true);
                    try {
                        if (isBaseElement(s.getType())) {
                            result.put(s.getName(), s.get(object));
                        } else {
                            result.put(s.getName(), toString(s.get(object)));
                        }
                    } catch (IllegalAccessException e) {
                        LOG.error(e);
                    }
                });
        return result.toString();
    }

    /**
     * Метод проверки идентичности двух произвольных обьектов.
     * Два обьекта считаются идентичными если их соответствующие поля имеют одинаковый тип и значение.
     * Поля обьектов помеченные аннотацие @Ignore не должны влиять на результат сравнения.
     *
     * @param object1
     * @param object2
     * @return
     */

    @Override
    public boolean isTheSame(Object object1, Object object2) {

        boolean result = true;
        List<Field> first = Arrays.asList(object1
                .getClass()
                .getDeclaredFields());
        List<Field> two = Arrays.asList(object2
                .getClass()
                .getDeclaredFields());

        for (int i = 0; i < first.size(); i++) {
            if (!first.get(i).isAnnotationPresent(Ignore.class)
                    && !two.get(i).isAnnotationPresent(Ignore.class)) {
                if (two.contains(first.get(i))) {
                    if (!baseElemField(first.get(i))) {
                        try {
                            if (!isTheSame(first.get(i).get(object1),
                                    first.get(i).get(object2))) {
                                result = false;
                            }
                        } catch (IllegalAccessException e) {
                            LOG.error(e);
                        }
                    } else {
                        try {
                            if (first.get(i).get(object1) != first.get(i).get(object2)) {
                                result = false;
                            }
                        } catch (IllegalAccessException e) {
                            LOG.error(e);
                        }
                    }
                } else {
                    result = false;
                }
            }
        }

        return result;
    }
}
