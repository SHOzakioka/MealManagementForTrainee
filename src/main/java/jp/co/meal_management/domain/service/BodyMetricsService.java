package jp.co.meal_management.domain.service;

import org.springframework.stereotype.Service;

@Service
public interface BodyMetricsService {

	public double calcurateMar(int age, int sex, double weightKg, double heightCm);

}
