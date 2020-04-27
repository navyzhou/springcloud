package com.yc.fresh.service;

import com.yc.fresh.entity.MemberInfo;

public interface IMemberInfoService {
	/**
	 * 登录
	 * @param bf
	 * @return
	 */
	public MemberInfo login(MemberInfo mf);
	
	/**
	 * 注册
	 * @param bf
	 * @return
	 */
	public int add(MemberInfo mf);
}
