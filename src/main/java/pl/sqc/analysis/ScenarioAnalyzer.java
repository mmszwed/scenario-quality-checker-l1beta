package pl.sqc.analysis;

import org.springframework.stereotype.Service;
import pl.sqc.model.Scenario;

@Service
public class ScenarioAnalyzer {

    public AnalysisResult analyze(Scenario scenario) {
        return new AnalysisResult(
                StepCounter.countSteps(scenario),
                KeywordStepCounter.countKeywordSteps(scenario),
                MissingActorVisitor.findMissingActorSteps(scenario));
    }
}
