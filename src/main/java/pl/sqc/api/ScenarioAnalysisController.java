package pl.sqc.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sqc.analysis.AnalysisResult;
import pl.sqc.analysis.ScenarioAnalyzer;
import pl.sqc.model.Scenario;
import tools.jackson.databind.JsonNode;

/**
 * Endpoint REST do analizy scenariuszy: {@code POST /api/scenarios/analyze}.
 *
 * <p>Przyjmuje scenariusz w formacie JSON, zamienia go na model {@link Scenario}
 * i zwraca wyniki analizy. Niepoprawne dane kończą się odpowiedzią HTTP 400.
 */
@RestController
@RequestMapping("/api/scenarios")
public class ScenarioAnalysisController {

    /** Usługa wykonująca analizy scenariusza. */
    private final ScenarioAnalyzer analyzer;

    /**
     * Tworzy kontroler z usługą analizy wstrzykiwaną przez Springa.
     *
     * @param analyzer usługa wykonująca analizy scenariusza
     */
    public ScenarioAnalysisController(ScenarioAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    /**
     * Analizuje scenariusz przesłany w treści żądania.
     *
     * @param body scenariusz w JSON: {@code title}, {@code actors}, {@code systemActor}
     *             i {@code steps} z zagnieżdżonymi {@code subSteps}
     * @return liczba kroków, liczba kroków ze słowem kluczowym i kroki bez aktora
     * @throws org.springframework.web.server.ResponseStatusException z kodem 400,
     *         gdy JSON nie opisuje poprawnego scenariusza
     */
    @PostMapping(value = "/analyze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnalysisResult analyze(@RequestBody JsonNode body) {
        Scenario scenario = ScenarioRequestMapper.toScenario(body);
        return analyzer.analyze(scenario);
    }
}
