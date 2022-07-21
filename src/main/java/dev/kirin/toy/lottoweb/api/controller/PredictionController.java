package dev.kirin.toy.lottoweb.api.controller;

import dev.kirin.toy.lottoweb.api.facade.PredictionFacade;
import dev.kirin.toy.lottoweb.api.vo.PredictionNumberMapVo;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionResult;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/prediction", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PredictionController {
    private PredictionFacade facade;

    @GetMapping
    public PredictionNumberMapVo getPredictionMap() {
        return facade.getPredictionMap();
    }

    @GetMapping(value = "/{roundNo}/result")
    public List<PredictionResult> getLastPredictionResult(@PathVariable Integer roundNo) {
        return facade.getLastPredictionResult(roundNo);
    }
}
