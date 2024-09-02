package jp.co.meal_management.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_infos", indexes = {
		@Index(name = "idx_user_id", columnList = "user_id")
})
@Data
public class UserInfos implements Serializable{

	@Id
	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_name_kana")
	private String userNameKna;

	@Column(name = "birthday")
	private LocalDateTime birthday;

	@Column(name = "age")
	private int age;

	@Column(name = "sex_id")
	private int sexId;

	@Column(name = "height_cm")
	private double heightCm;

	@Column(name = "body_make_setting_id")
	private int bodyMakeSettingId;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private UserAuths userAuths;

}
