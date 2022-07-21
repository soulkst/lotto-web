package dev.kirin.toy.lottoweb.core.scrap.entity;

import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.entity.converter.DefinitionConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ScrapConfig {
    @Id
    @Enumerated(EnumType.STRING)
    @NotNull
    private ScrapType scrapType;

    @Lob
    private String url;

    @Lob
    private String basePath;

    @Lob
    @Convert(converter = DefinitionConverter.class)
    private ScrapDefinition definition;

    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.lastUpdated = LocalDateTime.now();
    }
}
