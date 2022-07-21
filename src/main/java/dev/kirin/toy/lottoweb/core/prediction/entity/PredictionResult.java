package dev.kirin.toy.lottoweb.core.prediction.entity;

import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;
import dev.kirin.toy.lottoweb.core.prediction.entity.id.PredictionResultId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(PredictionResultId.class)
public class PredictionResult {
    @Id
    private Integer roundNo;

    @Id
    private PredictionType predictionType;

    @Lob
    @Size(min = 7, max = 7)
    private List<Integer> numbers;

    @NotNull
    @Min(0)
    private Double hitRate;
}
