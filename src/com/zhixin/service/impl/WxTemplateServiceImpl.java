package com.zhixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhixin.dao.WxTemplateDao;
import com.zhixin.model.Doc_Company;
import com.zhixin.model.PageBean;
import com.zhixin.model.X_Msg_Type;
import com.zhixin.service.WxTemplateService;

@Service(value="wxTemplateService")
public class WxTemplateServiceImpl implements WxTemplateService {

	
	@Resource(name="wxTemplateDao")
	private WxTemplateDao wxTemplateDao;
	
	@Override
	public PageBean findpageX_Msg_Type(String currentPage,String com_id) {
		// TODO Auto-generated method stub
		return wxTemplateDao.findpageX_Msg_Type(currentPage,com_id);
	}

	@Override
	public X_Msg_Type findX_Msg_TypeById(String id) {
		// TODO Auto-generated method stub
		return wxTemplateDao.findX_Msg_TypeById(id);
	}

	@Override
	public void updateX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
		wxTemplateDao.updateX_Msg_Type(x_Msg_Type);
	}

	@Override
	public void deleteX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
		wxTemplateDao.deleteX_Msg_Type(x_Msg_Type);
	}

	@Override
	public void saveX_Msg_Type(X_Msg_Type x_Msg_Type) {
		// TODO Auto-generated method stub
		wxTemplateDao.saveX_Msg_Type(x_Msg_Type);
	}

	@Override
	public X_Msg_Type findX_Msg_TypeByTypebsAndcom_id(String typebs, Doc_Company doc_company) {
		// TODO Auto-generated method stub
		return wxTemplateDao.findX_Msg_TypeByTypebsAndcom_id(typebs, doc_company);
	}

}
