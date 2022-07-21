package dev.kirin.toy.lottoweb.core.scrap.repository;

import dev.kirin.toy.lottoweb.core.scrap.code.ScrapType;
import dev.kirin.toy.lottoweb.core.scrap.entity.ScrapConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapConfigRepository extends JpaRepository<ScrapConfig, ScrapType> {
}
