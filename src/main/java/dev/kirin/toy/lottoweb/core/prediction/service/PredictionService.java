package dev.kirin.toy.lottoweb.core.prediction.service;

import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionResult;
import dev.kirin.toy.lottoweb.core.prediction.dto.PredictionRoundDTO;
import dev.kirin.toy.lottoweb.core.prediction.entity.Prediction;

import java.util.List;

public interface PredictionService {
    int START_NUM = 1;
    int END_NUM = 45;

    Prediction getLastPrediction();

    List<PredictionResult> getResults();

    List<PredictionResult> getResult(Integer roundNo);

    void updatePredictions(List<PredictionRoundDTO> rounds);
}
