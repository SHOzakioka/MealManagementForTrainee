package jp.co.meal_management.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class BodyMetricsId implements Serializable {
	private UUID userId;
	private Date recordDate;

	public BodyMetricsId() {
	}

	public BodyMetricsId(UUID userId, Date recordDate) {
		this.userId = userId;
		this.recordDate = recordDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BodyMetricsId that = (BodyMetricsId) o;
		return userId == that.userId && Objects.equals(recordDate, that.recordDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, recordDate);
	}

}
