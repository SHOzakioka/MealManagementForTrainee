package jp.co.meal_management.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.meal_management.domain.entity.UserAuths;

/**
 * @author sho.okazaki
 * 
 */
public interface UserAuthRepository extends JpaRepository<UserAuths, UUID> {
	Optional<UserAuths> findByEmail(String email);

}
