package com.zhixin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Page;
import com.zhixin.model.Sys_Picture;
import com.zhixin.right_utils.PageData;
import com.zhixin.service.PicturesService;
import com.zhixin.service.WeixinOrderService;

@Controller
@RequestMapping(value="/wxorder")
public class WeixinOrderController extends BaseController {

	@Resource(name="weixinOrderService")
	private WeixinOrderService weixinOrderService;
	
	
	@Resource(name="picturesService")
	private PicturesService picturesService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/listorders")
	public ModelAndView list(Page page)throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			//Sys_Picture picture = picturesService.findpictoryBy_flag();
			
			try{
				//String USERNAME = pd.getString("USERNAME");
				/*Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				Sys_User user = (Sys_User) session.getAttribute(Const.SESSION_USER);
				if(null != USERNAME && !"".equals(USERNAME)){
					USERNAME = USERNAME.trim();
					pd.put("USERNAME", USERNAME);
				}
				Sys_Role roletop =roleService.findRoleById(user.getSys_role().getId()); 
				List<Sys_Role> toplist = new ArrayList<>();
				toplist.add(roletop);
				List<Sys_Role> rolist= RoleUtil.getAllRoles(toplist);
				for(Sys_Role role:rolist){
					if(role.getId().equals(roletop.getId())){
						rolist.remove(role);
						break;
					}
				}
				String roleId = pd.getString("ROLE_ID");
				if(roleId == null || "".equals(roleId)){
					pd.put("ROLE_ID", roletop.getId());
				}*/
				String open_id =pd.getString("fromUserName");
				String toUserName = pd.getString("toUserName");
				List<Sys_Picture> listpictures =picturesService.findPicturesByOpenid(open_id);
				//如果不是绑定用户，查询集团下工厂的宣传图
				if(listpictures.size()==0){
					listpictures =picturesService.findPictureByOrginalID(toUserName);
				}
				for(Sys_Picture picture:listpictures){
					if(picture.getFlag()==1){
						listpictures.remove(picture);
						mv.addObject("picturelog",picture);
						break;
					}
				}
				
				/*Sys_Picture picture = picturesService.findpictoryBy_flag();
				mv.addObject("picturelog",picture);*/
				mv.addObject("pd", pd);
				mv.addObject("roleList",new ArrayList());
				mv.setViewName("tinybar/oauth/wx_order");
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}finally {
				logAfter(logger);
			}	
		return mv;
	}
	
	
	
}
