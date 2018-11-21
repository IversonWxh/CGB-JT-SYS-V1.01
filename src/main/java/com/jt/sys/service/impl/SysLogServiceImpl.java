package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;
/**
 * 业务层SysLogService的实现类,业务层对象
 * @Service 注解对于spring底层创建而言与@Controller的待遇一样，
 * （Spring都会将其看成是由它管理的Bean对象）
 */

@Service //<bean id="" class="">
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired	//告诉spring帮我找这个类型的对象，然后自动DI注入 	DI(SPRING)
	//@Qualifier("sysLogDaoImpl")//按名字"sysLogDaoImpl"从容器找对象,然后进行DI
	private SysLogDao sysLogDao;	//ref (实现类依赖于SysLogDao)
	/*@Autowired
	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}*/
	
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//1.判定pageCurrent参数的合法性
		if(pageCurrent==null || pageCurrent<1) 
		throw new IllegalArgumentException("页码值不正确！");
		//2.基于用户名统计日志记录总数
		int rowCount = sysLogDao.getRowCount(username);
		//3.对日志记录总数进行验证（总数为0就没必要继续查询）
		if(rowCount==0) 
		throw new ServiceException("记录不存在！");
		//4.查询当前页要显示的记录
		int pageSize=3;			//页面大小
		int startIndex=(pageCurrent-1)*pageSize;		//起始位置
		List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
		//5.对查询的记录，总记录数以及相关分页信息 进行封装
		PageObject<SysLog> po = new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		//int pageCount = rowCount/pageSize;
		//if(rowCount%pageSize!=0) pageCount++;		//10行数据有4页
		int pageCount = (rowCount-1)/pageSize+1;
		po.setPageCount(pageCount);					//总页数
		//6.返回结果PageObject
		return po;
	}

	
	@Override
	public int deleteObjects(Integer... ids) {
		//1.验证参数合法性
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("请先选择id！");
		//2.执行删除操作
		int rows = sysLogDao.deleteObjects(ids);
		//3.验证删除结果
		if(rows==0)		//可能记录不存在，被别人删除了
		throw new ServiceException("删除记录可能已经存在！");
		//4.返回结果
		return rows;
	}

}

//@Service
//public class SysLogServiceImpl implements SysLogService{
//	
//	@Autowired
//	private SysLogDao sysLogDao;
//	
//	@Override
//	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
//		
//		if(pageCurrent==null||pageCurrent<1)
//		throw new IllegalArgumentException("页码值错误！");
//		
//		int rowCount = sysLogDao.getRowCount(username);
//		
//		if(rowCount==0)
//		throw new RuntimeException("记录不存在！");
//		
//		
//		
//		return null;
//	}
//	
//}
