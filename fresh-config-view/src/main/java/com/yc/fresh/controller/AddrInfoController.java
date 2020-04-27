package com.yc.fresh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.entity.AddrInfo;
import com.yc.fresh.entity.MemberInfo;
import com.yc.fresh.service.IAddrInfoService;
import com.yc.fresh.util.StringUtil;

@RestController
@RequestMapping("/addr")
public class AddrInfoController {
	@Autowired
	private IAddrInfoService service;

	@RequestMapping("/add")
	public int add(HttpSession session, AddrInfo af) {
		// 先校验用户是否登录
		Object obj = session.getAttribute("loginMenber");
		if (obj == null) {
			return -2;
		}

		MemberInfo mf = (MemberInfo)obj;
		af.setMno(mf.getMno());
		return service.add(af);
	}
	
	@RequestMapping("/update")
	public int update(String addrs) {
		if (StringUtil.isNull(addrs)) {
			return -2;
		}
		return service.update(addrs.split(","));
	}
	
	@RequestMapping("/findByMno")
	public List<AddrInfo> findByMno(HttpSession session) {
		Object obj = session.getAttribute("loginMenber");
		if (obj == null) {
			return null;
		}

		MemberInfo mf = (MemberInfo)obj;
		return service.findByMno(mf.getMno());
	}
}
