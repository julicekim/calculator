package com.codebeet.schedule;

public enum TimeUnit {
    HOUR(60 * 60),
    MINUTE(60),
    SECOND(1);

    int unit;

    public int getUnit() {return unit;}

    TimeUnit(int unit) {
        this.unit = unit;
    }

    public static long calculateSeconds(TimeUnit timeUnit, long baseTimes) {
        return timeUnit.getUnit() * baseTimes;
    }
}
