package jp.co.meal_management.domain.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.entity.BodyMetricsId;
import jp.co.meal_management.domain.service.BodyMetricsService;
import jp.co.meal_management.domain.service.BodyMetricsServiceImpl;

@Repository
public class BodyMetricsCustomRepositoryImpl implements BodyMetricsCustomRepository {

	@Autowired
	private BodyMetricsRepository bodyMetricsRepository;

	@Autowired
	private BodyMetricsService bodyMetricsService;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * user_idとrecord_dateを検索する
	 * ヒットしたらmetricをupdateする
	 * ヒットしなかったらレコードをinsertする
	 */
	@Override
	public void upsertBodyMetrics(Object obj) {

		// BodyMetricsエンティティであることを判定する
		if (obj instanceof BodyMetrics) {
			BodyMetrics inputBodyMetrics = (BodyMetrics) obj;

			int userId_ = inputBodyMetrics.getUserId();
			Date recordDate_ = inputBodyMetrics.getRecordDate();
			double weightKg_ = inputBodyMetrics.getWeightKg();
			double mar_ = inputBodyMetrics.getMar();

			// BodyMetricsの複合キーを取得
			BodyMetricsId complecationId = new BodyMetricsId(userId_, recordDate_);

			// レコードを検索
			Optional<BodyMetrics> foundEntity = bodyMetricsRepository.findById(complecationId);

			// レコードが存在する場合、体重とMARのみを更新する
			if (foundEntity.isPresent()) {

				BodyMetrics foundBodyMetrics = foundEntity.get();
				foundBodyMetrics.setWeightKg(weightKg_);
				foundBodyMetrics.setMar(mar_);
				bodyMetricsRepository.save(foundBodyMetrics);

			}
			// レコードが存在しない場合、レコードを挿入する
			else {

				bodyMetricsRepository.save(inputBodyMetrics);

			}

		} else {
			// 返されるメッセージはerrorMessage.propertiesで管理したい
			throw new IllegalArgumentException("渡された引数はBodyMetrics型ではありません。");
		}

	}

}
