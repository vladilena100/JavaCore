package com.solutions.vasilieva;


import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class srez {
    public static String srez(String file) {

        File fileName = new File(file);
        String regex = "[0-9]{4}";
        Pattern pattern = Pattern.compile(regex);
        String year = null;
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String monthIn = null;
        int numberMonth = 0;
        int numberDay = 0;
        String dayOfWeek = null;
        try {
            Scanner sc = new Scanner(fileName);
            while (sc.hasNextLine()) {
                String themap = sc.nextLine();
                final Matcher matcher = pattern.matcher(themap);
                if (themap.contains(regex)) {
                    year = matcher.group();
                }
                for (int i = 0; i < month.length; i++) {
                    Pattern pattern1 = Pattern.compile(month[i]);
                    Matcher matcher1 = pattern1.matcher(themap);
                    numberMonth = i;
                }
                for (int i = 0; i < days.length; i++) {
                    Pattern pattern2 = Pattern.compile(days[i]);
                    Matcher matcher2 = pattern2.matcher(themap);
                    numberDay = i;
                    dayOfWeek = days[i];
                }
            }
            int yearInt = Integer.parseInt(year);
            ArrayList<Date> arr1 = new ArrayList<Date>();
            ArrayList<LocalDate> sb = new ArrayList<LocalDate>();
            int countDays = new GregorianCalendar(yearInt, numberMonth, numberDay).getActualMaximum(Calendar.DAY_OF_MONTH);

            /* вычисление даты java7 */

            for (int i = 1; i <= countDays; i++) {
                int dayOfWeeks = new GregorianCalendar(yearInt, numberMonth, i).get(Calendar.DAY_OF_WEEK);
                if (dayOfWeeks == numberDay) {
                    arr1.add(new GregorianCalendar(yearInt, numberMonth, i).getTime());
                }
            }
            LocalDate date1 = LocalDate.of(yearInt, numberMonth, 1);
            YearMonth yearMonthObject = YearMonth.of(yearInt, numberMonth);
            int daysInMonth = yearMonthObject.lengthOfMonth();

            /* вычисление даты java7 */

            for (int i = 1; i <= daysInMonth; i++) {
                LocalDate date = LocalDate.of(yearInt, numberMonth, i);
                if (date.getDayOfWeek() == DayOfWeek.of(numberDay)) {
                    sb.add(LocalDate.of(yearInt, numberMonth, i));
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
