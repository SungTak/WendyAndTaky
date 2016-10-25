package com.taky.and.wendy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http://stackoverflow.com/questions/19468209/spring-security-403-error
		http.csrf().disable();
		
		http.sessionManagement().invalidSessionUrl("/");
		
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.and()
			.formLogin()
				.loginPage("/signin").permitAll().defaultSuccessUrl("/")
				.usernameParameter("email").passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/signout").permitAll().logoutSuccessUrl("/");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// http://zgundam.tistory.com/54
		// 기본 SHA-256, salt는 내부적으로 랜덤
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
