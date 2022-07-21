package dev.kirin.toy.lottoweb.api.controller;

import dev.kirin.toy.lottoweb.api.facade.RoundInfoFacade;
import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/round", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RoundInfoController {
    private final RoundInfoFacade facade;

    @GetMapping(value = "/last")
    public RoundInfo getLast() {
        return facade.getLastRound();
    }
}
