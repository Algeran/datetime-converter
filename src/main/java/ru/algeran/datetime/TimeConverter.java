package ru.algeran.datetime;


import ru.algeran.datetime.numbers.NumbersConverter;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeConverter implements Converter {

    private static final Map<String, TimeConverterUtility> TIME_MARKERS = new LinkedHashMap<>();
    private static final NumbersConverter NUMBERS_CONVERTER = new NumbersConverter();

    static {
        TIME_MARKERS.put("бе[зс][ \\-](\\d{1,3})[ \\-]((м[ие]н(ут(ы)?)?)[ \\-])?((((\\d{1,2})( час(а|ов)?)?)([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)|(пол(ден(ь)?|дня))|(полноч([ьи])?))?", matcher -> {
            int timeSubtraction = Integer.parseInt(matcher.group(1));

            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    9,
                    21,
                    24,
                    14,
                    20,
                    16,
                    18);
            LocalTime time = LocalTime.of(dayTimeUtility.evaluateHours() - 1, 60 - timeSubtraction);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("бе[зс][ \\-]четверт(и|ь)[ \\-]((м[ие]н(ут(ы)?)?)[ \\-])?((((\\d{1,2})( час(а|ов)?)?)([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)|(пол(ден(ь)?|дня))|(полноч([ьи])?))?", matcher -> {
            int timeSubtraction = 15;

            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    9,
                    21,
                    24,
                    14,
                    20,
                    16,
                    18);
            LocalTime time = LocalTime.of(dayTimeUtility.evaluateHours() - 1, 60 - timeSubtraction);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("пол(овин[ау])?([ \\-])?((\\d{1,2})([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    4,
                    null,
                    null,
                    7,
                    13,
                    9,
                    11);
            LocalTime time = LocalTime.of(dayTimeUtility.evaluateHours() - 1, 30);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("четверт(ь|и)([ \\-])?((\\d{1,2})([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    4,
                    null,
                    null,
                    7,
                    13,
                    9,
                    11);
            LocalTime time = LocalTime.of(dayTimeUtility.evaluateHours() - 1, 15);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("(пол(ден(ь)?|дня))|(полноч([ьи])?)", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    null,
                    1,
                    4,
                    null,
                    null,
                    null,
                    null);
            return dayTimeUtility.isMidnight() ? LocalTime.MIDNIGHT : LocalTime.NOON;
        });
        TIME_MARKERS.put("через (\\d{1,3})?( )?((час(ов|а)?)|(мин(ут(ы|ов|ок)?)?))( (и )?(\\d{1,3})?( )?(мин(ут(ы|ов|ок)?)?)?)?", matcher -> {
            TimeUtility timeUtility = new TimeUtility(matcher,
                    1,
                    4,
                    11);
            int minutesToAdd = timeUtility.getMinutesToAdd();
            return LocalTime.now().withNano(0).withSecond(0).plus(minutesToAdd, ChronoUnit.MINUTES);
        });
        TIME_MARKERS.put("(\\d{1,2})( час(а|ов)?)[ :\\-](\\d{1,2})( мин(ут(ы|у|ов|ок)?)?)([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    1,
                    null,
                    null,
                    10,
                    16,
                    12,
                    14);
            int hours = dayTimeUtility.evaluateHours();
            int minutes = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
            LocalTime time = LocalTime.of(hours, minutes);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("(\\d{1,2})[ :\\-](\\d{1,2})([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    1,
                    null,
                    null,
                    5,
                    11,
                    7,
                    9);
            int hours = dayTimeUtility.evaluateHours();
            int minutes = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
            LocalTime time = LocalTime.of(hours, minutes);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("час[ :\\-](\\d{1,2})( мин(ут(ы|у|ов|ок)?)?)?([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    null,
                    null,
                    null,
                    7,
                    13,
                    9,
                    11);
            dayTimeUtility.setDirectHours(1);
            int hours = dayTimeUtility.evaluateHours();
            int minutes = matcher.group(1) != null ? Integer.parseInt(matcher.group(1)) : 0;
            LocalTime time = LocalTime.of(hours, minutes);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("(\\d{1,2})( час(а|ов)?)?([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    1,
                    null,
                    null,
                    6,
                    12,
                    8,
                    10);
            int hours = dayTimeUtility.evaluateHours();
            LocalTime time = LocalTime.of(hours, 0);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
        TIME_MARKERS.put("час([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?", matcher -> {
            DayTimeUtility dayTimeUtility = new DayTimeUtility(matcher,
                    null,
                    null,
                    null,
                    3,
                    9,
                    5,
                    7);
            dayTimeUtility.setDirectHours(1);
            LocalTime time = LocalTime.of(dayTimeUtility.evaluateHours(), 0);
            if (dayTimeUtility.isNotLocked()) {
                time = correctTime(time);
            }
            return time;
        });
    }

    @Override
    public Optional<LocalTime> convert(final String template) {
        String templateWithNumbers = NUMBERS_CONVERTER.parseNumbersInTemplate(template);
        Pattern pattern;
        Matcher matcher;
        for (String timeTemplate : TIME_MARKERS.keySet()) {
            pattern = Pattern.compile(timeTemplate, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(templateWithNumbers);
            if (matcher.find()) {
                TimeConverterUtility timeConverter = TIME_MARKERS.get(timeTemplate);
                return Optional.of(timeConverter.convertTime(matcher));
            }
        }
        return Optional.empty();
    }

    static Map<String, TimeConverterUtility> getTimeMarkers() {
        return TIME_MARKERS;
    }

    private static LocalTime correctTime(LocalTime time) {
        if (time.isBefore(LocalTime.now())) {
            LocalTime newTime = time.plus(12L, ChronoUnit.HOURS);
            if (newTime.isAfter(LocalTime.now())) {
                time = newTime;
            }
        }
        return time;
    }
}
