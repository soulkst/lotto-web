package dev.kirin.toy.lottoweb.core.scrap.service;

import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import dev.kirin.toy.lottoweb.core.scrap.vo.ScrapConfigRequestVo;
import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;

public interface ScrapConfigService {
    ScrapConfig createConfig(ScrapType type, ScrapConfigRequestVo param);
    ScrapConfig updateConfig(ScrapType type, ScrapConfigRequestVo param);
    ScrapConfig getConfig(ScrapType type);
}
