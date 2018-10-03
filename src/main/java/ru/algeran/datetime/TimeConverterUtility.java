package ru.algeran.datetime;

import java.time.LocalTime;
import java.util.regex.Matcher;

@FunctionalInterface
interface TimeConverterUtility {
    LocalTime convertTime(Matcher matcher);
}
