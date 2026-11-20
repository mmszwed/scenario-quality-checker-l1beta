package pl.sqc.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import pl.sqc.model.Scenario;
import pl.sqc.model.Step;

class StepCounterTest {

    @Test
    void emptyScenarioHasZeroSteps() {
        Scenario scenario = new Scenario("Pusty", List.of("Bibliotekarz"), "System", List.of());

        assertThat(StepCounter.countSteps(scenario)).isZero();
    }

    @Test
    void flatScenarioCountsItsSteps() {
        Scenario scenario = new Scenario("Płaski", List.of("Bibliotekarz"), "System", List.of(
                new Step("Bibliotekarz loguje się"),
                new Step("System wyświetla panel"),
                new Step("Bibliotekarz wylogowuje się")));

        assertThat(StepCounter.countSteps(scenario)).isEqualTo(3);
    }

    @Test
    void nestedScenarioCountsStepsOnAllLevels() {
        Scenario scenario = new Scenario("Zagnieżdżony", List.of("Bibliotekarz"), "System", List.of(
                new Step("Bibliotekarz wybiera opcję"),
                new Step("IF: Bibliotekarz chce dodać egzemplarz", List.of(
                        new Step("Bibliotekarz wybiera książkę"),
                        new Step("FOR EACH: egzemplarz", List.of(
                                new Step("System nadaje numer"),
                                new Step("IF: numer zajęty", List.of(
                                        new Step("System nadaje numer"))))))),
                new Step("System zapisuje książkę")));

        assertThat(StepCounter.countSteps(scenario)).isEqualTo(8);
    }

    @Test
    void stepWithEmptySubScenarioCountsOnlyItself() {
        Scenario scenario = new Scenario("Pusty podscenariusz", List.of(), "System", List.of(
                new Step("IF: warunek", List.of())));

        assertThat(StepCounter.countSteps(scenario)).isEqualTo(1);
    }
}
