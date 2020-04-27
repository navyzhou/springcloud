package com.yc.fresh.mapper;

import com.yc.fresh.entity.MemberInfo;

/**
 * 商品类型
 * 源辰信息
 * @author navy
 * @2019年7月27日
 */
public interface IMemberInfoMapper {
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
