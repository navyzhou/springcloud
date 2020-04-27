package com.yc.fresh.controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.entity.MemberInfo;
import com.yc.fresh.service.IMemberInfoService;
import com.yc.fresh.util.SendMailUtil;

@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private SendMailUtil sendMailUtil;
	
	@Autowired
	private IMemberInfoService service;

	@RequestMapping("/code")
	public int code(String name, String email, HttpSession session) {
		int result = -1;
		try {
			String code = "";
			Random rd = new Random();
			while(code.length() < 6) {
				code += rd.nextInt(10);
			}
			if (sendMailUtil.sendHtmlMail(email, name, code)){
				session.setAttribute("code", code);
				// 启用一个定时任务，当3分钟后清空这个session中的值
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						session.removeAttribute("code");
					}
				};
				Timer timer = new Timer();
				timer.schedule(task, 180000);
				result = 1;
			}else {
				result = 0;
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 会员注册
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/reg")
	public int code(HttpSession session, MemberInfo mf) {
		String code = (String) session.getAttribute("code");
		if (!code.equals(mf.getRealName())) {
			return -2;
		}
		return service.add(mf);
	}
	
	/**
	 * 会员登录
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/login")
	public int login(HttpSession session, MemberInfo mf) {
		MemberInfo menberInfo =  service.login(mf);
		if (menberInfo != null) {
			session.setAttribute("loginMenber", menberInfo);
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/check")
	public MemberInfo checkLogin(HttpSession session) {
		Object obj = session.getAttribute("loginMenber");
		if (obj == null) {
			return new MemberInfo();
		} 
		return (MemberInfo)obj;
	}
}
