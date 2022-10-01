package com.codebeet.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BreakTime {

    @Getter
    private String timeFrom;

    @Getter
    private String timeTo;

    @Getter
    private long secondsFrom;

    @Getter
    private long secondsTo;

    @Getter
    private long totalSeconds;

    @Builder
    public BreakTime(String timeFrom, String timeTo) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;

        initialize();
    }

    void initialize() {
        this.secondsFrom = calculateSeconds(this.timeFrom);
        this.secondsTo = calculateSeconds(this.timeTo);

        this.totalSeconds = this.secondsTo - this.secondsFrom;
    }

    long calculateSeconds(String times) {
        long hour = Long.parseLong(times.substring(0, 2));
        long min = Long.parseLong(times.substring(2, 4));
        long sec = Long.parseLong(times.substring(4));

        return hour * (60 * 60)
               + min * (60)
               + sec;
    }
}
