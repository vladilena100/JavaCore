import com.nixsolutions.ppp.dates.DateUtilJava8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatesJava8 implements DateUtilJava8 {

    /**
     * Метод возвращает строковое значение периода между датами в формате "[A years] [B months] [C days]" (скобки
     * показывают опциональность части и не должны отображаться в результирующей строке).
     * множественное и единичное число должно правильно обрабатываться.
     *
     * @param date1
     * @param date2
     * @return
     */
    @Override
    public String between(LocalDate date1, LocalDate date2) {
        StringBuilder sb = new StringBuilder();
        Period period = Period.between(date1, date2);
        int years = period.getYears();
        int month = period.getMonths();
        int days = period.getDays();
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
     * Метод возвращает массив дат которые выподают на понедельник в указанном годе и месяце.
     *
     * @param instant
     * @return
     */

    @Override
    public LocalDate[] mondays(Instant instant) {
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneOffset.UTC);
        ArrayList<LocalDate> sb = new ArrayList<LocalDate>();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        for (int i = 1; i <= daysInMonth; i++) {
            LocalDate date = LocalDate.of(year, month, i);
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                sb.add(LocalDate.of(year, month, i));
            }
        }
        return sb.toArray(new LocalDate[0]);
    }

    /**
     * Метод проверяет является ли указанная дата пятницей 13-го.
     *
     * @param date
     * @return
     */

    @Override
    public boolean isFridays13(LocalDate date) {
        int day = date.getDayOfMonth();
        return (day == 13) && (date.getDayOfWeek() == DayOfWeek.FRIDAY);
    }

    /**
     * Метод конвертирует дату в строку используя полный формат и локаль для указанного языка
     *
     * @param date
     * @param language
     * @return
     */

    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {
        Locale lan = new Locale(language);
        return date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME.withLocale(lan));
    }
}
