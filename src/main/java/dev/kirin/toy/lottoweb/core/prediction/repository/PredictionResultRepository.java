package dev.kirin.toy.lottoweb.core.prediction.repository;

import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionResult;
import dev.kirin.toy.lottoweb.core.prediction.entity.id.PredictionResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionResultRepository extends JpaRepository<PredictionResult, PredictionResultId> {
    List<PredictionResult> findAllByRoundNo(Integer roundNo);
}
