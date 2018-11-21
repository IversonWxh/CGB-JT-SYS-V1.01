package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysLog;

public interface SysLogDao {
	/**
	 * 保存日志信息到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysLog entity);
	
	/**
	 * 基于日志id执行日志的删除操作
	 * @param ids
	 * @return
	 */
	int deleteObjects(@Param("ids")Integer... ids);
	
	/**按用户名获取记录总数
	 * 当dao接口方法参数应用在mybatis的动态sql中时，
	 * 无论方法中的参数有几个，都要使用
	 * @Param注解对参数进行定义！！！！
	 * 
	 * @param username 是查询条件
	 * */
	int getRowCount(@Param("username")String username);
	
	/**
	 * 基于查询条件查询当前数据
	 * @param username	用户名
	 * @param startIndex起始位置
	 * @param pageSize	页面大小（每页多少条记录）
	 * 说明:分页语句	limit startIndex,pageSize 
	 * @return			
	 */
	List<SysLog> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
}
