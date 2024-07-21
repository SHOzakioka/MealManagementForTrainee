package jp.co.meal_management.domain.repository;

import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 取得したカラムのvalueがテーブルにあれば更新し、無ければレコードを追加するメソッドです。 <br>
	 * 
	 * ex) <br>
	 * 体重の値を渡された場合、userIdとrecordDateの複合キーでbody_metricsテーブルを検索する。 <br>
	 * ・レコードがヒットした場合、体重の値だけを更新する。 <br>
	 * ・レコードがヒットしなかった場合、レコードを新規追加する。この時、必須カラム＋体重カラムのみ値が入る。 <br>
	 * 
	 */
	@Override
	public <T, ID, V> void upsert(JpaRepository<T, ID> repository, T entity, ID primaryKey, String fieldName, V value) {
		try {
			Optional<T> foundEntity = repository.findById(primaryKey);

			// 既存レコードをentityに入れる
			if (foundEntity.isPresent()) {
				entity = foundEntity.get();
			}

			// valueの型をアンボクシングする
			Class<?> valueType;
			if (value instanceof Double) {
				valueType = double.class;
			} else if (value instanceof Integer) {
				valueType = int.class;
			} else {
				valueType = value.getClass();
			}

			// entityのセッターメソッドの文字列を作成する
			String setterMethodName = "set" + capitalize(fieldName);
			// entityのセッターを作成する
			Method setterMethod = entity.getClass().getMethod(setterMethodName, valueType);
			// entityのセッターを実行する
			setterMethod.invoke(entity, value);
			// entityを保存する
			repository.save(entity);
		} catch (Exception e) {
			throw new RuntimeException("Error during upsert operation", e);
		}
	}

	/**
	 * 取得した文字列の先頭を大文字にして返すメソッドです
	 * 
	 * @return 先頭大文字の文字列
	 */
	private String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
