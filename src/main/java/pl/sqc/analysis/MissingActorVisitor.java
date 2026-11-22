package pl.sqc.analysis;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pl.sqc.model.Scenario;
import pl.sqc.model.ScenarioVisitor;
import pl.sqc.model.Step;
import pl.sqc.model.StepPath;

/**
 * Wyszukuje kroki, które nie zaczynają się od aktora, na wszystkich poziomach zagnieżdżenia.
 *
 * <p>Aktorami są aktorzy zewnętrzni i aktor systemowy z nagłówka scenariusza. Przed
 * sprawdzeniem z początku kroku usuwane jest słowo kluczowe ({@code IF}, {@code ELSE},
 * {@code FOR EACH}) z opcjonalnym dwukropkiem. Po nazwie aktora musi być biały znak
 * albo koniec tekstu.
 */
public final class MissingActorVisitor implements ScenarioVisitor {

    /** Znalezione kroki bez aktora w kolejności odwiedzania. */
    private final List<AnalysisResult.MissingActorStep> missingSteps = new ArrayList<>();

    /** Aktorzy z nagłówka odwiedzanego scenariusza, razem z aktorem systemowym. */
    private final Set<String> actors = new LinkedHashSet<>();

    /**
     * Zwraca kroki scenariusza, które nie zaczynają się od aktora.
     *
     * @param scenario analizowany scenariusz
     * @return niemodyfikowalna lista kroków bez aktora; pusta, gdy wszystkie kroki mają aktora
     */
    public static List<AnalysisResult.MissingActorStep> findMissingActorSteps(Scenario scenario) {
        MissingActorVisitor visitor = new MissingActorVisitor();
        scenario.accept(visitor);
        return List.copyOf(visitor.missingSteps);
    }

    /**
     * Zapamiętuje aktorów z nagłówka scenariusza. Pusty aktor systemowy jest pomijany.
     *
     * @param scenario odwiedzany scenariusz
     */
    @Override
    public void visitScenario(Scenario scenario) {
        actors.clear();
        actors.addAll(scenario.actors());

        if (scenario.systemActor() != null && !scenario.systemActor().isBlank()) {
            actors.add(scenario.systemActor());
        }
    }

    /**
     * Sprawdza, czy krok po usunięciu słowa kluczowego zaczyna się od aktora.
     * Jeśli nie, dodaje go do wyniku razem z numeracją.
     *
     * @param step odwiedzany krok
     * @param path położenie kroku w scenariuszu
     */
    @Override
    public void visitStep(Step step, StepPath path) {
        String text = StepKeywords.stripKeyword(step.text()).strip();

        boolean hasActor = actors.stream().anyMatch(actor -> startsWithActor(text, actor));

        if (!hasActor) {
            missingSteps.add(
                    new AnalysisResult.MissingActorStep(path.toString(), step.text())
            );
        }
    }

    /**
     * Sprawdza, czy tekst zaczyna się od nazwy aktora jako całego słowa,
     * np. {@code "Bibliotekarz wybiera"} pasuje do aktora {@code "Bibliotekarz"},
     * a {@code "Bibliotekarze wybierają"} nie.
     *
     * @param text  tekst kroku bez słowa kluczowego
     * @param actor nazwa aktora
     * @return {@code true}, gdy krok zaczyna się od aktora
     */
    private boolean startsWithActor(String text, String actor) {
        String normalizedActor = actor.strip();

        if (normalizedActor.isEmpty() || !text.startsWith(normalizedActor)) {
            return false;
        }

        return text.length() == normalizedActor.length()
                || Character.isWhitespace(text.charAt(normalizedActor.length()));
    }
}
