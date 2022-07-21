package dev.kirin.toy.lottoweb.batch.scheduler;

import dev.kirin.toy.lottoweb.batch.code.BatchMode;
import dev.kirin.toy.lottoweb.api.facade.DailyScrapBatchFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ScrappingBatch {
    private final DailyScrapBatchFacade facade;
    @Scheduled(cron = "0 0 1 * * SUN")
//    @Scheduled(cron = "0 * * * * *")  // for local test
    public void scrapping() {
        if(!facade.scrapData(BatchMode.AUTO)) {
            log.warn("(scrapping) Cannot scrap data.");
            return;
        }
        log.info("(scrapping) Scrapped data successfully.");
    }
}
