package jp.co.meal_management.config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.meal_management.domain.entity.UserAuths;
import jp.co.meal_management.domain.entity.UserInfos;
import jp.co.meal_management.domain.repository.UserInfoRepository;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	// private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

	@Autowired
	private UserInfoRepository userInfoRepository;

	/**
	 * ログイン成功時にSessionにUserInfosを保存するためのメソッドです。
	 * @throws ServletException 
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		try {
			// authenticationからUserIdを取得する
			UserAuths userAuths = (UserAuths) authentication.getPrincipal();
			UUID userId = userAuths.getUserId();

			// 取得したUserIdからUserInfosのレコードを検索する
			UserInfos userInfo = userInfoRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

			// SessionにuserInfosを保存する
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", userInfo);

			response.sendRedirect(request.getContextPath() + "/top");
		} catch (Exception e) {
			
			throw new ServletException("Unexpected error occurred during authentication success handling", e);
		}
	}

}
