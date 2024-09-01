package jp.co.meal_management.domain.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "user_auths", indexes = {
		@Index(name = "idx_user_id", columnList = "user_id")
}, uniqueConstraints = {
		@UniqueConstraint(name = "uk_email", columnNames = { "email" })
})
@Data
public class UserAuths implements UserDetails {

	/* フィールドの定義 */

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password_hash", unique = true)
	private String passwordHash;

	@Column(name = "authorities")
	private String authorities;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "failed_attempt")
	private int failedAttempt;

	@Column(name = "lock_time")
	private LocalDateTime lockTime;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	/* メソッドの定義 */

	// Userの権限を返すメソッド
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	// ハッシュ化したパスワードのセッター
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	// パスワードのゲッター
	@Override
	public String getPassword() {
		return this.passwordHash;
	}

	// ユーザー名のゲッター
	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
