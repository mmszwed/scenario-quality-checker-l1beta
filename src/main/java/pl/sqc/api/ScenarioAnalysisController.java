package pl.sqc.api;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.sqc.analysis.AnalysisResult;
import pl.sqc.analysis.ScenarioAnalyzer;
import pl.sqc.model.Scenario;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/scenarios")
public class ScenarioAnalysisController {

    private final ObjectProvider<ScenarioAnalyzer> analyzers;

    public ScenarioAnalysisController(ObjectProvider<ScenarioAnalyzer> analyzers) {
        this.analyzers = analyzers;
    }

    @PostMapping(value = "/analyze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnalysisResult analyze(@RequestBody JsonNode body) {
        Scenario scenario = ScenarioRequestMapper.toScenario(body);
        ScenarioAnalyzer analyzer = analyzers.getIfAvailable();
        if (analyzer == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Analizy są niedostępne");
        }
        return analyzer.analyze(scenario);
    }
}
