package com.zhixin.test;

import weixin.popular.api.MenuAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;
import weixin.popular.support.TokenManager;

public class Test_EditMenu {

	
	public static void main(String args[]){
		String appid="wx80d32387cee6c540";
		String secret ="469846fabb60680236fe86e6413900cd";
		//String access_token ="A5i8hJAhJRGD-qUvnqdN18-hZkJVevNyKBIWTZuPyYezcShK7NoP5JB8VevY46HDknZgnXkX7VqI7cbStj2WKU8Z7tpYGQ3TUBNEz9z5i6rjGu_E1s-xRxrH7noMg_uwNWWbAGAVEE";
		Token token=TokenAPI.token(appid, secret);
		//System.out.println(token.getErrcode()+token.getErrmsg());
		//System.out.println(token.getAccess_token());
		String access_token=token.getAccess_token();
		//System.out.println(access_token);
		//http://101.200.87.118:8080/wxplatform/static/tinybar/
		String menuJson ="{\"button\":["
				+ "{\"name\":\"账户查询\",\"sub_button\":[{\"type\":\"click\",\"name\":\"账户绑定\",\"key\":\"V1001_reast\"}]},"
				+ "{\"name\":\"信息查询\",\"sub_button\":[{\"type\":\"click\",\"name\":\"防伪码查询\",\"key\":\"V2001_secode\"},{\"type\":\"click\",\"name\":\"工厂待装查询\",\"key\":\"V2002_install\"}]},"
				+ "{\"name\":\"更多服务\",\"sub_button\":[{\"type\":\"view\",\"name\":\"网上下单\",\"url\":\"http://www.hnzxtech.cn/dangyang/\"}]}]}";
		
		//String menuJson1 ="{\"button\":[{\"type\":\"click\",\"name\":\"系统绑定\",\"key\":\"V1001_reast\"},{\"type\":\"click\",\"name\":\"进入系统\",\"key\":\"V1001_system\"}}";
		//String access_token ="Y-wAKh-amPO3xEzxWb5mt-yji30k5z3mfyRAOUzE3R-pCsJQx-xnYqelO33e2E-E_moXA-RveFBJ_inKx3vA0LIx9vePd8wcnyvunaIYc5ULHHcACAYUU";
		MenuAPI.menuCreate(access_token, menuJson);
		
	}
}
