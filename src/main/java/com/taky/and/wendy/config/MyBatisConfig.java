package com.taky.and.wendy.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.taky.and.wendy.*.mapper")
public class MyBatisConfig {
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		// http://okky.kr/article/292353, mapper가 두개가되면..
		// http://stackoverflow.com/questions/25405167/finding-resources-with-pathmatchingresourcepatternresolver-and-urlclassloader-in
		sessionFactory.setMapperLocations(new Resource[] {
				new ClassPathResource("sql/user.xml"),
				new ClassPathResource("sql/dateCoursePost.xml")
		});
		
		sessionFactory.setTypeAliasesPackage("com.taky.and.wendy");
		return sessionFactory.getObject();
	}
}
