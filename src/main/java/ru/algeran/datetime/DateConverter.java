package ru.algeran.datetime;


import ru.algeran.datetime.numbers.NumbersConverter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateConverter implements Converter {

    private static final Map<String, DateConverterUtility> DATE_MARKERS = new LinkedHashMap<>();
    private static final NumbersConverter NUMBERS_CONVERTER = new NumbersConverter();

    static {
        DATE_MARKERS.put("через (((\\d{1,3})( с половиной)? )|(пол([- ])?))?(г(од(а)?)?|лет)" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(м(ес(яц(а|[ео]в)?)?)?))?" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(нед(ел[яьию])?))?" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(д|дн(я|ей)?|день))?", matcher -> {
            DateUtility dateUtility = new DateUtility(matcher)
                    .year(5, 3, 4, 7)
                    .month(15, 13, 14, 17)
                    .week(26, 24, 25, 28)
                    .day(35, 33, 34, 37);
            return dateUtility.generateLocalDate();
        });
        DATE_MARKERS.put("через (((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(м(ес(яц(а|[ео]в)?)?)?))" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(нед(ел[яьию])?))?" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(д|дн(я|ей)?|день))?", matcher -> {
            DateUtility dateUtility = new DateUtility(matcher)
                    .month(5, 3, 4, 7)
                    .week(16, 14, 15, 18)
                    .day(25, 23, 24, 27);
            return dateUtility.generateLocalDate();
        });
        DATE_MARKERS.put("через (((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(нед(ел[яьию])?))" +
                "(( и|,)? ((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(д|дн(я|ей)?|день))?", matcher -> {
            DateUtility dateUtility = new DateUtility(matcher)
                    .week(5, 3, 4, 7)
                    .day(14, 12, 13, 16);
            return dateUtility.generateLocalDate();
        });
        DATE_MARKERS.put("через (((\\d{1,3})( с половиной)? |(пол(овин[ауы])?[- ]))?(д|дн(я|ей)?|день))", matcher -> {
            DateUtility dateUtility = new DateUtility(matcher)
                    .day(5, 3, 4, 7);
            return dateUtility.generateLocalDate();
        });
        DATE_MARKERS.put("следующ(ую|ий|ая|ей|ее) ((пн|пон(ед)?)" +
                "|(вт|втор(ник)?)" +
                "|(ср(ед[ау])?)" +
                "|(чт|чет(верг)?)" +
                "|(пт|пят(ниц)?)" +
                "|(сб|суб)" +
                "|(вс|вск|воскр))", matcher -> {
            String monday = matcher.group(3);
            String tuesday = matcher.group(5);
            String wednesday = matcher.group(7);
            String thursday = matcher.group(9);
            String friday = matcher.group(11);
            String saturday = matcher.group(13);
            String sunday = matcher.group(14);

            int dayToAdd = 0;
            int dayOfWeekNow = LocalDate.now().getDayOfWeek().getValue();
            dayToAdd += 7 - dayOfWeekNow;
            if (monday != null) {
                dayToAdd += 1;
            } else if (tuesday != null) {
                dayToAdd += 2;
            } else if (wednesday != null) {
                dayToAdd += 3;
            } else if (thursday != null) {
                dayToAdd += 4;
            } else if (friday != null) {
                dayToAdd += 5;
            } else if (saturday != null) {
                dayToAdd += 6;
            } else if (sunday != null) {
                dayToAdd += 7;
            }

            return LocalDate.now().plus(dayToAdd, ChronoUnit.DAYS);
        });
        DATE_MARKERS.put("( |^)((пн|пон(ед)?)" +
                "|(вт|втор(ник)?)" +
                "|(ср(ед[ау])?)" +
                "|(чт|чет(верг)?)" +
                "|(пт|пят(ниц)?)" +
                "|(сб|суб)" +
                "|(вс|вск|воскр))", matcher -> {
            String monday = matcher.group(3);
            String tuesday = matcher.group(5);
            String wednesday = matcher.group(7);
            String thursday = matcher.group(9);
            String friday = matcher.group(11);
            String saturday = matcher.group(13);
            String sunday = matcher.group(14);

            int dayToAdd = 0;
            int dayOfWeekNow = LocalDate.now().getDayOfWeek().getValue();
            if (monday != null) {
                dayToAdd += 7;
            } else if (tuesday != null) {
                if (dayOfWeekNow >= 2) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 1;
                }
            } else if (wednesday != null) {
                if (dayOfWeekNow >= 3) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 3 - dayOfWeekNow;
                }
            } else if (thursday != null) {
                if (dayOfWeekNow >= 4) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 4 - dayOfWeekNow;
                }
            } else if (friday != null) {
                if (dayOfWeekNow >= 5) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 5 - dayOfWeekNow;
                }
            } else if (saturday != null) {
                if (dayOfWeekNow >= 6) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 6 - dayOfWeekNow;
                }
            } else if (sunday != null) {
                if (dayOfWeekNow >= 7) {
                    dayToAdd += 7;
                } else {
                    dayToAdd += 7 - dayOfWeekNow;
                }
            }

            return LocalDate.now().plus(dayToAdd, ChronoUnit.DAYS);
        });
        DATE_MARKERS.put("(\\d{1,2})([- ](о)?[гв]о)?" +
                "(\\.|[- /|])(" +
                "(\\d{1,2})" +
                "|(янв([ао]р[ьяе])?)" +
                "|(фев(р)?([ао]л[ьяе])?)" +
                "|(мар(т([ае])?)?)" +
                "|(апр(ел[яие])?)" +
                "|(ма[йияе])" +
                "|(июн([ьяе])?)" +
                "|(июл([ьяе])?)" +
                "|(авг(уст(ае)?)?)" +
                "|(сент([еяи]б(ь)р[ьяе])?)" +
                "|(окт([яе]б(ь)?р[ьяе])?)" +
                "|(но(я/йа)б(р[ьяе])?)" +
                "|(дек([оа]бр[ьяе])?))" +
                "((\\.|[- /|])(\\d{2,4}))?", matcher -> {
            int day = Integer.parseInt(matcher.group(1));
            int month = -1;
            String monthGroup = matcher.group(6);
            String january = matcher.group(7);
            String february = matcher.group(9);
            String march = matcher.group(12);
            String april = matcher.group(15);
            String may = matcher.group(17);
            String june = matcher.group(18);
            String july = matcher.group(20);
            String august = matcher.group(22);
            String september = matcher.group(25);
            String october = matcher.group(28);
            String november = matcher.group(31);
            String december = matcher.group(34);
            if (monthGroup != null) {
                month = Integer.parseInt(monthGroup);
            } else if (january != null) {
                month = 1;
            } else if (february != null) {
                month = 2;
            } else if (march != null) {
                month = 3;
            } else if (april != null) {
                month = 4;
            } else if (may != null) {
                month = 5;
            } else if (june != null) {
                month = 6;
            } else if (july != null) {
                month = 7;
            } else if (august != null) {
                month = 8;
            } else if (september != null) {
                month = 9;
            } else if (october != null) {
                month = 10;
            } else if (november != null) {
                month = 11;
            } else if (december != null) {
                month = 12;
            }
            LocalDate nowDate = LocalDate.now();
            String yearGroup = matcher.group(38);
            boolean lock = true;
            if (yearGroup == null || yearGroup.isEmpty()) {
                yearGroup = String.valueOf(nowDate.getYear());
                lock = false;
            } else if (yearGroup.length() == 2) {
                yearGroup = String.valueOf(nowDate.getYear()).substring(0, 2) + yearGroup;
            } else if (yearGroup.length() == 3) {
                yearGroup = String.valueOf(nowDate.getYear()).substring(0, 3) + yearGroup;
            }
            int year = Integer.parseInt(yearGroup);
            LocalDate date = LocalDate.of(year, month, day);
            if (date.isBefore(nowDate) && !yearGroup.isEmpty() && !lock) {
                date = date.withYear(nowDate.getYear() + 1);
            }
            return date;
        });
        DATE_MARKERS.put("завтра", matcher -> LocalDate.now().plus(1, ChronoUnit.DAYS));
        DATE_MARKERS.put("вчера", matcher -> LocalDate.now().minus(1, ChronoUnit.DAYS));
        DATE_MARKERS.put("послезавтра", matcher -> LocalDate.now().plus(2, ChronoUnit.DAYS));
        DATE_MARKERS.put("позавчера", matcher -> LocalDate.now().minus(2, ChronoUnit.DAYS));
    }

    @Override
    public Optional<LocalDate> convert(final String template) {
        String templateWithNumbers = NUMBERS_CONVERTER.parseNumbersInTemplate(template);
        Pattern pattern;
        Matcher matcher;
        for (String dateTemplate : DATE_MARKERS.keySet()) {
            pattern = Pattern.compile(dateTemplate, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(templateWithNumbers);
            if (matcher.find()) {
                DateConverterUtility dateConverter = DATE_MARKERS.get(dateTemplate);
                return Optional.of(dateConverter.convertDate(matcher));
            }
        }
        return Optional.empty();
    }

    static Map<String, DateConverterUtility> getDateMarkers() {
        return DATE_MARKERS;
    }
}
