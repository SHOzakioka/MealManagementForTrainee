package jp.co.meal_management.use_case;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;
import jp.co.meal_management.domain.entity.UserInfos;
import jp.co.meal_management.domain.repository.BodyMetricsRepository;
import jp.co.meal_management.domain.repository.CustomRepository;
import jp.co.meal_management.domain.repository.UserInfoRepository;
import jp.co.meal_management.domain.service.BodyMetricsService;

/**
 * @author sho.okazaki <br>
 * 体重、基礎代謝(MAR)、1日の運動消費カロリー(TEA)を記録するユースケースクラス
 */

@Component
public class BodyMetricsRecordImpl implements MealManagementRecord {

	/* インスタンス変数の定義 */
	private final CustomRepository customRepository;
	private final BodyMetricsRepository bodyMetricsRepository;
	private final BodyMetricsService bodyMetricsService;

	public BodyMetricsRecordImpl(
			CustomRepository customRepository, BodyMetricsRepository bodyMetricsRepository,
			BodyMetricsService bodyMetricsService, UserInfoRepository userInfoRepository) {
		this.customRepository = customRepository;
		this.bodyMetricsRepository = bodyMetricsRepository;
		this.bodyMetricsService = bodyMetricsService;
	}

	/**
	 * 体重の値を更新するメソッドです。
	 */
	@Override
	public void saveWeightEntity(Optional<UserInfos> foundEntity, double weightKg) {
        BodyMetrics bodyMetrics = createBodyMetrics(foundEntity);
        updateBodyMetrics("weightKg", weightKg, bodyMetrics);
	}

	/**
	 * 基礎代謝の値を更新するメソッドです。
	 */
	@Override
	public void saveMarEntity(Optional<UserInfos> foundEntity, double weightKg) {
		BodyMetrics bodyMetrics = createBodyMetrics(foundEntity);
		
		// 基礎代謝を計算
		int age = foundEntity.get().getAge();
		int sexId = foundEntity.get().getSexId();
		double heightCm = foundEntity.get().getHeightCm();
        double mar = bodyMetricsService.calculateMar(age, sexId, weightKg, heightCm);
        updateBodyMetrics("mar", mar, bodyMetrics);
	}

	/**
	 * 運動消費カロリーの値を受取ってテーブルを更新するメソッドです。
	 */
	@Override
	public void saveTeaEntity(Optional<UserInfos> foundEntity, double tea) {
        BodyMetrics bodyMetrics = createBodyMetrics(foundEntity);
        updateBodyMetrics("tea", tea, bodyMetrics);
	}

	/**
	 * Sessinから取得したUser情報からBodyMetricsインスタンスを生成するメソッド
	 * 
	 */
	public BodyMetrics createBodyMetrics(Optional<UserInfos> foundEntity) {
        UUID userId = foundEntity.map(UserInfos::getUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
		Date currentTimestamp = new Date();
		
		BodyMetrics bodyMetrics = new BodyMetrics();
		bodyMetrics.setUserId(userId);
		bodyMetrics.setRecordDate(currentTimestamp);

		return bodyMetrics;
	}
	
	
    private void updateBodyMetrics(String field, double value, BodyMetrics bodyMetrics) {
        BodyMetricsId bodyMetricsId = new BodyMetricsId(bodyMetrics.getUserId(), bodyMetrics.getRecordDate());
        customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, field, value);
    }

}
