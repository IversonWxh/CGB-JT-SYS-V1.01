package com.test;

import org.junit.Assert;
import org.junit.Test;

import com.jt.common.vo.PageObject;  
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;

public class TestSysLogService extends TestBase{
	@Test
	public void testFindSysPageObjects() {
		//1.获取service对象
		SysLogService logService = ctx.getBean("sysLogServiceImpl",SysLogService.class);
		//测试logService值是否不为空(断言测试)
		Assert.assertNotEquals(	
				null,	//unexpected不期望出现的值
				logService);	//actual实际值
		//2.调用service对象方法
		PageObject<SysLog> po = logService.findPageObjects("admin", 2);
		//3.输出结果
		System.out.println("rowCount="+po.getRowCount());
		System.out.println("pageCount="+po.getPageCount());
		for(SysLog log : po.getRecords()) {
			System.out.println(log);
		}
	}
}
