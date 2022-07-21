package dev.kirin.toy.lottoweb.api.facade;

import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import dev.kirin.toy.lottoweb.core.round.service.RoundInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoundInfoFacade {
    private final RoundInfoService service;

    public RoundInfo getLastRound() {
        return service.getLastRound();
    }
}
