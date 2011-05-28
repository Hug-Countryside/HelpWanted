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
	// private String SMS_DELIVERED_ACTION = "SMS_DELIVERED_ACTION";
	// private int SMS_RECEIVER_LENGTH = 3;
	// private String SMS_SEND_ACTIOIN = "SMS_SEND_ACTIOIN";
	Msg msg = new Msg(this);
	/**
	 * ����ʱ����
	 */
	static final int UPDATE_INTERVAL = 100;
	/**
	 * ��һ�μ���ʱ��
	 */
	long mLastUpdateTime;
	/**
	 * ��һ�μ��ʱ�����ٶ���x��y��z�����ϵķ��������ں͵�ǰ���ٶȱȽ���
	 */
	float mLastX, mLastY, mLastZ;
	/**
	 * ҡ�μ����ֵ�������˶�ҡ�ε����г̶ȣ�ԽСԽ���С�
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
		 * TYPEע�� int TYPE_ACCELEROMETER ���ٶ� int TYPE_ALL
		 * �������ͣ�NexusOneĬ��Ϊ ���ٶ� int TYPE_GYROSCOPE ��ת��(�����̫��) int
		 * TYPE_LIGHT ���߸�Ӧ int TYPE_MAGNETIC_FIELD �ų� int TYPE_ORIENTATION
		 * ����ָ���룩�ͽǶ� int TYPE_PRESSUR ѹ���� int TYPE_PROXIMITY ���룿��̫�� int
		 * TYPE_TEMPERATURE �¶���
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
				// if (delta > shakeThreshold) { //
				// �����ٶȵĲ�ֵ����ָ������ֵ����Ϊ����һ��ҡ��
				setTitle("���ٶ�=" + (int) delta);
				if (delta > 2800) {
					try {
						if (mode.equals("1")) {
							// String strDestAddress = "10086";
							Location location = getGPSLocation(GraviyActivity.this);
							double longtitude = location.getLongitude();
							double latitude = location.getLatitude();
							double altitude = location.getAltitude();
							String locationstr = "����:"
									+ String.valueOf(latitude) + "γ��:"
									+ String.valueOf(longtitude) + "���Σ�"
									+ String.valueOf(altitude);
							String strMessage = msg1 + "\n" + locationstr;
							int i = 0;
							/* ����һȡ��default instance�� SmsManager���� */
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
						} else if (mode.equals("2")) {
							String phone = "15801202342";
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
		// ע��listener������������Ǽ���������
		sensorMgr
				.registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME);
		/*
		 * SENSOR_DELAY_FASTEST �����������Ȼ������ SENSOR_DELAY_GAME
		 * ��Ϸ��ʱ�������������һ��������͹��ˣ�����һ�����ѿ������
		 * SENSOR_DELAY_NORMAL �Ƚ��� SENSOR_DELAY_UI ����ģ��������Ǻ���ݵ����
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
				String defaultMsg = "��ȣ�����";
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
		menu.add(0, ITEM0, 0, "�� ��").setIcon(R.drawable.process);
		menu.add(0, ITEM1, 0, "�ص���һҳ").setIcon(R.drawable.refresh);
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