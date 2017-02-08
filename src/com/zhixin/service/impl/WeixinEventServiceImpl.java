package com.zhixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.WeixinEventDao;
import com.zhixin.service.WeixinEventService;

import weixin.popular.bean.message.EventMessage;

@Service(value="weixinEventService")
public class WeixinEventServiceImpl implements WeixinEventService {

	@Resource(name="weixineventdao")
	private WeixinEventDao weixineventdao;
	
	@Override
	public void update_suborunsub(EventMessage eventMessage) {
		// TODO Auto-generated method stub
		if ("subscribe".equals(eventMessage.getEvent())){
			//用户关注公共平台账号
			weixineventdao.save_subfactory(eventMessage);
		}else if("unsubscribe".equals(eventMessage.getEvent())){
			//用户取消关注公共平台账号
			weixineventdao.unsubfactory(eventMessage);
		}
	}

	

}
