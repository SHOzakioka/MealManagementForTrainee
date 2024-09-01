package jp.co.meal_management.use_case;

import java.util.UUID;

public interface MealManagementRecord {
	
	public void saveWeightEntity(UUID userId, double weightKg);
	public void saveTeaEntity(UUID userId, double tea);
}
