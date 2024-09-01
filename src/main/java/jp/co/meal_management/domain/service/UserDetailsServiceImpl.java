package jp.co.meal_management.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.meal_management.domain.entity.UserAuths;
import jp.co.meal_management.domain.repository.UserAuthRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserAuthRepository userAuthRepository;

	@Override
	public UserAuths loadUserByUsername(String email) throws UsernameNotFoundException {
		return userAuthRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}

}
