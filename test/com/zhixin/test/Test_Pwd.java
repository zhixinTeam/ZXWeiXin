package com.zhixin.test;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Test_Pwd {

	
	public static void main(String args[]){
		String username ="15238021630";
		String pwd="123456";
		
		String passwd = new  SimpleHash("SHA-1",username,pwd).toString();
		System.out.println(passwd);
		
		
	}
}
