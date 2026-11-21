package pl.sqc.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StepKeywordsTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "IF: Bibliotekarz wybiera opcję | IF",
            "IF Bibliotekarz wybiera opcję | IF",
            "ELSE: System wyświetla błąd | ELSE",
            "ELSE | ELSE",
            "FOR EACH: egzemplarz | FOR EACH",
            "FOR EACH egzemplarz | FOR EACH",
            "FOR  EACH: egzemplarz | FOR EACH",
            "IF : Bibliotekarz wybiera opcję | IF"
    })
    void recognizesKeywordAtStart(String text, String keyword) {
        assertThat(StepKeywords.startsWithKeyword(text)).isTrue();
        assertThat(StepKeywords.keyword(text)).contains(keyword);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "IFx Bibliotekarz wybiera opcję",
            "IFEQUAL: coś",
            "ELSEWHERE",
            "FOREACH egzemplarz",
            "FOR EACHx egzemplarz",
            "Bibliotekarz wybiera IF: opcję",
            "System sprawdza, IF warunek",
            "if: Bibliotekarz wybiera opcję",
            "Bibliotekarz wybiera opcję"
    })
    void ignoresStepsWithoutKeywordAtStart(String text) {
        assertThat(StepKeywords.startsWithKeyword(text)).isFalse();
        assertThat(StepKeywords.keyword(text)).isEmpty();
    }

    @Test
    void stripsKeywordAndOptionalColon() {
        assertThat(StepKeywords.stripKeyword("IF: Bibliotekarz wybiera opcję")).isEqualTo("Bibliotekarz wybiera opcję");
        assertThat(StepKeywords.stripKeyword("IF Bibliotekarz wybiera opcję")).isEqualTo("Bibliotekarz wybiera opcję");
        assertThat(StepKeywords.stripKeyword("FOR EACH: egzemplarz")).isEqualTo("egzemplarz");
        assertThat(StepKeywords.stripKeyword("ELSE:")).isEmpty();
    }

    @Test
    void leavesStepWithoutKeywordUnchanged() {
        assertThat(StepKeywords.stripKeyword("Wyświetla się formularz")).isEqualTo("Wyświetla się formularz");
        assertThat(StepKeywords.stripKeyword("IFx coś")).isEqualTo("IFx coś");
    }
}
