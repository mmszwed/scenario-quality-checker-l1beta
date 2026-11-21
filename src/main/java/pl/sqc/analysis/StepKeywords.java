package pl.sqc.analysis;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rozpoznaje słowo kluczowe na początku kroku: {@code IF}, {@code ELSE} albo {@code FOR EACH}.
 *
 * <p>Słowo kluczowe musi stać na początku tekstu i być pisane wielkimi literami.
 * Po nim może być dwukropek, spacja albo koniec tekstu, więc {@code IF:} i {@code IF warunek}
 * są rozpoznawane, a {@code IFx} i słowa w środku zdania nie.
 */
public final class StepKeywords {

    private static final Pattern KEYWORD_PREFIX =
            Pattern.compile("^\\s*(IF|ELSE|FOR\\s+EACH)(?=[\\s:]|$)\\s*:?\\s*");

    private StepKeywords() {
    }

    /**
     * Sprawdza, czy krok zaczyna się od słowa kluczowego.
     *
     * @param text tekst kroku
     * @return {@code true}, gdy tekst zaczyna się od {@code IF}, {@code ELSE} albo {@code FOR EACH}
     */
    public static boolean startsWithKeyword(String text) {
        return KEYWORD_PREFIX.matcher(text).find();
    }

    /**
     * Zwraca słowo kluczowe z początku kroku.
     *
     * @param text tekst kroku
     * @return słowo kluczowe, np. {@code "FOR EACH"}, albo pusty wynik, gdy krok go nie ma
     */
    public static Optional<String> keyword(String text) {
        Matcher matcher = KEYWORD_PREFIX.matcher(text);
        if (!matcher.find()) {
            return Optional.empty();
        }
        return Optional.of(matcher.group(1).replaceAll("\\s+", " "));
    }

    /**
     * Usuwa z początku kroku słowo kluczowe i opcjonalny dwukropek.
     *
     * <p>Przykład: {@code "IF: Bibliotekarz wybiera opcję"} daje {@code "Bibliotekarz wybiera opcję"}.
     *
     * @param text tekst kroku
     * @return tekst bez słowa kluczowego; dla kroku bez słowa kluczowego tekst bez zmian
     */
    public static String stripKeyword(String text) {
        Matcher matcher = KEYWORD_PREFIX.matcher(text);
        return matcher.find() ? text.substring(matcher.end()) : text;
    }
}
