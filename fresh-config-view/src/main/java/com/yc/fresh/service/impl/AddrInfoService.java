package com.yc.fresh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.fresh.entity.AddrInfo;
import com.yc.fresh.mapper.IAddrInfoMapper;
import com.yc.fresh.service.IAddrInfoService;
import com.yc.fresh.util.StringUtil;

@Service
public class AddrInfoService implements IAddrInfoService{
	@Autowired
	private IAddrInfoMapper mapper;

	// @Transactional
	@Override
	public int add(AddrInfo af) {
		if (StringUtil.isNull(af.getAno(), af.getProvince(), af.getCity(), af.getArea(),  af.getName(), af.getTel())) {
			return -2;
		}
		// int result = mapper.update(af.getMno());// 如果新添加的地址不需要设置为默认收货地址，则可以不要这个
		int result= mapper.add(af);
		return result;
	}

	@Override
	public int update(String[] anos) {
		if (anos.length < 1) {
			return -2;
		}
		return mapper.updates(anos);
	}

	@Override
	public List<AddrInfo> findByMno(int mno) {
		return mapper.findByMno(mno);
	}
}
