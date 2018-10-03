package ru.algeran.datetime;

import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;

class DayTimeUtility {

    private final Matcher matcher;
    private final Integer hoursGroupIndex;
    private final Integer middayGroupIndex;
    private final Integer midnightGroupIndex;
    private final Integer morningGroupIndex;
    private final Integer dayGroupIndex;
    private final Integer eveningGroupIndex;
    private final Integer nightGroupIndex;
    private boolean lock = false;
    private Integer directHours;

    DayTimeUtility(
            Matcher matcher,
            Integer hoursGroupIndex,
            Integer middayGroupIndex,
            Integer midnightGroupIndex,
            Integer morningGroupIndex,
            Integer dayGroupIndex,
            Integer eveningGroupIndex,
            Integer nightGroupIndex
    ) {
        this.matcher = matcher;
        this.hoursGroupIndex = hoursGroupIndex;
        this.middayGroupIndex = middayGroupIndex;
        this.midnightGroupIndex = midnightGroupIndex;
        this.morningGroupIndex = morningGroupIndex;
        this.dayGroupIndex = dayGroupIndex;
        this.eveningGroupIndex = eveningGroupIndex;
        this.nightGroupIndex = nightGroupIndex;
    }

    void setDirectHours(Integer directHours) {
        this.directHours = directHours;
    }

    int evaluateHours() {
        Integer resultHours = getHours();
        resultHours = resultHours != null ? resultHours : getMidday();
        resultHours = resultHours != null ? resultHours : getMidnight();
        resultHours = resultHours != null ? resultHours : LocalTime.now().get(ChronoField.CLOCK_HOUR_OF_DAY);
        return resultHours;
    }

    boolean isNotLocked() {
        return !lock;
    }

    boolean isMidnight() {
        return midnightGroupIndex != null && matcher.group(midnightGroupIndex) != null;
    }


    private boolean isMorning() {
        return morningGroupIndex != null && matcher.group(morningGroupIndex) != null;
    }

    private boolean isDay() {
        return dayGroupIndex != null && matcher.group(dayGroupIndex) != null;
    }

    private boolean isEvening() {
        return eveningGroupIndex != null && matcher.group(eveningGroupIndex) != null;
    }

    private boolean isNight() {
        return nightGroupIndex != null && matcher.group(nightGroupIndex) != null;
    }

    private Integer getHours() {
        Integer hours = null;
        if (hoursGroupIndex != null) {
            String hoursGroup = matcher.group(hoursGroupIndex);
            if (hoursGroup != null && !hoursGroup.isEmpty()) {
                hours = Integer.valueOf(hoursGroup);
                hours = correctHours(hours);
            }
        } else if (directHours != null) {
            hours = correctHours(directHours);
        }
        return hours;
    }

    private Integer correctHours(Integer hours) {
        if (isMorning()) {
            lock = true;
        } else if (isDay()) {
            if (hours >= 0 && hours <= 8) {
                hours += 12;
            }
            lock = true;
        } else if (isEvening()) {
            hours += 12;
            lock = true;
        } else if (isNight()) {
            if (hours >= 8 && hours <= 12) {
                hours += 12;
            }
            lock = true;
        }
        return hours;
    }

    private Integer getMidday() {
        Integer hours = null;
        if (isMidday()) {
            hours = 12;
            lock = true;
        }
        return hours;
    }

    private boolean isMidday() {
        return middayGroupIndex != null && matcher.group(middayGroupIndex) != null;
    }

    private Integer getMidnight() {
        Integer hours = null;
        if (isMidnight()) {
            hours = 24;
            lock = true;
        }
        return hours;
    }

}
