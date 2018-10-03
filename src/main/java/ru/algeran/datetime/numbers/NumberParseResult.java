package ru.algeran.datetime.numbers;

import java.util.Objects;

class NumberParseResult implements Comparable<NumberParseResult> {

    private Integer number;
    private int indexStart;
    private int indexEnd;
    private Case numberCase;
    private NumberFormat numberFormat;

    NumberParseResult(Case numberCase, NumberFormat numberFormat) {
        this.numberCase = numberCase;
        this.numberFormat = numberFormat;
    }

    NumberParseResult(NumberParseResult numberParseResult) {
        this.number = numberParseResult.getNumber();
        this.indexStart = numberParseResult.getIndexStart();
        this.indexEnd = numberParseResult.getIndexEnd();
        this.numberCase = numberParseResult.getNumberCase();
        this.numberFormat = numberParseResult.getNumberFormat();
    }

    NumberParseResult(Integer number, Case numberCase, NumberFormat numberFormat) {
        this.number = number;
        this.numberCase = numberCase;
        this.numberFormat = numberFormat;
    }

    Integer getNumber() {
        return number;
    }

    void setNumber(Integer number) {
        this.number = number;
    }

    int getIndexStart() {
        return indexStart;
    }

    void setIndexStart(int indexStart) {
        this.indexStart = indexStart;
    }

    int getIndexEnd() {
        return indexEnd;
    }

    void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

    NumberFormat getNumberFormat() {
        return numberFormat;
    }

    Case getNumberCase() {
        return numberCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberParseResult)) return false;
        NumberParseResult that = (NumberParseResult) o;
        return getIndexStart() == that.getIndexStart() &&
                getIndexEnd() == that.getIndexEnd() &&
                Objects.equals(getNumber(), that.getNumber()) &&
                getNumberCase() == that.getNumberCase() &&
                getNumberFormat() == that.getNumberFormat();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumber(), getIndexStart(), getIndexEnd(), getNumberCase(), getNumberFormat());
    }

    @Override
    public int compareTo(NumberParseResult o) {
        return this.indexStart - o.indexStart;
    }

    @Override
    public String toString() {
        return "NumberParseResult{" +
                "number=" + number +
                ", indexStart=" + indexStart +
                ", indexEnd=" + indexEnd +
                ", numberFormat=" + numberFormat +
                '}';
    }
}
