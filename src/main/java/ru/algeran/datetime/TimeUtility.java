package ru.algeran.datetime;

import java.util.regex.Matcher;

class TimeUtility {

    private final Matcher matcher;
    private final Integer firstTimeIndex;
    private final Integer hourIdentifierIndex;
    private final Integer secondTimeIndex;

    TimeUtility(
            Matcher matcher,
            Integer firstTimeIndex,
            Integer hourIdentifierIndex,
            Integer secondTimeIndex
    ) {

        this.matcher = matcher;
        this.firstTimeIndex = firstTimeIndex;
        this.hourIdentifierIndex = hourIdentifierIndex;
        this.secondTimeIndex = secondTimeIndex;
    }

    int getMinutesToAdd() {
        Integer time = getFirstTime();
        if (isFirstHourIdentifier()) {
            time = (time != null ? time : 1) * 60;
            if (isSecondTimeAvailable()) {
                time = time + getSecondTime();
            }
        }
        return time != null ? time : 1;
    }

    private boolean isFirstHourIdentifier() {
        return hourIdentifierIndex != null && matcher.group(hourIdentifierIndex) != null;
    }

    private Integer getFirstTime() {
        Integer hours = null;
        if (isFirstTimeAvailable()) {
            hours = Integer.valueOf(matcher.group(firstTimeIndex));
        }
        return hours;
    }

    private boolean isFirstTimeAvailable() {
        return firstTimeIndex != null && matcher.group(firstTimeIndex) != null;
    }

    private boolean isSecondTimeAvailable() {
        return secondTimeIndex != null && matcher.group(secondTimeIndex) != null;
    }

    private int getSecondTime() {
        return Integer.valueOf(matcher.group(secondTimeIndex));
    }
}
