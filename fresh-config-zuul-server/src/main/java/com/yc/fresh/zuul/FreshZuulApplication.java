package com.yc.fresh.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.yc.fresh.zuul.filter.TokenFilter;

@SpringBootApplication
@EnableZuulProxy
public class FreshZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreshZuulApplication.class, args);
	}

	// 将过滤器交给Spring管理
	@Bean
	public TokenFilter tokenFilter(){
		return new TokenFilter();
	}
}