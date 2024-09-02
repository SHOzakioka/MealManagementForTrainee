package jp.co.meal_management.infrastructure.security;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
import jp.co.meal_management.domain.entity.UserInfos;
import jp.co.meal_management.domain.repository.UserInfoRepository;

@Component
public class SessionUserProvider {

	private final UserInfoRepository userInfoRepository;

	public SessionUserProvider(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	public Optional<UserInfos> getCurrentUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		UserInfos user = (UserInfos) session.getAttribute("USER_INFO");

		if (user == null) {
			throw new RuntimeException("User not found in session");
		}

		return userInfoRepository.findById(user.getUserId());
	}

}
