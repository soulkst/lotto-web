package dev.kirin.toy.lottoweb.common.vo;

import dev.kirin.toy.lottoweb.common.code.ListOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SearchOrderVo {
    private String field;
    private ListOrder order;
}
