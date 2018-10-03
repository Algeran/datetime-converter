package ru.algeran.datetime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DateConverterTest {

    private final DateConverter dateConverter = new DateConverter();

    @Test
    public void dateConverterShouldCorrectConvertTemplateToLocalDate() {

        Optional<LocalDate> result = dateConverter.convert("через год");
        LocalDate nowDate = LocalDate.now();
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365, ChronoUnit.DAYS));

        result = dateConverter.convert("через пол года");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(183, ChronoUnit.DAYS));

        result = dateConverter.convert("через один год");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365, ChronoUnit.DAYS));

        result = dateConverter.convert("через два года");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 * 2, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять лет");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 * 5, ChronoUnit.DAYS));

        result = dateConverter.convert("через один с половиной год");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 183, ChronoUnit.DAYS));

        result = dateConverter.convert("через два с половиной года");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 * 2 + 183, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять с половиной лет");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 * 5 + 183, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и месяц");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 30, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и 2 месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 2 * 30, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пять месяцов");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 5 * 30, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пол месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 15, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и половину месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 15, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и неделю");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и 2 недели");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 7 * 2, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пять недель");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 5 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пол недели");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и половину недели");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через год, 2 месяца и 5 недель");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 2 * 30 + 5 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и день");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и 2 дня");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 2, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пять дней");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 5, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через год и половину дня");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через год, 2 месяца, 5 недель и пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... год ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(365 + 2 * 30 + 5 * 7 + 1, ChronoUnit.DAYS));

        /* -------------------------------------------------------------------------------------------- */

        result = dateConverter.convert("через месяц");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30, ChronoUnit.DAYS));

        result = dateConverter.convert("через пол месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(15, ChronoUnit.DAYS));

        result = dateConverter.convert("через один месяц");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30, ChronoUnit.DAYS));

        result = dateConverter.convert("через два месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2 * 30, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять месяцев");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(5 * 30, ChronoUnit.DAYS));

        result = dateConverter.convert("через один с половиной месяц");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 15, ChronoUnit.DAYS));

        result = dateConverter.convert("через два с половиной месяца");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2 * 30 + 15, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять с половиной месяцев");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(5 * 30 + 15, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и неделю");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и 2 недели");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 2 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и пять недель");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 5 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и пол недели");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и половину недели");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и день");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и 2 дня");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 2, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и пять дней");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 5, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц и половину дня");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через месяц, 5 недель и пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... месяц ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(30 + 5 * 7 + 1, ChronoUnit.DAYS));

        /* ---------------------------------------------------------------------------------- */

        result = dateConverter.convert("через неделю");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7, ChronoUnit.DAYS));

        result = dateConverter.convert("через пол недели");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(3, ChronoUnit.DAYS));

        result = dateConverter.convert("через одну неделю");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7, ChronoUnit.DAYS));

        result = dateConverter.convert("через две недели");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять недель");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(5 * 7, ChronoUnit.DAYS));

        result = dateConverter.convert("через одну с половиной неделю");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через две с половиной недели");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2 * 7 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять с половиной недель");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(5 * 7 + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("через неделю и день");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через неделю и 2 дня");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 2, ChronoUnit.DAYS));

        result = dateConverter.convert("через неделю и пять дней");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 5, ChronoUnit.DAYS));

        result = dateConverter.convert("через неделю и пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("через неделю и половину дня");
        assertThat(result)
                .as("Определение даты формата 'через ... неделю ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 + 1, ChronoUnit.DAYS));

        /* ------------------------------------------------------------------------------- */

        result = dateConverter.convert("через день");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(1, ChronoUnit.DAYS));

        result = dateConverter.convert("через пол дня");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(1, ChronoUnit.DAYS));

        result = dateConverter.convert("через один день");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(1, ChronoUnit.DAYS));

        result = dateConverter.convert("через два дня");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять дней");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(5, ChronoUnit.DAYS));

        result = dateConverter.convert("через один с половиной день");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(2, ChronoUnit.DAYS));

        result = dateConverter.convert("через два с половиной дня");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(3, ChronoUnit.DAYS));

        result = dateConverter.convert("через пять с половиной дней");
        assertThat(result)
                .as("Определение даты формата 'через ... день ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(6, ChronoUnit.DAYS));

        /* -------------------------------------------------------------------------------------- */

        int dayOfWeekCorrective = 7 - nowDate.getDayOfWeek().getValue();
        result = dateConverter.convert("в следующий понедельник");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующий пн");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 1, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующий вторник");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 2, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующий вт");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 2, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую среду");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую ср");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 3, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующий четверг");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 4, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующий чт");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 4 , ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую пятницу");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 5, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую пт");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 5, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую субботу");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 6, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующую сб");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 6, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующее воскресенье");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 7, ChronoUnit.DAYS));

        result = dateConverter.convert("в следующее вск");
        assertThat(result)
                .as("Определение даты формата 'следующий ...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekCorrective + 7, ChronoUnit.DAYS));

        /* ------------------------------------------------------------------------------------- */

        int dayOfWeekNow = nowDate.getDayOfWeek().getValue();
        result = dateConverter.convert("в понедельник");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7 , ChronoUnit.DAYS));

        result = dateConverter.convert("в пн");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(7, ChronoUnit.DAYS));

        result = dateConverter.convert("во вторник");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 2 ? 7 : 1, ChronoUnit.DAYS));

        result = dateConverter.convert("во вт");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 2 ? 7 : 1, ChronoUnit.DAYS));

        result = dateConverter.convert("в среду");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 3 ? 7 : 3 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в ср");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 3 ? 7 : 3 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в четверг");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 4 ? 7 : 4 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в чт");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 4 ? 7 : 4 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в пятницу");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 5 ? 7 : 5 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в пт");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 5 ? 7 : 5 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в субботу");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 6 ? 7 : 6 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в сб");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 6 ? 7 : 6 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в воскресенье");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 7 ? 7 : 7 - dayOfWeekNow, ChronoUnit.DAYS));

        result = dateConverter.convert("в вск");
        assertThat(result)
                .as("Определение даты формата 'в пн/вт/cр...'")
                .isPresent()
                .get()
                .isEqualTo(nowDate.plus(dayOfWeekNow >= 7 ? 7 : 7 - dayOfWeekNow, ChronoUnit.DAYS));

        /* ---------------------------------------------------------------------------------------- */

        LocalDate expected = LocalDate.of(nowDate.getYear(), 12, 25);
        if (expected.isBefore(nowDate)) {
            expected = expected.plus(1, ChronoUnit.YEARS);
        }
        result = dateConverter.convert("25 12");
        assertThat(result)
                .as("Определение даты формата 'dd MM yyyy'")
                .isPresent()
                .get()
                .isEqualTo(expected);

        result = dateConverter.convert("25 8 2018");
        assertThat(result)
                .as("Определение даты формата 'dd MM yyyy'")
                .isPresent()
                .get()
                .isEqualTo(LocalDate.of(2018, 8, 25));

        result = dateConverter.convert("01.08.2018");
        assertThat(result)
                .as("Определение даты формата 'dd MM yyyy'")
                .isPresent()
                .get()
                .isEqualTo(LocalDate.of(2018, 8, 1));

        result = dateConverter.convert("01-08-2018");
        assertThat(result)
                .as("Определение даты формата 'dd MM yyyy'")
                .isPresent()
                .get()
                .isEqualTo(LocalDate.of(2018, 8, 1));

        result = dateConverter.convert("01.08.18");
        assertThat(result)
                .as("Определение даты формата 'dd MM yyyy'")
                .isPresent()
                .get()
                .isEqualTo(LocalDate.of(2018, 8, 1));

    }
}