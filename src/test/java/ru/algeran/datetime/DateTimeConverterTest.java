package ru.algeran.datetime;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeConverterTest {

    private final DateTimeConverter dateTimeConverter = new DateTimeConverter();

    @Test
    public void dateTimeConverterShouldCorrectConvertTemplateToLocalDateTime() {
        LocalDateTime nowDateTime = LocalDateTime.now().withNano(0).withSecond(0);
        LocalDateTime expected = LocalDateTime.of(nowDateTime.getYear(), 10, 25, 6, 30);
        if (expected.isBefore(nowDateTime)) {
            expected = expected.plus(1, ChronoUnit.YEARS);
        }
        Optional<LocalDateTime> result = dateTimeConverter.convert("в 6 30 25 октября");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isIn(expected, expected.withHour(18));

        result = dateTimeConverter.convert("в 6-30 25 октября");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isIn(expected, expected.withHour(18));

        result = dateTimeConverter.convert("в 6 30 вечера 25 октября");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(expected.withHour(18));

        result = dateTimeConverter.convert("в 6 30 утра 25 октября");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(expected);

        result = dateTimeConverter.convert("3 утра 2 января 2018");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 1, 2, 3, 0));

        result = dateTimeConverter.convert("половина 5 утра 2 января 2018");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 1, 2, 4, 30));

        result = dateTimeConverter.convert("полдень 2 января 2018");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 1, 2, 12, 0));

        result = dateTimeConverter.convert("полночь 2 января 2018");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 1, 2, 0, 0));

        result = dateTimeConverter.convert("в 13 53 3 февраля 18-го");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 2, 3, 13, 53));

        result = dateTimeConverter.convert("в 13-53 3 февраля 18-го");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 2, 3, 13, 53));

        result = dateTimeConverter.convert("6 вечера 3 февраля 18-го");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 2, 3, 18, 0));

        result = dateTimeConverter.convert("3 утра 3 февраля 18-го");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(LocalDateTime.of(2018, 2, 3, 3, 0));

        result = dateTimeConverter.convert("в 18 30");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(nowDateTime.withHour(18).withMinute(30));

        result = dateTimeConverter.convert("завтра в 18 30");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(nowDateTime.withHour(18).withMinute(30).plus(1, ChronoUnit.DAYS));

        result = dateTimeConverter.convert("вчера в 18 30");
        assertThat(result)
                .as("Определение даты и времени")
                .isPresent()
                .get()
                .isEqualTo(nowDateTime.withHour(18).withMinute(30).minus(1, ChronoUnit.DAYS));

    }
}