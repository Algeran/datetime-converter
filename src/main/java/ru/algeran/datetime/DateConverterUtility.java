package ru.algeran.datetime;

import java.time.LocalDate;
import java.util.regex.Matcher;

@FunctionalInterface
interface DateConverterUtility {

    LocalDate convertDate(Matcher matcher);
}
