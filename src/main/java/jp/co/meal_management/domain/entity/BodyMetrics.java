package jp.co.meal_management.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@IdClass(BodyMetricsId.class)
@Data
public class BodyMetrics {

	@Id
	@Column(name = "user_id")
	private int userId;

	@Id
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "weight_kg")
	private double weightKg;

	@Column(name = "mar")
	private double mar;

	@Column(name = "tea")
	private double tea;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

}
