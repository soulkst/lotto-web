package dev.kirin.toy.lottoweb.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PredictionNumberMapVo {
    private int roundNo;
    private Map<Integer, Integer> numberMap;
}
