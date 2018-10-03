package ru.algeran.datetime.numbers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumbersConverterTest {

    private final NumbersConverter numbersConverter = new NumbersConverter();

    @Test
    public void numberConverterShouldCorrectDefineNumbersInTemplate() {
        String result = numbersConverter.parseNumbersInTemplate("что то там семнадцатого что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 17 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там ста пятнацати что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 115 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там сто пятнацать что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 115 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там сто пять что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 105 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там сто двадцать пять что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 125 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там сто двадцать что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 120 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там двадцать что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 20 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там двадцать пять что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 25 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там пятнадцать что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 15 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там семь что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 7 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там без двадцати семь что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там без 20 7 что то там");

        result = numbersConverter.parseNumbersInTemplate("четыре тридцать двадцать пятого сентября");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("4 30 25 сентября");

        result = numbersConverter.parseNumbersInTemplate("что то там без двадцати трех семь вечера одиннадцатого мая что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там без 23 7 вечера 11 мая что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там восемь что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там 8 что то там");

        result = numbersConverter.parseNumbersInTemplate("что то там полтретьего полчетвертого половина восьмого пол-восьмого что то там");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("что то там пол3 пол4 половина 8 пол-8 что то там");

        result = numbersConverter.parseNumbersInTemplate("две тысячи восемнадцатый год");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("2018 год");

        result = numbersConverter.parseNumbersInTemplate("в полвторого");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в пол2");

        result = numbersConverter.parseNumbersInTemplate("в пол-второго");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в пол-2");

        result = numbersConverter.parseNumbersInTemplate("в пол второго");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в пол 2");

        result = numbersConverter.parseNumbersInTemplate("через сто двадцать пять минут");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("через 125 минут");

        result = numbersConverter.parseNumbersInTemplate("в четырнадцать тридцать");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в 14 30");

        result = numbersConverter.parseNumbersInTemplate("в четырнадцать часов тридцать минут");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в 14 часов 30 минут");

        result = numbersConverter.parseNumbersInTemplate("двадцать пятого двенадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("25 12");

        result = numbersConverter.parseNumbersInTemplate("двадцать пятого восьмого две тысячи восемнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("25 8 2018");

        result = numbersConverter.parseNumbersInTemplate("в шесть тридцать двадцать пятого октября");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("в 6 30 25 октября");
    }
}