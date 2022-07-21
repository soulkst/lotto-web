package dev.kirin.toy.lottoweb.core.scrap.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kirin.toy.lottoweb.common.exception.AlreadyExistsException;
import dev.kirin.toy.lottoweb.common.exception.BadRequestException;
import dev.kirin.toy.lottoweb.common.exception.NotFoundException;
import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.core.scrap.service.ScrapConfigService;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrapConfigRequestVo;
import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.repository.ScrapConfigRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ScrapConfigServiceImpl implements ScrapConfigService {
    private final ScrapConfigRepository repository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public ScrapConfig createConfig(ScrapType type, ScrapConfigRequestVo param) {
        if(repository.existsById(type)) {
            throw new AlreadyExistsException(type);
        }

        try {
            ScrapDefinition definition = objectMapper.readValue(param.getDefinition(), ScrapDefinition.class);
            ScrapConfig entity = ScrapConfig.builder()
                    .url(param.getUrl())
                    .basePath(param.getBasePath())
                    .definition(definition)
                    .build();
            return repository.save(entity);
        } catch (JsonProcessingException e) {
            log.error("(createConfig) Cannot convert to definition. Cause : {}", e.getLocalizedMessage());
            log.debug("(createConfig) Cannot convert to definition. (stack-trace)", e);
            throw new BadRequestException(e);
        }
    }

    @Transactional
    @Override
    public ScrapConfig updateConfig(ScrapType type, ScrapConfigRequestVo param) {
        ScrapConfig scrapConfig = repository.findById(type)
                .orElseThrow(() -> new NotFoundException(type));
        if(StringUtils.hasText(param.getUrl())) {
            scrapConfig.setUrl(param.getUrl());
        }
        if(StringUtils.hasText(param.getBasePath())) {
            scrapConfig.setBasePath(param.getBasePath());
        }
        if(StringUtils.hasText(param.getDefinition())) {
            try {
                ScrapDefinition definition = objectMapper.readValue(param.getDefinition(), ScrapDefinition.class);
                scrapConfig.setDefinition(definition);
            } catch (JsonProcessingException e) {
                log.error("(updateConfig) Cannot convert to definition. Cause : {}", e.getLocalizedMessage());
                log.debug("(updateConfig) Cannot convert to definition. (stack-trace)", e);
                throw new BadRequestException(e);
            }
        }
        return scrapConfig;
    }

    @Override
    public ScrapConfig getConfig(ScrapType type) {
        return repository.findById(type)
                .orElseThrow(() -> new NotFoundException(type));
    }
}
