import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class DaysBetweenLocalDates {
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.of(2022, 2, 9, 0,0);
        LocalDateTime date3 = date2.plusMonths(1);
        long days = DAYS.between(date2, date3);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(days);
    }
}
