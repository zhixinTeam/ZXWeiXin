package com.zhixin.client;

import java.util.Timer;
import java.util.TimerTask;


import weixin.popular.support.TokenManager;

public class SendMsg {

	
	
	public static void main(String args []){
		
		SendMsg send = new SendMsg();
		//send.init();
		
	}
	
	
	public  void init(){
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
			}
		},0,1000);
		this.test();
	}
	
	
	public void test(){
	}
	
}
