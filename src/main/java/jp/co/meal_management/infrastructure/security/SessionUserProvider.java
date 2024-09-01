package jp.co.meal_management.infrastructure.security;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
import jp.co.meal_management.domain.entity.UserInfos;

@Component
public class SessionUserProvider {

	public UserInfos getCurrentUser() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		UserInfos user = (UserInfos) session.getAttribute("USER_INFO");

		if (user == null) {
			throw new RuntimeException("User not found in session");
		}

		return user;
	}

	public UUID getCurrentUserId() {
		return getCurrentUser().getUserId();
	}

}
