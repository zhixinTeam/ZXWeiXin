package com.zhixin.tools;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 短信工具类
 * Created by LV on 2016/4/15 0015.
 * Email:LvLoveYuForever@gmail.com
 */
public class MobileMessageSend {
   
    public static String sendMsg(String phone) throws IOException {

    	 DefaultHttpClient httpClient = new DefaultHttpClient();
         String url = "https://api.netease.im/sms/sendcode.action";
         HttpPost httpPost = new HttpPost(url);

         String appKey = "0a54e5b02dc39f474ed1a8fb7048a84f";
         String appSecret = "049c08b3b766";
         String nonce =  "12345";
         String curTime = String.valueOf((new Date()).getTime() / 1000L);
         String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

         // 设置请求的header
         httpPost.addHeader("AppKey", appKey);
         httpPost.addHeader("Nonce", nonce);
         httpPost.addHeader("CurTime", curTime);
         httpPost.addHeader("CheckSum", checkSum);
         httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

         // 设置请求的参数
         List<NameValuePair> nvps = new ArrayList<NameValuePair>();
         nvps.add(new BasicNameValuePair("mobile", phone));
         httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

         // 执行请求
         HttpResponse response = httpClient.execute(httpPost);
         return EntityUtils.toString(response.getEntity(), "utf-8");
        //判断是否发送成功，发送成功返回true
       // String code= JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8")).getString("code");
      /* if (code.equals("200"))
            {return 0;}

        return 500;*/
    }
    
    
    public  static void main(String ages[]){
    	System.out.println("+++++++++++++");
    	try {
			MobileMessageSend.sendMsg("13193762319");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
}