package dev.kirin.toy.lottoweb.core.prediction.entity;

import dev.kirin.toy.lottoweb.core.prediction.entity.converter.PredictionItemConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Prediction {
    @Id
    private Integer roundNo;

    @Lob
    @Convert(converter = PredictionItemConverter.class)
    private List<PredictionItem> predictions;
}
