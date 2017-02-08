package com.zhixin.entity;

public class Json_Car {
	
	private String goodsname;
	
	private String vehicle_Lane ;
	
	private String car_num;

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getVehicle_Lane() {
		return vehicle_Lane;
	}

	public void setVehicle_Lane(String vehicle_Lane) {
		this.vehicle_Lane = vehicle_Lane;
	}

	public String getCar_num() {
		return car_num;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public Json_Car(String goodsname, String vehicle_Lane, String car_num) {
		super();
		this.goodsname = goodsname;
		this.vehicle_Lane = vehicle_Lane;
		this.car_num = car_num;
	}
	
	public Json_Car() {
		super();
	}

}
