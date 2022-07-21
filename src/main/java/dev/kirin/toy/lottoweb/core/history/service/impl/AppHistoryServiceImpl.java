package dev.kirin.toy.lottoweb.core.history.service.impl;

import dev.kirin.toy.lottoweb.common.vo.Pageable;
import dev.kirin.toy.lottoweb.core.history.service.AppHistoryService;
import dev.kirin.toy.lottoweb.core.history.code.HistoryLevel;
import dev.kirin.toy.lottoweb.core.history.entity.AppHistory;
import dev.kirin.toy.lottoweb.core.history.repository.AppHistoryDAO;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistorySearchParam;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistoryVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AppHistoryServiceImpl implements AppHistoryService {
    private final AppHistoryDAO dao;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void addHistory(Enum<?> category, Enum<?> type, HistoryLevel level, String message) {
        try {
            AppHistory history = AppHistory.builder()
                    .historyDate(LocalDateTime.now())
                    .historyCategory(category.name())
                    .historyType(type.name())
                    .message(message)
                    .historyLevel(level)
                    .build();
            dao.save(history);
        } catch (Exception e) {
            log.error("(addHistory) Cannot add history. Cause : {}", e.getLocalizedMessage());
            log.debug("(addHistory) Cannot add history (stack-trace)", e);
        }

    }

    @Override
    public Pageable<AppHistoryVo> getHistories(AppHistorySearchParam param) {
        int tmpPage = param.getPage() < 0 ? 1 : param.getPage();
        Page<AppHistory> pageable = dao.findAllPageable(param, tmpPage, SIZE);
        List<AppHistoryVo> histories = pageable.getContent().stream()
                .map(AppHistoryVo::fromEntity)
                .collect(Collectors.toList());
        return Pageable.from(pageable, histories);
    }
}
