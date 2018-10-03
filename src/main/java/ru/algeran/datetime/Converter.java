package ru.algeran.datetime;

import java.time.temporal.Temporal;
import java.util.Optional;

public interface Converter {

    Optional<? extends Temporal> convert(final String template);

}
