package ru.algeran.datetime.numbers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumbersConverter {

    private static final Map<String, NumberParseResult> NUMBERS = new HashMap<>();

    static {
        NUMBERS.put("(^| )(д[еи]в[яе]т(ь|ый))( |$)", new NumberParseResult(9, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(д[еи]в[яе]ти)( |$)", new NumberParseResult(9, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(д[еи]в[яе]т[оа][гв][оа])( |$)", new NumberParseResult(9, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(в[ао]с[еи]м(ь|ой)?)( |$)", new NumberParseResult(8, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(в[ао]с(ь)?ми)( |$)", new NumberParseResult(8, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(в[ао]с(ь)?м[оа][гв][оа])( |$)", new NumberParseResult(8, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(семь)( |$)", new NumberParseResult(7, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(седьмой)( |$)", new NumberParseResult(7, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(семи)( |$)", new NumberParseResult(7, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(се(д|дь)?м[оа][гв][оа])( |$)", new NumberParseResult(7, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(шест(ь|ой))( |$)", new NumberParseResult(6, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(шести)( |$)", new NumberParseResult(6, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(шест[оа][гв][оа])( |$)", new NumberParseResult(6, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(пят(ь|ый))( |$)", new NumberParseResult(5, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(пяти)( |$)", new NumberParseResult(5, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(пят[оа][гв][оа])( |$)", new NumberParseResult(5, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(ч[еяи]тыр[еи])( |$)", new NumberParseResult(4, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(ч[еяи]т(ь)?в[ёео]ртый)( |$)", new NumberParseResult(4, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(ч[еяи]тыр[ёо]х)( |$)", new NumberParseResult(4, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(ч[еяи]т(ь)?в[ёео]рт[оа][гв][оа])( |$)", new NumberParseResult(4, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(три)( |$)", new NumberParseResult(3, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(трет(ь)?ий)( |$)", new NumberParseResult(3, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(трет(ь)?[еи])( |$)", new NumberParseResult(3, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(тр[еёо]х)( |$)", new NumberParseResult(3, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(трет(ь)?[еия][гв][оа])( |$)", new NumberParseResult(3, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(два)( |$)", new NumberParseResult(2, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(вт[ао]рой)( |$)", new NumberParseResult(2, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(дв(ух|е))(?! ты(с([яеи]ч[еи])?|(ш|сч)и))( |$)", new NumberParseResult(2, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(вт[ао]р[оа][гв][оа])( |$)", new NumberParseResult(2, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(од[иы]н)( |$)", new NumberParseResult(1, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(первый)( |$)", new NumberParseResult(1, Case.nominative, NumberFormat.single));
        NUMBERS.put("(^| )(одно(го|й))( |$)", new NumberParseResult(1, Case.parental, NumberFormat.single));
        NUMBERS.put("(^| )(одну)( |$)", new NumberParseResult(1, Case.parental, NumberFormat.single));
        NUMBERS.put("()?(перв([оа][гв][оа]))( |$)", new NumberParseResult(1, Case.other, NumberFormat.single));
        NUMBERS.put("(^| )(дев[яе](т)?на(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(19, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(дев[яе](т)?на(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(19, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(дев[яе](т)?на(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(19, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(в[оа]с[еи]мна(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(18, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(в[оа]с[еи]мна(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(18, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(в[оа]с[еи]мна(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(18, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(с[еи]мна(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(17, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(с[еи]мна(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(17, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(?<!во)(с[еи]мна(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(17, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(ш[еи]с(т)?на(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(16, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(ш[еи]с(т)?на(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(16, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(ш[еи]с(т)?на(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(16, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(п[яе](т)?на(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(15, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(п[яе](т)?на(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(15, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(п[яе](т)?на(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(15, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(ч[яе]тырн[ао](д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(14, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(ч[яе]тырн[ао](д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(14, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(ч[яе]тырн[ао](д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(14, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(тр[ие]на(д)?(ц|тс|тьс)ат(ь|ый))( |$)", new NumberParseResult(13, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(тр[ие]на(д)?(ц|тс|тьс)ати)( |$)", new NumberParseResult(13, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(тр[ие]на(д)?(ц|тс|тьс)ат[оа][гв][оа])( |$)", new NumberParseResult(13, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(дв[иея]на(д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(12, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(дв[иея]на(д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(12, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(дв[иея]на(д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(12, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(од[ие]н(н)?[ао](д)?(ц|тс|тьс)[ао]т(ь|ый))( |$)", new NumberParseResult(11, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(од[ие]н(н)?[ао](д)?(ц|тс|тьс)[ао]ти)( |$)", new NumberParseResult(11, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(од[ие]н(н)?[ао](д)?(ц|тс|тьс)[ао]т[оа][гв][оа])( |$)", new NumberParseResult(11, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(д[иея]с[ея]т(ь|ый))( |$)", new NumberParseResult(10, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(^| )(д[иея]с[ея]ти)( |$)", new NumberParseResult(10, Case.parental, NumberFormat.unique));
        NUMBERS.put("()?(д[иея]с[ея]т[оа][гв][оа])( |$)", new NumberParseResult(10, Case.other, NumberFormat.unique));
        NUMBERS.put("(^| )(два(д)?(ц|тс|тьс)ат(ь|ый))( |$)", new NumberParseResult(20, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(два(д)?(ц|тс|тьс)ати)( |$)", new NumberParseResult(20, Case.parental, NumberFormat.dozen));
        NUMBERS.put("()?(два(д)?(ц|тс|тьс)ат[оа][гв][оа])( |$)", new NumberParseResult(20, Case.other, NumberFormat.dozen));
        NUMBERS.put("(^| )(три(дс|дц|тс|тьс)[ая]т(ь|ый))( |$)", new NumberParseResult(30, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(три(дс|дц|тс|тьс)[ая]ти)( |$)", new NumberParseResult(30, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(сор[оа]к([ао]вой)?)( |$)", new NumberParseResult(40, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(сор[оа]ка)( |$)", new NumberParseResult(40, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(п[яеи](дь|ть|т)((и)?д)?[еи]сят(ь|ый)?)( |$)", new NumberParseResult(50, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(п[яеи]сят(ь|ый)?)( |$)", new NumberParseResult(50, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(п[яеи]ти(д)?[еи]ся(т|ть)[еи])( |$)", new NumberParseResult(50, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(ш[еи][зс]д[иея]ся[тд](ь|ый)?)( |$)", new NumberParseResult(60, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(ш[еи][зс]т[ие]д[еи]сят(ь|ый)?)( |$)", new NumberParseResult(60, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(ш[еи][зс]т[ие]д[ие]с[ея]т[ие])( |$)", new NumberParseResult(60, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(сем(ь)?(и)?д[еия]с[яеи]т(ый)?)( |$)", new NumberParseResult(70, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(сем(ь)?д[еия]с[яеи]ти)( |$)", new NumberParseResult(70, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(вос[еяи]м(ь)?(и)?д[еия]с[яие][дт](ый)?)( |$)", new NumberParseResult(80, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(вос[еяи]м(ь)?д[еия]с[яие][дт]и)( |$)", new NumberParseResult(80, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(^| )(д[еия]в[еия]но[зс]т([оа]|ый))( |$)", new NumberParseResult(90, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(^| )(сто)( |$)", new NumberParseResult(100, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("(^| )(ста)( |$)", new NumberParseResult(100, Case.parental, NumberFormat.hundred));
        NUMBERS.put("(^| )(дв[е][зс]т(ь)?[еи])( |$)", new NumberParseResult(200, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("(^| )(двух[зс]т[оа])( |$)", new NumberParseResult(200, Case.parental, NumberFormat.hundred));
        NUMBERS.put("(^| )(тр[еи]ст[ао])( |$)", new NumberParseResult(300, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("(^| )(тр[еёо]хст[ао])( |$)", new NumberParseResult(300, Case.parental, NumberFormat.hundred));
        NUMBERS.put("(^| )(дв(е|ух) ты(с([яеи]ч[еи])?|(ш|сч)и))( |$)", new NumberParseResult(2000, Case.nominative, NumberFormat.thousand));
    }

    private Set<NumberParseResult> defineNumbers(final String template) {
        String lowerCaseTemplate = template.toLowerCase();
        Set<NumberParseResult> numberParseResults = new TreeSet<>();
        Pattern pattern;
        Matcher matcher;
        for (String numberTemplate : NUMBERS.keySet()) {
            pattern = Pattern.compile(numberTemplate, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(lowerCaseTemplate);
            while (matcher.find()) {
                NumberParseResult numberParseResult = new NumberParseResult(NUMBERS.get(numberTemplate));
                int startIndex = matcher.start(2);
                int endIndex = startIndex + matcher.group(2).length();
                numberParseResult.setIndexStart(startIndex);
                numberParseResult.setIndexEnd(endIndex);
                numberParseResults.add(numberParseResult);
            }
        }
        return numberParseResults;
    }

    public String parseNumbersInTemplate(final String template) {
        String lowerCaseTemplate = template.toLowerCase();
        Set<NumberParseResult> definedNumbers = defineNumbers(template);
        Set<NumberParseResult> handledNumbers = new TreeSet<>();

        NumberParseResult previous = null;
        for (NumberParseResult parseResult : definedNumbers) {
            if (previous != null && isNumbersNear(previous.getIndexEnd(), parseResult.getIndexStart(), lowerCaseTemplate)) {
                switch (previous.getNumberFormat()) {
                    case thousand:
                        if (parseResult.getNumberFormat() != NumberFormat.thousand) {
                            NumberParseResult newParseResult = new NumberParseResult(parseResult.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() + parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else {
                            handledNumbers.add(previous);
                            previous = parseResult;
                        }
                        break;
                    case hundred:
                        if (parseResult.getNumberFormat() != NumberFormat.hundred && isCasesMatches(previous.getNumberCase(), parseResult.getNumberCase())) {
                            NumberParseResult newParseResult = new NumberParseResult(parseResult.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() + parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else {
                            handledNumbers.add(previous);
                            previous = parseResult;
                        }
                        break;
                    case dozen:
                        if (parseResult.getNumberFormat() == NumberFormat.single && isCasesMatches(previous.getNumberCase(), parseResult.getNumberCase())) {
                            NumberParseResult newParseResult = new NumberParseResult(parseResult.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() + parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else {
                            handledNumbers.add(previous);
                            previous = parseResult;
                        }
                        break;
                    default:
                        handledNumbers.add(previous);
                        previous = parseResult;
                        break;
                }
            } else {
                if (previous != null) {
                    handledNumbers.add(previous);
                }
                previous = parseResult;
            }
        }
        if (previous != null) {
            handledNumbers.add(previous);
        }
        int correction = 0;
        for (NumberParseResult handledNumber : handledNumbers) {
            String number = String.valueOf(handledNumber.getNumber());
            lowerCaseTemplate = lowerCaseTemplate.substring(0, handledNumber.getIndexStart() - correction)
                    + String.valueOf(handledNumber.getNumber())
                    + lowerCaseTemplate.substring(handledNumber.getIndexEnd() - correction);
            correction += handledNumber.getIndexEnd() - handledNumber.getIndexStart() - number.length();
        }

        return lowerCaseTemplate;
    }

    private boolean isNumbersNear(int previousEndIndex, int nextStartIndex, String template) {
        return nextStartIndex - previousEndIndex == 1 && template.substring(previousEndIndex, nextStartIndex).equalsIgnoreCase(" ");
    }

    private boolean isCasesMatches(Case previousNumberCase, Case currentNumberCase) {
        boolean result = false;
        if (previousNumberCase == currentNumberCase) {
            result = true;
        } else if (previousNumberCase.equals(Case.nominative) && !currentNumberCase.equals(Case.parental)) {
            result = true;
        }
        return result;
    }
}
