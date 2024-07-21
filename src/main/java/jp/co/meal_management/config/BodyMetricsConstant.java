package jp.co.meal_management.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author sho.okazaki <br>
 * BodyMetricsServiceでMARを計算するための定数を扱うクラス
 *
 */

@Configuration
@ConfigurationProperties(prefix = "body-metrics-constant")
@Getter
@Setter
public class BodyMetricsConstant {

	private int men;
	private int women;
	private int other;
	private double menWeightKgConst;
	private double menHeightCmConst;
	private double menAgeConst;
	private double menConst;
	private double womenWeightKgConst;
	private double womenHeightCmConst;
	private double womenAgeConst;
	private double womenConst;

}
