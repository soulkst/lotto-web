package dev.kirin.toy.lottoweb.core.round.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrappedRoundVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoundInfo {
    @Id
    private Integer roundNo;

    @Lob
    @Size(min = 6, max = 6)
    @NotNull
    private List<Integer> numbers;

    @NotNull
    @Min(1)
    @Max(42)
    private Integer bonus;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate drawDate;

    public static RoundInfo fromScrappedData(ScrappedRoundVo scrappedData) {
        return RoundInfo.builder()
                .roundNo(scrappedData.getNo())
                .drawDate(scrappedData.getDrawDate())
                .numbers(scrappedData.getNumbers())
                .bonus(scrappedData.getBonus())
                .build();
    }
}
