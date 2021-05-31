package com.solution.vasilieva;

import com.nixsolutions.ppp.collections.DefaultIterator;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * the class implements methods for creating a new collection
 *
 * @param <E>
 * @author Vladileva Vasilieva
 * @since 26.05.21
 */

public class DefaultCollection<E> implements com.nixsolutions.ppp.collections.DefaultCollection {

    private static final Logger LOG = LogManager.getLogger(DefaultCollection.class);

    private int size;
    protected int count = 0;

    E[] values;

    public DefaultCollection() {
        values = (E[]) new Object[0];
    }

    /**
     * Метод возвращает размер коллекции
     *
     * @return
     */

    @Override
    public int size() {
        return values.length;
    }

    /**
     * метод проверяет пустая ли коллекция
     *
     * @return
     */

    @Override
    public boolean isEmpty() {
        if (values.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * метод проверяет присутствует ли в коллекции объект
     *
     * @param o
     * @return
     */

    @Override
    public boolean contains(Object o) {

        boolean result1 = false;
        boolean result2 = true;
        try {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == (E) o) {
                    result1 = true;
                } else result2 = false;
            }
        } catch (ClassCastException e) {
            LOG.error(e);
            return false;
        }
        return (result1 == true);
    }

    /**
     * поведенческий шаблон
     *
     * @return
     */

    @Override
    public DefaultIterator iterator() {
        return new DefaultIteratorClass<>(values);
    }

    /**
     * метод возвращает массив определенного типа
     *
     * @return
     */

    @Override
    public Object[] toArray() {
        Object arrayToReturn[] = new Object[values.length];
        for (int i = 0; i < values.length; i++) {
            arrayToReturn[i] = values[i];
        }
        return arrayToReturn;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Object[] arr = new Object[values.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = values[i];
        }
        return arr;
    }

    /**
     * метод добавления элемента в коллекцию
     *
     * @param o
     * @return
     */

    @Override
    public boolean add(Object o) {

        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = (E) o;
            return true;
        } catch (ClassCastException e) {
            LOG.error(e);
        }
        return false;
    }

    /**
     * метод удаления элемента из коллекции
     *
     * @param o
     * @return
     */

    @Override
    public boolean remove(Object o) {

        try {
            int number = 0;
            for (int i = 0; i < values.length; i++) {
                if (values[i] == (E) o) {
                    number = i;
                }
            }
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, number);
            int elemAfterNumber = temp.length - number - 1;
            System.arraycopy(temp, number + 1, values, number, elemAfterNumber);
            return true;
        } catch (ClassCastException e) {
            LOG.error(e);
            return false;
        }
    }

    /**
     * метод добавления новой коллекции к изначальной
     *
     * @param c
     * @return
     */

    @Override
    public boolean addAll(Collection c) {

        try {
            Object[] a = c.toArray();
            int count = a.length;
            E[] temp = values;
            values = (E[]) new Object[temp.length + count];
            int number = values.length - temp.length;
            System.arraycopy(temp, 0, values, 0, values.length - count);
            System.arraycopy(a, number, values, values.length - 1, number);
            return true;
        } catch (ClassCastException e) {
            LOG.error(e);
            return false;
        }
    }

    /**
     * метод изменяет все элементы на null
     */

    @Override
    public void clear() {
        final Object[] es = values;
        for (int i = 0; i < values.length; i++)
            es[i] = null;
    }

    /**
     * удаляет элементы не принадлежащие переданной коллекции
     *
     * @param c
     * @return
     */

    @Override
    public boolean retainAll(Collection c) {

        Object[] a = c.toArray();
        try {
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (values[i] != a[j]) {
                        E[] temp = values;
                        values = (E[]) new Object[temp.length - 1];
                        System.arraycopy(temp, 0, values, 0, i);
                        int elemAfterNumber = temp.length - i - 1;
                        System.arraycopy(temp, i + 1, values, i, elemAfterNumber);
                    }
                }
            }
            return true;
        } catch (ClassCastException e) {
            LOG.error(e);
            return false;
        }
    }

    /**
     * удаляет элементы, принадлежащие переданной
     *
     * @param c
     * @return
     */

    @Override
    public boolean removeAll(Collection c) {
        Object[] a = c.toArray();
        try {
            for (int i = 0; i < values.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (values[i] == a[j]) {
                        E[] temp = values;
                        values = (E[]) new Object[temp.length - 1];
                        System.arraycopy(temp, 0, values, 0, i);
                        int elemAfterNumber = temp.length - i - 1;
                        System.arraycopy(temp, i + 1, values, i, elemAfterNumber);
                    }
                }
            }
            return true;
        } catch (ClassCastException e) {
            LOG.error(e);
            return false;
        }
    }

    /**
     * метод проверяет соответствие коллекций
     *
     * @param c
     * @return
     */

    @Override
    public boolean containsAll(Collection c) {

        Object[] a = c.toArray();
        boolean result1 = false;
        boolean result2 = true;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (values[i] == a[j]) {
                    result1 = true;
                } else {
                    result2 = false;
                }
            }
        }
        return ((result1 == true) && (result2 == true)) ? true : false;
    }


    /**
     * Класс итератор позволяет проверить наличие следующего элемента
     * и вывести следующий элемент
     *
     * @author Vladilena Vasilieva
     * @since 26.05.21
     */
   public static class DefaultIteratorClass<E> implements DefaultIterator<E> {

        private int index = 0;
        E[] values;

        DefaultIteratorClass(E[] values) {
            this.values = values;
        }

        /**
         * Метод проверяет есть ли следующий элемент
         *
         * @return
         */

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        /**
         * метод возвращает следующий элемент
         *
         * @return
         */

        @Override
        public E next() {
            return values[index++];
        }
    }
}
