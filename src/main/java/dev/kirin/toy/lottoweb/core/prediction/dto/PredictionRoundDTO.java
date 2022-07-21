package dev.kirin.toy.lottoweb.core.prediction.dto;

import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PredictionRoundDTO implements Comparable<PredictionRoundDTO> {
    private Integer no;
    private List<Integer> numbers;

    public static PredictionRoundDTO fromEntity(RoundInfo roundInfo) {
        List<Integer> numbers = new ArrayList<>(roundInfo.getNumbers());
        numbers.add(roundInfo.getBonus());
        return PredictionRoundDTO.builder()
                .no(roundInfo.getRoundNo())
                .numbers(numbers)
                .build();
    }

    @Override
    public int compareTo(PredictionRoundDTO o) {
        return Integer.compare(getNo(), o.getNo());
    }
}
