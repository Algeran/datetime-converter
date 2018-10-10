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
        NUMBERS.put("д[еи]в[яе]т(ь)?(?!ь?[сн]|[аыио])", new NumberParseResult(9, Case.nominative, NumberFormat.single));
        NUMBERS.put("д[еи]в[яе]ти(?!с)", new NumberParseResult(9, Case.parental, NumberFormat.single));
        NUMBERS.put("д[еи]в[яе]т(ы[ий]|ая|ое)", new NumberParseResult(9, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("д[еи]в[яе]т[оа]([вг][оа]|[йи])", new NumberParseResult(9, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("в[ао]с[еи]м(ь)?(?![нидс])", new NumberParseResult(8, Case.nominative, NumberFormat.single));
        NUMBERS.put("в[ао]с(ь)?м[еи](?![дс])", new NumberParseResult(8, Case.parental, NumberFormat.single));
        NUMBERS.put("в[ао]с(ь)?м(о[ийе]|ая)", new NumberParseResult(8, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("в[ао]с(ь)?мо[гв][ао]", new NumberParseResult(8, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("(?<!во)сем(ь)?(?![ин]|ь?[дс])", new NumberParseResult(7, Case.nominative, NumberFormat.single));
        NUMBERS.put("(?<!во)се(д(ь)?)?ми(?![дс])", new NumberParseResult(7, Case.parental, NumberFormat.single));
        NUMBERS.put("(?<!во)сед(ь)?м(о[ийе]|ая)", new NumberParseResult(7, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("(?<!во)сед(ь)?мо[гв][ао]", new NumberParseResult(7, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("ш[ие]ст(ь)?(?![аиыо]|ь?[ндс])", new NumberParseResult(6, Case.nominative, NumberFormat.single));
        NUMBERS.put("ш[ие]сти(?![дс])", new NumberParseResult(6, Case.parental, NumberFormat.single));
        NUMBERS.put("ш[ие]ст(о[ийе]|ая)", new NumberParseResult(6, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("ш[ие]сто[гв][ао]", new NumberParseResult(6, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("пят(ь)?(?!ь?[динс]|[ыоа])", new NumberParseResult(5, Case.nominative, NumberFormat.single));
        NUMBERS.put("п[ея]ти(?![дс])", new NumberParseResult(5, Case.parental, NumberFormat.single));
        NUMBERS.put("пят(ы[йи]|ое|ая)", new NumberParseResult(5, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("пят[ао]([вг][ао]|[ий])", new NumberParseResult(5, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("ч[еяаиы][тп]ыр[еи](?![хс])", new NumberParseResult(4, Case.nominative, NumberFormat.single));
        NUMBERS.put("ч[еяаиы][пт]ыр[еёо]х", new NumberParseResult(4, Case.parental, NumberFormat.single));
        NUMBERS.put("ч[еяаиы]т(ь)?в[еёо]рт(ы[йи]|ая|ое)", new NumberParseResult(4, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("ч[еяаиы]т(ь)?в[еёо]рт[ао]([ий]|[вг][ао])", new NumberParseResult(4, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("три(?![сдн])", new NumberParseResult(3, Case.nominative, NumberFormat.single));
        NUMBERS.put("тр[еёо]х(?!с)", new NumberParseResult(3, Case.parental, NumberFormat.single));
        NUMBERS.put("трет((ь)?и[йи]|ь[яеи](?![гйи]))", new NumberParseResult(3, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("трет(ь)?([еи][гв][ао]|е[йи])", new NumberParseResult(3, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("д(ы)?в[ае](?![днс])", new NumberParseResult(2, Case.nominative, NumberFormat.single));
        NUMBERS.put("д(ы)?вух(?!с)", new NumberParseResult(2, Case.parental, NumberFormat.single));
        NUMBERS.put("вт[ао]р[ао][ийяе]", new NumberParseResult(2, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("вт[ао]р[ао][гв][ао]", new NumberParseResult(2, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("один(?![нао])", new NumberParseResult(1, Case.nominative, NumberFormat.single));
        NUMBERS.put("одн[уао](?![гв])", new NumberParseResult(1, Case.nominative, NumberFormat.single));
        NUMBERS.put("одн[ао]([гв][оа]|[йи])", new NumberParseResult(1, Case.parental, NumberFormat.single));
        NUMBERS.put("перв(ы[йи]|ое|ая)", new NumberParseResult(1, Case.adjective_nominative, NumberFormat.single));
        NUMBERS.put("перв[ао]([йи]|[вг][ао])", new NumberParseResult(1, Case.adjective_parental, NumberFormat.single));
        NUMBERS.put("д[ие]в[яе]([дт](ь)?)?на([дт](ь)?)?(([тд])?ц|[дт](ь)?с)[ао][дт](ь)?(?![иоаы])", new NumberParseResult(19, Case.nominative, NumberFormat.unique));
        NUMBERS.put("д[ие]в[яе]([дт](ь)?)?на([дт](ь)?)?(([тд])?ц|[дт](ь)?с)[ао][дт]и(?![йи])", new NumberParseResult(19, Case.parental, NumberFormat.unique));
        NUMBERS.put("д[ие]в[яе]([дт](ь)?)?на([дт](ь)?)?(([тд])?ц|[дт](ь)?с)[ао][дт]([ао][яе]|ы[йи])", new NumberParseResult(19, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("д[ие]в[яе]([дт](ь)?)?на([дт](ь)?)?(([тд])?ц|[дт](ь)?с)[ао][дт][ао]([вг][ао]|[йи])", new NumberParseResult(19, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("в[оа]с[еи]мна([дт](ь)?)?(ц|[дт](ь)?с)[ао][дт](ь)?(?![иаоы])", new NumberParseResult(18, Case.nominative, NumberFormat.unique));
        NUMBERS.put("в[оа]с[еи]мна([дт](ь)?)?(ц|[дт](ь)?с)[ао][дт]и(?![йи])", new NumberParseResult(18, Case.parental, NumberFormat.unique));
        NUMBERS.put("в[оа]с[еи]мна([дт](ь)?)?(ц|[дт](ь)?с)[ао][дт](ая|ое|ы[йи])", new NumberParseResult(18, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("в[оа]с[еи]мна([дт](ь)?)?(ц|[дт](ь)?с)[ао][дт][ао]([вг][ао]|[йи])", new NumberParseResult(18, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("(?<!в[ао])с[еи]мна([дт])?(ц|[тд](ь)?с)[ао]т(ь)?(?![иаоы])", new NumberParseResult(17, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(?<!в[ао])с[еи]мна([дт])?(ц|[тд](ь)?с)[ао]т[ие](?![йи])", new NumberParseResult(17, Case.parental, NumberFormat.unique));
        NUMBERS.put("(?<!в[ао])с[еи]мна([дт])?(ц|[тд](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(17, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("(?<!в[ао])с[еи]мна([дт])?(ц|[тд](ь)?с)[ао]т[ао]([вг][ао]|[йи])", new NumberParseResult(17, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("ш[еи]с([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т(ь)?(?![иыао])", new NumberParseResult(16, Case.nominative, NumberFormat.unique));
        NUMBERS.put("ш[еи]с([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(16, Case.parental, NumberFormat.unique));
        NUMBERS.put("ш[еи]с([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т(ы[йи]|ая|ое)", new NumberParseResult(16, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("ш[еи]с([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т[ао]([вг][ао]|[йи])", new NumberParseResult(16, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("п[яе]([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т(ь)?(?![иаоы])", new NumberParseResult(15, Case.nominative, NumberFormat.unique));
        NUMBERS.put("п[яе]([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(15, Case.parental, NumberFormat.unique));
        NUMBERS.put("п[яе]([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(15, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("п[яе]([тд])?на([тд])?(ц|[тд](ь)?с)[ао]т[ао]([вг][ао]|[йи])", new NumberParseResult(15, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("ч[яе]тырн[ао]([тд])?(ц|[тд](ь)?с)[ао]т(ь)?(?![иаоы])", new NumberParseResult(14, Case.nominative, NumberFormat.unique));
        NUMBERS.put("ч[яе]тырн[ао]([тд])?(ц|[тд](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(14, Case.parental, NumberFormat.unique));
        NUMBERS.put("ч[яе]тырн[ао]([тд])?(ц|[тд](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(14, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("ч[яе]тырн[ао]([тд])?(ц|[тд](ь)?с)[ао]т[ао]([вг][ао]|[йи])", new NumberParseResult(14, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("тр[ие]на([тд])?(ц|[тд](ь)?с)[ао]т(ь)?(?![иоыа])", new NumberParseResult(13, Case.nominative, NumberFormat.unique));
        NUMBERS.put("тр[ие]на([тд])?(ц|[тд](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(13, Case.parental, NumberFormat.unique));
        NUMBERS.put("тр[ие]на([тд])?(ц|[тд](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(13, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("тр[ие]на([тд])?(ц|[тд](ь)?с)[ао]т[ао]([вг][ао]|[ий])", new NumberParseResult(13, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("дв[иея]на([дт])?(ц|[дт](ь)?с)[ао]т(ь)?(?![иаыо])", new NumberParseResult(12, Case.nominative, NumberFormat.unique));
        NUMBERS.put("дв[иея]на([дт])?(ц|[дт](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(12, Case.parental, NumberFormat.unique));
        NUMBERS.put("дв[иея]на([дт])?(ц|[дт](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(12, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("дв[иея]на([дт])?(ц|[дт](ь)?с)[ао]т[ао]([вг][ао]|[ий])", new NumberParseResult(12, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("од[ие](н){1,2}[ао]([дт])?(ц|[дт](ь)?с)[ао]т(ь)?(?![иаоы])", new NumberParseResult(11, Case.nominative, NumberFormat.unique));
        NUMBERS.put("од[ие](н){1,2}[ао]([дт])?(ц|[дт](ь)?с)[ао]т[ие](?![ий])", new NumberParseResult(11, Case.parental, NumberFormat.unique));
        NUMBERS.put("од[ие](н){1,2}[ао]([дт])?(ц|[дт](ь)?с)[ао]т(ая|ое|ы[йи])", new NumberParseResult(11, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("од[ие](н){1,2}[ао]([дт])?(ц|[дт](ь)?с)[ао]т[ао]([вг][ао]|[ий])", new NumberParseResult(11, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("(?<![тьиеясм])д[иея]с[ея][тд](ь)?(?![иаоы])", new NumberParseResult(10, Case.nominative, NumberFormat.unique));
        NUMBERS.put("(?<![тьиеясм])д[иея]с[ея][тд][ие](?![ий])", new NumberParseResult(10, Case.parental, NumberFormat.unique));
        NUMBERS.put("(?<![тьиеясм])д[иея]с[ея][тд](ая|ое|ы[йи])", new NumberParseResult(10, Case.adjective_nominative, NumberFormat.unique));
        NUMBERS.put("(?<![тьиеясм])д[иея]с[ея][тд][ао]([вг][ао]|[йи])", new NumberParseResult(10, Case.adjective_parental, NumberFormat.unique));
        NUMBERS.put("два([дт])?(ц|[дт](ь)?с)а[дт](ь)?(?![иыоа])", new NumberParseResult(20, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("два([дт])?(ц|[дт](ь)?с)а[дт][ие](?![ий])", new NumberParseResult(20, Case.parental, NumberFormat.dozen));
        NUMBERS.put("два([дт])?(ц|[дт](ь)?с)а[дт](ая|ое|ы[йи])", new NumberParseResult(20, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("два([дт])?(ц|[дт](ь)?с)а[дт][ао]([вг][ао]|[ий])", new NumberParseResult(20, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("три([дт](ь)?)?[цс][ая]т(ь)?(?![иыоа])", new NumberParseResult(30, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("три([дт](ь)?)?[цс][ая]т[ие](?![ий])", new NumberParseResult(30, Case.parental, NumberFormat.dozen));
        NUMBERS.put("три([дт](ь)?)?[цс][ая]т(ая|ое|ы[йи])", new NumberParseResult(30, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("три([дт](ь)?)?[цс][ая]т[ао]([вг][ао]|[ий])", new NumberParseResult(30, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("с[оа]р[оа]к(?![ао])", new NumberParseResult(40, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("с[оа]р[оа]ка", new NumberParseResult(40, Case.parental, NumberFormat.dozen));
        NUMBERS.put("с[оа]р[оа]к[оа]в(ая|о[ейи])", new NumberParseResult(40, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("с[оа]р[оа]к[оа]в[ао][вг][ао]", new NumberParseResult(40, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("п[яеи](([тд](ь)?)([ие])?д[еи])?сят(ь)?(?![иыоа])", new NumberParseResult(50, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("п[яеи](([тд](ь)?)([ие])?д[еи])?сят[ие](?![ий])", new NumberParseResult(50, Case.parental, NumberFormat.dozen));
        NUMBERS.put("п[яеи](([тд](ь)?)([ие])?д[еи])?сят(ая|ое|ы[йи])", new NumberParseResult(50, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("п[яеи](([тд](ь)?)([ие])?д[еи])?сят[ао]([вг][ао]|[ий])", new NumberParseResult(50, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("ш[еи][зс](я|([дт])?([ьи])?[дт][иея]с[яе])[тд](ь)?(?![иыоа])", new NumberParseResult(60, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("ш[еи][зс](я|([дт])?([ьи])?[дт][иея]с[яе][тд])[ие](?![ий])", new NumberParseResult(60, Case.parental, NumberFormat.dozen));
        NUMBERS.put("ш[еи][зс](я|([дт])?([ьи])?[дт][иея]с[яе][тд])(ая|ое|ы[йи])", new NumberParseResult(60, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("ш[еи][зс](я|([дт])?([ьи])?[дт][иея]с[яе][тд])[ао]([вг][ао]|[ий])", new NumberParseResult(60, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("(?<!в[оа])с[еи]м([иь])?([дт][еия])?[сз][яеи][тд](ь)?(?![иыоа])", new NumberParseResult(70, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("(?<!в[оа])с[еи]м([иь])?([дт][еия])?[сз][яеи][тд][ие](?![ий])", new NumberParseResult(70, Case.parental, NumberFormat.dozen));
        NUMBERS.put("(?<!в[оа])с[еи]м([иь])?([дт][еия])?[сз][яеи][тд](ая|ое|ы[йи])", new NumberParseResult(70, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("(?<!в[оа])с[еи]м([иь])?([дт][еия])?[сз][яеи][тд][ао]([вг][ао]|[ий])", new NumberParseResult(70, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("вос[ье]м[иь]([дт][еи])?[зс][еая][дт](ь)?(?![иыоа])", new NumberParseResult(80, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("вос[ье]м[иь]([дт][еи])?[зс][еая][дт][ие](?![ий])", new NumberParseResult(80, Case.parental, NumberFormat.dozen));
        NUMBERS.put("вос[ье]м[иь]([дт][еи])?[зс][еая][дт](ая|ое|ы[йи])", new NumberParseResult(80, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("вос[ье]м[иь]([дт][еи])?[зс][еая][дт][ао]([вг][ао]|[ий])", new NumberParseResult(80, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("д[еия]в[еия]но[зс][тд]о(?![яегий])", new NumberParseResult(90, Case.nominative, NumberFormat.dozen));
        NUMBERS.put("д[еия]в[еия]но[зс][тд]а(?![яегйи])", new NumberParseResult(90, Case.parental, NumberFormat.dozen));
        NUMBERS.put("д[еия]в[еия]но[зс][тд](ая|ое|ы[йи])", new NumberParseResult(90, Case.adjective_nominative, NumberFormat.dozen));
        NUMBERS.put("д[еия]в[еия]но[зс][тд][ао]([вг][ао]|[ий])", new NumberParseResult(90, Case.adjective_parental, NumberFormat.dozen));
        NUMBERS.put("(?<![оеиыа])сто", new NumberParseResult(100, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("(?<![хиеоё])ста", new NumberParseResult(100, Case.parental, NumberFormat.hundred));
        NUMBERS.put("(?<![хиеё])сот(ы[йи]|ая|ое)", new NumberParseResult(100, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("(?<![хиеё])сот[ао]([вг][ао]|[йи])", new NumberParseResult(100, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("две[зс]т(ь)?[еи]", new NumberParseResult(200, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("двух[сз]т[ао]", new NumberParseResult(200, Case.parental, NumberFormat.hundred));
        NUMBERS.put("двух[сз]от(ы[йи]|ое|ая)", new NumberParseResult(200, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("двух[сз]от[ао]([вг][ао]|[йи])", new NumberParseResult(200, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("три[зс]т[ао]", new NumberParseResult(300, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("тр[иеё]хст[ао]", new NumberParseResult(300, Case.parental, NumberFormat.hundred));
        NUMBERS.put("тр[иеё]хсот(ы[йи]|ое|ая)", new NumberParseResult(300, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("тр[иеё]хсот[ао]([вг][ао]|[йи])", new NumberParseResult(300, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("ч[еяаиы][тп]ыр[еи][зс]т[ао]", new NumberParseResult(400, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("ч[еяаиы][тп]ыр[еи]х[зс]т[ао]", new NumberParseResult(400, Case.parental, NumberFormat.hundred));
        NUMBERS.put("ч[еяаиы][тп]ыр[еи]х[зс]от(ы[йи]|ое|ая)", new NumberParseResult(400, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("ч[еяаиы][тп]ыр[еи]х[зс]от[ао]([вг][ао]|[йи])", new NumberParseResult(400, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("п[ея]т(ь)?со[тд]", new NumberParseResult(500, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("п[ея]т(ь)?[ие]ст[ао]", new NumberParseResult(500, Case.parental, NumberFormat.hundred));
        NUMBERS.put("п[ея]т(ь)?[ие]сот(ы[йи]|ое|ая)", new NumberParseResult(500, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("п[ея]т(ь)?[ие]сот[ао]([вг][ао]|[йи])", new NumberParseResult(500, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("ш[ие][сз](т)?(с)?о[тд]", new NumberParseResult(600, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("ш[ие][сз]т(ь)?[ие]ст[ао]", new NumberParseResult(600, Case.parental, NumberFormat.hundred));
        NUMBERS.put("ш[ие][сз]т(ь)?[ие]сот(ы[йи]|ое|ая)", new NumberParseResult(600, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("ш[ие][сз]т(ь)?[ие]сот[ао]([вг][ао]|[йи])", new NumberParseResult(600, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("(?<!во)сем(ь)?со[тд]", new NumberParseResult(700, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("сем(ь)?([ие])?ста", new NumberParseResult(700, Case.parental, NumberFormat.hundred));
        NUMBERS.put("сем(ь)?([ие])?сот(ы[йи]|ое|ая)", new NumberParseResult(700, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("сем(ь)?([ие])?сот[ао]([вг][ао]|[йи])", new NumberParseResult(700, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("во[сз][еи]м(ь)?со[тд]", new NumberParseResult(800, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("во[сз]м(ь)?([ие])?ста", new NumberParseResult(800, Case.parental, NumberFormat.hundred));
        NUMBERS.put("во[сз]м(ь)?([ие])?сот(ы[йи]|ое|ая)", new NumberParseResult(800, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("во[сз]м(ь)?([ие])?сот[ао]([вг][ао]|[йи])", new NumberParseResult(800, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("д[еи]в[яе]т(ь)?со[тд]", new NumberParseResult(900, Case.nominative, NumberFormat.hundred));
        NUMBERS.put("д[еи]в[яе]т(ь)?([ие])?ста", new NumberParseResult(900, Case.parental, NumberFormat.hundred));
        NUMBERS.put("д[еи]в[яе]т(ь)?([ие])?сот(ы[йи]|ое|ая)", new NumberParseResult(900, Case.adjective_nominative, NumberFormat.hundred));
        NUMBERS.put("д[еи]в[яе]т(ь)?([ие])?сот[ао]([вг][ао]|[йи])", new NumberParseResult(900, Case.adjective_parental, NumberFormat.hundred));
        NUMBERS.put("тыс(([яеи])?[чшщ]([аеи])?)?", new NumberParseResult(1000, Case.nominative, NumberFormat.thousand));
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
                int startIndex = matcher.start();
                int endIndex = startIndex + matcher.group().length();
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
                        if (NumberFormat.thousand != parseResult.getNumberFormat() && isCasesMatches(previous.getNumberCase(), parseResult.getNumberCase())) {
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
                        if (NumberFormat.thousand == parseResult.getNumberFormat()) {
                            NumberParseResult newParseResult = new NumberParseResult(previous.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() * parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else if (NumberFormat.hundred != parseResult.getNumberFormat() && isCasesMatches(previous.getNumberCase(), parseResult.getNumberCase())) {
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
                        if (NumberFormat.thousand == parseResult.getNumberFormat()) {
                            NumberParseResult newParseResult = new NumberParseResult(previous.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() * parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else if (parseResult.getNumberFormat() == NumberFormat.single && isCasesMatches(previous.getNumberCase(), parseResult.getNumberCase())) {
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
                        if (NumberFormat.thousand == parseResult.getNumberFormat()) {
                            NumberParseResult newParseResult = new NumberParseResult(previous.getNumberCase(), parseResult.getNumberFormat());
                            newParseResult.setNumber(previous.getNumber() * parseResult.getNumber());
                            newParseResult.setIndexStart(previous.getIndexStart());
                            newParseResult.setIndexEnd(parseResult.getIndexEnd());
                            previous = newParseResult;
                        } else {
                            handledNumbers.add(previous);
                            previous = parseResult;
                        }
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
        boolean result = false;
        if (nextStartIndex - previousEndIndex == 1 &&
                (template.substring(previousEndIndex, nextStartIndex).equalsIgnoreCase(" ")
                        || template.substring(previousEndIndex, nextStartIndex).equalsIgnoreCase("-")
                )) {
            result = true;
        } else if (nextStartIndex - previousEndIndex == 0) {
            result = true;
        }
        return result;
    }

    private boolean isCasesMatches(Case previousNumberCase, Case currentNumberCase) {
        boolean result = false;
        if (Case.nominative.equals(previousNumberCase) && !Case.parental.equals(currentNumberCase)) {
            result = true;
        } else if (Case.parental.equals(previousNumberCase) && Case.parental.equals(currentNumberCase)) {
            result = true;
        }
        return result;
    }
}
