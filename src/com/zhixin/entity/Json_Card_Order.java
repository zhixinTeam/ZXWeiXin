package com.zhixin.entity;

public class Json_Card_Order {

	private String setdate;
	
	private String billnumber;
	
	private String stockno ;
	
	private String stockname ;
	
	private String maxnumber;

	public String getSetdate() {
		return setdate;
	}

	public void setSetdate(String setdate) {
		this.setdate = setdate;
	}

	public String getBillnumber() {
		return billnumber;
	}

	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}

	public String getStockno() {
		return stockno;
	}

	public void setStockno(String stockno) {
		this.stockno = stockno;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public String getMaxnumber() {
		return maxnumber;
	}

	public void setMaxnumber(String maxnumber) {
		this.maxnumber = maxnumber;
	}

	public Json_Card_Order(String setdate, String billnumber, String stockno, String stockname, String maxnumber) {
		super();
		this.setdate = setdate;
		this.billnumber = billnumber;
		this.stockno = stockno;
		this.stockname = stockname;
		this.maxnumber = maxnumber;
	}

	public Json_Card_Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
