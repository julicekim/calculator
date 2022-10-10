package com.codebeet;

import com.codebeet.schedule.Backward;
import com.codebeet.schedule.BreakTime;
import com.codebeet.schedule.Forward;
import com.codebeet.schedule.TimeUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    void forward() {
        List<BreakTime> breakTimes = getBreakTimes();
        //        long minutes = 60; // 09:00
        //        long minutes = 120; // 10:10
        //        long minutes = 170; // 11:00
        long minutes = 200;

        //        long minutes = 240; // 13:10
        //        long minutes = 340; // 14:50
        //        long minutes = 350; // 15:10
        //        long minutes = 670; // 21:00
        //        long minutes = 920; // 02:10
        //        long minutes = 1150; // 06:00
        //        long minutes = 1160; // 06:20
        //        long minutes = 1200; // 07:00
        //        long minutes = 1260; // 08:00

        long secondsFrom = calculateSeconds("113000");
        long secondsTo = secondsFrom +
                         TimeUnit.calculateSeconds(
                                 TimeUnit.MINUTE,
                                 minutes
                         );

        LocalDateTime dateTime = UtilDates.toLocalDate(secondsTo);

        Forward forward = new Forward();

        long totalBreakTimes = forward.calculateTotalBreakTimeBetweenFromAndTo(breakTimes, secondsFrom, secondsTo);

        logger.info("시작시간 : {}", dateTime);
        logger.info("총 휴식 시간 : {} 초", totalBreakTimes);
        logger.info("종료시간 : {}", dateTime.plusSeconds(totalBreakTimes));

        assertEquals(10800, totalBreakTimes);
    }

    @Test
    void backward() {

        List<BreakTime> breakTimes = getBreakTimes();

        //        long minutes = 20; // 07:40
        //        long minutes = 60; // 07:00
        //        long minutes = 110; // 06:00
        //        long minutes = 120; // 05:50
        //        long minutes = 170; // 05:00
        //        long minutes = 230; // 04:00
        //        long minutes = 290; // 03:00
        //        long minutes = 340; // 02:10
        //        long minutes = 350; // 01:00
        //        long minutes = 360; // 00:50
        //        long minutes = 410; // 00:00
        //        long minutes = 470; // 23:00
        //        long minutes = 530; // 22:00
        //        long minutes = 590; // 21:00
        //        long minutes = 650; // 20:00
        //        long minutes = 710; // 19:00
        //        long minutes = 770; // 18:00
        //        long minutes = 790; // 17:40
        //        long minutes = 800; // 17:00
        //        long minutes = 810; // 16:50
        //        long minutes = 860; // 16:00
        //        long minutes = 910; // 15:10
        //        long minutes = 920; // 14:50
        //        long minutes = 970; // 14:00
        //        long minutes = 980; // 13:50
        //        long minutes = 1020; // 13:10
        //        long minutes = 1030; // 12:00
        //        long minutes = 1040; // 11:50
        //        long minutes = 1090; // 11:00
        //        long minutes = 1140; // 10:10
        //        long minutes = 1150; // 09:50
        //        long minutes = 1160; // 09:40
        //        long minutes = 1200; // 09:00
        long minutes = 1260; // 08:00

        long secondsTo = calculateSeconds("320000");
        long secondsFrom = secondsTo -
                           TimeUnit.calculateSeconds(
                                   TimeUnit.MINUTE,
                                   minutes
                           );

        LocalDateTime dateTime = UtilDates.toLocalDate(secondsFrom);

        Backward backward = new Backward();

        long totalBreakTimes = backward.calculateTotalBreakTimeBetweenFromAndTo(breakTimes, secondsFrom, secondsTo);

        logger.info("시작시간 : {}", dateTime);
        logger.info("총 휴식 시간 : {} 초", totalBreakTimes);
        logger.info("종료시간 : {}", dateTime.minusSeconds(totalBreakTimes));
        assertEquals(10800, totalBreakTimes);
    }

    long calculateSeconds(String times) {
        long hour = Long.parseLong(times.substring(0, 2));
        long min = Long.parseLong(times.substring(2, 4));
        long sec = Long.parseLong(times.substring(4));

        return hour * (60 * 60)
               + min * (60)
               + sec;
    }

    List<BreakTime> getBreakTimes() {

        BreakTime breakTime1 = BreakTime
                .builder()
                .timeFrom("095000")
                .timeTo("100000")
                .build();

        BreakTime breakTime2 = BreakTime
                .builder()
                .timeFrom("120000")
                .timeTo("130000")
                .build();
        BreakTime breakTime3 = BreakTime
                .builder()
                .timeFrom("145000")
                .timeTo("150000")
                .build();
        BreakTime breakTime4 = BreakTime
                .builder()
                .timeFrom("170000")
                .timeTo("173000")
                .build();
        BreakTime breakTime5 = BreakTime
                .builder()
                .timeFrom("250000")
                .timeTo("260000")
                .build();
        BreakTime breakTime6 = BreakTime
                .builder()
                .timeFrom("300000")
                .timeTo("301000")
                .build();

        return new CopyOnWriteArrayList<>(
                Arrays.asList(breakTime1, breakTime2, breakTime3, breakTime4, breakTime5, breakTime6));

    }
}
