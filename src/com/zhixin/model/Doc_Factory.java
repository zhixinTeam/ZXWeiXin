package com.zhixin.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Doc_Factory implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -364105721380200216L;

	private String id;
	
	private String factoryname;
	
	private String serviceurl;
	
	//private String ipurl;
	
	private String serviceparam;
	
	private Doc_Company doc_company;
	
	private Sys_User user;
	
	private Timestamp createdate ;
	
	private Integer status;
	
	private String orgcode ;  //组织机构代码
	
	
	private Date startdate;
	
	private Date enddate;
	
	private String editlog;
	

	private Integer is_bind;
	
	
	
	private  Set<Wx_BindUser>   wx_bindusers = new HashSet<>();
	
	
	private Set<Sys_Role> sys_roles= new HashSet<>(); 
	
	
	private Set<Link_BindCustomers_Factorys> bindcustomer_factorys = new HashSet<>() ;
	
	
	private Set<Sys_Picture> sys_pictures= new HashSet<>(); 

	
	private Set<Sys_User> sys_users = new HashSet<>();
	
	//private Set<Shop_Client> shopclients = new HashSet<>();
	//添加shop user 与factory关联
	private Set<Shop_User> shopusers = new HashSet<>();
	
	
	private Set<Shop_Order> shoporders = new HashSet<>();
	
	
	
	private Set<Shop_Goods> shopgoods = new HashSet<>();
	//对应的app信息
	private Set<App_activity> app_activitys = new HashSet<>();
	
	private Set<App_pack> app_packs = new HashSet<>();
	
	private Set<App_picture> app_pictures = new HashSet<>();
	
	

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}

	public Set<Shop_Goods> getShopgoods() {
		return shopgoods;
	}

	public void setShopgoods(Set<Shop_Goods> shopgoods) {
		this.shopgoods = shopgoods;
	}

	/*public Set<Shop_Client> getShopclients() {
		return shopclients;
	}

	public void setShopclients(Set<Shop_Client> shopclients) {
		this.shopclients = shopclients;
	}
*/
	public Set<Sys_User> getSys_users() {
		return sys_users;
	}

	public void setSys_users(Set<Sys_User> sys_users) {
		this.sys_users = sys_users;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getServiceurl() {
		return serviceurl;
	}

	public void setServiceurl(String serviceurl) {
		this.serviceurl = serviceurl;
	}

	

	public String getServiceparam() {
		return serviceparam;
	}

	public void setServiceparam(String serviceparam) {
		this.serviceparam = serviceparam;
	}

	public Doc_Company getDoc_company() {
		return doc_company;
	}

	public void setDoc_company(Doc_Company doc_company) {
		this.doc_company = doc_company;
	}

	public Set<Sys_Role> getSys_roles() {
		return sys_roles;
	}

	public void setSys_roles(Set<Sys_Role> sys_roles) {
		this.sys_roles = sys_roles;
	}

	public Sys_User getUser() {
		return user;
	}

	public void setUser(Sys_User user) {
		this.user = user;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Sys_Picture> getSys_pictures() {
		return sys_pictures;
	}

	public void setSys_pictures(Set<Sys_Picture> sys_pictures) {
		this.sys_pictures = sys_pictures;
	}

	public Set<Wx_BindUser> getWx_bindusers() {
		return wx_bindusers;
	}

	public void setWx_bindusers(Set<Wx_BindUser> wx_bindusers) {
		this.wx_bindusers = wx_bindusers;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getEditlog() {
		return editlog;
	}

	public void setEditlog(String editlog) {
		this.editlog = editlog;
	}

	public Set<Link_BindCustomers_Factorys> getBindcustomer_factorys() {
		return bindcustomer_factorys;
	}

	public void setBindcustomer_factorys(Set<Link_BindCustomers_Factorys> bindcustomer_factorys) {
		this.bindcustomer_factorys = bindcustomer_factorys;
	}

	public Integer getIs_bind() {
		return is_bind;
	}

	public void setIs_bind(Integer is_bind) {
		this.is_bind = is_bind;
	}

	public Set<App_activity> getApp_activitys() {
		return app_activitys;
	}

	public void setApp_activitys(Set<App_activity> app_activitys) {
		this.app_activitys = app_activitys;
	}

	public Set<App_pack> getApp_packs() {
		return app_packs;
	}

	public void setApp_packs(Set<App_pack> app_packs) {
		this.app_packs = app_packs;
	}

	public Set<App_picture> getApp_pictures() {
		return app_pictures;
	}

	public void setApp_pictures(Set<App_picture> app_pictures) {
		this.app_pictures = app_pictures;
	}

	public Set<Shop_User> getShopusers() {
		return shopusers;
	}

	public void setShopusers(Set<Shop_User> shopusers) {
		this.shopusers = shopusers;
	}

	/*public String getIpurl() {
		return ipurl;
	}

	public void setIpurl(String ipurl) {
		this.ipurl = ipurl;
	}

	*/
	

	

	
	
	
	
	
}
