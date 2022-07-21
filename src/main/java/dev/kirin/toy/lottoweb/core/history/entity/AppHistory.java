package dev.kirin.toy.lottoweb.core.history.entity;

import dev.kirin.toy.lottoweb.core.history.code.HistoryLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AppHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNum;

    @NotNull
    private LocalDateTime historyDate;

    @NotNull
    @Column(length = 20)
    private String historyCategory;

    @NotNull
    @Column(length = 20)
    private String historyType;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private HistoryLevel historyLevel;

    @Lob
    private String message;
}
