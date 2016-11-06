package com.taky.and.wendy.config;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		TomcatEmbeddedServletContainerFactory tomcat =  new TomcatEmbeddedServletContainerFactory() {
			private Logger logger = LoggerFactory.getLogger(this.getClass());	
			
			@Value("${wat.file.home}")
			private String fileHome;

			// 이미지 저장 논리 경로 설정
			// http://stackoverflow.com/questions/25995635/can-i-enable-the-tomcat-manager-app-for-spring-boots-embedded-tomcat
			// http://tibang.tistory.com/626
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				try {
					File file = new File(fileHome + "/static/image/upload");
					if (file.isDirectory() == false) {
						file.mkdirs();
					}
					
					tomcat.addWebapp("/static/image", file.getAbsolutePath());
					
				} catch (ServletException e) {
					logger.error("### 톰캣의 URL 물리 경로를 찾지 못했습니다!", e);
				}
				
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

		};
		
		return tomcat;
	}
}
