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
	private final UserInfoRepository userInfoRepository;
	private final BodyMetricsRepository bodyMetricsRepository;
	private final BodyMetricsService bodyMetricsService;

	public BodyMetricsRecordImpl(
			CustomRepository customRepository, BodyMetricsRepository bodyMetricsRepository,
			BodyMetricsService bodyMetricsService, UserInfoRepository userInfoRepository) {
		this.customRepository = customRepository;
		this.bodyMetricsRepository = bodyMetricsRepository;
		this.bodyMetricsService = bodyMetricsService;
		this.userInfoRepository = userInfoRepository;
	}

	// インスタンス変数としてuserIdとrecordDateを取得しておきたい

	/**
	 * 体重の値を受取ってテーブルを更新するメソッドです。 <br>
	 * 体重を更新すると同時に基礎代謝も更新します。
	 */
	@Override
	public void saveWeightEntity(UUID userId, double weightKg) {
		// 会員情報の取得
		Optional<UserInfos> foundEntity = userInfoRepository.findById(userId);
		int age = foundEntity.get().getAge(); 
		int sexId = foundEntity.get().getSexId();
		double heightCm = foundEntity.get().getHeightCm();
		
		
		// BodyMetricsのインスタンスを生成
		BodyMetrics bodyMetrics = new BodyMetrics();

		// BodyMetricsIdの生成
		BodyMetricsId bodyMetricsId = new BodyMetricsId(foundEntity.get().getUserId(), new Date());

		// 基礎代謝を計算
		double mar = bodyMetricsService.calcurateMar(age, sexId, weightKg, heightCm);

		// 体重を更新
		customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, "weightKg", weightKg);

		// 基礎代謝を更新
		customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, "mar", mar);
	}

	@Override
	public void saveTeaEntity(UUID userId, double tea) {

		// BodyMetricsのインスタンスを生成
		BodyMetrics bodyMetrics = new BodyMetrics();
	}

}
