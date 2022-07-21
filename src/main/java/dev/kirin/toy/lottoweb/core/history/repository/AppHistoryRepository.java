package dev.kirin.toy.lottoweb.core.history.repository;

import dev.kirin.toy.lottoweb.core.history.entity.AppHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppHistoryRepository extends JpaRepository<AppHistory, Integer> {
}
