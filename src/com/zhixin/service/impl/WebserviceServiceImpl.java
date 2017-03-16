package com.zhixin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.zhixin.dao.CompanyDao;
import com.zhixin.dao.FactoryDao;
import com.zhixin.dao.WebserviceDao;
import com.zhixin.dao.WxBindCustomerDao;
import com.zhixin.dao.shop.ShopOrderDao;
import com.zhixin.entity.Json_Order;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Goods;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Wx_BindCustomer;
import com.zhixin.model.X_Eventmsg;
import com.zhixin.service.WebserviceService;
/*
import com.zhixin.dao.shop.ShopDriverDao;
import com.zhixin.dao.shop.ShopGoodsDao;
import com.zhixin.dao.shop.ShopOrderDao;
import com.zhixin.service.shop.ShopOrderService;*/
import com.zhixin.tools.Base64Utils;
import com.zhixin.tools.ParseXml;
import com.zhixin.tools.TimestampUtil;

import net.sf.json.JSONObject;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.support.TokenManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

//////////////

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




//////////////

@Service(value="webserviceservice")
public class WebserviceServiceImpl implements WebserviceService {

	@Resource(name="webservicedao")
	private WebserviceDao webservicedao;
	
	@Resource(name="factorydao")
	private FactoryDao factorydao;
	
	@Resource(name="wxbindcustomerDao")
	private WxBindCustomerDao wxbindcustomerDao;
	
	@Resource(name="shoporderDao")
	private ShopOrderDao shoporderDao;
	
	/*
	@Resource(name="shopgoodsDao")
	private ShopGoodsDao shopgoodsDao;
	
	
	
	@Resource(name="shopdriverDao")
	private ShopDriverDao shopdriverDao;*/
	
	
	//获取客户注册信息
	@Override
	public String getCustomerInfo(String param) {
		// TODO Auto-generated method stub
				Element theElement=null,  root=null;
				String fac_id ="";
				Document xmldoc=ParseXml.toDocment(param);
				root=xmldoc.getDocumentElement();
				try{
					theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
				    fac_id=theElement.getTextContent();
				}catch(Exception e){
					return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
				}
				
			    String xmlStr = null;
			    
			  
		        try {
		        	 Document document = ParseXml.newDocment();

		            Element newroot = document.createElement("DATA");
		            document.appendChild(newroot);
		           // head start 	
		            Element head = document.createElement("head");

		            Element errcode = document.createElement("errcode");
		            errcode.setTextContent("0");
		            head.appendChild(errcode);

		            Element errmsg = document.createElement("errmsg");
		            errmsg.setTextContent("OK");

		            head.appendChild(errmsg);

		            

		            newroot.appendChild(head);
		            
		            // head end 
		            
		            // items start 	
		            List <Wx_BindCustomer> customer_list = webservicedao.findCustomerList( fac_id );
				    Doc_Factory factory =	factorydao.getById(fac_id);
				    if(customer_list.size()==0){
					}else{
						Element items = document.createElement("items");
			            for(Wx_BindCustomer customer:customer_list){

				            Element item = document.createElement("item");
			            	Element phoneid = document.createElement("Phone");
					        phoneid.setTextContent(customer.getPhone());
					        item.appendChild(phoneid);
					        Element Bindcustomerid = document.createElement("Bindcustomerid");
					        Bindcustomerid.setTextContent(customer.getId());
					        item.appendChild(Bindcustomerid);
					        Element Namepinyin = document.createElement("Namepinyin");
					        Namepinyin.setTextContent(customer.getNamepinyin());
					        item.appendChild(Namepinyin);
					        Element Email = document.createElement("Email");
					        Email.setTextContent(customer.getEmail());
					        item.appendChild(Email);
					        items.appendChild(item);
			            }
			            newroot.appendChild(items);
					}
		           
		            
		            
		            
		         // items end 	

		            TransformerFactory transFactory = TransformerFactory.newInstance();
		            Transformer transFormer = transFactory.newTransformer();
		            transFormer.setOutputProperty(OutputKeys.INDENT, "yes");  
		            transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
		            
		            DOMSource domSource = new DOMSource(document);

		            //export string
		            ByteArrayOutputStream bos = new ByteArrayOutputStream();
		            transFormer.transform(domSource, new StreamResult(bos));
		            xmlStr = bos.toString();
		            //-------
		            //save as file
		           
		            //--------
		        } catch (TransformerConfigurationException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
					
		        }catch (TransformerException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
					
		        }
		        return xmlStr;
			    
			  
	}

	
	@Override
	public String savemsg_event(String param) {
		// TODO Auto-generated method stub
		Document xmldoc=ParseXml.toDocment(param);
		Element root=xmldoc.getDocumentElement();
		
	    String billid ="";
		String card ="";
		String truck ="";
		String stockno ="";
		String stockname ="";
		String cusid ="";
		String CusName="";
		String cusaccount="";
		String makedate ="";
		String makeman ="";
		String transid ="";
		String transname ="";
		String searial="";
		String outfact ="";
		String outman ="";
		//报表消息
		String count ="";
		String qty="";
		String start_date="";
		String end_date ="";
		JSONObject jb = new JSONObject();	
		List listjb = new ArrayList();
		String msg_type= "";
		String fac_id="";
		String customer_id="";
		Element theElement=null;
		try{
			theElement=(Element) ParseXml.selectSingleNode("/DATA/head/MsgType", root);
			msg_type=theElement.getTextContent();
			theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    fac_id=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/ToUser", root);
		    customer_id=theElement.getTextContent();
		}catch (Exception e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
			
		}
		
	    if("1".equals(msg_type) || "2".equals(msg_type)|| "4".equals(msg_type)){
	    	Doc_Factory factory =null;
			Wx_BindCustomer wx_bindcustomer =null;
	    	try{
				theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/BillID", root);
			    billid=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/Card", root);
			    card=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/Truck", root);
			    truck=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/StockNo", root);
			    stockno=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/StockName", root);
			    stockname=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/CusID", root);
			    cusid=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/CusName", root);
			    CusName=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/CusAccount", root);
			    cusaccount=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/MakeDate", root);
			    makedate=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/MakeMan", root);
			    makeman=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/TransID", root);
			    transid=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/TransName", root);
			    transname=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/Searial", root);
			    searial=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/OutFact", root);
			    outfact=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/Items/Item/OutMan", root);
			    outman=theElement.getTextContent();
			    
			    JSONObject jo = new JSONObject();	
				//Map<String, String> map1 = new HashMap<String, String>();
				jo.put("billid", billid);
				jo.put("card", card);
				jo.put("truck", truck);
				jo.put("stockno", stockno);
				jo.put("stockname", stockname);
				jo.put("cusid", cusid);
				jo.put("CusName", CusName);
				jo.put("cusaccount", cusaccount);
				jo.put("makedate", makedate);
				jo.put("makeman", makeman);
				jo.put("transid", transid);
				jo.put("transname", transname);
				jo.put("searial", searial);
				jo.put("outfact", outfact);
				jo.put("outman", outman);
				X_Eventmsg x_eventmsg = new X_Eventmsg();
				
				
				factory =factorydao.getById(fac_id);
				wx_bindcustomer = wxbindcustomerDao.getById(customer_id);
				x_eventmsg.setReviceuser(wx_bindcustomer.getOpenid());
				x_eventmsg.setContent(jo.toString());
				x_eventmsg.setSenduser(factory.getDoc_company().getOriginalID());
				//x_eventmsg.setCreateTime((String) mapparam.get(""));
				//根据传过来的type找到对应的模版id
				
				x_eventmsg.setFactoryid(fac_id);
				x_eventmsg.setCreateTime(System.currentTimeMillis());
				//x_eventmsg.setEvent(jb.get("event").toString());
				x_eventmsg.setMsgType(msg_type);
				//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
				x_eventmsg.setSendcount(0);
				x_eventmsg.setIssend(0);
				webservicedao.save(x_eventmsg);	
			}catch(Exception e){
				String str_erro="";
				
				str_erro=e.getMessage();
				return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+str_erro+e.toString()+"</errmsg>\n</head>\n</DATA>";
			}
	    	
	    }else if("3".equals(msg_type)){
	    	Wx_BindCustomer wx_bindcustomer =null;
			Doc_Factory factory =null;
	    	try{
		    	NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
		    	for (int i = 0; i < nodelist.getLength(); i++) {
		    		Node employee = nodelist.item(i);
		    		NodeList datalist =employee.getChildNodes();
		    		for(int j=0;j<datalist.getLength();j++){
		    			Node data = datalist.item(j);
		    			if ("StockNo".equals(data.getNodeName())) {
							stockno=data.getTextContent();
						} else if ("StockName".equals(data.getNodeName())) {
							stockname=data.getTextContent();
						}  else if ("Count".equals(data.getNodeName())) {
							count=data.getTextContent();
						}  else if ("Qty".equals(data.getNodeName())) {
							qty=data.getTextContent();
						} 
		    		}
		    		
		    		Map<String, String> map1 = new HashMap<String, String>();
					map1.put("stockno", stockno);
					map1.put("stockname", stockname);
					map1.put("count", count);
					map1.put("qty", qty);
					listjb.add(map1);
		    	}
		    	//报表开始终止时间
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/remark/StartDate", root);
			    start_date=theElement.getTextContent();
			    theElement=(Element) ParseXml.selectSingleNode("/DATA/remark/EndDate", root);
			    end_date=theElement.getTextContent();
		    	jb.put("content",listjb);
				jb.put("start_date", start_date);
				jb.put("end_date", end_date);
				
			
				X_Eventmsg x_eventmsg = new X_Eventmsg();
				factory =factorydao.getById(fac_id);
				wx_bindcustomer = wxbindcustomerDao.getById(customer_id);
				x_eventmsg.setReviceuser(wx_bindcustomer.getOpenid());
				x_eventmsg.setContent(jb.toString());
				x_eventmsg.setSenduser(factory.getDoc_company().getOriginalID());
				//x_eventmsg.setCreateTime((String) mapparam.get(""));
				//根据传过来的type找到对应的模版id
				
				x_eventmsg.setFactoryid(fac_id);
				x_eventmsg.setCreateTime(System.currentTimeMillis());
				//x_eventmsg.setEvent(jb.get("event").toString());
				x_eventmsg.setMsgType(msg_type);
				//x_eventmsg.setPicurl((String) mapparam.get("picurl"));
				x_eventmsg.setSendcount(0);
				x_eventmsg.setIssend(0);
				webservicedao.save(x_eventmsg);
			}catch(Exception e){
				String str_erro="";
				str_erro=e.getMessage();
				return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+str_erro+e.toString()+"</errmsg>\n</head>\n</DATA>";
			}
	    }else{
	    	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>5</errcode>\n<errmsg> MsgType is not exit !</errmsg>\n</head>\n</DATA>";
			
	    }
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>\n<DATA>\n<head>\n<errcode>0</errcode>\n<errmsg>OK</errmsg>\n</head>\n");
		
		sb.append("</DATA>");	
		return sb.toString();
		
	}

	@Override
	public String update_bindfac(String parstr) {
		// TODO Auto-generated method stub
		String fac_id ="";
		String customerid  ="";
		String isbind ="";
		try{
			Document xmldoc=ParseXml.toDocment(parstr);
			Element root=xmldoc.getDocumentElement();
			Element theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    fac_id=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/ToUser", root);
		    customerid=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/IsBind", root);
		    isbind=theElement.getTextContent();
			int is_bind=0 ;
			is_bind =Integer.parseInt(isbind);
			webservicedao.updateLinkfac_customer(fac_id,customerid,is_bind);
			//发送消息
			Wx_BindCustomer wx_bindcustomer =wxbindcustomerDao.getById(customerid);
			String opinid=wx_bindcustomer.getOpenid();
			//获取token
			Doc_Factory factory =factorydao.getById(fac_id);
			Doc_Company company =factory.getDoc_company();
			String wx_token =TokenManager.getToken(company.getAppid());
			String content_text="";
			if(is_bind==1){
				content_text ="工厂客户号绑定成功,\n请注意查收推送的消息。";
				String keyq ="{\"touser\":\""+opinid+"\",\"msgtype\": \"text\",\"text\": { \"content\": \""+content_text+"\"}}";
				BaseResult result=MessageAPI.messageCustomSend(wx_token, keyq);
			}
		}catch( Exception  e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>2</errcode>\n<errmsg> 'IsBind'"+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>0</errcode>\n<errmsg>OK</errmsg>\n</head>\n</DATA>";
	}

	//添加删除商品
	/*@SuppressWarnings("unchecked")
	@Override
	public String save_shopgoods(String params) {
		// TODO Auto-generated method stub
		Element theElement=null,  root=null;
		String type  ="";
		String factoryID ="";
		Document xmldoc=ParseXml.toDocment(params);
		root=xmldoc.getDocumentElement();
		try{
			
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/type", root);
		    type=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    factoryID=theElement.getTextContent();
			//新增商品
			Shop_Goods shopgoods =null;
			Doc_Factory factory =null;
			String clientID ="";
			String goodsID ="";
			String goodsname ="";
			String goodstype="";
			if("add".equals(type)){
			  NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
			  String cash ="";
			  String clientnumber ="";
			  List listjb = new ArrayList<Shop_Goods>();
			  shopgoods = new Shop_Goods();
			  factory =factorydao.getById(factoryID);
			  for (int i = 0; i < nodelist.getLength(); i++) {
			    		Node employee = nodelist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("goodsID".equals(data.getNodeName())) {
			    				goodsID=data.getTextContent();
							}  else if ("goodsname".equals(data.getNodeName())) {
								goodsname=data.getTextContent();
							}  else if ("goodstype".equals(data.getNodeName())) {
								goodstype=data.getTextContent();
							} 
			    		}
			    		shopgoods.setGoodstype(goodstype);
			    		shopgoods.setGoodsID(goodsID);
			    		shopgoods.setGoodsname(goodsname);
			    		shopgoods.setDoc_factory(factory);
						listjb.add(shopgoods);
			    }
			 shopgoodsDao.saveShopGoods(listjb); 
			 
			//删除商品
			}else if("del".equals(type)){
				 	NodeList dellist =ParseXml.selectNodes("/DATA/Items/Item", root);
				 	List listdel = new ArrayList<>();
					for (int i = 0; i < dellist.getLength(); i++) {
			    		Node employee = dellist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("goodsID".equals(data.getNodeName())) {
			    				goodsID=data.getTextContent();
							} 
			    		}
			    		listdel.add(goodsID);
			    	}
					shopgoodsDao.deleteShopGoods(listdel);
				
				
			}else if("update".equals(type)){
				  NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
				  String cash ="";
				  String clientnumber ="";
				  List listjb = new ArrayList<Shop_Goods>();
				  shopgoods = new Shop_Goods();
				  factory =factorydao.getById(factoryID);
				  for (int i = 0; i < nodelist.getLength(); i++) {
				    		Node employee = nodelist.item(i);
				    		NodeList datalist =employee.getChildNodes();
				    		for(int j=0;j<datalist.getLength();j++){
				    			Node data = datalist.item(j);
				    			if ("goodsID".equals(data.getNodeName())) {
				    				goodsID=data.getTextContent();
								}  else if ("goodsname".equals(data.getNodeName())) {
									goodsname=data.getTextContent();
								}  else if ("goodstype".equals(data.getNodeName())) {
									goodstype=data.getTextContent();
								} 
				    		}
				    		shopgoods.setGoodsID(goodsID);
				    		shopgoods.setGoodsname(goodsname);
				    		shopgoods.setGoodstype(goodstype);
				    		shopgoods.setDoc_factory(factory);
							listjb.add(shopgoods);
				    }
				  
				  shopgoodsDao.updateShopGoods(listjb); 
				
			}
		}catch(Exception e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>0</errcode>\n<errmsg>OK</errmsg>\n</head>\n</DATA>";

	}

*/
	@Override
	public String save_shopclients(String params) {
		// TODO Auto-generated method stub
		Element theElement=null,  root=null;
		String type  ="";
		String factoryID ="";
		String customerID="";
		Document xmldoc=ParseXml.toDocment(params);
		root=xmldoc.getDocumentElement();
		//wxbindcustomerDao
		try{
			
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/type", root);
		    type=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    factoryID=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Customer", root);
		    customerID=theElement.getTextContent();
			//新增客户
			Shop_Client shopclient =null;
			Doc_Factory factory =null;
			String clientID ="";
			String clientnumber ="";
			String clientname ="";
			if("add".equals(type)){
			  shopclient = new Shop_Client();
			  NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
			  String cash ="";
			  List listjb = new ArrayList<Shop_Client>();
			  factory =factorydao.getById(factoryID);
			  shopclient.setDoc_factory(factory);
			  if(factory ==null)
				  return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>8</errcode>\n<errmsg> factoryID is null</errmsg>\n</head>\n</DATA>";
				
			 
				 	Wx_BindCustomer wx_BindCustomer =wxbindcustomerDao.findFactoryBycustomerID(customerID);
				 	if(wx_BindCustomer ==null)
				 		 return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>9</errcode>\n<errmsg> customerID is not fond</errmsg>\n</head>\n</DATA>";
			    	//如果账号没有关联工厂 进行关联
				 	wxbindcustomerDao.updateLinkFactoryBycustomer(factory,wx_BindCustomer);
				 	for (int i = 0; i < nodelist.getLength(); i++) {
			    		Node employee = nodelist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("clientname".equals(data.getNodeName())) {
			    				clientname=data.getTextContent();
							}  else if ("cash".equals(data.getNodeName())) {
								cash=data.getTextContent();
							}  else if ("clientnumber".equals(data.getNodeName())) {
								clientnumber=data.getTextContent();
							}
			    		}
			    		shopclient.setBindcustmoer(wx_BindCustomer);
			    		shopclient.setCash(cash);
			    		shopclient.setClientnumber(clientnumber);
			    		shopclient.setClientname(clientname);
			    		Shop_Client ylclient=	wxbindcustomerDao.findClientByFactory( clientnumber,factoryID);
			    		if(ylclient ==null)
			    			listjb.add(shopclient);
			    	}
			    	if(listjb.size()>0)
			    		wxbindcustomerDao.saveShopClient(listjb); 
			    	else
			    		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> clientnumber is exit !</errmsg>\n</head>\n</DATA>";
			    	
			
			//删除客户
			}else if("del".equals(type)){
				 	NodeList dellist =ParseXml.selectNodes("/DATA/Items/Item", root);
				 	List listdel = new ArrayList<>();
					for (int i = 0; i < dellist.getLength(); i++) {
			    		Node employee = dellist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("clientnumber".equals(data.getNodeName())) {
			    				clientnumber=data.getTextContent();
							} 
			    		}
			    		listdel.add(clientnumber);
			    	}
					wxbindcustomerDao.deleteByClientID(listdel);
				
				
			}else if("update".equals(type)){
				 /* shopuser =	shopuserDao.findShopUserByPhoneandFactoryid(phone,factoryID);
				  shopclient = new Shop_Client();
				  NodeList nodelist =ParseXml.selectNodes("/DATA/Items/Item", root);
				  String cash ="";
				  List listjb = new ArrayList<Shop_Client>();
				  for (int i = 0; i < nodelist.getLength(); i++) {
			    		Node employee = nodelist.item(i);
			    		NodeList datalist =employee.getChildNodes();
			    		for(int j=0;j<datalist.getLength();j++){
			    			Node data = datalist.item(j);
			    			if ("clientID".equals(data.getNodeName())) {
								clientID=data.getTextContent();
							}else if ("cash".equals(data.getNodeName())) {
								cash=data.getTextContent();
							}else if ("clientnumber".equals(data.getNodeName())) {
								clientnumber=data.getTextContent();
							}else if ("clientname".equals(data.getNodeName())) {
								clientname=data.getTextContent();
							}     
			    		}
			    		shopclient.setShopuser(shopuser);
			    		shopclient.setCash(cash);
			    		shopclient.setClientnumber(clientnumber);
			    		shopclient.setClientname(clientname);
			    		//shopclient.setDoc_factory(factory);
			    		listjb.add(shopclient);
				 	}
				  
				  shopuserDao.updateShopClients(listjb); */
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>0</errcode>\n<errmsg>OK</errmsg>\n</head>\n</DATA>";

	}

	
	@Override
	public String updateget_shoporders(String params) {
		// TODO Auto-generated method stub
		String fac_id ="";
		String idnumber  ="";
		String isbind ="";
		try{
			Document xmldoc=ParseXml.toDocment(params);
			Element root=xmldoc.getDocumentElement();
			Element theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    fac_id=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/ID", root);
		    idnumber=theElement.getTextContent();
		}catch( Exception  e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>2</errcode>\n<errmsg> 'IsBind'"+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		String xmlStr = null;
		  
        try {
        	 Document document = ParseXml.newDocment();

            Element newroot = document.createElement("DATA");
            document.appendChild(newroot);
           // head start 	
            Element head = document.createElement("head");

            Element errcode = document.createElement("errcode");
            errcode.setTextContent("0");
            head.appendChild(errcode);

            Element errmsg = document.createElement("errmsg");
            errmsg.setTextContent("OK");

            head.appendChild(errmsg);

            

            newroot.appendChild(head);
            
            // head end 
            
            // items start 	
            List<Json_Order> shop_orders=shoporderDao.update_get_shoporders(fac_id,idnumber);
		    if(shop_orders.isEmpty()){
			}else{
				Element items = document.createElement("items");
				for(Json_Order json_order:shop_orders){
					 Element item = document.createElement("item");
						Element order_id = document.createElement("order_id");
						order_id.setTextContent(json_order.getO_id());
				        item.appendChild(order_id);
				        Element fac_order_no = document.createElement("fac_order_no");
				        fac_order_no.setTextContent(json_order.getFac_order_no());
				        item.appendChild(fac_order_no);
				        Element ordernumber = document.createElement("ordernumber");
				        ordernumber.setTextContent(json_order.getOrdernumber());
				        item.appendChild(ordernumber);
				        Element goodsID = document.createElement("goodsID");
				        goodsID.setTextContent(json_order.getGoodsID());
				        item.appendChild(goodsID);
				        
				        
				        Element drivername = document.createElement("drivername");
				        drivername.setTextContent(json_order.getDrivername());
				        item.appendChild(drivername);
				        
				        Element driverphone = document.createElement("driverphone");
				        driverphone.setTextContent(json_order.getDriverphone());
				        item.appendChild(driverphone);
				        
				        Element tracknumber = document.createElement("tracknumber");
				        tracknumber.setTextContent(json_order.getTracknumber());
				        item.appendChild(tracknumber);
				        
				        
				        Element goodsname = document.createElement("goodsname");
				        goodsname.setTextContent(json_order.getGoodsname());
				        item.appendChild(goodsname);
				        Element data = document.createElement("data");
				        data.setTextContent(json_order.getData());
				        item.appendChild(data);
				        items.appendChild(item);
				}
	            newroot.appendChild(items);
			}
		    // items end 	
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            transFormer.setOutputProperty(OutputKeys.INDENT, "yes"); 

            transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource domSource = new DOMSource(document);
            //export string
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transFormer.transform(domSource, new StreamResult(bos));
            xmlStr = bos.toString();
            xmlStr =xmlStr.replace("GBK", "UTF-8");
            //-------
            //save as file
           
            //--------
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
			
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
			
        }
        return xmlStr;
	}
	

	@Override
	public String updatecomplete_shoporders(String params) {
		// TODO Auto-generated method stub
		String ordernumber ="";
		String customerid  ="";
		String status ="";
		try{
			Document xmldoc=ParseXml.toDocment(params);
			Element root=xmldoc.getDocumentElement();
			Element theElement=(Element) ParseXml.selectSingleNode("/DATA/head/ordernumber", root);
			ordernumber=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/status", root);
		    status=theElement.getTextContent();
			int is_bind=0 ;
			is_bind =Integer.parseInt(status);
			webservicedao.updatecomplete_shoporders(ordernumber,is_bind);
			//发送消息
			
		}catch( Exception  e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>2</errcode>\n<errmsg> 'IsBind'"+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>0</errcode>\n<errmsg>OK</errmsg>\n</head>\n</DATA>";

	}
	
	//获取提货单详情
	@Override
	public String updateget_shoporderByNO(String params) {
		// TODO Auto-generated method stub
		String fac_id ="";
		String ordernumber  ="";
		String isbind ="";
		try{
			Document xmldoc=ParseXml.toDocment(params);
			Element root=xmldoc.getDocumentElement();
			Element theElement=(Element) ParseXml.selectSingleNode("/DATA/head/Factory", root);
		    fac_id=theElement.getTextContent();
		    theElement=(Element) ParseXml.selectSingleNode("/DATA/head/NO", root);
		    ordernumber=theElement.getTextContent();
		}catch( Exception  e){
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>2</errcode>\n<errmsg> 'IsBind'"+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
		}
		String xmlStr = null;
		  
        try {
        	 Document document = ParseXml.newDocment();

            Element newroot = document.createElement("DATA");
            document.appendChild(newroot);
           // head start 	
            Element head = document.createElement("head");

            Element errcode = document.createElement("errcode");
            errcode.setTextContent("0");
            head.appendChild(errcode);

            Element errmsg = document.createElement("errmsg");
            errmsg.setTextContent("OK");

            head.appendChild(errmsg);

            

            newroot.appendChild(head);
            
            // head end 
            
            // items start 	
            List<Json_Order> shop_orders=shoporderDao.update_get_shoporderByNO(fac_id,ordernumber);
		    if(shop_orders.isEmpty()){
			}else{
				Element items = document.createElement("items");
				for(Json_Order json_order:shop_orders){
					 Element item = document.createElement("item");
						Element order_id = document.createElement("order_id");
						order_id.setTextContent(json_order.getO_id());
				        item.appendChild(order_id);
				        Element fac_order_no = document.createElement("fac_order_no");
				        fac_order_no.setTextContent(json_order.getFac_order_no());
				        item.appendChild(fac_order_no);
				        Element ordernumber_new = document.createElement("ordernumber");
				        ordernumber_new.setTextContent(json_order.getOrdernumber());
				        item.appendChild(ordernumber_new);
				        Element goodsID = document.createElement("goodsID");
				        goodsID.setTextContent(json_order.getGoodsID());
				        item.appendChild(goodsID);
				        
				        
				        Element drivername = document.createElement("drivername");
				        drivername.setTextContent(json_order.getDrivername());
				        item.appendChild(drivername);
				        
				        Element driverphone = document.createElement("driverphone");
				        driverphone.setTextContent(json_order.getDriverphone());
				        item.appendChild(driverphone);
				        
				        Element tracknumber = document.createElement("tracknumber");
				        tracknumber.setTextContent(json_order.getTracknumber());
				        item.appendChild(tracknumber);
				        
				        
				        Element goodsname = document.createElement("goodsname");
				        goodsname.setTextContent(json_order.getGoodsname());
				        item.appendChild(goodsname);
				        Element data = document.createElement("data");
				        data.setTextContent(json_order.getData());
				        item.appendChild(data);
				        items.appendChild(item);
				}
	            newroot.appendChild(items);
			}
		    // items end 	
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            transFormer.setOutputProperty(OutputKeys.INDENT, "yes"); 

            transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource domSource = new DOMSource(document);
            //export string
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transFormer.transform(domSource, new StreamResult(bos));
            xmlStr = bos.toString();
            xmlStr =xmlStr.replace("GBK", "UTF-8");
            //-------
            //save as file
           
            //--------
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
			
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>3</errcode>\n<errmsg> "+e.getMessage()+"</errmsg>\n</head>\n</DATA>";
			
        }
        return xmlStr;
	}
	

	@Override
	public void saveeventMsg(X_Eventmsg x_eventmsg) {
		// TODO Auto-generated method stub
		webservicedao.save(x_eventmsg);	
	}

}
