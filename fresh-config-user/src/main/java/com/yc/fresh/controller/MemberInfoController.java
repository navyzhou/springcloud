package com.yc.fresh.controller;

import java.util.HashMap;
import java.util.Map;
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

@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private SendMailController sendMailUtil;
	
	@Autowired
	private IMemberInfoService service;
	
	public Map<String, Integer> resultMap(Integer result) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("code", result);
		return map;
	}

	@RequestMapping("/code")
	public Map<String, Integer> code(String name, String email, HttpSession session) {
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
		return this.resultMap(result);
	}
	
	/**
	 * 会员注册
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/reg")
	public Map<String, Integer> code(HttpSession session, MemberInfo mf) {
		Object obj = session.getAttribute("code");
		
		if (obj == null) {
			return this.resultMap(-2);
		}
		
		String code = (String) obj;
		int result;
		if (!code.equals(mf.getRealName())) {
			result = -1;
		}
		result =  service.add(mf);
		return this.resultMap(result);
	}
	
	/**
	 * 会员登录
	 * @param session
	 * @param mf
	 * @return
	 */
	@RequestMapping("/login")
	public Map<String, Integer> login(HttpSession session, MemberInfo mf) {
		MemberInfo menberInfo =  service.login(mf);
		int result = 0;
		if (menberInfo != null) {
			session.setAttribute("loginMenber", menberInfo);
			result = 1;
		}
		return this.resultMap(result);
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
