package ru.algeran.datetime;


import ru.algeran.datetime.numbers.NumbersConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeConverter implements Converter {

    private static final NumbersConverter NUMBERS_CONVERTER = new NumbersConverter();

    @Override
    public Optional<LocalDateTime> convert(final String template) {
        String templateWithNumbers = NUMBERS_CONVERTER.parseNumbersInTemplate(template);

        Pattern pattern;
        Matcher matcher;
        LocalDate date = null;
        LocalTime time = null;
        for (String timeTemplate : TimeConverter.getTimeMarkers().keySet()) {
            pattern = Pattern.compile(timeTemplate, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(templateWithNumbers);
            if (matcher.find()) {
                TimeConverterUtility timeConverter = TimeConverter.getTimeMarkers().get(timeTemplate);
                try {
                    time = timeConverter.convertTime(matcher);
                    templateWithNumbers = templateWithNumbers.substring(0, matcher.start()) + templateWithNumbers.substring(matcher.end());
                } catch (Exception e) {
                    // log
                }
                break;
            }
        }
        for (String dateTemplate : DateConverter.getDateMarkers().keySet()) {
            pattern = Pattern.compile(dateTemplate, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(templateWithNumbers);
            if (matcher.find()) {
                DateConverterUtility dateConverter = DateConverter.getDateMarkers().get(dateTemplate);
                try {
                    date = dateConverter.convertDate(matcher);
                } catch (Exception e) {
                    // log
                }
                break;
            }
        }
        LocalDateTime dateTime = null;
        if (date != null || time != null) {
            date = date != null ? date : LocalDate.now();
            time = time != null ? time : LocalTime.now();
            dateTime = LocalDateTime.of(date, time);
        }
        return Optional.ofNullable(dateTime);
    }

}
