package dev.kirin.toy.lottoweb.core.scrap.entity.type;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.DateDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ScrapDefinition {
    private DataDefinition no;
    private DateDefinition drawDate;
    private DataDefinition numbers;
    private DataDefinition bonus;
}
