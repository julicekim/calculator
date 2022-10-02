package com.codebeet;

import com.codebeet.schedule.Backward;
import com.codebeet.schedule.BreakTime;
import com.codebeet.schedule.Forward;
import com.codebeet.schedule.TimeUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    void forward() {
        List<BreakTime> breakTimes = getBreakTimes();
        //        long mintues = 0;

        //        long mintues = 240; // 13:10
        //        long mintues = 340; // 14:50
        //        long mintues = 350; // 15:10
        //        long mintues = 670; // 21:00
        //        long mintues = 920; // 02:10
        //        long mintues = 1150; // 06:00
        //        long mintues = 1160; // 06:20
        //        long mintues = 1200; // 07:00
        long mintues = 1260; // 08:00

        long secondsFrom = calculateSeconds("080000");
        long secondsTo = secondsFrom +
                         TimeUnit.calculateSeconds(
                                 TimeUnit.MINUTE,
                                 mintues
                         );

        LocalDateTime dateTime = UtilDates.toLocalDate(secondsTo);

        Forward forward = new Forward();

        long totalBreakTimes = forward.getTotalBreakTimeBetweenFromAndTo(breakTimes, 0, secondsFrom, secondsTo);

        logger.info("시작시간 : {}", dateTime);
        logger.info("총 휴식 시간 : {} 초", totalBreakTimes);
        logger.info("종료시간 : {}", dateTime.plusSeconds(totalBreakTimes));

        assertEquals(10800, totalBreakTimes);
    }

    @Test
    void backward() {

        List<BreakTime> breakTimes = getBreakTimes();

        //        long mintues = 20; // 07:40
        //        long mintues = 60; // 07:00
        //        long mintues = 120; // 05:50
        //        long mintues = 170; // 05:00
        //        long mintues = 230; // 04:00
        //        long mintues = 290; // 03:00
        //        long mintues = 350; // 02:00
        //        long mintues = 360; // 00:50
        //        long mintues = 410; // 00:00
        //        long mintues = 470; // 23:00
        //        long mintues = 530; // 22:00
        //        long mintues = 590; // 21:00
        //        long mintues = 650; // 20:00
        //        long mintues = 710; // 19:00
        //        long mintues = 770; // 18:00
        //        long mintues = 800; // 17:30
        //        long mintues = 810; // 16:50
        //        long mintues = 860; // 16:00
        //        long mintues = 920; // 15:00
        //        long mintues = 930; // 14:40
        //        long mintues = 970; // 14:00
        //        long mintues = 1030; // 13:00
        //        long mintues = 1040; // 11:50
        //        long mintues = 1090; // 11:00
        //        long mintues = 1150; // 10:00
        //        long mintues = 1160; // 09:40
        //        long mintues = 1200; // 09:00
        long mintues = 1260; // 09:00

        long secondsTo = calculateSeconds("320000");
        long secondsFrom = secondsTo -
                           TimeUnit.calculateSeconds(
                                   TimeUnit.MINUTE,
                                   mintues
                           );

        LocalDateTime dateTime = UtilDates.toLocalDate(secondsFrom);

        Backward backward = new Backward();

        long totalBreakTimes = backward.getTotalBreakTimeBetweenFromAndTo(breakTimes, 0, secondsFrom, secondsTo);

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

        return new ArrayList<>(
                Arrays.asList(breakTime1, breakTime2, breakTime3, breakTime4, breakTime5, breakTime6));

    }
}
