package pl.sqc.analysis;

import org.springframework.stereotype.Service;
import pl.sqc.model.Scenario;

/**
 * Usługa uruchamiająca wszystkie analizy scenariusza.
 *
 * <p>Każda analiza to osobny odwiedzający ({@link StepCounter}, {@link KeywordStepCounter},
 * {@link MissingActorVisitor}). Usługa zbiera ich wyniki w jeden {@link AnalysisResult}.
 */
@Service
public class ScenarioAnalyzer {

    /**
     * Analizuje scenariusz: liczy kroki, kroki ze słowem kluczowym i wyszukuje kroki bez aktora.
     *
     * @param scenario analizowany scenariusz
     * @return wyniki wszystkich trzech analiz
     */
    public AnalysisResult analyze(Scenario scenario) {
        return new AnalysisResult(
                StepCounter.countSteps(scenario),
                KeywordStepCounter.countKeywordSteps(scenario),
                MissingActorVisitor.findMissingActorSteps(scenario));
    }
}
