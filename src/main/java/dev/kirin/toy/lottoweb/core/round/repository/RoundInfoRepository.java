package dev.kirin.toy.lottoweb.core.round.repository;

import dev.kirin.toy.lottoweb.core.round.entity.RoundInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundInfoRepository extends JpaRepository<RoundInfo, Integer> {
    RoundInfo findFirstByOrderByRoundNoDesc();
}
