package com.zhixin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Shop_Goods implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8664541712249204028L;

	private String g_id;
	
	private String goodsname;

	private String goodstype;
	
	private String goodsID;
	
	private String unitprice;
	
	private Set<Shop_Order> shoporders = new HashSet<>();
	
	private  Doc_Factory doc_factory ;
	
	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	

	public String getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}

	public Set<Shop_Order> getShoporders() {
		return shoporders;
	}

	public void setShoporders(Set<Shop_Order> shoporders) {
		this.shoporders = shoporders;
	}

	public Doc_Factory getDoc_factory() {
		return doc_factory;
	}

	public void setDoc_factory(Doc_Factory doc_factory) {
		this.doc_factory = doc_factory;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	@Override
	public String toString() {
		return "Shop_Goods [g_id=" + g_id + ", goodsname=" + goodsname + ", goodstype=" + goodstype + ", goodsID="
				+ goodsID + ", unitprice=" + unitprice + "]";
	}
	
	
	
	
	
	
	
}
