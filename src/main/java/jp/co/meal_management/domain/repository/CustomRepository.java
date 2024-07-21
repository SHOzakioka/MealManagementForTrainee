package jp.co.meal_management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository {

	public <T, ID, V> void upsert(JpaRepository<T, ID> repository, T entity, ID primaryKey, String fieldName, V value);

}
