package com.zhixin.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.CompanyDao;
import com.zhixin.dao.SendMsgDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.model.X_Msg_Type;
import com.zhixin.service.SendMsgService;
import com.zhixin.tools.TimestampUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.message.templatemessage.TemplateMessage;
import weixin.popular.bean.message.templatemessage.TemplateMessageItem;
import weixin.popular.bean.message.templatemessage.TemplateMessageResult;
import weixin.popular.support.TokenManager;
import weixin.popular.util.JsonUtil;

@Service(value="sendmsgservice")
public class SendMsgServiceImpl implements SendMsgService{

	
	@Resource(name="sendmsgdao")
	private SendMsgDao sendmsgdao ;

	/*@Resource(name="companydao")
	private CompanyDao companydao;*/
	
	@Override
	public void update_sendMsgService() {
		// TODO Auto-generated method stub
		List<X_Eventmsg> listunmsg =sendmsgdao.findUnsendMsg();
		System.out.println(listunmsg.size()+"listunmsg的数量++++++++++++++++++++");
		for(X_Eventmsg msg: listunmsg){
			String type1 = msg.getMsgType();
			String id = msg.getId();
			String content =msg.getContent();
			String openid =msg.getOpenid();
			String factoryid =msg.getFactoryid();
			Map<String,String> retmap =sendmsgdao.findMsgType(factoryid,type1);
		    //String originalID =	retmap.get("originalID");
			int sendcount =msg.getSendcount();
			sendcount =sendcount+1;
			TemplateMessage templatemsg =null;
			System.out.println("模板消息的类型++++++++++++++++++++"+type1);
			 switch(type1)
		        {
		              case "1":
		            	   templatemsg =this.sendBilling(retmap, content);
			               break;
		              case "2":
		            	  templatemsg =this.sendExwfac(retmap, content);
		            	  break;
		            	  
		              case "3":
		            	  templatemsg =this.sendReport(retmap, content);
		            	  break;
		              case "4":
		            	  templatemsg =this.senddelorder(retmap, content);
		            	  break;
		              case "5":
		            	  templatemsg =this.sendxthd(retmap, content);
		            	  break;
		              default:
		            	   System.out.println("other"); }
			//修改状态
				int issend =0;
			if(openid !=null){
				templatemsg.setTouser(openid);
				//查看详情跳转的地址
				String get_param="?type="+type1+"&msg_id="+id;
				//if(!type1.equals("5"))
				templatemsg.setUrl("http://www.hnzxtech.cn/wxplatform/wxuser/msgDeatils"+get_param);
				TemplateMessageResult templateresult =MessageAPI.messageTemplateSend(TokenManager.getToken(retmap.get("appid")), templatemsg);
				//修改状态
				if("0".equals(templateresult.getErrcode()))
					issend=1;
				System.out.println("模板消息发送成功+++++++++状态是1+++++++++++++++++");
			}
				
			sendmsgdao.update_issend_msg(id,sendcount,issend);
				
		}
		
		
		
		
	}
	//下提货单通知
	private TemplateMessage sendxthd(Map<String, String> retmap, String content) {
		// TODO Auto-generated method stub
		 JSONObject jb = JSONObject.fromObject(content);
		 	System.out.println("enter sendxthd++++++");
		    String first = "电子委托单号："+jb.get("thorderno").toString();
		    String remark = "点击详情，查看提货二维码。";
		    LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap<>();


		    TemplateMessageItem item2 = new TemplateMessageItem(jb.get("clientname").toString(), "#173177");
		    data.put("keyword1", item2);

		    TemplateMessageItem item3 = new TemplateMessageItem(jb.get("stockname").toString(), "#173177");
		    data.put("keyword2", item3);

		    TemplateMessageItem item4 = new TemplateMessageItem(jb.get("goodsnumber").toString(), "#173177");
		    data.put("keyword3", item4);

		    TemplateMessageItem item5 = new TemplateMessageItem(jb.get("thrq").toString(), "#173177");
		    data.put("keyword4", item5);
		    data.put("first", new TemplateMessageItem(first, "#173177"));
		    data.put("remark", new TemplateMessageItem(remark, "#FF0000"));
		    TemplateMessage template = new TemplateMessage();
		    template.setData(data);
		    template.setTemplate_id((String)retmap.get("templateid"));
		    return template;
	}
	//开单通知
	 private TemplateMessage sendBilling(Map<String, String> retmap, String content)
	  {
	    JSONObject jb = JSONObject.fromObject(content);
	    String first = "";
	    String remark = "开单成功,谢谢对产品支持。";
	    LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap<>();

	    TemplateMessageItem item1 = new TemplateMessageItem(jb.get("CusName").toString(), "#173177");
	    data.put("keyword1", item1);

	    TemplateMessageItem item2 = new TemplateMessageItem(jb.get("billid").toString(), "#173177");
	    data.put("keyword2", item2);

	    TemplateMessageItem item3 = new TemplateMessageItem(jb.get("stockno").toString(), "#173177");
	    data.put("keyword3", item3);

	    TemplateMessageItem item4 = new TemplateMessageItem(jb.get("stockname").toString(), "#173177");
	    data.put("keyword4", item4);

	    TemplateMessageItem item5 = new TemplateMessageItem(TimestampUtil.timestamptoString(TimestampUtil.getnowtime()), "#173177");
	    data.put("keyword5", item5);
	    data.put("first", new TemplateMessageItem(first, "#173177"));
	    data.put("remark", new TemplateMessageItem(remark, "#173177"));
	    TemplateMessage template = new TemplateMessage();
	    template.setData(data);
	    template.setTemplate_id((String)retmap.get("templateid"));
	    return template;
	  }
	 //出厂通知
	  private TemplateMessage sendExwfac(Map<String, String> retmap, String content)
	  {
	    JSONObject jb = JSONObject.fromObject(content);
	    String first = "";
	    String remark = "货物出厂，请注意查收。";
	    LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap();

	    TemplateMessageItem item1 = new TemplateMessageItem(jb.get("billid").toString(), "#173177");
	    data.put("keyword1", item1);

	    TemplateMessageItem item2 = new TemplateMessageItem(jb.get("truck").toString(), "#173177");
	    data.put("keyword2", item2);

	    TemplateMessageItem item3 = new TemplateMessageItem(jb.get("stockname").toString(), "#173177");
	    data.put("keyword3", item3);

	    TemplateMessageItem item4 = new TemplateMessageItem(jb.get("transname").toString(), "#173177");
	    data.put("keyword4", item4);

	    TemplateMessageItem item5 = new TemplateMessageItem(TimestampUtil.timestamptoString(TimestampUtil.getnowtime()), "#173177");
	    data.put("keyword5", item5);
	    data.put("first", new TemplateMessageItem(first, "#173177"));
	    data.put("remark", new TemplateMessageItem(remark, "#173177"));
	    TemplateMessage template = new TemplateMessage();
	    template.setData(data);
	    template.setTemplate_id((String)retmap.get("templateid"));
	    return template;
	  }
	  //订单撤销通知
	  private TemplateMessage senddelorder(Map<String, String> retmap, String content)
	  {
	    JSONObject jb = JSONObject.fromObject(content);
	    String first = "";
	    String remark = "订单已经删除，欢迎您再次下单。";
	    LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap();

	    TemplateMessageItem item1 = new TemplateMessageItem(jb.get("CusName").toString(), "#173177");
	    data.put("keyword1", item1);
	    TemplateMessageItem item2 = new TemplateMessageItem(jb.get("stockno").toString(), "#173177");
	    data.put("keyword2", item2);

	    TemplateMessageItem item3 = new TemplateMessageItem(jb.get("billid").toString(), "#173177");
	    data.put("keyword3", item3);

	    TemplateMessageItem item4 = new TemplateMessageItem(jb.get("stockname").toString(), "#173177");
	    data.put("keyword4", item4);
	    TemplateMessageItem item5 = new TemplateMessageItem(TimestampUtil.timestamptoString(TimestampUtil.getnowtime()), "#173177");
	    data.put("keyword5", item5);
	    data.put("first", new TemplateMessageItem(first, "#173177"));
	    data.put("remark", new TemplateMessageItem(remark, "#173177"));
	    TemplateMessage template = new TemplateMessage();
	    template.setData(data);
	    template.setTemplate_id((String)retmap.get("templateid"));
	    return template;
	  }
      //监控报表通知
	  private TemplateMessage sendReport(Map<String, String> retmap, String content)
	  {
	    JSONObject jb = JSONObject.fromObject(content);
	    JSONArray ja = jb.getJSONArray("content");
	    String first = "工厂销量通知";
	    String remark = "销量报表，请注意校对。";
	    LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap();
	    TemplateMessageItem item1 = new TemplateMessageItem(jb.get("start_date").toString(), "#173177");
	    data.put("keyword1", item1);
	    TemplateMessageItem item2 = new TemplateMessageItem(jb.get("end_date").toString(), "#173177");
	    data.put("keyword2", item2);
	    TemplateMessageItem item3 = new TemplateMessageItem("查看报表数据，请点击详情！", "#173177");
	    data.put("keyword3", item3);
	    data.put("first", new TemplateMessageItem(first, "#173177"));
	    data.put("remark", new TemplateMessageItem(remark, "#173177"));
	    TemplateMessage template = new TemplateMessage();
	    template.setData(data);
	    template.setTemplate_id((String)retmap.get("templateid"));
	    return template;
	  }

	@Override
	public X_Eventmsg findmsgbyID(String msg_id) {
		// TODO Auto-generated method stub
		return sendmsgdao.findMsgbyID(msg_id);
	}
	
	
}
