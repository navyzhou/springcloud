package com.yc.fresh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/*
 * @SpringBootApplication。 由于大量项目都会在主要的配置类上添加@Configuration,@EnableAutoConfiguration,@ComponentScan三个注解。
 * 因此Spring Boot提供了@SpringBootApplication注解，该注解可以替代上面三个注解（使用Spring注解继承实现）。
 */

@MapperScan("com.yc.fresh.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableRedisHttpSession
public class FreshUserStartController extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FreshUserStartController.class);
	}
	 
	public static void main(String[] args){
		SpringApplication.run(FreshUserStartController.class, args);
	}
}
