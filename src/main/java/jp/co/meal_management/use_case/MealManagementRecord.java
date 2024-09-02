package jp.co.meal_management.use_case;

import java.util.Optional;

import jp.co.meal_management.domain.entity.UserInfos;

public interface MealManagementRecord {
	
	public void saveWeightEntity(Optional<UserInfos> foundEntity, double weightKg);
	public void saveMarEntity(Optional<UserInfos> foundEntity, double weightKg);
	public void saveTeaEntity(Optional<UserInfos> foundEntity, double tea);
}
