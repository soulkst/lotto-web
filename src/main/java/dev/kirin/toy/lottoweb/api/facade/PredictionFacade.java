package dev.kirin.toy.lottoweb.api.facade;

import dev.kirin.toy.lottoweb.api.vo.PredictionNumberMapVo;
import dev.kirin.toy.lottoweb.core.prediction.entity.Prediction;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionResult;
import dev.kirin.toy.lottoweb.core.prediction.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PredictionFacade {
    private final PredictionService service;

    public PredictionNumberMapVo getPredictionMap() {
        Prediction lastPrediction = service.getLastPrediction();
        Map<Integer, Integer> numberMap = lastPrediction.getPredictions().stream()
                .collect(Collectors.toMap(PredictionItem::getNo, PredictionItem::getAppearCount));
        return PredictionNumberMapVo.builder()
                .roundNo(lastPrediction.getRoundNo())
                .numberMap(numberMap)
                .build();
    }

    public List<PredictionResult> getLastPredictionResult(Integer roundNo) {
        return service.getResult(roundNo);
    }
}
