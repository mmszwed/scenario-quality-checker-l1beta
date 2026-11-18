package pl.sqc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.sqc.model.Scenario;
import pl.sqc.model.Step;
import tools.jackson.databind.JsonNode;

final class ScenarioRequestMapper {

    private ScenarioRequestMapper() {
    }

    static Scenario toScenario(JsonNode body) {
        if (!body.isObject()) {
            throw invalid("Scenariusz musi być obiektem JSON");
        }
        return new Scenario(
                text(body.path("title"), "title"),
                list(body.path("actors"), "actors", actor -> text(actor, "actors")),
                text(body.path("systemActor"), "systemActor"),
                list(body.path("steps"), "steps", ScenarioRequestMapper::toStep));
    }

    private static Step toStep(JsonNode node) {
        if (!node.isObject()) {
            throw invalid("Krok musi być obiektem JSON");
        }
        return new Step(
                text(node.path("text"), "text"),
                list(node.path("subSteps"), "subSteps", ScenarioRequestMapper::toStep));
    }

    private static String text(JsonNode node, String field) {
        if (!node.isString() || node.asString().isBlank()) {
            throw invalid("Pole " + field + " musi zawierać niepusty tekst");
        }
        return node.asString();
    }

    private static <Element> List<Element> list(JsonNode node, String field, Function<JsonNode, Element> mapper) {
        if (node.isMissingNode() || node.isNull()) {
            return List.of();
        }
        if (!node.isArray()) {
            throw invalid("Pole " + field + " musi być tablicą");
        }
        List<Element> result = new ArrayList<>(node.size());
        for (JsonNode element : node) {
            result.add(mapper.apply(element));
        }
        return result;
    }

    private static ResponseStatusException invalid(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }
}
