package com.yc.fresh.mapper;

import java.util.List;
import java.util.Map;

import com.yc.fresh.entity.GoodsInfo;

/**
 * 商品类型
 * 源辰信息
 * @author navy
 * @2019年7月27日
 */
public interface IGoodsInfoMapper {
	/**
	 * 添加商品信息
	 * @param gf
	 * @return
	 */
	public int add(GoodsInfo gf);
	
	/**
	 * 查询每种类型的商品的前三条数据
	 * @return
	 */
	public List<GoodsInfo> finds();

	/**
	 * 根据类型分页查询
	 * @return
	 */
	public List<GoodsInfo> findByType(Map<String, Integer> map);

	/**
	 * 根据商品编号，查询商品信息
	 * @param gno
	 * @return
	 */
	public GoodsInfo findByGno(int gno);
	
	/**
	 * 根据类型获取商品数量
	 * @param tno 类型编号
	 * @return
	 */
	public int getTotal(Integer tno);
	
	/**
	 * 根据购物车编号修改库存量
	 * @param cnos
	 * @return
	 */
	public int updateStore(String[] cnos);
}
