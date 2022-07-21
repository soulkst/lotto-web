package dev.kirin.toy.lottoweb.api.controller;

import dev.kirin.toy.lottoweb.api.facade.AppHistoryFacade;
import dev.kirin.toy.lottoweb.common.vo.Pageable;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistorySearchParam;
import dev.kirin.toy.lottoweb.core.history.vo.AppHistoryVo;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/history", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AppHistoryController {
    private final AppHistoryFacade facade;

    @GetMapping
    @Validated
    public Pageable<AppHistoryVo> list(@ModelAttribute Optional<AppHistorySearchParam> param) {
        return facade.list(param.orElse(new AppHistorySearchParam()));
    }
}
