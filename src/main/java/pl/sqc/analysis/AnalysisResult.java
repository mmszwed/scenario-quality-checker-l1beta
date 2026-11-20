package pl.sqc.analysis;

import java.util.List;

public record AnalysisResult(int stepCount, int keywordStepCount, List<MissingActorStep> missingActorSteps) {

    public record MissingActorStep(String path, String text) {
    }
}
