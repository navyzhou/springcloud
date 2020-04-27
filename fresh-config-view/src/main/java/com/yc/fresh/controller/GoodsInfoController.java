package com.yc.fresh.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.fresh.entity.GoodsInfo;
import com.yc.fresh.service.IGoodsInfoService;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
	@Autowired
	private IGoodsInfoService service;

	// 保存路径
	private String savePath = "pics";

	@RequestMapping("/upload")
	public Map<String, Object> upload(@RequestParam("upload")MultipartFile pic, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!pic.isEmpty()) {
			try {
				String path = request.getServletContext().getRealPath("");
				String temp = request.getServletContext().getInitParameter("uploadPath");
				if (temp != null) {
					savePath = temp;
				}

				savePath += "/" + new Date().getTime() + "_" + pic.getOriginalFilename(); // 为了避免重名，在上传文件名的前面加上一个时间戳
				File dest = new File(new File(path).getParentFile(), savePath);
				pic.transferTo(dest); // 将图片保存到服务器
				
				map.put("fileName", pic.getOriginalFilename());
				map.put("uploaded", 1);
				map.put("url", "../../../" + savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 添加商品信息
	 * @param pics
	 * @param session
	 * @param gf
	 * @return
	 */
	@RequestMapping("/addGood")
	public int addGood(@RequestParam("pic")MultipartFile[] pics,  HttpServletRequest request, HttpSession session, GoodsInfo gf) {
		if (pics != null && pics.length > 0 && !pics[0].isEmpty()) {
			try {
				String basePath = request.getServletContext().getRealPath("");
				String temp = request.getServletContext().getInitParameter("uploadPath");
				if (temp != null) {
					savePath = temp;
				}
				String picStr = "";
				File dest = null;
				String path = null;
				for (MultipartFile fl : pics) {
					path = savePath + "/" + new Date().getTime() + "_" + fl.getOriginalFilename();
					dest = new File(new File(basePath).getParentFile(), path);
					fl.transferTo(dest); // 将图片保存到服务器
					picStr += path + ";";
				}
				if (!"".equals(picStr)) {
					picStr = picStr.substring(0, picStr.lastIndexOf(";"));
				}
				gf.setPics(picStr);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return service.add(gf);
	}
	
	@RequestMapping("/finds")
	public Map<String, Object> finds() {
		return service.finds();
	}
	
	@RequestMapping("/findByFirst")
	//public Map<String, Object> findByFirst(@RequestParam  Map<String, String> map) {
	public Map<String, Object> findByFirst(int tno, int page, int rows) {
		return service.findByFirst(tno, page, rows);
	}
	
	@RequestMapping("/findByPage")
	//public List<GoodsInfo> findByPage(@RequestParam Map<String, String> map) {
	public List<GoodsInfo> findByPage(int tno, int page, int rows) {
		return service.findByType(tno, page, rows);
	}
}
