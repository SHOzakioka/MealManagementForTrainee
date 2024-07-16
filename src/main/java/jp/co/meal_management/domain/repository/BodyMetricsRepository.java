package jp.co.meal_management.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;

public interface BodyMetricsRepository extends JpaRepository<BodyMetrics, BodyMetricsId> {

	@Override
	Optional<BodyMetrics> findById(BodyMetricsId id);
}
