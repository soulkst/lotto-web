package dev.kirin.toy.lottoweb.core.scrap.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScrappedRoundVo implements Comparable<ScrappedRoundVo> {
    private Integer no;
    private LocalDate drawDate;
    private List<Integer> numbers;
    private Integer bonus;

    @Override
    public int compareTo(ScrappedRoundVo o) {
        return Integer.compare(getNo(), o.getNo());
    }
}
