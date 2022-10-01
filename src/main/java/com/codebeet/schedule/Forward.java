package com.codebeet.schedule;

import java.util.List;

public class Forward {

    public long getTotalBreakTimeBetweenFromAndTo(List<BreakTime> breakTimes, int firstIndex, long secondsFrom,
                                                  long secondsTo) {

        long totalSeconds = 0;
        long adjSecondsFrom = 0;

        int lastIndex = -1;
        for (int i = firstIndex; i < breakTimes.size(); i++) {

            BreakTime breakTime = breakTimes.get(i);
            if (breakTime.getSecondsFrom() > secondsFrom && secondsFrom <= breakTime.getSecondsTo()) {
                if (breakTime.getSecondsFrom() - secondsTo == 0) {
                    continue;
                }

                long diffSeconds = secondsTo - breakTime.getSecondsTo();

                if (diffSeconds > 0 || (breakTime.getTotalSeconds() - Math.abs(diffSeconds) >= 0)) {
                    totalSeconds += breakTime.getTotalSeconds();
                    adjSecondsFrom = breakTime.getSecondsTo() + 10;
                    lastIndex = i + 1;
                }
            }
        }

        long adjSecondsTo = secondsTo + totalSeconds;

        if (lastIndex > -1 && lastIndex <= breakTimes.size() - 1) {
            long seconds = 0;
            while (true) {
                seconds = getTotalBreakTimeBetweenFromAndTo(breakTimes, lastIndex, adjSecondsFrom, adjSecondsTo);
                if (seconds == 0L) {
                    break;
                }
                totalSeconds += seconds;
                adjSecondsTo += seconds;
                adjSecondsFrom = breakTimes.get(lastIndex).getSecondsTo() + 10;
            }
        }

        return totalSeconds;
    }
}
