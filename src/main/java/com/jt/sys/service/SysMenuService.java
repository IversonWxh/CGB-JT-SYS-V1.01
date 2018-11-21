package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuService {
	/**
	 * 将菜单信息更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	
	/**
	 * 将菜单信息持久化到数据库
	 * @param entity
	 * @return
	 */
	int saveObject(SysMenu entity);
	
	/**
	 * 查询menu树节点信息
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	List<Map<String,Object>> findObjects();
	
	
	/**
	 * 基于菜单id删除菜单以及菜单和角色关系数据
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
}
