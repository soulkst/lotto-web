package dev.kirin.toy.lottoweb.api.facade;

import dev.kirin.toy.lottoweb.batch.code.BatchHistoryType;
import dev.kirin.toy.lottoweb.batch.code.BatchMode;
import dev.kirin.toy.lottoweb.batch.exception.BatchException;
import dev.kirin.toy.lottoweb.common.exception.AlreadyExistsException;
import dev.kirin.toy.lottoweb.core.history.code.HistoryLevel;
import dev.kirin.toy.lottoweb.core.history.service.AppHistoryService;
import dev.kirin.toy.lottoweb.core.prediction.dto.PredictionRoundDTO;
import dev.kirin.toy.lottoweb.core.prediction.service.PredictionService;
import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import dev.kirin.toy.lottoweb.core.round.service.RoundInfoService;
import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import dev.kirin.toy.lottoweb.core.scrap.parser.ScrapDataParser;
import dev.kirin.toy.lottoweb.core.scrap.service.ScrapConfigService;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrappedRoundVo;
import dev.kirin.toy.lottoweb.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class DailyScrapBatchFacade {
    private final ScrapConfigService configService;
    private final ScrapDataParser parser;
    private final RoundInfoService roundInfoService;
    private final AppHistoryService historyService;
    private final PredictionService predictionService;

    public boolean scrapData(BatchMode mode) {
        BatchHistoryType historyType = BatchHistoryType.SCRAP;
        try {
            ScrapConfig dailyConfig = configService.getConfig(ScrapType.DAILY);

            ScrappedRoundVo dailyScrappedData = parser.parse(dailyConfig.getUrl(), dailyConfig.getBasePath(), dailyConfig.getDefinition());
            log.info("(scrapData) Scrapped data : {}", dailyScrappedData);

            Integer currentRound = dailyScrappedData.getNo();
            Integer lastRound = roundInfoService.getLastRoundNo();

            boolean isRequiredAll = (currentRound - lastRound) > 1;
            if(!isRequiredAll) {
                try {
                    roundInfoService.newInfo(RoundInfo.fromScrappedData(dailyScrappedData));
                    historyService.addHistory(historyType, mode, HistoryLevel.OK, StringUtils.format("최근 회차 '{}' 데이터 수집 완료.", currentRound));
                } catch (AlreadyExistsException e) {
                    historyService.addHistory(historyType, mode, HistoryLevel.WARN, StringUtils.format("최근 회차 '{}' 중복 데이터.(무시)", currentRound));
                }
            } else {
                historyType = BatchHistoryType.SCRAP_ALL;
                ScrapConfig allConfig = configService.getConfig(ScrapType.ALL);
                log.debug("(scrapData) request round. lastRound = {}, currentRound = {}", lastRound + 1, currentRound);
                String url = StringUtils.format(allConfig.getUrl(), lastRound, currentRound);
                List<ScrappedRoundVo> allScrappedDataList = parser.parseList(url, allConfig.getBasePath(), allConfig.getDefinition());
                List<RoundInfo> newRounds = allScrappedDataList.stream()
                        .filter(scrappedData -> scrappedData.getNo() > lastRound)
                        .sorted()
                        .map(RoundInfo::fromScrappedData).collect(Collectors.toList());

                Integer start = newRounds.get(0).getRoundNo();
                Integer end = newRounds.get(newRounds.size() - 1).getRoundNo();

                log.info("(scrapData) Scrapped data : '{}' ~ '{}'", start, end);

                roundInfoService.newInfos(newRounds);
                historyService.addHistory(historyType, mode, HistoryLevel.OK, StringUtils.format("회차 '{}' ~ '{}' 데이터 수집 완료.", start, end));
            }

            List<PredictionRoundDTO> rounds = roundInfoService.getRounds().stream()
                    .map(PredictionRoundDTO::fromEntity)
                    .collect(Collectors.toList());
            predictionService.updatePredictions(rounds);

            log.info("(scrapData) update all predictions");
            return true;
        } catch (Exception e) {
            log.error("(scrapData) Scrap error. Cause : {}", e.getLocalizedMessage());
            log.debug("(scrapData) Scrap error (stack-trace)", e);
            historyService.addHistory(historyType, mode, HistoryLevel.FAIL, e.getLocalizedMessage());
            throw new BatchException(e);
        }
    }
}
