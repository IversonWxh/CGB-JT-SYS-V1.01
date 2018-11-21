package com.jt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 在此控制器定义相关页面映射
 * @author cgb
 *
 */
@Controller
@RequestMapping("/")
public class PageController {
	/**通过次方法返回页面首页*/
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	/**通过此方法返回分页页面*/
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
	   return "login";
	}
}
//http://localhost/CGB-JT-SYS-V1.01/doIndexUI.do