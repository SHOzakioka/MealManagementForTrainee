package jp.co.meal_management.domain.service;

public interface BodyMetricsService {

	// 計算した基礎代謝(MAR)を返す
	public double calcurateMar(int sex, double weightKg, double heightCm, int age);

}
