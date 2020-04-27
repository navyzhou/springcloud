package com.yc.fresh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.entity.AdminInfo;
import com.yc.fresh.service.IAdminInfoService;

@RestController
@RequestMapping("/back")
public class AdminInfoController {
	@Autowired
	private IAdminInfoService service;
	
	@RequestMapping("/login")
	public int login(AdminInfo af, HttpSession session) {
		AdminInfo adminInfo = service.login(af);
		if (adminInfo != null) {
			session.setAttribute("currentAdminInfo", adminInfo);
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/admin/findAll")
	public List<AdminInfo> findAll() {
		return service.findAll();
	}
	
	@RequestMapping("/admin/add")
	public int add(AdminInfo af) {
		return service.add(af);
	}
	
	@RequestMapping("/admin/update")
	public int update(int aid) {
		return service.updatPwd(aid);
	}
	
	@RequestMapping("/manager")
	public String successLogin() {
		return "index";
	}
}
