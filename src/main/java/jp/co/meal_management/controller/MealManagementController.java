package jp.co.meal_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.meal_management.domain.entity.BodyMetrics;
import jp.co.meal_management.use_case.MealManagementRecord;

@Controller
public class MealManagementController {

	@Autowired
	private MealManagementRecord mealManagementRecord;

	@GetMapping("/top")
	public String showMealManagementTop(Model model) {
		return "mealManagementTop";

	}

	@GetMapping("/tdee-details")
	public String showTdeeDetails(Model model) {
		return "mealManagementTdeeDetails";

	}

	@GetMapping("/tdci-details")
	public String showTdciDetails(Model model) {
		return "mealManagementTdciDetails";

	}

	@GetMapping("/exercise-record")
	public String showExerciseRecord(Model model) {
		return "mealManagementExerciseRecord";

	}

	@GetMapping("/meal-record")
	public String showMealRecord(Model model) {
		return "mealManagementMealRecord";

	}

	@GetMapping("/weight-record")
	public String showWeightRecord(Model model) {
		model.addAttribute("bodyMetrics", new BodyMetrics());
		return "mealManagementWeightRecord";

	}

	@PostMapping("/weight-record")
	public String postWeightRecord(@ModelAttribute BodyMetrics bodyMetrics) {

		mealManagementRecord.saveWeightEntity(bodyMetrics.getWeightKg());
		return "mealManagementWeightRecord";

	}

}
