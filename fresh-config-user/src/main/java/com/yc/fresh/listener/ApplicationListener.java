package com.yc.fresh.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 当应用程序一启动，自动创建文件上传后的保存目录
 * 源辰信息
 * @author navy
 * @2019年7月29日
 */
@Component //被spring容器管理
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "uploadload")
public class ApplicationListener implements ServletContextListener {
	@Value("${photoPath}")
	private String photoPath;
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }
    
    public void contextInitialized(ServletContextEvent arg0)  {
    	String basePath = arg0.getServletContext().getRealPath("/");
    	File fl = new File(basePath,"../"+photoPath);
		if (!fl.exists()) {
			fl.mkdirs();
		}
    }
}
