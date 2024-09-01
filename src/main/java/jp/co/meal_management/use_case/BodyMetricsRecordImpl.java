package jp.co.meal_management.use_case;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;
import jp.co.meal_management.domain.entity.UserInfos;
import jp.co.meal_management.domain.repository.BodyMetricsRepository;
import jp.co.meal_management.domain.repository.CustomRepository;
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
			BodyMetricsService bodyMetricsService) {
		this.customRepository = customRepository;
		this.bodyMetricsRepository = bodyMetricsRepository;
		this.bodyMetricsService = bodyMetricsService;
	}

	// インスタンス変数としてuserIdとrecordDateを取得しておきたい

	/**
	 * 体重の値を受取ってテーブルを更新するメソッドです。 <br>
	 * 体重を更新すると同時に基礎代謝も更新します。
	 */
	@Override
	public void saveWeightEntity(double weightKg) {
		// UserInfoのインスタンスを生成
		UserInfos userInfos = new UserInfos();
		// BodyMetricsのインスタンスを生成
		BodyMetrics bodyMetrics = new BodyMetrics();
		
		// ここは本当はSessionIdからUserIdを引き当てたい。
		userInfos.setUserId(UUID.fromString("c8c5ddf0-2f64-4fdb-ab6b-2b31fc9d6247"));

		// user_idを設定
		bodyMetrics.setUserId(userInfos.getUserId());

		// 今日の日付を設定
		bodyMetrics.setRecordDate(new Date());

		// BodyMetricsIdのuserIdに値を設定
		BodyMetricsId bodyMetricsId = new BodyMetricsId(bodyMetrics.getUserId(), bodyMetrics.getRecordDate());

		// 体重を更新
		customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, "weightKg", weightKg);

		// 基礎代謝を計算
		double mar = bodyMetricsService.calcurateMar(1, weightKg, 172.6, 25);

		// 基礎代謝を更新
		customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, "mar", mar);
	}
	
	@Override
	public void saveTeaEntity(double tea) {
		
		// BodyMetricsのインスタンスを生成
		BodyMetrics bodyMetrics = new BodyMetrics();
	}

}
