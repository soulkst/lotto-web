package dev.kirin.toy.lottoweb.core.scrap;

import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.DateDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.core.scrap.repository.ScrapConfigRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Configuration
@AllArgsConstructor
@Slf4j
public class ScrapConfigInitializer {
    private final ScrapConfigRepository repository;

    @PostConstruct
    @Transactional
    public void init() {
        log.info("Executed config initializer.");
        ScrapConfig dailyConfig = repository.findById(ScrapType.DAILY).orElse(null);
        if(dailyConfig == null) {
            ScrapDefinition definition = ScrapDefinition.builder()
                    .no(new DataDefinition("h4 > strong", DataType.NUMBER))
                    .drawDate(new DateDefinition("p.desc", DataType.DATE, "yyyyMMdd"))
                    .numbers(new DataDefinition("div.num.win > p > span", DataType.NUMBER_LIST))
                    .bonus(new DataDefinition("div.num.bonus > p > span", DataType.NUMBER))
                    .build();
            dailyConfig = ScrapConfig.builder()
                    .url("https://dhlottery.co.kr/gameResult.do?method=byWin")
                    .basePath("div.win_result")
                    .scrapType(ScrapType.DAILY)
                    .definition(definition)
                    .build();
            repository.save(dailyConfig);
            log.info("Create default daily config.");
        }
        ScrapConfig allConfig = repository.findById(ScrapType.ALL).orElse(null);
        if(allConfig == null) {
            ScrapDefinition definition = ScrapDefinition.builder()
                    .no(new DataDefinition("td:nth-child(1)", DataType.NUMBER))
                    .drawDate(new DateDefinition("td:nth-child(2)", DataType.DATE, "yyyyMMdd"))
                    .numbers(new DataDefinition("td:nth-child(n+13):nth-last-child(n+2)", DataType.NUMBER_LIST))
                    .bonus(new DataDefinition("td:last-child", DataType.NUMBER))
                    .build();
            allConfig = ScrapConfig.builder()
                    .url("https://dhlottery.co.kr/gameResult.do?method=allWinExel&gubun=byWin&nowPage=&drwNoStart={}&drwNoEnd={}")
                    .basePath("body > table:last-child tr:nth-child(n+3):not(:has(td[rowspan]))")
                    .scrapType(ScrapType.ALL)
                    .definition(definition)
                    .build();
            repository.save(allConfig);
            log.info("Create default all config.");
        }
        log.info("Finished config initializer.");
    }
}
