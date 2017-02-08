package com.zhixin.tools;

import javax.annotation.Resource;

import weixin.popular.bean.message.EventMessage;

public class NewsMsgUtil {

	
	
	public static String getBindNewsXML(EventMessage eventMessage){
		 String params ="";
		/*String startxml ="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>3</ArticleCount>    "+ 
				"<Articles>       ";
		
		String bodyxml = " <item>        "+   
				"<Title><![CDATA[[苹果产品信息查询]]></Title> "+
				"<Description><![CDATA[序列号：USE IMEI NUMBERIMEI号：358031058974471设备名称：iPhone 5C设备颜色：设备容量：激活状态：已激活电话支持：未过期[2014-01-13]硬件保修：未过期[2014-10-14]生产工厂：中国]]>    </Description> "+  
				"<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/J0HibacFzxqiaFPjlg4oIq6V6XxOHjvjpia8BL8ybhrtBSt07kBcf71y7KatElKCY3Dg37ZaDFdAEmecazvGAzvMA/0]]></PicUrl>   "+      
				"<Url><![CDATA[http://www.baidu.com]]></Url>      "+
				"</item>     ";
		
		String endxml="</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";*/ http://www.hnzxtech.cn/
		params+="?fromUserName="+eventMessage.getFromUserName();
		params+="&toUserName="+eventMessage.getToUserName();
		String resthttp="www.hnzxtech.cn/wxplatform/wxuser/goAddU"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/wxuser/goAddU"+params;
		//String resthttp="101.200.195.224/wxplatform/wxuser/goAddU"+params;
		String edithttp="";
		 System.out.println("ddddddddd2");
		String readhttp="";
		//https://mmbiz.qlogo.cn/mmbiz_jpg/aPeI5BiaNQ1kPlh6Q17P78JAyibuKcqLz2HcHYu1YI66aoo3CUJ9R3to5aEQbolPwTp8l9uarHQ5bBZ3y5aUjFRQ/0?wx_fmt=jpeg
		String toXml ="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>1</ArticleCount>    "+ 
				"<Articles>       "
				+ " <item>        "+   
				"<Title><![CDATA[[注册业务系统]]]></Title> "+
				"<Description><![CDATA[注册工厂对应的业务系统]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/menu/register.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+

				/*" <item>        "+   
				"<Title><![CDATA[[修改绑定]]]></Title> "+
				"<Description><![CDATA[修改业务系统绑定]]>    </Description> "+  
				"<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/J0HibacFzxqiaFPjlg4oIq6V6XxOHjvjpia8BL8ybhrtBSt07kBcf71y7KatElKCY3Dg37ZaDFdAEmecazvGAzvMA/0]]></PicUrl>   "+      
				"<Url><![CDATA[http://www.baidu.com]]></Url>      "+
				"</item>     "+*/

				 /*" <item>        "+   
				"<Title><![CDATA[[绑定须知]]]></Title> "+
				"<Description><![CDATA[绑定情况详细描述]]>    </Description> "+  
				"<PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/J0HibacFzxqiaFPjlg4oIq6V6XxOHjvjpia8BL8ybhrtBSt07kBcf71y7KatElKCY3Dg37ZaDFdAEmecazvGAzvMA/0]]></PicUrl>   "+      
				"<Url><![CDATA[http://www.baidu.com]]></Url>      "+
				"</item>     "+*/
				"</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";
		
		
		return toXml;
		
		
	}

	public static String getSystemNewsXML(EventMessage eventMessage) {
		 String params =""; 
		// TODO Auto-generated method stub
		params+="?fromUserName="+eventMessage.getFromUserName();
		params+="&toUserName="+eventMessage.getToUserName();
		String resthttp="www.hnzxtech.cn/wxplatform/wxuser/tophoneindex"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/wxuser/tophoneindex"+params;
		//String resthttp="101.200.195.224/wxplatform/wxuser/tophoneindex"+params;
		String toXml ="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>1</ArticleCount>    "+ 
				"<Articles>       "
				+ " <item>        "+   
				"<Title><![CDATA[[进入系统]]]></Title> "+
				"<Description><![CDATA[进入工厂业务系统]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/20161123/6616c03ec95b4a4a8e27c4d710004913.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+

				
				"</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";
		return toXml;
	}
	
	public static String getSecodeNewsXML(EventMessage eventMessage) {
		 String params ="";
		// TODO Auto-generated method stub
		params+="?fromUserName="+eventMessage.getFromUserName();
		params+="&toUserName="+eventMessage.getToUserName();
		String resthttp="www.hnzxtech.cn/wxplatform/tool/go_query"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/tool/go_query"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/wxuser/tophoneindex"+params;
		//String resthttp="101.200.195.224/wxplatform/wxuser/tophoneindex"+params;
		String toXml ="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>1</ArticleCount>    "+ 
				"<Articles>       "
				+ " <item>        "+   
				"<Title><![CDATA[[防伪码查询]]]></Title> "+
				"<Description><![CDATA[进入防伪码查询系统]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/menu/query_code.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+
				"</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";
		return toXml;
	}
	
	
	public static String getCarsNewsXML(EventMessage eventMessage) {
		 String params ="";
		// TODO Auto-generated method stub
		params+="?fromUserName="+eventMessage.getFromUserName();
		params+="&toUserName="+eventMessage.getToUserName();
		String resthttp="www.hnzxtech.cn/wxplatform/tool/go_car"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/tool/go_car"+params;
		//String resthttp="bigbug.nat123.net/wxplatform/wxuser/tophoneindex"+params;
		//String resthttp="101.200.195.224/wxplatform/wxuser/tophoneindex"+params;
		String toXml ="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>1</ArticleCount>    "+ 
				"<Articles>       "
				+ " <item>        "+   
				"<Title><![CDATA[[工厂待装查询]]]></Title> "+
				"<Description><![CDATA[进入工厂待装查询系统]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/menu/query_car.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+
				"</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";
		return toXml;
	}
	
	
	public static  String getReportNewsXML(EventMessage eventMessage) {
		
		 String params ="";
		 String toXml="";
			// TODO Auto-generated method stub
			params+="?fromUserName="+eventMessage.getFromUserName();
			params+="&toUserName="+eventMessage.getToUserName();
			String resthttp="www.hnzxtech.cn/wxplatform/tool/go_car"+params;
			//String resthttp="bigbug.nat123.net/wxplatform/tool/go_car"+params;
			//String resthttp="bigbug.nat123.net/wxplatform/wxuser/tophoneindex"+params;
			//String resthttp="101.200.195.224/wxplatform/wxuser/tophoneindex"+params;
		System.out.println("ssssssssssssssssssssssssssssss"+eventMessage.getStatus());
		if (eventMessage.getStatus().equals("0")) {
			
			toXml="<xml> "+
					"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
					"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
					"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
					"<MsgType><![CDATA[news]]></MsgType>   "+
					"<Content><![CDATA[]]></Content>     "+
					"<ArticleCount>1</ArticleCount>    "+ 
					"<Articles>       "
					+ " <item>        "+   
					"<Title><![CDATA[[你没有权限查询报表]]]></Title> "+
					"<Description><![CDATA[错误]]>    </Description> "+  
					"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/20161123/35679d398222424ca0575a0b3d25aa5c.jpg]]></PicUrl>   "+      
					"<Url><![CDATA["+resthttp+"]]></Url>      "+
					"</item>     "+
					"</Articles>    "+
					"<FuncFlag>0</FuncFlag> "+
					"</xml>";
		}else{
			toXml="<xml> "+
				"<ToUserName><![CDATA["+eventMessage.getFromUserName()+"]]></ToUserName>  "+
				"<FromUserName><![CDATA["+eventMessage.getToUserName()+"]]></FromUserName> "+  
				"<CreateTime>"+eventMessage.getCreateTime()+"</CreateTime>   "+
				"<MsgType><![CDATA[news]]></MsgType>   "+
				"<Content><![CDATA[]]></Content>     "+
				"<ArticleCount>3</ArticleCount>    "+ 
				"<Articles>       "
				+ " <item>        "+   
				"<Title><![CDATA[[年度报表]]]></Title> "+
				"<Description><![CDATA[进入年度报表查询]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/20161123/35679d398222424ca0575a0b3d25aa5c.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+
				 " <item>        "+   
				"<Title><![CDATA[[季度报表查询]]]></Title> "+
				"<Description><![CDATA[进入季度报表查询]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/20161123/35679d398222424ca0575a0b3d25aa5c.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+
				 " <item>        "+   
				"<Title><![CDATA[[月度报表查询]]]></Title> "+
				"<Description><![CDATA[进入月度报表查询]]>    </Description> "+  
				"<PicUrl><![CDATA[http://www.hnzxtech.cn/uploadFiles/uploadImgs/20161123/35679d398222424ca0575a0b3d25aa5c.jpg]]></PicUrl>   "+      
				"<Url><![CDATA["+resthttp+"]]></Url>      "+
				"</item>     "+
				"</Articles>    "+
				"<FuncFlag>0</FuncFlag> "+
				"</xml>";
		}
		return toXml;
	}
	
	
	
	
	
}
