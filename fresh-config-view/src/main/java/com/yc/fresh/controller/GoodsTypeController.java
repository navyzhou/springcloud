package com.yc.fresh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.fresh.entity.GoodsType;
import com.yc.fresh.service.IGoodsTypeService;

@RestController
@RequestMapping("/type")
public class GoodsTypeController {
	@Autowired
	private IGoodsTypeService service;
	
	@RequestMapping("/findAll")
	public List<GoodsType> findAll() {
		return service.findAll();
	}
	

	@RequestMapping("/finds")
	public List<GoodsType> finds() {
		return service.finds();
	}
}
