package com.codebeet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

public class UtilDates {

    public static LocalDateTime toLocalDate(long seconds) {
        BaseTime baseTimes = new BaseTime(seconds);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);

        localDateTime = localDateTime.with(ChronoField.YEAR, LocalDate.now().getYear())
                                     .with(ChronoField.MONTH_OF_YEAR, LocalDate.now().getMonthValue())
                                     .with(ChronoField.DAY_OF_MONTH, LocalDate.now().getDayOfMonth());

        return localDateTime;
    }

    public static class BaseTime {

        private long hour;

        private long minute;

        private long second;

        public BaseTime(long seconds) {
            this.hour = seconds / 3600;
            this.minute = (seconds % 3600) / 60;
            this.second = seconds % 60;
        }
    }
}
