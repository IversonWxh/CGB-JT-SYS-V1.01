package com.jt.sys.service;

import java.util.Map;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserService {
	
	/**
	 * 基于用户id获取用户自身信息，对应部门信息以及角色id
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(
				Integer userId);

	 /**
	  * 禁用或启用用户对象
	  * @param id
	  * @param valid
	  * @param modifiedUser
	  * @return
	  */
	int validById(Integer id,Integer valid,String modifiedUser);
   
	 /**
	  * 保存用户自身信息以及用户与角色的关系数据
	  * @param entity
	  * @param roleIds
	  * @return
	  */
	int saveObject(SysUser entity,Integer[] roleIds);
	
	/**
	 * 更新用户信息
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser entity,Integer[] roleIds);
	
	PageObject<SysUserDeptResult> findPageObjects(
			String username,
			Integer pageCurrent);
}
