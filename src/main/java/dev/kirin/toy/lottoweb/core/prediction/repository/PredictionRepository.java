package dev.kirin.toy.lottoweb.core.prediction.repository;

import dev.kirin.toy.lottoweb.core.prediction.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Integer> {
    Prediction findFirstByOrderByRoundNoDesc();
}
