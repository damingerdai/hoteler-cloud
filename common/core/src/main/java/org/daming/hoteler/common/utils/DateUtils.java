package org.daming.hoteler.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gming001
 * @version 2024-01-18 19:20
 */
public class DateUtils {

    public static long getDaysNums(LocalDate beginDate, LocalDate endDate) {
        var duration = Duration.between(beginDate, endDate);
        return duration.toDays();
    }

    public static long getDaysNums(LocalDateTime beginDate, LocalDateTime endDate) {
        var duration = Duration.between(beginDate, endDate);
        return duration.toDays();
    }

    public static List<LocalDate> getDates(LocalDate beginDate, LocalDate endDate) {
        var stream = beginDate.datesUntil(endDate);
        return stream.collect( Collectors.toList() );
    }

    public static boolean isToday(LocalDate date) {
        return LocalDate.now(ZoneId.systemDefault()).isEqual(date);
    }
}
