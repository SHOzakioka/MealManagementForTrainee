package jp.co.meal_management.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
public class UserInfos {

	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_name_kana")
	private String userNameKna;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "age")
	private int age;

	@Column(name = "sex_id")
	private int sexId;

	@Column(name = "height_cm")
	private double heightCm;

	@Column(name = "body_make_setting_id")
	private int bodyMakeSettingId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

}
