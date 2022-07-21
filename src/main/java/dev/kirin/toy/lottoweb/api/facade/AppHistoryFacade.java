package dev.kirin.toy.lottoweb.api.facade;

import dev.kirin.toy.lottoweb.common.vo.Pageable;
import dev.kirin.toy.lottoweb.core.history.service.AppHistoryService;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistorySearchParam;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistoryVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppHistoryFacade {
    private final AppHistoryService service;

    public Pageable<AppHistoryVo> list(AppHistorySearchParam param) {
        return service.getHistories(param);
    }
}
