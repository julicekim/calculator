package com.codebeet.schedule;

import java.util.List;

public class Forward {

    public long getTotalBreakTimeBetweenFromAndTo(List<BreakTime> breakTimes, int firstIndex, long secondsFrom,
                                                  long secondsTo) {

        long totalSeconds = 0;
        long adjSecondsFrom = secondsFrom;
        long adjSecondsTo = secondsTo;

        int index = 0;
        for (int i = firstIndex; i < breakTimes.size(); i++) {

            BreakTime breakTime = breakTimes.get(i);
            if (breakTime.getSecondsFrom() > secondsFrom && secondsFrom <= breakTime.getSecondsTo()) {
                if (breakTime.getSecondsFrom() - adjSecondsTo == 0) {
                    continue;
                }

                long diffSeconds = adjSecondsTo - breakTime.getSecondsFrom();

                if (diffSeconds > 0 || (breakTime.getTotalSeconds() - Math.abs(diffSeconds) >= 0)) {
                    totalSeconds += breakTime.getTotalSeconds();
                    adjSecondsTo += breakTime.getTotalSeconds();
                    adjSecondsFrom = breakTime.getSecondsTo() + 10;
                    index = i;
                }
            }
        }

        if (index == 0) {
            return totalSeconds;
        }

        adjSecondsTo = secondsTo + totalSeconds;

        List<BreakTime> subBreakTimes = breakTimes.subList(index + 1, breakTimes.size());

        long subTotalSeconds = 0;
        while (true) {
            subTotalSeconds = getTotalBreakTimeBetweenFromAndTo(subBreakTimes, 0, adjSecondsFrom, adjSecondsTo);
            if (subTotalSeconds == 0) {
                break;
            }
            adjSecondsFrom = adjSecondsTo + 10;

            totalSeconds += subTotalSeconds;
            adjSecondsTo += subTotalSeconds;
        }

        return totalSeconds;
    }
}
