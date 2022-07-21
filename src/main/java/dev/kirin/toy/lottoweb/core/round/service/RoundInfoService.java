package dev.kirin.toy.lottoweb.core.round.service;


import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;

import java.util.List;

public interface RoundInfoService {
    void newInfo(RoundInfo roundInfo);

    List<RoundInfo> getRounds();

    void newInfos(List<RoundInfo> roundInfoList);

    RoundInfo getLastRound();

    Integer getLastRoundNo();
}
