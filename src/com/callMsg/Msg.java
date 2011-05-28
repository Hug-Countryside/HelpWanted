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
		/* 寤烘�涓��寰�efault instance��SmsManager瀵硅薄 */
		System.out.println("strDestAddress:" + strDestAddress + ","
				+ "strMessage:" + strMessage);
		SmsManager smsManager = SmsManager.getDefault();
		// TODO Auto-generated method stub
		/* 妫���朵欢浜虹�璇��寮��绠��瀛�����瓒��70瀛�� */
		try {
			/*
			 * 涓や釜�′欢�芥��ラ�杩�����涓����绠�� *
			 * ��缓���PendingIntent瀵硅薄骞朵娇��etBroadcast()�规�杩��Broadcast *
			 * 灏�endingIntent,�佃�,绠�����绛���颁���endTextMessage()�规����绠��
			 */
			PendingIntent mPI = PendingIntent.getBroadcast(activity, 0,
					new Intent(), 0);
			smsManager.sendTextMessage(strDestAddress, null, strMessage, mPI,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Toast.makeText(Msg.this, "������!!", Toast.LENGTH_SHORT).show();
	}

	public void call(String phone) {
		// SmsManager smsManager = SmsManager.getDefault();
		if (phone != null) {

		}
	}
}
