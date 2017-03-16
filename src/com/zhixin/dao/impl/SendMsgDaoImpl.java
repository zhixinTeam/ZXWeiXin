package com.zhixin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.SendMsgDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.model.X_Msg_Type;
@Service(value="sendmsgdao")
public class SendMsgDaoImpl  extends DaoSupportImpl<X_Eventmsg> implements SendMsgDao{

	@Override
	public List<X_Eventmsg> findUnsendMsg() {
		// TODO Auto-generated method stub
		System.out.println("enter findUnsendMsg++++++++");
		Query query=getSession().createQuery("from X_Eventmsg x where x.issend = '0' and x.sendcount<'3' order by createTime asc");  //带条件的查询语句     
		List<X_Eventmsg> list=query.list();
		System.out.println(list.size()+"要修改的数量+++++++++++++++++");
		for(X_Eventmsg msg: list){
			String c_id =	msg.getReviceuser();
			Shop_Client client=(Shop_Client) getSession().createQuery("from Shop_Client x where x.clientnumber ='"+c_id+"' ").uniqueResult();  //带条件的查询语句     
			
			if(client!=null)
				msg.setOpenid(client.getBindcustmoer().getOpenid());
			else
				msg.setOpenid(msg.getReviceuser());
		}
		return list;
	}

	@Override
	public void update_issend_msg(String stub_str ,int sendcount,int issend) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("update X_Eventmsg  set issend ='"+issend+"' ,sendcount='"+sendcount+"' where id =?");
		query.setParameter(0, stub_str);
		query.executeUpdate();
		
	}

	@Override
	public Map<String ,String> findMsgType(String factoryid, String type1) {
		// TODO Auto-generated method stub
		Map<String,String> returmap = new HashMap<>();
		
		//doc_factorys
		String templateid ="";
		String appid = "";
		String originalID="";
		Query query =getSession().createQuery("from Doc_Factory f  where f.id='"+factoryid+"' ");
		List<Doc_Factory> listcom =query.list();
		if(listcom.size()>0){
			Doc_Factory fac =listcom.get(0);
			Doc_Company com =fac.getDoc_company();
			appid =com.getAppid();
			originalID=com.getOriginalID();
			Set<X_Msg_Type> msgtypes =com.getX_msg_types();
			for(X_Msg_Type msgtype :msgtypes){
				if (type1.equals(msgtype.getTypebs())){
					templateid = msgtype.getTemplateid();
					break;
				}
		}
		
		}
		
		/*Query query =getSession().createQuery("from Doc_Company c  where c.originalID='"+factoryid+"' ");
		List<Doc_Company> listcom =query.list();
		String templateid ="";
		String appid = "";
		if(listcom.size()>0){
			Doc_Company com = listcom.get(0);
			appid =com.getAppid();
			Set<X_Msg_Type> msgtypes =com.getX_msg_types();
			
			for(X_Msg_Type msgtype :msgtypes){
				if (type1.equals(msgtype.getTypebs())){
					templateid = msgtype.getTemplateid();
					break;
				}
			}
			
		}*/
		returmap.put("originalID", originalID);
		returmap.put("templateid", templateid);
		returmap.put("appid", appid);
		return returmap;
		//return "0G-6H8iGN0UGUBGy__Wzhs0bVUcwNjlZC3fv0NFREsw";
	}

	@Override
	public X_Eventmsg findMsgbyID(String msg_id) {
		// TODO Auto-generated method stub
		Query query =getSession().createQuery("from X_Eventmsg  x  where x.id='"+msg_id+"' ");
		List<X_Eventmsg> listmsg =query.list();
		if(listmsg.isEmpty())
			return new X_Eventmsg();
		else 
			return listmsg.get(0);
	}

	//推送微信消息，修改消息状态
	/*@Override
	public void update_sendMsgService() {
		// TODO Auto-generated method stub
		
	}*/

}
