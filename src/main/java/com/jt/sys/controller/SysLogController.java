package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
/**spring mvc中控制层对象（后端控制器）
 * 思考：如何提供电商系统的性能？（响应的速度）
 * 1）影响系统性能的原因？
 * a)请求数据传输时间（带宽，数据量，压缩）
 * b)数据处理时间（架构，CPU，硬盘，内存，线程数，池，算法）
 * c)响应数据传输时间（带宽，距离，数据量，缓存）
 * d)响应数据渲染时间（html，CSS）
 * 
 * 2）解决问题
 * */
@Controller
@RequestMapping("/log/")
public class SysLogController {	//Handler
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 返回日志列表页面
	 * @return
	 */
	@RequestMapping("doLogListUI")
	public String doLogListUI() {
//		return "log";
		return "sys/log_list";		//html
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(
			Integer... ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete OK");
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
		try {Thread.sleep(2000);}
		catch (Exception e) {e.printStackTrace();}
		PageObject<SysLog> pageObject = sysLogService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObject);
	}//借助JSonResult对象封装控制层要返回的结果
}
