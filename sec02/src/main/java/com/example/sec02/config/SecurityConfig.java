package com.example.sec02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 기능 활성화: 스프링 시큐리티 필터가 스프링 필터체인에 등록 됨.
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/regist", "/registProc").permitAll()
				.requestMatchers("/members/**").hasAnyRole("ADMIN", "MEMBER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				);
		
		http.formLogin((auth) -> auth
				//.loginPage("/login") // 로그인 폼 지정 - 쓰지 않으면 Spring 제공 로그인 폼 사용
				//.loginProcessingUrl("/loginProc") // 로그인 폼 지정 후 폼 정보 보내는 곳 - 이후 스르핑부트에서 알아서 해줌
				.permitAll()
				);
		
		http.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
