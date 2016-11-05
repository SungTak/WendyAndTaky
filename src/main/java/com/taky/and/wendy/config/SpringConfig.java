package com.taky.and.wendy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableAsync
public class SpringConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private ITemplateResolver templateResolver;
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		// 디폴트 view resolver 전달, sec사용을 위함
		// http://stackoverflow.com/questions/18309864/secauthorize-and-secauthentication-annotations-dont-work
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}
	
	/**
	 * file upload resolver
	 * - http://stackoverflow.com/questions/25830503/spring-mvc-http-status-400-required-multipartfile-parameter-file-is-not-pre
	 * - http://java.ihoney.pe.kr/351
	 * - CommonsMultipartResolver쓰면 안된다.
	 * @return
	 */
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
}
