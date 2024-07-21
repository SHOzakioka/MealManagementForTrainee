package jp.co.meal_management.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;

@Repository
public interface BodyMetricsRepository extends JpaRepository<BodyMetrics, BodyMetricsId> {

	@Override
	Optional<BodyMetrics> findById(BodyMetricsId id);
}
