package dev.kirin.toy.lottoweb.core.history.vo;

import dev.kirin.toy.lottoweb.common.vo.SearchOrderVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AppHistorySearchParam {
    private int page;
    private String category;
    private String type;
    private String level;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    private String message;
    private List<SearchOrderVo> order;
}
