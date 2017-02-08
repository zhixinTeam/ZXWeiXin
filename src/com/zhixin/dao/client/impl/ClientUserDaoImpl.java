package com.zhixin.dao.client.impl;

import org.springframework.stereotype.Service;

import com.zhixin.base.DaoSupportImpl;
import com.zhixin.dao.client.ClientUserDao;
import com.zhixin.model.Shop_User;

@Service(value="clientUserDao")
public class ClientUserDaoImpl extends DaoSupportImpl<Shop_User> implements ClientUserDao{

}
