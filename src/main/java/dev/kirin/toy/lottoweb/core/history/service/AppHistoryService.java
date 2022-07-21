package dev.kirin.toy.lottoweb.core.history.service;

import dev.kirin.toy.lottoweb.common.service.PageableService;
import dev.kirin.toy.lottoweb.common.vo.Pageable;
import dev.kirin.toy.lottoweb.core.history.code.HistoryLevel;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistorySearchParam;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistoryVo;

public interface AppHistoryService extends PageableService {
    void addHistory(Enum<?> category, Enum<?> type, HistoryLevel level, String message);

    Pageable<AppHistoryVo> getHistories(AppHistorySearchParam param);
}
