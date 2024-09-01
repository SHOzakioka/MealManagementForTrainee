package jp.co.meal_management.domain.service;

import org.springframework.stereotype.Service;

import jp.co.meal_management.config.BodyMetricsConstant;

/**
 * @author sho.okazaki <br>
 * 基礎代謝を計算するserviceクラス
 */

@Service
public class BodyMetricsServiceImpl implements BodyMetricsService {
	
	private final BodyMetricsConstant bodyMetricsConstant;
	
	public BodyMetricsServiceImpl(BodyMetricsConstant bodyMetricsConstant) {
		this.bodyMetricsConstant = bodyMetricsConstant;
	}

	/**
	 * 計算した基礎代謝(MAR)を返す
	 * @return MAR
	 */
	@Override
	public double calcurateMar(int age, int sex, double weightKg, double heightCm) {
		if (bodyMetricsConstant.getMen() == sex) {
			return bodyMetricsConstant.getMenWeightKgConst() * weightKg
					+ bodyMetricsConstant.getMenHeightCmConst() * heightCm
					- bodyMetricsConstant.getMenAgeConst() * age
					+ bodyMetricsConstant.getMenConst();
		} else {
			return bodyMetricsConstant.getWomenWeightKgConst() * weightKg
					+ bodyMetricsConstant.getWomenHeightCmConst() * heightCm
					- bodyMetricsConstant.getWomenAgeConst() * age
					+ bodyMetricsConstant.getWomenConst();
		}
	}
}
