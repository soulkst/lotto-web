package dev.kirin.toy.lottoweb.core.prediction.service.impl;

import dev.kirin.toy.lottoweb.core.prediction.dto.PredictionRoundDTO;
import dev.kirin.toy.lottoweb.core.prediction.entity.Prediction;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionResult;
import dev.kirin.toy.lottoweb.core.prediction.repository.PredictionRepository;
import dev.kirin.toy.lottoweb.core.prediction.repository.PredictionResultRepository;
import dev.kirin.toy.lottoweb.core.prediction.service.PredictionService;
import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository repository;
    private final PredictionResultRepository resultRepository;

    @Override
    public Prediction getLastPrediction() {
        return repository.findFirstByOrderByRoundNoDesc();
    }

    @Override
    public List<PredictionResult> getResults() {
        return resultRepository.findAll();
    }

    @Override
    public List<PredictionResult> getResult(Integer roundNo) {
        return resultRepository.findAllByRoundNo(roundNo);
    }

    @Override
    @Transactional
    public void updatePredictions(List<PredictionRoundDTO> rounds) {
        List<Prediction> predictions = new ArrayList<>();
        List<PredictionResult> predictionResults = new ArrayList<>();

        Map<Integer, Integer> numberMap = IntStream.rangeClosed(START_NUM, END_NUM)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));


        List<PredictionType> types = Arrays.asList(PredictionType.values());

        Map<Integer, List<Integer>> roundNumbersMap = rounds.stream()
                .collect(Collectors.toMap(PredictionRoundDTO::getNo, PredictionRoundDTO::getNumbers));
        roundNumbersMap.forEach((key, value) -> {
            int predictionRoundNo = key + 1;
            value.forEach(num -> numberMap.put(num, numberMap.getOrDefault(num, 0) + 1));
            List<PredictionItem> predictionItems = numberMap.entrySet().stream()
                    .map(entry -> new PredictionItem(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
            types.forEach(type -> {
                List<Integer> predictedNumbers = predictionItems.stream()
                        .sorted(type.getSorter())
                        .limit(7)
                        .map(PredictionItem::getNo)
                        .collect(Collectors.toList());
                Double hitRate = null;
                if(roundNumbersMap.containsKey(predictionRoundNo)) {
                    hitRate = roundNumbersMap.get(predictionRoundNo).stream()
                            .filter(predictedNumbers::contains)
                            .count() * 1.0 / predictedNumbers.size();
                }
                PredictionResult predictionResult = PredictionResult.builder()
                        .roundNo(predictionRoundNo)
                        .predictionType(type)
                        .numbers(predictedNumbers)
                        .hitRate(hitRate)
                        .build();
                predictionResults.add(predictionResult);

            });
            predictions.add(new Prediction(key, predictionItems));
        });
        repository.deleteAll();
        repository.saveAll(predictions);

        resultRepository.deleteAll();
        resultRepository.saveAll(predictionResults);
    }

}
