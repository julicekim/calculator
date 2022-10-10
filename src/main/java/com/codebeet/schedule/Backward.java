package com.codebeet.schedule;

import java.util.List;

public class Backward {

    public long calculateTotalBreakTimeBetweenFromAndTo(List<BreakTime> breakTimes, long secondsFrom,
                                                        long secondsTo) {

        long totalSeconds = 0;
        long adjSecondsFrom = secondsFrom;
        long adjSecondsTo = secondsTo;

        int backIndex = breakTimes.size() - 1;
        int index = 0;
        for (int i = backIndex; i >= 0; i--) {
            BreakTime breakTime = breakTimes.get(i);
            if (adjSecondsFrom <= breakTime.getSecondsTo()) {
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
            subTotalSeconds = calculateTotalBreakTimeBetweenFromAndTo(subBreakTimes, adjSecondsFrom, adjSecondsTo);
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
