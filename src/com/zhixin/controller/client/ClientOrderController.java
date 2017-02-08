package com.zhixin.controller.client;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhixin.base.BaseController;
import com.zhixin.entity.Json_ShopOrder;
import com.zhixin.model.Doc_Factory;
import com.zhixin.model.ShopLink_User_Driver;
import com.zhixin.model.Shop_Client;
import com.zhixin.model.Shop_Driver;
import com.zhixin.model.Shop_Order;
import com.zhixin.model.Shop_User;
import com.zhixin.right_utils.Const;
import com.zhixin.right_utils.PageData;
import com.zhixin.right_utils.PathUtil;
import com.zhixin.right_utils.TwoDimensionCode;
import com.zhixin.service.FactoryService;
import com.zhixin.service.client.ClientOrderService;
import com.zhixin.service.client.ClientUserService;
import com.zhixin.service.shop.ShopClientService;
import com.zhixin.service.shop.ShopGoodsService;
import com.zhixin.service.shop.ShopOrderService;
import com.zhixin.service.shop.ShopUserService;
import com.zhixin.tools.TokenProccessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/clientOrder")
public class ClientOrderController extends BaseController {

	@Resource(name = "clientorderService")
	private ClientOrderService clientorderService;

	@Resource(name = "clientuserService")
	private ClientUserService clientuserService;

	@Resource(name = "shoporderService")
	private ShopOrderService shoporderService;

	@Resource(name = "shopuserService")
	private ShopUserService shopuserService;

	@Resource(name = "factoryService")
	private FactoryService factoryService;

	@Resource(name = "shopclientService")
	private ShopClientService shopclientService;

	@Resource(name = "shopgoodsService")
	private ShopGoodsService shopgoodsService;

	@Resource(name = "clientorderService")
	private ClientOrderService clientOrderService;

	/**
	 * app下提货单页面 1.若没有登录，先进入登录界面
	 *
	 */
	@ResponseBody
	@RequestMapping("/order_list")
	public void place_order(HttpServletResponse response, String currentPage, HttpServletRequest request)
			throws Exception {
		response.setHeader("P3P","CP=CAO PSA OUR");
		currentPage = request.getParameter("currentPage");
		PageData pd = new PageData();
		pd = this.getPageData();
		if (currentPage == "" || currentPage == null) {
			currentPage = "1";
		}
		int current = Integer.parseInt(currentPage);
		if (current <= 0) {
			currentPage = "1";
		}

		// List<Json_ShopOrder>
		// jsonorderlist=clientOrderService.listOrders(currentPage,
		// "8aac0f915773bc9f015773cfc8c70001");
		// Json_ShopOrder json_shopOrder=jsonorderlist.get(0);
		JSONObject jo = new JSONObject();
		jo.put("msg", "success");
		String encoderImgId = "";
		try {
			/*Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);*/
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession(); 
			String sessionId= pd.getString("sessionId");
			Shop_User shopuser = (Shop_User) session.getAttribute(sessionId);
			if(shopuser==null&&sessionId!=null){
				shopuser =shopuserService.getShopUserByid(sessionId);
			}
			String wx_token = TokenProccessor.getInstance().makeToken();
			session.setAttribute("wx_token", wx_token);
			//Shop_User shopuser = (Shop_User) session.getAttribute(Const.SESSION_SHOPUSER);
			if (shopuser != null) {

				/*
				 * jo.put("faset", faset); jo.put("driverlist", driverlist);
				 * jo.put("wx_token", wx_token); jo.put("shopuser", shopuser);
				 */
				List<Json_ShopOrder> jsonorderlist = clientOrderService.listOrders(currentPage, shopuser.getU_id());
				if (jsonorderlist.size() != 0) {
					Json_ShopOrder json_shopOrder = jsonorderlist.get(0);
					JSONArray jsonorderlists = JSONArray.fromObject(jsonorderlist);
					jo.put("jsonorderlists", jsonorderlists);
					String orderNO = json_shopOrder.getOrdernumber();
					String errInfo = "success";
					if (null == orderNO || "".equals(orderNO)) {
						errInfo = "error";
					} else {
						encoderImgId = orderNO + ".png";
						try {
							String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId; // 存放路径
							// 如果已经生成二维码，不在生成
							File file = new File(filePath);
							if (file.exists()) {
							} else {
								com.zhixin.tools.QRCodeUtil.encode(orderNO, "", filePath, true); // 执行生成二维码
							}
						} catch (Exception e) {
							errInfo = "error";
						}
					}
				}else{
					jo.put("message", "0");
				}
				
			} else {
				jo.put("msg", "error");

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// jo.put("jsonactivitys", jsonactivitys);
			jo.put("currentPage", currentPage);
			jo.put("encoderImgId", encoderImgId);
			PrintWriter out;
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.write(jo.toString());
			out.flush();
			out.close();
		}

	}

	
	@ResponseBody
	@RequestMapping("/order_cancel")
	public void editOrder(HttpServletResponse response,String o_id, HttpServletRequest request){
		response.setHeader("P3P","CP=CAO PSA OUR");
		o_id=request.getParameter("o_id");
		Shop_Order shop_Order=clientorderService.oneOrder(o_id);
		shop_Order.setStatus(2);
		clientorderService.updateShopOrder(shop_Order);
		
	}
	

}
