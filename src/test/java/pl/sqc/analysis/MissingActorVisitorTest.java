package pl.sqc.analysis;

import org.junit.jupiter.api.Test;
import pl.sqc.model.Scenario;
import pl.sqc.model.Step;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MissingActorVisitorTest {

    @Test
    void shouldAcceptStepStartingWithActor() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("IF: Bibliotekarz dodaje książkę"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAcceptSystemActor() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("IF: System zapisuje książkę"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldDetectStepWithoutActor() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("Wyświetla się formularz"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
        assertEquals("Wyświetla się formularz", result.get(0).text());
        assertEquals("1.", result.get(0).path());
    }

    @Test
    void shouldDetectMissingActorsInNestedSteps() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(
                        new Step(
                                "IF: Bibliotekarz loguje się",
                                List.of(
                                        new Step("Wyświetla się formularz"),
                                        new Step("IF: System zapisuje dane")
                                )
                        )
                )
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
        assertEquals("1.1.", result.get(0).path());
        assertEquals("Wyświetla się formularz", result.get(0).text());
    }

    @Test
    void shouldDetectAllMissingActorsAtDifferentLevels() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(
                        new Step(
                                "IF: Bibliotekarz loguje się",
                                List.of(new Step("Formularz jest widoczny"))
                        ),
                        new Step("Operacja zakończona")
                )
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(2, result.size());
        assertEquals("1.1.", result.get(0).path());
        assertEquals("2.", result.get(1).path());
    }

    @Test
    void shouldReturnEmptyListWhenAllStepsHaveActors() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz", "Czytelnik"),
                "System",
                List.of(
                        new Step("IF: Bibliotekarz dodaje książkę"),
                        new Step("IF: Czytelnik wypożycza książkę"),
                        new Step("IF: System zapisuje dane")
                )
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAcceptStepWithoutKeywordButWithActor() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("Bibliotekarz dodaje książkę"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAcceptKeywordWithoutColon() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("IF Bibliotekarz dodaje książkę"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldAcceptElseAndForEachKeywords() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(
                        new Step("ELSE: Bibliotekarz anuluje operację"),
                        new Step("FOR EACH: Bibliotekarz sprawdza książkę")
                )
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldRejectActorThatIsOnlyPrefixOfWord() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("IF: Bibliotekarzowi wyświetla się komunikat"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
        assertEquals("1.", result.get(0).path());
    }

    @Test
    void shouldRejectKeywordOnlyStep() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("IF:"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
        assertEquals("IF:", result.get(0).text());
    }

    @Test
    void shouldNotTreatBlankSystemActorAsActor() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                " ",
                List.of(new Step("Wyświetla się formularz"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
    }

    @Test
    void shouldRejectLowercaseKeyword() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(new Step("if: Bibliotekarz dodaje książkę"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
    }

    @Test
    void shouldDetectValidChildUnderInvalidParentAndKeepOrder() {
        Scenario scenario = new Scenario(
                "Test",
                List.of("Bibliotekarz"),
                "System",
                List.of(
                        new Step(
                                "Formularz jest widoczny",
                                List.of(
                                        new Step(
                                                "IF: Bibliotekarz wypełnia pola",
                                                List.of(new Step("Brak aktora"))
                                        )
                                )
                        )
                )
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(2, result.size());
        assertEquals("1.", result.get(0).path());
        assertEquals("1.1.1.", result.get(1).path());
    }

    @Test
    void shouldRejectShorterActorName() {
        Scenario scenario = new Scenario(
            "Test",
            List.of("System Administrator"),
            "Bibliotekarz",
            List.of(new Step("IF: System zapisuje dane"))
        );

        var result = MissingActorVisitor.findMissingActorSteps(scenario);

        assertEquals(1, result.size());
    }
}
