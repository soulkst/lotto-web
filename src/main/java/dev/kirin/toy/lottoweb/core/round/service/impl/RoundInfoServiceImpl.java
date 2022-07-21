package dev.kirin.toy.lottoweb.core.round.service.impl;

import dev.kirin.toy.lottoweb.common.exception.AlreadyExistsException;
import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import dev.kirin.toy.lottoweb.core.round.repository.RoundInfoRepository;
import dev.kirin.toy.lottoweb.core.round.service.RoundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoundInfoServiceImpl implements RoundInfoService {
    @Autowired
    private RoundInfoRepository repository;

    @Override
    @Transactional
    public void newInfo(RoundInfo roundInfo) {
        RoundInfo existsRound = repository.findById(roundInfo.getRoundNo()).orElse(null);
        if(existsRound != null) {
            throw new AlreadyExistsException(roundInfo.getRoundNo());
        }
        repository.save(roundInfo);
    }

    @Override
    public List<RoundInfo> getRounds() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void newInfos(List<RoundInfo> roundInfoList) {
        repository.saveAll(roundInfoList);
    }

    @Override
    public RoundInfo getLastRound() {
        return repository.findFirstByOrderByRoundNoDesc();
    }

    @Override
    public Integer getLastRoundNo() {
        Integer lastRoundNo = 0;
        RoundInfo lastRoundInfo = repository.findFirstByOrderByRoundNoDesc();
        if(lastRoundInfo != null && lastRoundInfo.getRoundNo() != null) {
            lastRoundNo = lastRoundInfo.getRoundNo();
        }
        return lastRoundNo;
    }
}
