package com.callMsg;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;

public class Msg {

	private Activity activity;

	public Msg(Activity activity) {
		this.activity = activity;
	}

	public void sendMsg(String strDestAddress, String strMessage) {
		/* 建构一取得default instance的 SmsManager对象 */
		System.out.println("strDestAddress:" + strDestAddress + ","
				+ "strMessage:" + strMessage);
		SmsManager smsManager = SmsManager.getDefault();
        // TODO Auto-generated method stub
        /* 检查收件人电话格式与简讯字数是否超过70字符 */
		try {
            /*
             * 两个条件都检查通过的情况下,发送简讯 * --- 当然,检测功能还没完成. = =||
             * 先建构一PendingIntent对象并使用getBroadcast()方法进行Broadcast *
             * 将PendingIntent,电话,简讯文字等参数传入sendTextMessage()方法发送简讯
             */
			PendingIntent mPI = PendingIntent.getBroadcast(activity, 0,
					new Intent(), 0);
			smsManager.sendTextMessage(strDestAddress, null, strMessage, mPI,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Toast.makeText(Msg.this, "送出成功!!", Toast.LENGTH_SHORT).show();
		// 送出成功的弹窗在Gravity中
	}

	public void call(String phone) {
		// SmsManager smsManager = SmsManager.getDefault();
		if (phone != null) {

		}
	}
}
