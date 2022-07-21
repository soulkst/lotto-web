package dev.kirin.toy.lottoweb.core.scrap.definition;

import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Validated
public class DateDefinition extends DataDefinition {
    @NotNull
    private String format;

    public DateDefinition(@NotNull @NotEmpty String path, @NotNull DataType type, String format) {
        super(path, type);
        this.format = format;
    }
}
