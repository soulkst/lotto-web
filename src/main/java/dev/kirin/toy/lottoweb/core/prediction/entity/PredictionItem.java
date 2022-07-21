package dev.kirin.toy.lottoweb.core.prediction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PredictionItem {
    private int no;
    private int appearCount;
}
