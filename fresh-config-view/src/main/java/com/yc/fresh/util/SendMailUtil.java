package com.yc.fresh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component //被spring容器管理
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "email")
@Order(1) //如果多个自定义ApplicationRunner，用来标明执行顺序*/
public class SendMailUtil{
	@Value("${sendEmail}")
	private String sendEmail;
	
	@Value("${pwd}")
	private String pwd;

	public boolean sendHtmlMail(String receiveAccount, String name, String code) throws MessagingException{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 

		//设定mail server 
		//senderImpl.setHost("smtp.163.com"); 
		senderImpl.setHost("smtp.qq.com");
		senderImpl.setDefaultEncoding("utf-8");

		//建立邮件消息,发送简单邮件和html邮件的区别 
		MimeMessage mailMessage = senderImpl.createMimeMessage(); 
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage); 

		//设置收件人，寄件人 
		messageHelper.setTo(receiveAccount); 
		messageHelper.setFrom(sendEmail); 
		messageHelper.setSubject("天天生鲜注册中心"); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		//true 表示启动HTML格式的邮件 
		String content = "<span style=\"font-size:16px;font-weight:blod;font-family:'微软雅黑'\">尊敬的 "+name+" ，您好：</span><br /><br />" +
				"<div style='width:800px'>&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size:14px;font-family:'微软雅黑'\">" +
				"欢迎注册天天生鲜网，您本次的注册码为 <span style='font-size:25px;font-weight:bold'>"+code+"</span>，3分钟之内有效，请尽快使用！</span><br/><br /><span style=\"font-size:14px;font-family:'微软雅黑';float:right;\">"
						+ "<a href='http://www.hyycinfo.com'>衡阳市源辰信息科技有限公司技术部</a></span><br/><span style=\"font-size:14px;font-family:'微软雅黑';float:right;padding-right:30px;\">"+sdf.format(new Date())+"</span></div>";
		messageHelper.setText("<!doctype html><html><head><meta charset='utf-8'/></head><body>" + content + "</body></html>",true); 

		senderImpl.setUsername(sendEmail) ; // 根据自己的情况,设置username
		senderImpl.setPassword(pwd) ; // 根据自己的情况, 设置password
		Properties prop = new Properties() ;
		prop.put("mail.smtp.auth", "true") ; // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000") ; 
		senderImpl.setJavaMailProperties(prop); 
		//发送邮件 
		senderImpl.send(mailMessage); 
		return true; 
	} 
}
