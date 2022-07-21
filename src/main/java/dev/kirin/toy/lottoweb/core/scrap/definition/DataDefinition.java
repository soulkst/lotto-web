package dev.kirin.toy.lottoweb.core.scrap.definition;

import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class DataDefinition {
    @NotNull
    @NotEmpty
    private String path;
    @NotNull
    private DataType type;
}
