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

@RestController
@RequestMapping("/api/scenarios")
public class ScenarioAnalysisController {

    private final ScenarioAnalyzer analyzer;

    public ScenarioAnalysisController(ScenarioAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @PostMapping(value = "/analyze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnalysisResult analyze(@RequestBody JsonNode body) {
        Scenario scenario = ScenarioRequestMapper.toScenario(body);
        return analyzer.analyze(scenario);
    }
}
