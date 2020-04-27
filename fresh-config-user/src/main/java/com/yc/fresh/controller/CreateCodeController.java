package com.yc.fresh.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping
@SessionAttributes
public class CreateCodeController{
	@RequestMapping("/code")
	public void createCode(HttpSession session, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String code = getRandomCode();
		
		// 将随机生成的验证码存到session中，以便用户登录的时候获取校验
		session.setAttribute("validateCode", code);

		// 创建验证码图形对象，大小为110*28
		BufferedImage image = getCodeImage(code, 110, 28);

		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 获取验证码图形
	 * @param code
	 * @param width
	 * @param height
	 * @return
	 */
	private BufferedImage getCodeImage(String code, int width, int height) {
		// 创建图形对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形的绘制绘制对象
		Graphics g = image.getGraphics();
		
		// 设置图形的背景颜色
		g.setColor(getRandomColor(225, 30));

		// 设置绘制的边界
		g.fillRect(0, 0, width, height);

		// 设置字体 Courier New
		g.setFont(new Font("楷体", Font.ITALIC, 20));

		// 生产干扰线

		// 设置干扰线的颜色
		g.setColor(getRandomColor(160, 40));
		
		// 随机绘制干扰线
		int x1, y1, x2, y2;
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			x1 = rd.nextInt(width);
			y1 = rd.nextInt(height);
			x2 = rd.nextInt(width);
			y2 = rd.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}

		// 绘制验证码
		char[] codes = code.toCharArray();
		for (int i = 0, len = codes.length; i < len; i++) {
			// 设置验证码绘制的颜色
			g.setColor(getRandomColor(50, 80));

			g.drawString(String.valueOf(codes[i]), 10 + 24*i, 20);
		}

		g.dispose();

		return image;
	}

	/**
	 * 生成验证码的方法
	 * @return
	 */
	private String getRandomCode() {
		// 如何生存4位的随机验证码？ - 包含大写字母、小写字母和数字
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag;
		for (int i = 0; i < 4; i++) {
			flag = rd.nextInt(3);
			switch(flag) {
			case 0: sbf.append(rd.nextInt(10)); break;  // 生成数字
			case 1: sbf.append((char)(rd.nextInt(26) + 65)); break;  // 生成大写字母
			case 2: sbf.append((char)(rd.nextInt(26) + 97)); break;	// 生成小写字母
			//case 3: sbf.append((char)(rd.nextInt(20000) + 19968)); break;	// 生成汉字
			}
		}
		return sbf.toString();
	}

	/**
	 * 获取随机颜色的方法
	 * @return
	 */
	private Color getRandomColor(int start, int bound) {
		Random rd = new Random();
		int r = start + rd.nextInt(bound);
		int g = start + rd.nextInt(bound);
		int b = start + rd.nextInt(bound);
		return new Color(r, g, b);
	}
}
