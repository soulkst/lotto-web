package dev.kirin.toy.lottoweb.core.scrap.parser;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataValueHandler;
import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrappedRoundVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Component
public class ScrapDataParser {
    protected final DataValueHandler valueHandler;

    public final List<ScrappedRoundVo> parseList(String url, String basePath, ScrapDefinition scrapDefinition) {
        try {
            Document document = Jsoup.connect(url).ignoreContentType(true).get();
            return parseScrapData(document.select(basePath), scrapDefinition);
        } catch (IOException e) {
            throw new ScrapParserException(e);
        }
    }

    public final List<ScrappedRoundVo> parseList(String basePath, ScrapDefinition scrapDefinition, String html) {
        Document document = Jsoup.parse(html);
        return parseScrapData(document.select(basePath), scrapDefinition);
    }

    public final ScrappedRoundVo parse(String url, String basePath, ScrapDefinition scrapDefinition) {
        return parseList(url, basePath, scrapDefinition).get(0);
    }

    public final ScrappedRoundVo parse(String basePath, ScrapDefinition scrapDefinition, String html) {
        return parseList(basePath, scrapDefinition, html).get(0);
    }

    protected final List<ScrappedRoundVo> parseScrapData(Elements elements, ScrapDefinition scrapDefinition) {
        return elements.stream()
                .map(element -> getValue(element, scrapDefinition))
                .collect(Collectors.toList());
    }

    protected ScrappedRoundVo getValue(Element element, ScrapDefinition scrapDefinition) {
        return ScrappedRoundVo.builder()
                .no(valueHandler.getValue(element, scrapDefinition.getNo()))
                .drawDate(valueHandler.getValue(element, scrapDefinition.getDrawDate()))
                .numbers(valueHandler.getValue(element, scrapDefinition.getNumbers()))
                .bonus(valueHandler.getValue(element, scrapDefinition.getBonus()))
                .build();
    }
}
