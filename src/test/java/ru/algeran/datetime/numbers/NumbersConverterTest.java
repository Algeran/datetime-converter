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

        result = numbersConverter.parseNumbersInTemplate("двенадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("12");

        result = numbersConverter.parseNumbersInTemplate("сорок сороковой");
        assertThat(result)
                .as("Проверка распознавания чисел")
                .isEqualTo("40 40");
    }

    @Test
    public void numberConverterShouldCorrectDefineCases() {
        // nominative
        String result = numbersConverter.parseNumbersInTemplate("двадцать первого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать один");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать одна");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать первый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать первая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать первое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        result = numbersConverter.parseNumbersInTemplate("двадцать одного");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцать первого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        //parental
        result = numbersConverter.parseNumbersInTemplate("двадцати один");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцати одна");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцати первый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцати первая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцати первое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцати одного");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21");
        //adjective_nominative
        result = numbersConverter.parseNumbersInTemplate("двадцатый один");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатый одна");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатый первый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатый первая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатый первое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатый одного");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        //adjective_parental
        result = numbersConverter.parseNumbersInTemplate("двадцатого один");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатого одна");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатого первый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатого первая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатого первое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");
        result = numbersConverter.parseNumbersInTemplate("двадцатого одного");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20 1");

        //nominative
        result = numbersConverter.parseNumbersInTemplate("сто двадцать");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        result = numbersConverter.parseNumbersInTemplate("сто двадцати");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        //parental
        result = numbersConverter.parseNumbersInTemplate("ста двадцать");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("ста двадцати");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("120");
        //adjective_nominative
        result = numbersConverter.parseNumbersInTemplate("сотый двадцать");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотый двадцати");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");

        //adjective_parental
        result = numbersConverter.parseNumbersInTemplate("сотого двадцать");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
        result = numbersConverter.parseNumbersInTemplate("сотого двадцати");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("100 20");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_Thousands() {
        //parental
        String result = numbersConverter.parseNumbersInTemplate("двадцать одна тысяча тридцать восемь");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21038");
        result = numbersConverter.parseNumbersInTemplate("двадцать одна тысяча тридцати восьми");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21000 38");
        result = numbersConverter.parseNumbersInTemplate("двадцать одна тысяча тридцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21030");
        result = numbersConverter.parseNumbersInTemplate("двадцать одна тысяча тридцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21030");

        result = numbersConverter.parseNumbersInTemplate("двадцати одной тысячи тридцать восемь");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21000 38");
        result = numbersConverter.parseNumbersInTemplate("двадцати одной тысячи тридцати восьми");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21038");
        result = numbersConverter.parseNumbersInTemplate("двадцати одной тысячи тридцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21000 30");
        result = numbersConverter.parseNumbersInTemplate("двадцати одной тысячи тридцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("21000 30");

        result = numbersConverter.parseNumbersInTemplate("двадцатая тысяча тридцати восьми");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20000 38");
        result = numbersConverter.parseNumbersInTemplate("двадцатая тысяча тридцать восемь");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20000 38");
        result = numbersConverter.parseNumbersInTemplate("двадцатая тысяча тридцатый");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20000 30");
        result = numbersConverter.parseNumbersInTemplate("двадцатая тысяча тридцатого");
        assertThat(result)
                .as("Проверка распознавания чисел с тысячами")
                .isEqualTo("20000 30");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_9() {

        String result = numbersConverter.parseNumbersInTemplate("девят");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девять");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девяти");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девятая");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девятое");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девятый");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девятого");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
        result = numbersConverter.parseNumbersInTemplate("девятой");
        assertThat(result)
                .as("Проверка распознавания чисел 9")
                .isEqualTo("9");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_8() {
        String result = numbersConverter.parseNumbersInTemplate("восем");
        assertThat(result)
                .as("Проверка распознавания чисел 8 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восемь");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восьми");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восьмая");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восьмой");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восьмое");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
        result = numbersConverter.parseNumbersInTemplate("восьмого");
        assertThat(result)
                .as("Проверка распознавания чисел 8")
                .isEqualTo("8");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_7() {
        String result = numbersConverter.parseNumbersInTemplate("сем");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("семь");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("семи");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("седьмой");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("седьмая");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("седьмое");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
        result = numbersConverter.parseNumbersInTemplate("седьмого");
        assertThat(result)
                .as("Проверка распознавания чисел 7")
                .isEqualTo("7");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_6() {
        String result = numbersConverter.parseNumbersInTemplate("шест");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шесть");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шести");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шестой");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шестая");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шестое");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
        result = numbersConverter.parseNumbersInTemplate("шестого");
        assertThat(result)
                .as("Проверка распознавания чисел 6")
                .isEqualTo("6");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_5() {
        String result = numbersConverter.parseNumbersInTemplate("пят");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пять");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пяти");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пятый");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пятое");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пятый");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пятого");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
        result = numbersConverter.parseNumbersInTemplate("пятой");
        assertThat(result)
                .as("Проверка распознавания чисел 5")
                .isEqualTo("5");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_4() {
        String result = numbersConverter.parseNumbersInTemplate("четыре");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четырех");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четвертый");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четвертая");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четвертое");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четвертого");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
        result = numbersConverter.parseNumbersInTemplate("четвертой");
        assertThat(result)
                .as("Проверка распознавания чисел 4")
                .isEqualTo("4");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_3() {
        String result = numbersConverter.parseNumbersInTemplate("три");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("трех");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("третий");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("третья");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("третье");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("третьего");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
        result = numbersConverter.parseNumbersInTemplate("третьей");
        assertThat(result)
                .as("Проверка распознавания чисел 3")
                .isEqualTo("3");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_2() {
        String result = numbersConverter.parseNumbersInTemplate("два");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("две");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("двух");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("второй");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("вторая");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("второе");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
        result = numbersConverter.parseNumbersInTemplate("второго");
        assertThat(result)
                .as("Проверка распознавания чисел 2")
                .isEqualTo("2");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_1() {
        String result = numbersConverter.parseNumbersInTemplate("одна");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("одно");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("одного");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("одной");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("первый");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("первое");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("первая");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("первого");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("первой");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
        result = numbersConverter.parseNumbersInTemplate("один");
        assertThat(result)
                .as("Проверка распознавания чисел 1")
                .isEqualTo("1");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_19() {
        String result = numbersConverter.parseNumbersInTemplate("девятнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
        result = numbersConverter.parseNumbersInTemplate("девятнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 19")
                .isEqualTo("19");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_18() {
        String result = numbersConverter.parseNumbersInTemplate("восемнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
        result = numbersConverter.parseNumbersInTemplate("восемнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 18")
                .isEqualTo("18");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_17() {
        String result = numbersConverter.parseNumbersInTemplate("семнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
        result = numbersConverter.parseNumbersInTemplate("семнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 17")
                .isEqualTo("17");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_16() {
        String result = numbersConverter.parseNumbersInTemplate("шестнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
        result = numbersConverter.parseNumbersInTemplate("шестнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 16")
                .isEqualTo("16");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_15() {
        String result = numbersConverter.parseNumbersInTemplate("пятнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
        result = numbersConverter.parseNumbersInTemplate("пятнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 15")
                .isEqualTo("15");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_14() {
        String result = numbersConverter.parseNumbersInTemplate("четырнадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
        result = numbersConverter.parseNumbersInTemplate("четырнадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 14")
                .isEqualTo("14");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_13() {
        String result = numbersConverter.parseNumbersInTemplate("тринадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
        result = numbersConverter.parseNumbersInTemplate("тринадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 13")
                .isEqualTo("13");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_12() {
        String result = numbersConverter.parseNumbersInTemplate("двенадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
        result = numbersConverter.parseNumbersInTemplate("двенадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 12")
                .isEqualTo("12");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_11() {
        String result = numbersConverter.parseNumbersInTemplate("одинадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
        result = numbersConverter.parseNumbersInTemplate("одиннадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 11")
                .isEqualTo("11");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_10() {
        String result = numbersConverter.parseNumbersInTemplate("десят");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десять");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десяти");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десятая");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десятый");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десятое");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десятого");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
        result = numbersConverter.parseNumbersInTemplate("десятой");
        assertThat(result)
                .as("Проверка распознавания чисел 10")
                .isEqualTo("10");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_20() {
        String result = numbersConverter.parseNumbersInTemplate("двадцат");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцать");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцати");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
        result = numbersConverter.parseNumbersInTemplate("двадцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 20")
                .isEqualTo("20");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_30() {
        String result = numbersConverter.parseNumbersInTemplate("тридцат");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцать");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцати");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцатый");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцатая");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцатое");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцатого");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
        result = numbersConverter.parseNumbersInTemplate("тридцатой");
        assertThat(result)
                .as("Проверка распознавания чисел 30")
                .isEqualTo("30");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_40() {
        String result = numbersConverter.parseNumbersInTemplate("сорок");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
        result = numbersConverter.parseNumbersInTemplate("сорока");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
        result = numbersConverter.parseNumbersInTemplate("сороковой");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
        result = numbersConverter.parseNumbersInTemplate("сороковая");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
        result = numbersConverter.parseNumbersInTemplate("сороковое");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
        result = numbersConverter.parseNumbersInTemplate("сорокового");
        assertThat(result)
                .as("Проверка распознавания чисел 40")
                .isEqualTo("40");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_50() {
        String result = numbersConverter.parseNumbersInTemplate("писят");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесят");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесять");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесяти");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесятый");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесятое");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесятая");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесятого");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
        result = numbersConverter.parseNumbersInTemplate("пятьдесятой");
        assertThat(result)
                .as("Проверка распознавания чисел 50")
                .isEqualTo("50");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_60() {
        String result = numbersConverter.parseNumbersInTemplate("шисят");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестьдесят");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестьдесять");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесяти");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесятый");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесятое");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесятый");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесятого");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
        result = numbersConverter.parseNumbersInTemplate("шестидесятой");
        assertThat(result)
                .as("Проверка распознавания чисел 60")
                .isEqualTo("60");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_70() {
        String result = numbersConverter.parseNumbersInTemplate("семисят");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семсят");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семьсят");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семьдесят");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семьдесять");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесяти");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесятый");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесятое");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесятая");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесятого");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
        result = numbersConverter.parseNumbersInTemplate("семидесятой");
        assertThat(result)
                .as("Проверка распознавания чисел 70")
                .isEqualTo("70");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_80() {
        String result = numbersConverter.parseNumbersInTemplate("восьмисят");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восемисят");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восемисят");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восемьсят");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восемьдесят");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восемьдесять");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесяти");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесятый");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесятое");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесятая");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесятого");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
        result = numbersConverter.parseNumbersInTemplate("восьмидесятой");
        assertThat(result)
                .as("Проверка распознавания чисел 80")
                .isEqualTo("80");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_90() {
        String result = numbersConverter.parseNumbersInTemplate("девяносто");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяноста");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяностый");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяностое");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяностая");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяностого");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
        result = numbersConverter.parseNumbersInTemplate("девяностой");
        assertThat(result)
                .as("Проверка распознавания чисел 90")
                .isEqualTo("90");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_100() {
        String result = numbersConverter.parseNumbersInTemplate("сто");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("ста");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("сотый");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("сотая");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("сотое");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("сотого");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
        result = numbersConverter.parseNumbersInTemplate("сотой");
        assertThat(result)
                .as("Проверка распознавания чисел 100")
                .isEqualTo("100");
    }

    @Test
    public void numberConverter_shouldCorrectDefine_200() {
        String result = numbersConverter.parseNumbersInTemplate("двести");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухста");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухсотый");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухсотое");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухсотая");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухсотого");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
        result = numbersConverter.parseNumbersInTemplate("двухсотой");
        assertThat(result)
                .as("Проверка распознавания чисел 200")
                .isEqualTo("200");
    }
}