package ru.algeran.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;

class DateUtility {
    private int daysToAdd = 0;

    private final Matcher matcher;
    private Integer halfYear;
    private Integer yearGroup;
    private Integer sumHalfYear;
    private Integer yearMarker;
    private Integer halfMonth;
    private Integer monthGroup;
    private Integer sumHalfMonth;
    private Integer monthMarker;
    private Integer halfWeek;
    private Integer weekGroup;
    private Integer sumHalfWeek;
    private Integer weekMarker;
    private Integer halfDay;
    private Integer dayGroup;
    private Integer sumHalfDay;
    private Integer dayMarker;

    DateUtility(Matcher matcher) {
        this.matcher = matcher;
    }

    DateUtility year(
            Integer halfYear,
            Integer yearGroup,
            Integer sumHalfYear,
            Integer yearMarker
    ) {

        this.halfYear = halfYear;
        this.yearGroup = yearGroup;
        this.sumHalfYear = sumHalfYear;
        this.yearMarker = yearMarker;
        return this;
    }

    DateUtility month(
            Integer halfMonth,
            Integer monthGroup,
            Integer sumHalfMonth,
            Integer monthMarker
    ) {

        this.halfMonth = halfMonth;
        this.monthGroup = monthGroup;
        this.sumHalfMonth = sumHalfMonth;
        this.monthMarker = monthMarker;
        return this;
    }

    DateUtility week(
            Integer halfWeek,
            Integer weekGroup,
            Integer sumHalfWeek,
            Integer weekMarker
    ) {
        this.halfWeek = halfWeek;
        this.weekGroup = weekGroup;
        this.sumHalfWeek = sumHalfWeek;
        this.weekMarker = weekMarker;
        return this;
    }

    DateUtility day(
            Integer halfDay,
            Integer dayGroup,
            Integer sumHalfDay,
            Integer dayMarker
    ) {
        this.halfDay = halfDay;
        this.dayGroup = dayGroup;
        this.sumHalfDay = sumHalfDay;
        this.dayMarker = dayMarker;
        return this;
    }

    LocalDate generateLocalDate() {
        yearAdjustment();
        monthAdjustment();
        weekAdjustment();
        dayAdjustment();
        return LocalDate.now().plus(daysToAdd, ChronoUnit.DAYS);
    }

    private void yearAdjustment() {
        if (isHalfYearExists()
                || isSumHalfYearExists()) {
            daysToAdd += 183;
        }
        if (isYearGroupExists()) {
            daysToAdd += Integer.parseInt(matcher.group(yearGroup)) * 365;
        }
        if (!isHalfYearExists()
                && !isSumHalfYearExists()
                && !isYearGroupExists()
                && isYearMarkerExists()) {
            daysToAdd += 365;
        }
    }

    private boolean isHalfYearExists() {
        return halfYear != null && matcher.group(halfYear) != null;
    }

    private boolean isSumHalfYearExists() {
        return sumHalfYear != null && matcher.group(sumHalfYear) != null;
    }

    private boolean isYearGroupExists() {
        return yearGroup != null && matcher.group(yearGroup) != null;
    }

    private boolean isYearMarkerExists() {
        return yearMarker != null && matcher.group(yearMarker) != null;
    }

    private void monthAdjustment() {
        if (isHalfMonthExists()
                || isSumHalfMonthExists()) {
            daysToAdd += 15;
        }
        if (isMonthGroupExists()) {
            daysToAdd += Integer.parseInt(matcher.group(monthGroup)) * 30;
        }
        if (!isHalfMonthExists()
                && !isSumHalfMonthExists()
                && !isMonthGroupExists()
                && isMonthMarkerExists()) {
            daysToAdd += 30;
        }
    }

    private boolean isHalfMonthExists() {
        return halfMonth != null && matcher.group(halfMonth) != null;
    }

    private boolean isSumHalfMonthExists() {
        return sumHalfMonth != null && matcher.group(sumHalfMonth) != null;
    }

    private boolean isMonthGroupExists() {
        return monthGroup != null && matcher.group(monthGroup) != null;
    }

    private boolean isMonthMarkerExists() {
        return monthMarker != null && matcher.group(monthMarker) != null;
    }

    private void weekAdjustment() {
        if (isHalfWeekExists()
                || isSumHalfWeekExists()) {
            daysToAdd += 3;
        }
        if (isWeekGroupExists()) {
            daysToAdd += Integer.parseInt(matcher.group(weekGroup)) * 7;
        }
        if (!isHalfWeekExists()
                && !isSumHalfWeekExists()
                && !isWeekGroupExists()
                && isWeekMarkerExists()) {
            daysToAdd += 7;
        }
    }

    private boolean isHalfWeekExists() {
        return halfWeek != null && matcher.group(halfWeek) != null;
    }

    private boolean isSumHalfWeekExists() {
        return sumHalfWeek != null && matcher.group(sumHalfWeek) != null;
    }

    private boolean isWeekGroupExists() {
        return weekGroup != null && matcher.group(weekGroup) != null;
    }

    private boolean isWeekMarkerExists() {
        return weekMarker != null && matcher.group(weekMarker) != null;
    }

    private void dayAdjustment() {
        if (isHalfDayExists()
                || isSumHalfDayExists()) {
            daysToAdd += 1;
        }
        if (isDayGroupExists()) {
            daysToAdd += Integer.parseInt(matcher.group(dayGroup));
        }
        if (!isHalfDayExists()
                && !isSumHalfDayExists()
                && !isDayGroupExists()
                && isDayMarkerExists()) {
            daysToAdd += 1;
        }
    }

    private boolean isHalfDayExists() {
        return halfDay != null && matcher.group(halfDay) != null;
    }

    private boolean isSumHalfDayExists() {
        return sumHalfDay != null && matcher.group(sumHalfDay) != null;
    }

    private boolean isDayGroupExists() {
        return dayGroup != null && matcher.group(dayGroup) != null;
    }

    private boolean isDayMarkerExists() {
        return dayMarker != null && matcher.group(dayMarker) != null;
    }
}
