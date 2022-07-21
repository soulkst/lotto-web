package dev.kirin.toy.lottoweb.api.controller;

import dev.kirin.toy.lottoweb.api.facade.DailyScrapBatchFacade;
import dev.kirin.toy.lottoweb.batch.code.BatchId;
import dev.kirin.toy.lottoweb.batch.code.BatchMode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/batch")
public class BatchExecuteController {
    private final DailyScrapBatchFacade facade;


    @PostMapping(value = "/{batchId}/exec")
    public HttpStatus executeManually(@PathVariable("batchId") BatchId batchId) {
        switch (batchId) {
            case SCRAP:
                return facade.scrapData(BatchMode.MANUAL) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
