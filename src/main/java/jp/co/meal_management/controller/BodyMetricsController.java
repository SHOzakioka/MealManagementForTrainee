package jp.co.meal_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.domain.service.BodyMetricsServiceImpl;

@RestController
@RequestMapping("/api/body-metrics")
public class BodyMetricsController {

	@Autowired
	private BodyMetricsServiceImpl bodyMetricsService;

	@PostMapping
	public BodyMetrics createBodyMetrics(@RequestBody BodyMetrics bodyMetrics) {
		//		return bodyMetricsService.saveBodyMetrics(bodyMetrics);
		return bodyMetrics;
	}

}
