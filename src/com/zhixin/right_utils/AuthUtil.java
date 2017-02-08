package com.zhixin.right_utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zhixin.model.Sys_Auth;


public class AuthUtil {

	
	/**
	 * 遍历权限树，把所有的部门遍历出来放到同一个集合中返回，并且其中所有部门的名称都修改了，以表示层次。
	 * 
	 * @param topList
	 * @return
	 */
	public static List<Sys_Auth> getAllAuths(List<Sys_Auth> topList) {
		List<Sys_Auth> list = new ArrayList<Sys_Auth>();
		walkAuthTreeList(topList, "┣", list);
		return list;
	}

	/**
	 * 遍历部门树，把遍历出的部门信息放到指定的集合中
	 * 
	 * @param topList
	 */
	private static void walkAuthTreeList(Collection<Sys_Auth> topList, String prefix, List<Sys_Auth> list) {
		for (Sys_Auth top : topList) {
			// 顶点
			Sys_Auth copy = new Sys_Auth(); // 使用副本，因为原对象在Session中
			copy.setId(top.getId());
			copy.setAuthname(prefix + top.getAuthname());
			list.add(copy); // 把副本添加到同一个集合中

			// 子树
			walkAuthTreeList(top.getChildren(), "　" + prefix, list); // 使用全角的空格
		}
	}
}
