package jp.co.meal_management.domain.service;

import org.springframework.stereotype.Service;

@Service
public interface BodyMetricsService {

	public double calculateMar(int age, int sex, double weightKg, double heightCm);

}
