package pl.sqc.analysis;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import pl.sqc.model.Scenario;
import pl.sqc.model.ScenarioVisitor;
import pl.sqc.model.Step;
import pl.sqc.model.StepPath;

public final class MissingActorVisitor implements ScenarioVisitor {

    private final List<AnalysisResult.MissingActorStep> missingSteps = new ArrayList<>();
    private final Set<String> actors = new LinkedHashSet<>();

    public static List<AnalysisResult.MissingActorStep> findMissingActorSteps(Scenario scenario) {
        MissingActorVisitor visitor = new MissingActorVisitor();
        scenario.accept(visitor);
        return List.copyOf(visitor.missingSteps);
    }

    @Override
    public void visitScenario(Scenario scenario) {
        actors.clear();
        actors.addAll(scenario.actors());

        if (scenario.systemActor() != null && !scenario.systemActor().isBlank()) {
            actors.add(scenario.systemActor());
        }
    }

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

    private boolean startsWithActor(String text, String actor) {
        String normalizedActor = actor.strip();

        if (normalizedActor.isEmpty() || !text.startsWith(normalizedActor)) {
            return false;
        }

        return text.length() == normalizedActor.length()
                || Character.isWhitespace(text.charAt(normalizedActor.length()));
    }
}
