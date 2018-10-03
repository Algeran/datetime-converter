package ru.algeran.datetime;

import org.junit.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeConverterTest {

    private final TimeConverter timeConverter = new TimeConverter();

    @Test
    public void timeConverterShouldCorrectConvertFromTemplates() {
        /* regex : бе[зс][ \\-](\\d{1,3})[ \\-]((м[ие]н(ут(ы)?)?)[ \\-])?((((\\d{1,2})( час(а|ов)?)?)([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)|(пол(ден(ь)?|дня))|(полноч([ьи])?))? */
        Optional<LocalTime> result = timeConverter.convert("без 40 6");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 20), LocalTime.of(17, 20));

        result = timeConverter.convert("без 15 2");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 45), LocalTime.of(13, 45));

        result = timeConverter.convert("без 15 12");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(23, 45), LocalTime.of(11, 45));

        result = timeConverter.convert("без 15 18");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(17, 45));

        result = timeConverter.convert("без 15 минут 12");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(23, 45), LocalTime.of(11, 45));

        result = timeConverter.convert("без 15 минут полдень");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(11, 45));

        result = timeConverter.convert("без 15 минут полночь");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(23, 45));

        result = timeConverter.convert("без 15 минут 6 ночи");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 45));

        result = timeConverter.convert("без 15 минут 6 дня");
        assertThat(result)
                .as("Определение времени формата 'без ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(17, 45));

        /* ----------------------------------------------- */
        /* regex : бе[зс][ \\-]четверт(и|ь)[ \\-]((м[ие]н(ут(ы)?)?)[ \\-])?((((\\d{1,2})( час(а|ов)?)?)([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?)|(пол(ден(ь)?|дня))|(полноч([ьи])?))? */

        result = timeConverter.convert("без четверти 6");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 45), LocalTime.of(17, 45));

        result = timeConverter.convert("без четверти минут 12");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(11, 45), LocalTime.of(23, 45));

        result = timeConverter.convert("без четверти полдень");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(11, 45));

        result = timeConverter.convert("без четверти полночь");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(23, 45));

        result = timeConverter.convert("без четверти 6 ночи");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 45));

        result = timeConverter.convert("без четверти 6 дня");
        assertThat(result)
                .as("Определение времени формата 'без четверти ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(17, 45));

        /* --------------------------------------------------------------- */
        /* regex : пол(овин[ау])?([ \\-])?((\\d{1,2})([ \\-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?) */

        result = timeConverter.convert("половина 6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("пол 6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("пол-6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("пол6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("половина6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("половина-6");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 30), LocalTime.of(17, 30));

        result = timeConverter.convert("пол 6 утра");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 30));

        result = timeConverter.convert("пол 6 ночи");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 30));

        result = timeConverter.convert("пол 6 дня");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(17, 30));

        result = timeConverter.convert("пол 6 вечера");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(17, 30));

        result = timeConverter.convert("пол 8");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(7, 30), LocalTime.of(19, 30));

        result = timeConverter.convert("пол 1");
        assertThat(result)
                .as("Определение времени формата 'половина/пол ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(0, 30), LocalTime.of(12, 30));

        /* ------------------------------------------------------ */
        /* regex : четверт(ь|и)([ \-])?((\d{1,2})([ \-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))?) */

        result = timeConverter.convert("четверть 6");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 15), LocalTime.of(17, 15));

        result = timeConverter.convert("четверть-6");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 15), LocalTime.of(17, 15));

        result = timeConverter.convert("четверть6");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(5, 15), LocalTime.of(17, 15));

        result = timeConverter.convert("четверть 6 утра");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 15));

        result = timeConverter.convert("четверть 6 ночи");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(5, 15));

        result = timeConverter.convert("четверть 6 дня");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(17, 15));

        result = timeConverter.convert("четверть 6 вечера");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(17, 15));

        result = timeConverter.convert("четверть 8");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(7, 15), LocalTime.of(19, 15));

        result = timeConverter.convert("четверть 1");
        assertThat(result)
                .as("Определение времени формата 'четверть ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(0, 15), LocalTime.of(12, 15));

        /* ------------------------------------------------- */
        /* regex : (пол(ден(ь)?|дня))|(полноч([ьи])?) */

        result = timeConverter.convert("полдень");
        assertThat(result)
                .as("Определение времени формата 'полдень'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(12, 0));

        result = timeConverter.convert("полдня");
        assertThat(result)
                .as("Определение времени формата 'полдень'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(12, 0));

        result = timeConverter.convert("полночь");
        assertThat(result)
                .as("Определение времени формата 'полночь'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(0, 0));

        /* ----------------------------------------------------------- */
        /* regex : через (\d{1,3})?( )?((час(ов|а)?)|(мин(ут(ы|ов|ок)?)?))( (и )?(\d{1,3})?( )?(мин(ут(ы|ов|ок)?)?)?)? */

        result = timeConverter.convert("через 5 часов");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0).plus(5, ChronoUnit.HOURS));

        result = timeConverter.convert("через 5 час");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0).plus(5, ChronoUnit.HOURS));

        result = timeConverter.convert("через 5 час 15 минут");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(5, ChronoUnit.HOURS)
                        .plus(15, ChronoUnit.MINUTES)
                );

        result = timeConverter.convert("через 5 час 15 мин");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(5, ChronoUnit.HOURS)
                        .plus(15, ChronoUnit.MINUTES)
                );

        result = timeConverter.convert("через 5 час и 15 минут");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(5, ChronoUnit.HOURS)
                        .plus(15, ChronoUnit.MINUTES)
                );

        result = timeConverter.convert("через 5 мин");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(5, ChronoUnit.MINUTES));

        result = timeConverter.convert("через 5 минут");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(5, ChronoUnit.MINUTES));

        result = timeConverter.convert("через 125 минут");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(125, ChronoUnit.MINUTES));

        result = timeConverter.convert("через час 5 минут");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(65, ChronoUnit.MINUTES));

        result = timeConverter.convert("через час");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(60, ChronoUnit.MINUTES));

        result = timeConverter.convert("через минуту");
        assertThat(result)
                .as("Определение времени формата 'через ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.now().withSecond(0).withNano(0)
                        .plus(1, ChronoUnit.MINUTES));

        /* ----------------------------------------------- */
        /* regex : (\d{1,2})( час(а|ов)?)?[ :\-](\d{1,2})( мин(ут(ы|у|ов|ок)?)?)?([ \-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))? */

        result = timeConverter.convert("в 14 30");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(14, 30));

        result = timeConverter.convert("в 14 часов 30 минут");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(14, 30));

        result = timeConverter.convert("в 2 часа 30 минут");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(2, 30), LocalTime.of(14, 30));

        result = timeConverter.convert("в 1 30");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 30), LocalTime.of(13, 30));

        result = timeConverter.convert("в 1:30");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 30), LocalTime.of(13, 30));

        result = timeConverter.convert("в 1-30");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 30), LocalTime.of(13, 30));

        result = timeConverter.convert("в 7-30 утра");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(7, 30));

        result = timeConverter.convert("в 1-30 дня");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(13, 30));

        result = timeConverter.convert("в 1-30 вечера");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(13, 30));

        result = timeConverter.convert("в 1-30 ночи");
        assertThat(result)
                .as("Определение времени формата '... часов ... минут'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(1, 30));

        /* ----------------------------------------------- */
        /* regex : час[ :\-](\d{1,2})( мин(ут(ы|у|ов|ок)?)?)?([ \-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))? */

        result = timeConverter.convert("в час 30");
        assertThat(result)
                .as("Определение времени формата 'час ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 30), LocalTime.of(13, 30));

        result = timeConverter.convert("в час 30 минут");
        assertThat(result)
                .as("Определение времени формата 'час ... минут'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 30), LocalTime.of(13, 30));

        /* ----------------------------------------- */
        /* regex : (\d{1,2})( час(а|ов)?)?([ \-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))? */

        result = timeConverter.convert("в 7");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(7, 0), LocalTime.of(19, 0));

        result = timeConverter.convert("в 7 утра");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(7, 0));

        result = timeConverter.convert("в 7 ночи");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(7, 0));

        result = timeConverter.convert("в 7 дня");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(19, 0));

        result = timeConverter.convert("в 7 вечера");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(19, 0));

        result = timeConverter.convert("в 7 часов");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(7, 0), LocalTime.of(19, 0));

        result = timeConverter.convert("в 7 часов утра");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(7, 0));

        result = timeConverter.convert("в 7 часов ночи");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(7, 0));

        result = timeConverter.convert("в 7 часов дня");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(19, 0));

        result = timeConverter.convert("в 7 часов вечера");
        assertThat(result)
                .as("Определение времени формата '... часов'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(19, 0));

        /* -------------------------------------------------- */
        /* regex : час([ \-]((утр(а)?)|(вечер(а)?)|(ноч(и)?)|(дня)))? */

        result = timeConverter.convert("в час");
        assertThat(result)
                .as("Определение времени формата 'час ...'")
                .isPresent()
                .get()
                .isIn(LocalTime.of(1, 0), LocalTime.of(13, 0));

        result = timeConverter.convert("в час утра");
        assertThat(result)
                .as("Определение времени формата 'час ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(1, 0));

        result = timeConverter.convert("в час ночи");
        assertThat(result)
                .as("Определение времени формата 'час ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(1, 0));

        result = timeConverter.convert("в час вечера");
        assertThat(result)
                .as("Определение времени формата 'час ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(13, 0));

        result = timeConverter.convert("в час дня");
        assertThat(result)
                .as("Определение времени формата 'час ...'")
                .isPresent()
                .get()
                .isEqualTo(LocalTime.of(13, 0));

    }
}