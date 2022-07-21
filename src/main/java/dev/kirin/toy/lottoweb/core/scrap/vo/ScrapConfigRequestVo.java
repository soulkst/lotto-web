package dev.kirin.toy.lottoweb.core.scrap.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScrapConfigRequestVo {
    @NotNull
    @NotEmpty
    private String url;
    @NotNull
    @NotEmpty
    private String basePath;
    @NotNull
    @NotEmpty
    private String definition;
}
