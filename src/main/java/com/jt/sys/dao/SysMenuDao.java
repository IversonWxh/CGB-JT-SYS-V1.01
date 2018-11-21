package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;
/**
 * 基于此接口实现数据库菜单操作
 * @author cgb
 *
 */
public interface SysMenuDao {	//一般一张表对应一个Dao接口
	
	/**
	 * 基于菜单id获取菜单对应的权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds") Integer[] menuIds);
	
	/**
	 * 更新菜单数据
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	
	/**
	 * 将菜单信息写入到数据库
	 * @param entity 封装了菜单信息的pojo对象
	 * @return
	 */
	int insertObject(SysMenu entity);
	
	/**
	 * 获取菜单节点信息（id,name,parentId）
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	 * 基于菜单id统计是否有子元素
	 * @param id
	 * @return
	 */
	int getChildCount(Integer id);
	
	
	/**
	 * 基于菜单id执行删除操作
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/**
	 * 查询所有菜单以及上级菜单的名称，然后一行
	 * 记录映射一个map对象，多个map存储到list集合
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}
