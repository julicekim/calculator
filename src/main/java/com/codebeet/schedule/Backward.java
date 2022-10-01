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

        int lastIndex = 0;
        int backIndex = breakTimes.size() - firstIndex - 1;
        for (int i = backIndex; i >= 0; i--) {
            BreakTime breakTime = breakTimes.get(i);
            if (breakTime.getSecondsFrom() <= secondsFrom && secondsFrom < breakTime.getSecondsTo()) {

                if (breakTime.getSecondsTo() - secondsFrom == 0) {
                    continue;
                }

                totalSeconds += breakTime.getTotalSeconds();
                adjSecondsFrom -= breakTime.getTotalSeconds();
                adjSecondsTo = breakTime.getSecondsFrom() - 10;
                lastIndex += 1;
            }
        }

        if (lastIndex > 0 && lastIndex <= breakTimes.size() - 1) {
            long seconds = 0;
            while (true) {
                seconds = getTotalBreakTimeBetweenFromAndTo(breakTimes, lastIndex,
                                                            secondsFrom, adjSecondsTo
                );
                if (seconds == 0L) {
                    break;
                }
                totalSeconds += seconds;
                adjSecondsTo = breakTimes.get(lastIndex).getSecondsTo() - 10;
            }
        }

        return totalSeconds;
    }
}
