package jp.co.meal_management.use_case;

import java.util.Date;

import org.springframework.stereotype.Component;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;
import jp.co.meal_management.domain.repository.BodyMetricsRepository;
import jp.co.meal_management.domain.repository.CustomRepository;

/**
 * @author sho.okazaki <br>
 * 体重、基礎代謝(MAR)、1日の運動消費カロリー(TEA)を記録するユースケースクラス
 */

@Component
public class BodyMetricsSaveRecordImpl implements MealManagementRecord {

	/* インスタンス変数の定義 */
	private final CustomRepository customRepository;
	private final BodyMetricsRepository bodyMetricsRepository; 
	
	public BodyMetricsSaveRecordImpl(CustomRepository customRepository, BodyMetricsRepository bodyMetricsRepository) {
		this.customRepository = customRepository;
		this.bodyMetricsRepository = bodyMetricsRepository;
	}

	// インスタンス変数としてuserIdとrecordDateを取得しておきたい

	/**
	 * 体重の値を受取ってテーブルを更新するメソッドです。
	 */
	@Override
	public void saveWeightEntity(double weightKg) {
		BodyMetrics bodyMetrics = new BodyMetrics();
		
		// user_idを設定
		bodyMetrics.setUserId(005);
		// 今日の日付を設定
		bodyMetrics.setRecordDate(new Date());
		
		// BodyMetricsIdのuserIdに値を設定
		BodyMetricsId bodyMetricsId = new BodyMetricsId(bodyMetrics.getUserId(),bodyMetrics.getRecordDate()); 
		

		customRepository.upsert(bodyMetricsRepository, bodyMetrics, bodyMetricsId, "weightKg", weightKg);
	}

}
