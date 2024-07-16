package jp.co.meal_management.use_case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.meal_management.domain.repository.BodyMetricsCustomRepositoryImpl;

@Component
public class BodyMetricsSaveRecordImpl implements MealManagementRecord {

	@Autowired
	BodyMetricsCustomRepositoryImpl bodyMetricsCustomRepositoryImpl;

	@Override
	public void saveRecord(Object obj) {
		bodyMetricsCustomRepositoryImpl.upsertBodyMetrics(obj);

	}

}
