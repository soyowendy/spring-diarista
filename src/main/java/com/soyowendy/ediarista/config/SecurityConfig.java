package com.soyowendy.ediarista.config;

import com.soyowendy.ediarista.core.enums.TipoUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig  {
	@Value("${com.soyowendy.ediarista.rememberMe.key}")
	private String rememberMeKey;
	@Value("${com.soyowendy.ediarista.validitySeconds}")
	private int rememberMeValiditySeconds;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth) -> {
				auth.requestMatchers("/admin/**").hasAuthority(TipoUsuario.ADMIN.toString());
				auth.anyRequest().authenticated();
			})
			.formLogin((form) ->
					form.loginPage("/admin/login").permitAll().usernameParameter("email").passwordParameter("senha")
							.defaultSuccessUrl("/admin/servicos"))
			.logout((logout) ->
					logout.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout", "GET")))
			.rememberMe((remember) ->
					remember.tokenValiditySeconds(rememberMeValiditySeconds).key(rememberMeKey).rememberMeParameter("lembrar"));
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/webjars/**");
	}
}
