package com.hoofee.everything.main.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class SmsUtils {
	/**
	 * 发送短信
	 * @param context
	 * @param phone
	 * @param content
	 */
	public static void sendSms(Context context,String phone,String content)
	{
		Uri smsToUri = Uri.parse("smsto://"+phone);  
		  
		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);  
		  
		intent.putExtra("sms_body", content);  
		  
		context.startActivity(intent); 
	}
}
