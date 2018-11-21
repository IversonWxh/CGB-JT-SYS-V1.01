package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	
	@Override
	public int updateObject(SysMenu entity) {
		//1.验证参数的有效性
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空！");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("名字不能为空！");
		//...
		//2.将对象持久化到数据库
		int rows = sysMenuDao.updateObject(entity);
		//3.验证结果并返回
		if(rows==0)
		throw new ServiceException("记录可能已经不存在！");
		return rows;
	}
	
	@Override
	public int saveObject(SysMenu entity) {
		//1.验证参数的有效性
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空！");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("名字不能为空！");
		//...
		//2.将对象持久化到数据库
		int rows = sysMenuDao.insertObject(entity);
		//3.验证结果并返回
		if(rows==0)
			throw new ServiceException("insert error");
		return rows;
	}
	
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> list = sysMenuDao.findZtreeMenuNodes();
		if(list==null||list.size()==0) 
		throw new ServiceException("没有菜单信息");
		return list;
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.对id进行合法校验
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效！");
		//2.删除菜单自身信息
		//2.1判定是否有子菜单
		int count=sysMenuDao.getChildCount(id);
		if(count>0)
		throw new ServiceException("请先删除子菜单！");
		//2.2没有子菜单则删除当前菜单
		count=sysMenuDao.deleteObject(id);
		if(count==0)
		throw new ServiceException("此记录可能已经不存在！");
		//3.删除菜单和角色关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.返回结果（删除的菜单的行数）
		return count;
	}
	
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if(list==null||list.size()==0) 
		throw new ServiceException("没有记录");
		return list;
	}

}
