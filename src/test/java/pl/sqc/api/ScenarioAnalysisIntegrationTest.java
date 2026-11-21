package pl.sqc.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScenarioAnalysisIntegrationTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Test
    void analyzesNestedScenarioAndReturnsAllResultsAsJson() throws IOException, InterruptedException {
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
        assertThat(mapper.readTree(response.body())).isEqualTo(mapper.readTree("""
                {
                  "stepCount": 2,
                  "keywordStepCount": 1,
                  "missingActorSteps": [{"path": "1.1.", "text": "Wyświetla się formularz"}]
                }
                """));
    }

    @Test
    void analyzesEmptyScenario() throws IOException, InterruptedException {
        HttpResponse<String> response = post("""
                {"title":"Pusty scenariusz","systemActor":"System"}
                """);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(mapper.readTree(response.body())).isEqualTo(mapper.readTree("""
                {"stepCount":0,"keywordStepCount":0,"missingActorSteps":[]}
                """));
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
    void returnsBadRequestForInvalidInput(String body) throws IOException, InterruptedException {
        assertThat(post(body).statusCode()).isEqualTo(400);
    }

    private HttpResponse<String> post(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/api/scenarios/analyze"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
