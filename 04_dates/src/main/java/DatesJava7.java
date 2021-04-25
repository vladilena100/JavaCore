import com.nixsolutions.ppp.dates.DateUtilJava7;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatesJava7 implements DateUtilJava7 {
    @Override
    public String between(Date date1, Date date2) {
        long miliseconds = date2.getTime() - date1.getTime();


        return null;
    }

    /**
     *Метод вычисляет количество дней в каждом месяце в указанном году.
     * @param year
     * @return количество дней в месяцах
     */

    @Override
    public int[] daysInMonth(int year) {
        int month = 12;
        int day = 1;
        int days[] = new int[12];
        for(int i = 0; i < month; i++){
            days[i] = new GregorianCalendar(year, i, day).getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        return days;
    }

    /**
     * Метод возвращает массив дат которые выподают на понедельник в указанном годе и месяце.
     * @param month
     * @param year
     * @return
     */

    @Override
    public Date[] mondays(int month, int year) {
        Calendar c = Calendar.getInstance();
        int day = 1;
        int days = new GregorianCalendar(year, month, day).getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i = 1; i <= days; i++) {
            //c.setTime();
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == 2){

            }
        }
        return new Date[0];
    }

    @Override
    public boolean isFridays13(Date date) {
        return false;
    }

    @Override
    public String formatFull(Date date, String language) {
        return null;
    }
}
