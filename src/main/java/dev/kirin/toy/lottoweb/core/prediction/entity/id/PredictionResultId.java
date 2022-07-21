package dev.kirin.toy.lottoweb.core.prediction.entity.id;

import dev.kirin.toy.lottoweb.core.prediction.code.PredictionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PredictionResultId implements Serializable {
    private Integer roundNo;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PredictionType predictionType;
}
