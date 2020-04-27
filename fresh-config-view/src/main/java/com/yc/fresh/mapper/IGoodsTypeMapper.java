package com.yc.fresh.mapper;

import java.util.List;

import com.yc.fresh.entity.GoodsType;

/**
 * 商品类型
 * 源辰信息
 * @author navy
 * @2019年7月27日
 */
public interface IGoodsTypeMapper {
	/**
	 * 查询所有
	 * @return
	 */
	public List<GoodsType> findAll();
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<GoodsType> finds();
}
