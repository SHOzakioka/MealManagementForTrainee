package jp.co.meal_management.domain.service;

import org.springframework.stereotype.Service;
import java.util.ResourceBundle;

/**
 * @author sho.okazaki
 *
 */

@Service
public class BodyMetricsServiceImpl implements BodyMetricsService {

	/* 男性 */
	private final Integer MEN;
	/* 女性 */
	private final Integer WOMEN;
	/* その他 */
	private final Integer OTHER;

	/* ハリスベネディクト方程式　男性体重の系数 */
	private final double MEN_WEIGHT_KG_CONST;
	/* ハリスベネディクト方程式　男性身長の系数 */
	private final double MEN_HEIGHT_CM_CONST;
	/* ハリスベネディクト方程式　男性年齢の系数 */
	private final double MEN_AGE_CONST;
	/* ハリスベネディクト方程式　男性定数項 */
	private final double MEN_CONST;

	/* ハリスベネディクト方程式　女性体重の系数 */
	private final double WOMEN_WEIGHT_KG_CONST;
	/* ハリスベネディクト方程式　女性身長の系数 */
	private final double WOMEN_HEIGHT_CM_CONST;
	/* ハリスベネディクト方程式　女性年齢の系数 */
	private final double WOMEN_AGE_CONST;
	/* ハリスベネディクト方程式　女性定数項 */
	private final double WOMEN_CONST;

	/**
	 * コンストラクタ
	 * 役割：プロパティファイルの定数を取得
	 */
	public BodyMetricsServiceImpl() {
		ResourceBundle rb = ResourceBundle.getBundle("Constant");

		this.MEN = Integer.parseInt(rb.getString("MEN"));
		this.WOMEN = Integer.parseInt(rb.getString("WOMEN"));
		this.OTHER = Integer.parseInt(rb.getString("OTHER"));
		this.MEN_WEIGHT_KG_CONST = Integer.parseInt(rb.getString("MEN_WEIGHT_KG_CONST"));
		this.MEN_HEIGHT_CM_CONST = Integer.parseInt(rb.getString("MEN_HEIGHT_CM_CONST"));
		this.MEN_AGE_CONST = Integer.parseInt(rb.getString("MEN_AGE_CONST"));
		this.MEN_CONST = Integer.parseInt(rb.getString("MEN_CONST"));
		this.WOMEN_WEIGHT_KG_CONST = Integer.parseInt(rb.getString("WOMEN_WEIGHT_KG_CONST"));
		this.WOMEN_HEIGHT_CM_CONST = Integer.parseInt(rb.getString("WOMEN_HEIGHT_CM_CONST"));
		this.WOMEN_AGE_CONST = Integer.parseInt(rb.getString("WOMEN_AGE_CONST"));
		this.WOMEN_CONST = Integer.parseInt(rb.getString("WOMEN_CONST"));
	}

	/**
	 * 計算した基礎代謝(MAR)を返す
	 * @return MAR
	 */
	@Override
	public double calcurateMar(int sex, double weightKg, double heightCm, int age) {
		if (MEN.equals(sex)) {
			return MEN_WEIGHT_KG_CONST * weightKg + MEN_HEIGHT_CM_CONST * heightCm - MEN_AGE_CONST * age + MEN_CONST;
		} else {
			return WOMEN_WEIGHT_KG_CONST * weightKg + WOMEN_HEIGHT_CM_CONST * heightCm - WOMEN_AGE_CONST * age
					+ WOMEN_CONST;
		}
	}
}
