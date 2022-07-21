package dev.kirin.toy.lottoweb.scrap.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.DataValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.definition.DateDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.core.scrap.definition.handler.DateValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.definition.handler.NoneValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.definition.handler.NumberListValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.definition.handler.NumberValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.core.scrap.parser.ScrapDataParser;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrappedRoundVo;
import dev.kirin.toy.lottoweb.util.FileUtils;
import dev.kirin.toy.lottoweb.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Slf4j
@DisplayName("Scrap Data Parser Test")
@ExtendWith(MockitoExtension.class)
public class ScrapDataParserTest {
    private static ScrapDataParser parser;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        DataValueHandler valueHandler = new DataValueHandler(Arrays.asList(new NumberValueHandler()
                , new DateValueHandler(), new NumberListValueHandler(), new NoneValueHandler()));

        ScrapDataParserTest.parser = new ScrapDataParser(valueHandler);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Daily data(current) parser")
    public void testDailyData() throws IOException {
        ScrapDefinition definition = ScrapDefinition.builder()
                .no(new DataDefinition("h4 > strong", DataType.NUMBER))
                .drawDate(new DateDefinition("p.desc", DataType.DATE, "yyyyMMdd"))
                .numbers(new DataDefinition("div.num.win > p > span", DataType.NUMBER_LIST))
                .bonus(new DataDefinition("div.num.bonus > p > span", DataType.NUMBER))
                .build();
        log.debug("(testDailyData) definition : {}", JsonUtils.toJson(definition));
        ScrapConfig scrapConfig = ScrapConfig.builder()
                .basePath("div.win_result")
                .scrapType(ScrapType.DAILY)
                .definition(definition)
                .build();
        String html = loadHtmlData("classpath:test-data/latest.html");
        ScrappedRoundVo scrappedRound = parser.parse(scrapConfig.getBasePath(), scrapConfig.getDefinition(), html);
        log.debug("(testDailyData) scrapped data : {}", objectMapper.writeValueAsString(scrappedRound));

        Assertions.assertNotNull(scrappedRound);
    }

    @Test
    @DisplayName("All data(current) parser")
    public void testAllData() throws IOException {
        ScrapDefinition definition = ScrapDefinition.builder()
                .no(new DataDefinition("td:nth-child(1)", DataType.NUMBER))
                .drawDate(new DateDefinition("td:nth-child(2)", DataType.DATE, "yyyyMMdd"))
                .numbers(new DataDefinition("td:nth-child(n+13):nth-last-child(n+2)", DataType.NUMBER_LIST))
                .bonus(new DataDefinition("td:last-child", DataType.NUMBER))
                .build();
        log.debug("(testAllData) definition : {}", JsonUtils.toJson(definition));
        ScrapConfig scrapConfig = ScrapConfig.builder()
                .basePath("body > table:last-child tr:nth-child(n+3):not(:has(td[rowspan]))")
                .scrapType(ScrapType.ALL)
                .definition(definition)
                .build();
        String html = loadHtmlData("classpath:test-data/all.html");
        List<ScrappedRoundVo> scrappedRound = parser.parseList(scrapConfig.getBasePath(), scrapConfig.getDefinition(), html);
        log.debug("(testAllData) scrapped data : {}", objectMapper.writeValueAsString(scrappedRound));

        Assertions.assertNotNull(scrappedRound);
    }

    private String loadHtmlData(String path) throws FileNotFoundException {
        String file = ResourceUtils.getURL(path).getFile();
        return FileUtils.readAsString(file, Charset.forName("EUC-KR"));
    }
}
