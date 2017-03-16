package com.zhixin.webservice.ws;


import javax.annotation.Resource;
import javax.jws.WebService;
import com.zhixin.service.WebserviceService;
@WebService
public class ReviceWSImpl implements ReviceWS{

	@Resource(name="webserviceservice")
	private WebserviceService webserviceservice;
	
	
	public String mainfuncs(String funcname,String params){
		
		String return_str ="";
        switch(funcname)
        {
              case "getCustomerInfo":
	               return_str =webserviceservice.getCustomerInfo( params);
	               break;
              case "send_event_msg":
            	  return_str =webserviceservice.savemsg_event( params);
            	  break;
            	  
              case "get_Bindfunc":
            	  return_str =webserviceservice.update_bindfac( params);
            	  break;
              case "edit_shopclients":
            	  return_str =webserviceservice.save_shopclients( params);
            	  break;
              case "get_shoporders":
            	  return_str =webserviceservice.updateget_shoporders( params);
            	  break;
             /* case "edit_shopgoods":
            	  return_str =webserviceservice.save_shopgoods( params);
            	  break;
              */
              
              case "get_shoporderByNO":
            	  return_str =webserviceservice.updateget_shoporderByNO( params);
            	  break;
              case "complete_shoporders":
            	  return_str =webserviceservice.updatecomplete_shoporders( params);
            	  break;
              default:
            	   return_str="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DATA>\n<head>\n<errcode>1</errcode>\n<errmsg>function is no exsit !</errmsg>\n</head>\n</DATA>";
        }
		
		return return_str;
	}

}
