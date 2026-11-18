package pl.sqc.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pl.sqc.analysis.AnalysisResult;
import pl.sqc.analysis.AnalysisResult.MissingActorStep;
import pl.sqc.analysis.ScenarioAnalyzer;
import pl.sqc.model.Scenario;
import pl.sqc.model.Step;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScenarioAnalysisIntegrationTest {

    @LocalServerPort
    private int port;

    @MockitoBean
    private ScenarioAnalyzer analyzer;

    @Test
    void mapsNestedScenarioAndReturnsAnalysisResultAsJson() throws IOException, InterruptedException {
        Scenario scenario = new Scenario("Dodanie książki", List.of("Bibliotekarz"), "System",
                List.of(new Step("IF: Bibliotekarz dodaje książkę",
                        List.of(new Step("Wyświetla się formularz")))));
        when(analyzer.analyze(scenario)).thenReturn(
                new AnalysisResult(2, 1, List.of(new MissingActorStep("1.1.", "Wyświetla się formularz"))));

        HttpResponse<String> response = post("""
                {
                  "title": "Dodanie książki",
                  "actors": ["Bibliotekarz"],
                  "systemActor": "System",
                  "steps": [{
                    "text": "IF: Bibliotekarz dodaje książkę",
                    "subSteps": [{"text": "Wyświetla się formularz"}]
                  }]
                }
                """);

        assertThat(response.statusCode()).isEqualTo(200);
        ObjectMapper mapper = new ObjectMapper();
        assertThat(mapper.readTree(response.body())).isEqualTo(mapper.readTree("""
                {
                  "stepCount": 2,
                  "keywordStepCount": 1,
                  "missingActorSteps": [{"path": "1.1.", "text": "Wyświetla się formularz"}]
                }
                """));
        verify(analyzer).analyze(scenario);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "{",
            "[]",
            """
            {"systemActor":"System"}
            """,
            """
            {"title":123,"systemActor":"System"}
            """,
            """
            {"title":"Test","systemActor":"System","actors":"Bibliotekarz"}
            """,
            """
            {"title":"Test","systemActor":"System","steps":[{"text":"Krok","subSteps":[{"text":" "}]}]}
            """,
            """
            {"title":"Test","systemActor":"System","steps":[null]}
            """
    })
    void rejectsInvalidInputBeforeCallingAnalyzer(String body) throws IOException, InterruptedException {
        assertThat(post(body).statusCode()).isEqualTo(400);
        verifyNoInteractions(analyzer);
    }

    private HttpResponse<String> post(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/api/scenarios/analyze"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
