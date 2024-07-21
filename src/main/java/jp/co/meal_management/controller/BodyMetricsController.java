package jp.co.meal_management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.meal_management.domain.entity.BodyMetrics;

@RestController
@RequestMapping("/api/body-metrics")
public class BodyMetricsController {

	@PostMapping
	public BodyMetrics createBodyMetrics(@RequestBody BodyMetrics bodyMetrics) {
		return bodyMetrics;
	}

}
