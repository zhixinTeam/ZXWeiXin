package com.zhixin.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zhixin.model.Sys_Role;

public class RoleUtil {
	
	
	
	
	
	/**
	 * 遍历role树，把所有的role遍历出来放到同一个集合中返回，并且其中所有role的名称都修改了，以表示层次。
	 * 
	 * @param topList
	 * @return
	 */
	public static List<Sys_Role> getAllRoles(List<Sys_Role> topList) {
		List<Sys_Role> list = new ArrayList<Sys_Role>();
		walkRoleList(topList, "┣", list);
		return list;
	}
	
	
	
	/**
	 * 遍历角色树，把遍历出的部门信息放到指定的集合中
	 * 
	 * @param topList
	 */
	private static void walkRoleList(Collection<Sys_Role> topList, String prefix, List<Sys_Role> list) {
		for (Sys_Role top : topList) {
			// 顶点
			Sys_Role copy = new Sys_Role(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setRolename(prefix + top.getRolename());
			list.add(copy); // 把副本添加到同一个集合中

			// 子树
			walkRoleList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
	
	
	
}
