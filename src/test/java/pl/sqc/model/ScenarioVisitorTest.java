package pl.sqc.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ScenarioVisitorTest {

    /** Zapisuje kolejność odwiedzin i liczy, ile razy odwiedzono każdy krok. */
    private static final class RecordingVisitor implements ScenarioVisitor {
        private final List<String> visited = new ArrayList<>();
        private final Map<Step, Integer> visitsPerStep = new IdentityHashMap<>();
        private final List<Integer> depths = new ArrayList<>();
        private int scenarioVisits;

        @Override
        public void visitScenario(Scenario scenario) {
            scenarioVisits++;
            visited.add("scenario:" + scenario.title());
        }

        @Override
        public void visitStep(Step step, StepPath path) {
            visited.add(path + " " + step.text());
            visitsPerStep.merge(step, 1, Integer::sum);
            depths.add(path.depth());
        }
    }

    /** Scenariusz z czterema poziomami zagnieżdżenia i powtórzonym tekstem kroku. */
    private static Scenario nestedScenario() {
        return new Scenario(
                "Dodanie książki",
                List.of("Bibliotekarz"),
                "System",
                List.of(
                        new Step("Bibliotekarz wybiera opcję dodania książki"),
                        new Step("IF: Bibliotekarz chce dodać egzemplarz", List.of(
                                new Step("Bibliotekarz wybiera książkę"),
                                new Step("FOR EACH: egzemplarz", List.of(
                                        new Step("System nadaje numer"),
                                        new Step("IF: numer zajęty", List.of(
                                                new Step("System nadaje numer"))))))),
                        new Step("System zapisuje książkę")));
    }

    @Test
    void visitsEveryStepOnAllLevelsExactlyOnceInOrder() {
        Scenario scenario = nestedScenario();
        RecordingVisitor visitor = new RecordingVisitor();

        scenario.accept(visitor);

        assertThat(visitor.visited).containsExactly(
                "scenario:Dodanie książki",
                "1. Bibliotekarz wybiera opcję dodania książki",
                "2. IF: Bibliotekarz chce dodać egzemplarz",
                "2.1. Bibliotekarz wybiera książkę",
                "2.2. FOR EACH: egzemplarz",
                "2.2.1. System nadaje numer",
                "2.2.2. IF: numer zajęty",
                "2.2.2.1. System nadaje numer",
                "3. System zapisuje książkę");
        assertThat(visitor.scenarioVisits).isEqualTo(1);
        assertThat(visitor.visitsPerStep).hasSize(8).allSatisfy((step, count) -> assertThat(count).isEqualTo(1));
        assertThat(visitor.depths).contains(1, 2, 3, 4);
    }

    @Test
    void emptyScenarioVisitsOnlyScenario() {
        Scenario scenario = new Scenario("Pusty", List.of(), "System", List.of());
        RecordingVisitor visitor = new RecordingVisitor();

        scenario.accept(visitor);

        assertThat(visitor.visited).containsExactly("scenario:Pusty");
        assertThat(visitor.visitsPerStep).isEmpty();
    }

    @Test
    void visitorWithOnlyVisitStepWorksThroughDefaultMethod() {
        List<String> texts = new ArrayList<>();

        nestedScenario().accept((step, path) -> texts.add(step.text()));

        assertThat(texts).hasSize(8).first().isEqualTo("Bibliotekarz wybiera opcję dodania książki");
    }

    @Test
    void modelKeepsHeaderData() {
        Scenario scenario = nestedScenario();

        assertThat(scenario.title()).isEqualTo("Dodanie książki");
        assertThat(scenario.actors()).containsExactly("Bibliotekarz");
        assertThat(scenario.systemActor()).isEqualTo("System");
        assertThat(scenario.steps()).hasSize(3);
        assertThat(scenario.steps().get(1).hasSubSteps()).isTrue();
        assertThat(scenario.steps().get(0).hasSubSteps()).isFalse();
    }

    @Test
    void modelIsImmutableAndCopiesInputLists() {
        List<String> actors = new ArrayList<>(List.of("Bibliotekarz"));
        List<Step> steps = new ArrayList<>(List.of(new Step("Bibliotekarz loguje się")));
        Scenario scenario = new Scenario("Logowanie", actors, "System", steps);

        actors.add("Czytelnik");
        steps.clear();

        assertThat(scenario.actors()).containsExactly("Bibliotekarz");
        assertThat(scenario.steps()).hasSize(1);
        assertThatThrownBy(() -> scenario.steps().add(new Step("x")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void missingListsBecomeEmpty() {
        Scenario scenario = new Scenario("Bez kroków", null, "System", null);
        Step step = new Step("Krok", null);

        assertThat(scenario.actors()).isEmpty();
        assertThat(scenario.steps()).isEmpty();
        assertThat(step.subSteps()).isEmpty();
    }

    @Test
    void requiredFieldsCannotBeNull() {
        assertThatThrownBy(() -> new Scenario(null, List.of(), "System", List.of()))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Scenario("Tytuł", List.of(), null, List.of()))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Step(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void stepPathFormatsNumberingAndDepth() {
        StepPath path = StepPath.root().child(4).child(3);

        assertThat(path.toString()).isEqualTo("4.3.");
        assertThat(path.depth()).isEqualTo(2);
        assertThat(path.indices()).containsExactly(4, 3);
        assertThat(StepPath.root().toString()).isEmpty();
        assertThatThrownBy(() -> StepPath.root().child(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
