package dev.kirin.toy.lottoweb.common.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter(AccessLevel.MODULE)
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("app")
public class AppProperties {
    private String keyFile;
}
