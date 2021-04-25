import com.nixsolutions.ppp.dates.DateUtilJava8;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DateUtilJava8 c = new DatesJava8();
        System.out.println(c.between(LocalDate.of(2019, 6, 15),
                                     LocalDate.of(2021, 2, 1)));
    }
}
