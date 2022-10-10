package com.codebeet.schedule;

import java.util.List;

public class Forward {

    public long calculateTotalBreakTimeBetweenFromAndTo(List<BreakTime> breakTimes, long secondsFrom,
                                                        long secondsTo) {

        long totalSeconds = 0;
        long adjSecondsFrom = secondsFrom;
        long adjSecondsTo = secondsTo;

        int index = 0;
        for (int i = 0; i < breakTimes.size(); i++) {

            BreakTime breakTime = breakTimes.get(i);
            if (adjSecondsTo >= breakTime.getSecondsTo()) {

                totalSeconds += breakTime.getTotalSeconds();
                adjSecondsTo += breakTime.getTotalSeconds();
                adjSecondsFrom = breakTime.getSecondsTo() + 10;
                index = i;
            }
        }

        if (index == 0) {
            return totalSeconds;
        }

        adjSecondsTo = secondsTo + totalSeconds;

        List<BreakTime> subBreakTimes = breakTimes.subList(index + 1, breakTimes.size());

        long subTotalSeconds = 0;
        while (true) {
            subTotalSeconds = calculateTotalBreakTimeBetweenFromAndTo(subBreakTimes, adjSecondsFrom, adjSecondsTo);
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
