import com.nixsolutions.ppp.dates.DateUtilJava7;

import java.text.DateFormat;
import java.util.*;

public class DatesJava7 implements DateUtilJava7 {
    @Override
    public String between(Date date1, Date date2) {
        StringBuilder sb = new StringBuilder();
        Date date;
        if (date1.after(date2)) {
            date = date2;
            date2 = date1;
        } else {
            date = date1;
            date1 = date2;
        }
        Calendar c1 = new GregorianCalendar(date.getYear() + 1900,
                date.getMonth(), date.getDate());
        Calendar c2 = new GregorianCalendar(date2.getYear() + 1900,
                date2.getMonth(), date2.getDate());
        int monthDiff = ((c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12
                + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        int dayDiff = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if ((monthDiff > 0) && (dayDiff < 0)) {
            monthDiff--;
            c1.add(Calendar.MONTH, monthDiff);
            dayDiff = c1.getActualMaximum(Calendar.DAY_OF_MONTH)
                    - c1.get(Calendar.DATE) + c2.get(Calendar.DATE);
        } else if ((monthDiff < 0) && (dayDiff > 0)) {
            monthDiff++;
            dayDiff -= c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        int years = monthDiff / 12;
        int month = (int) (monthDiff % 12);
        int days = dayDiff;
        if (years != 0) {
            if (years == 1) {
                sb.append(years).append(" year ");
            } else {
                sb.append(years).append(" years ");
            }
        }
        if (month != 0) {
            if (month == 1) {
                sb.append(month).append(" month ");
            } else {
                sb.append(month).append(" months ");
            }
        }
        if (days != 0) {
            if (days == 1) {
                sb.append(days).append(" day ");
            } else {
                sb.append(days).append(" days ");
            }
        }
        return sb.toString();
    }

    /**
     * Метод вычисляет количество дней в каждом месяце в указанном году.
     *
     * @param year
     * @return количество дней в месяцах
     */

    @Override
    public int[] daysInMonth(int year) {
        int month = 12;
        int day = 1;
        int days[] = new int[12];
        for (int i = 0; i < month; i++) {
            days[i] = new GregorianCalendar(year, i, day).getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return days;
    }

    /**
     * Метод возвращает массив дат которые выподают на понедельник в указанном годе и месяце.
     *
     * @param month
     * @param year
     * @return
     */

    @Override
    public Date[] mondays(int month, int year) {
        Calendar c = Calendar.getInstance();
        int day = 1;
        ArrayList<Date> arr1 = new ArrayList<Date>();
        int days = new GregorianCalendar(year, month, day).getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= days; i++) {
            int dayOfWeek = new GregorianCalendar(year, month, i).get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 2) {
                arr1.add(new GregorianCalendar(year, month, i).getTime());
            }
        }
        return arr1.toArray(new Date[0]);
    }

    /**
     * Метод проверяет является ли указанная дата пятницей 13-го.
     *
     * @param date
     * @return
     */

    @Override
    public boolean isFridays13(Date date) {
        Calendar calendar = Calendar.getInstance();
        //Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return (day == 13) && (dayOfWeek == 6);
    }

    /**
     * Метод конвертирует дату в строку используя полный формат и локаль для указанного языка.
     *
     * @param date
     * @param language
     * @return
     */

    @Override
    public String formatFull(Date date, String language) {
        Locale lan = new Locale(language);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, lan);
        String formattedDate = df.format(date);
        return formattedDate;
    }
}
