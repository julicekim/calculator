package com.codebeet.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Backward {

    private static final Logger logger = LoggerFactory.getLogger(Backward.class);

    public long getTotalBreakTimeBetweenFromAndTo(List<BreakTime> breakTimes, int firstIndex, long secondsFrom,
                                                  long secondsTo) {

        long totalSeconds = 0;
        long adjSecondsFrom = secondsFrom;
        long adjSecondsTo = 0;

        int backIndex = breakTimes.size() - firstIndex - 1;
        int index = 0;
        for (int i = backIndex; i > 0; i--) {
            BreakTime breakTime = breakTimes.get(i);
            if (secondsTo < breakTime.getSecondsTo()) {
                secondsTo = breakTime.getSecondsFrom() - 10;
                continue;
            }

            if (breakTime.getSecondsTo() - adjSecondsFrom == 0) {
                continue;
            }

            long diffSeconds = breakTime.getSecondsTo() - adjSecondsFrom;

            if (diffSeconds > 0 || (breakTime.getTotalSeconds() - Math.abs(diffSeconds)) >= 0) {
                totalSeconds += breakTime.getTotalSeconds();
                adjSecondsFrom -= breakTime.getTotalSeconds();
                adjSecondsTo = breakTime.getSecondsFrom() - 10;
                index = i;
            }
        }

        if (index == 0) {
            return totalSeconds;
        }

        List<BreakTime> subBreakTimes = breakTimes.subList(0, index);

        long subTotalSeconds = 0;
        while (true) {
            subTotalSeconds = getTotalBreakTimeBetweenFromAndTo(subBreakTimes, 0, adjSecondsFrom, adjSecondsTo);
            if (subTotalSeconds == 0) {
                break;
            }

            adjSecondsTo = adjSecondsFrom - 10;

            totalSeconds += subTotalSeconds;
            adjSecondsFrom -= subTotalSeconds;
        }

        return totalSeconds;
    }
}
