package com.helpWanted;

import java.util.ArrayList;
import java.util.List;

import com.callMsg.Msg;
import com.db.PhoneName_DBAdapter;
import com.db.SettingValue_DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;

public class GraviyActivity extends Activity {

	PhoneName_DBAdapter p_db;
	SettingValue_DBAdapter s_db;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	private List<String> addressList = new ArrayList<String>();
	private String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
	private int SMS_RECEIVER_LENGTH = 3;
	private String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
	Msg msg = new Msg(this);
	/**
	 * 检测的时间间隔
	 */
	static final int UPDATE_INTERVAL = 100;
	/**
	 * 上一次检测的时间
	 */
	long mLastUpdateTime;
	/**
	 * 上一次检测时，加速度在x、y、z方向上的分量，用于和当前加速度比较求差。
	 */
	float mLastX, mLastY, mLastZ;
	/**
	 * 摇晃检测阈值，决定了对摇晃的敏感程度，越小越敏感。
	 */
	public float shakeThreshold = 5000;
	private float x, y, z;
	String mode;
	String msg1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.callmsg);
		setContentView(R.layout.callmsg);
		SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		getInitizeValue();// get Initialize value
		getUser();
		// mode = "1";
		// msg1 = "ok";

		// p_db = new PhoneName_DBAdapter(this);
		// p_db.open();
		// p_db.insertTitle(username, phone);
		// String name = null;

		/*
		 * TYPE注解 int TYPE_ACCELEROMETER 加速度 int TYPE_ALL 所有类型，NexusOne默认为 加速度
		 * int TYPE_GYROSCOPE 回转仪(这个不太懂) int TYPE_LIGHT 光线感应 int
		 * TYPE_MAGNETIC_FIELD 磁场 int TYPE_ORIENTATION 定向（指北针）和角度 int
		 * TYPE_PRESSUR 压力计 int TYPE_PROXIMITY 距离？不太懂 int TYPE_TEMPERATURE 温度啦
		 */

		SensorEventListener lsn = new SensorEventListener() {
			public void onSensorChanged(SensorEvent e) {

				long currentTime = System.currentTimeMillis();
				long diffTime = currentTime - mLastUpdateTime;
				if (diffTime < UPDATE_INTERVAL)
					return;
				mLastUpdateTime = currentTime;
				float x = e.values[0];
				float y = e.values[1];
				float z = e.values[2];
				float deltaX = x - mLastX;
				float deltaY = y - mLastY;
				float deltaZ = z - mLastZ;
				mLastX = x;
				mLastY = y;
				mLastZ = z;
				float delta = FloatMath.sqrt(deltaX * deltaX + deltaY * deltaY
						+ deltaZ * deltaZ)
						/ diffTime * 10000;
				// if (delta > shakeThreshold) { // 当加速度的差值大于指定的阈值，认为这是一个摇晃
				setTitle("摇晃手机求救"+"加速度=" + (int) delta);
				if (delta > 2800) {
					try {
						if (mode.equals("1")) {
							// String strDestAddress = "10086";
							Location location = getGPSLocation(GraviyActivity.this);
							double longtitude = location.getLongitude();
							double latitude = location.getLatitude();
							double altitude = location.getAltitude();
							String locationstr = "经度:"
									+ String.valueOf(latitude) + "纬度:"
									+ String.valueOf(longtitude) + "海拔："
									+ String.valueOf(altitude);
							// 构造短信内容
							String strMessage = msg1 + "\n" + locationstr;
							int i = 0;
							/* 建构一取得default instance的 SmsManager对象 */
							// 依次给userList中的人发短信
							while (i < addressList.size()) {
								msg.sendMsg(addressList.get(i), strMessage);    
								i++;
							}
							new AlertDialog.Builder(GraviyActivity.this)
									.setTitle("message:")
									.setMessage(strMessage)
									.setPositiveButton(
											"ok",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialogiinterface,
														int i) {

												}
											}).show();
						} // 设置中设置为了电话模式
						else if (mode.equals("2")) {
							// 默认给最后个人打电话.因为数据库里添加条目是"后来居上"
							// TODO 能够设置给第几个人打...= =||
							String phone = addressList.get(0);
							Intent intent = new Intent(Intent.ACTION_CALL,
									Uri.parse("tel:" + phone));
							startActivity(intent);
						}
						return;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					delta = 0;
				}
			}

			public void onAccuracyChanged(Sensor s, int accuracy) {

			}
		};
		// 注册listener，第三个参数是检测的灵敏度
		sensorMgr
				.registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME);
		/*
		 * SENSOR_DELAY_FASTEST 最灵敏，快的然你无语 SENSOR_DELAY_GAME
		 * 游戏的时候用这个，不过一般用这个就够了，和上一个很难看出区别 SENSOR_DELAY_NORMAL 比较慢。
		 * SENSOR_DELAY_UI 最慢的，几乎就是横和纵的区别
		 */
	}

	public Location getGPSLocation(Context context) {
		LocationManager locMan = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		Location location = locMan
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = locMan
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		return location;
	}

	public void getInitizeValue() {
		// Cursor c = p_db.getTitle(1);
		s_db = new SettingValue_DBAdapter(this);
		s_db.open();
		String id = "100";
		Cursor s = s_db.getTitle(1);
		int i = 0;
		try {

			if (s.moveToFirst()) {
				do {
					mode = s.getString(1);
					msg1 = s.getString(2);
					id = s.getString(0);

				} while (s.moveToNext());
			} else {
				String defaultMsg = "求救!以下是我现在所处在的位置!";
				s_db.insertTitle("1", defaultMsg);
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
		}
		// Toast.makeText(this, "mode=" + mode, Toast.LENGTH_LONG).show();
		// Toast.makeText(GraviyActivity.this, id + msg1 + mode,
		// Toast.LENGTH_SHORT).show();
		s.close();
	}

	public void getUser() {
		p_db = new PhoneName_DBAdapter(this);
		p_db.open();
		// p_db.insertTitle(username, phone);
		// String name = null;
		Cursor c = p_db.getAllTitles();
		int i = 0;
		try {

			if (c.moveToFirst()) {
				do {
					addressList.add(c.getString(2));
					// (String.valueOf(i));
					// i++;
					// Toast.makeText(this, i, Toast.LENGTH_LONG).show();
				} while (c.moveToNext());
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "关 于").setIcon(R.drawable.process);
		menu.add(0, ITEM1, 0, "回到上一页").setIcon(R.drawable.refresh);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ITEM0:
			actionClickMenuItem1();
			break;
		case ITEM1:
			actionClickMenuItem2();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void actionClickMenuItem1() {
		Intent intent = new Intent(GraviyActivity.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		finish();
	}

}