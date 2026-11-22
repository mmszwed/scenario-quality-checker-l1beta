package pl.sqc.analysis;

import java.util.List;

/**
 * Wynik analizy scenariusza zwracany przez REST API.
 *
 * <p>Łączy wyniki trzech analiz: liczbę wszystkich kroków, liczbę kroków ze słowem
 * kluczowym i listę kroków, które nie zaczynają się od aktora.
 *
 * @param stepCount         liczba wszystkich kroków na każdym poziomie zagnieżdżenia
 * @param keywordStepCount  liczba kroków zaczynających się od {@code IF}, {@code ELSE} albo {@code FOR EACH}
 * @param missingActorSteps kroki bez aktora w kolejności występowania; pusta lista, gdy ich nie ma
 */
public record AnalysisResult(int stepCount, int keywordStepCount, List<MissingActorStep> missingActorSteps) {

    /**
     * Krok, który nie zaczyna się od żadnego aktora z nagłówka scenariusza.
     *
     * @param path numeracja kroku, np. {@code "4.3."}
     * @param text oryginalna treść kroku
     */
    public record MissingActorStep(String path, String text) {
    }
}
