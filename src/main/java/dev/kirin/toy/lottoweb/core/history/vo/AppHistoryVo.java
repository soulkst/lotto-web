package dev.kirin.toy.lottoweb.core.history.vo;

import dev.kirin.toy.lottoweb.core.history.entity.AppHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AppHistoryVo {
    private long no;
    private LocalDateTime created;
    private String category;
    private String type;
    private String level;
    private String message;

    public static AppHistoryVo fromEntity(AppHistory entity) {
        return AppHistoryVo.builder()
                .no(entity.getHistoryNum())
                .created(entity.getHistoryDate())
                .category(entity.getHistoryCategory())
                .type(entity.getHistoryType())
                .level(entity.getHistoryLevel().name())
                .message(entity.getMessage())
                .build();
    }
}
