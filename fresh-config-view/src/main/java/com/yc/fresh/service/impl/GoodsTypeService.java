package com.yc.fresh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.fresh.entity.GoodsType;
import com.yc.fresh.mapper.IGoodsTypeMapper;
import com.yc.fresh.service.IGoodsTypeService;

/**
 * 后台管理员业务层实现
 * 源辰信息
 * @author navy
 * @2019年7月27日
 */
@Service
public class GoodsTypeService implements IGoodsTypeService{
	@Autowired
	private IGoodsTypeMapper mapper;
	
	@Override
	public List<GoodsType> findAll() {
		return mapper.findAll();
	}

	@Override
	public List<GoodsType> finds() {
		return mapper.finds();
	}
}
