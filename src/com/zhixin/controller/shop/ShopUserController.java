package com.zhixin.controller.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhixin.base.BaseController;
import com.zhixin.service.shop.ShopUserService;

@Controller
@RequestMapping(value="/shopuser")
public class ShopUserController extends  BaseController{
	
	@Resource(name="shopuserService")
	private ShopUserService shopuserService;
	

}
