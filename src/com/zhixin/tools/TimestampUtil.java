package com.zhixin.tools;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtil {

	
	public static Timestamp getnowtime(){
		Date date = new Date();       
		Timestamp nousedate = new Timestamp(date.getTime());
		return nousedate;
	}
	
	public static String timestamptoString(Timestamp ts){
		
		String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try {  
            //方法一  
            tsStr = sdf.format(ts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return tsStr; 
	}
	
	
}
