package pl.sqc.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import pl.sqc.model.Scenario;
import pl.sqc.model.Step;

class KeywordStepCounterTest {

    @Test
    void scenarioWithoutKeywordsGivesZero() {
        Scenario scenario = new Scenario("Bez słów kluczowych", List.of("Bibliotekarz"), "System", List.of(
                new Step("Bibliotekarz loguje się"),
                new Step("System wyświetla panel IF: nie dotyczy"),
                new Step("IFx Bibliotekarz wylogowuje się")));

        assertThat(KeywordStepCounter.countKeywordSteps(scenario)).isZero();
    }

    @Test
    void emptyScenarioGivesZero() {
        Scenario scenario = new Scenario("Pusty", List.of(), "System", List.of());

        assertThat(KeywordStepCounter.countKeywordSteps(scenario)).isZero();
    }

    @Test
    void countsEachKeywordOnce() {
        Scenario scenario = new Scenario("Płaski", List.of("Bibliotekarz"), "System", List.of(
                new Step("IF: Bibliotekarz chce dodać książkę"),
                new Step("ELSE: Bibliotekarz anuluje"),
                new Step("FOR EACH: egzemplarz"),
                new Step("IF Bibliotekarz zatwierdza"),
                new Step("System zapisuje książkę")));

        assertThat(KeywordStepCounter.countKeywordSteps(scenario)).isEqualTo(4);
    }

    @Test
    void countsKeywordStepsOnAllLevels() {
        Scenario scenario = new Scenario("Zagnieżdżony", List.of("Bibliotekarz"), "System", List.of(
                new Step("Bibliotekarz wybiera opcję"),
                new Step("IF: Bibliotekarz chce dodać egzemplarz", List.of(
                        new Step("Bibliotekarz wybiera książkę"),
                        new Step("FOR EACH: egzemplarz", List.of(
                                new Step("System nadaje numer"),
                                new Step("IF: numer zajęty", List.of(
                                        new Step("System nadaje nowy numer"))),
                                new Step("ELSE: System zapisuje numer"))))),
                new Step("System zapisuje książkę")));

        assertThat(KeywordStepCounter.countKeywordSteps(scenario)).isEqualTo(4);
    }
}
