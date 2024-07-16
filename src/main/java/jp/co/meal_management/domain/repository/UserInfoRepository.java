/**
 * 
 */
package jp.co.meal_management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.meal_management.domain.entity.UserInfos;

/**
 * @author sho.okazaki
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfos, Integer> {

}
