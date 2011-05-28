package com.helpWanted;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass extends Activity {
	private ImageView mImageView;
	private TextView mTextView;
	private String fileName = "/data/data/irdc.ex04_22/ex04_22_2.png";
	private SensorManager mSensorManager;
	private CompassControl compassControl;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compass);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mImageView = (ImageView) findViewById(R.id.m_CompassBody);
		mTextView = (TextView) findViewById(R.id.mTextView);
		compassControl = new CompassControl(mImageView, mTextView, this);
	}

	protected void onResume() {
		super.onResume();
		compassControl.onResume();
	}

	protected void onPause() {
		compassControl.onPause();
		super.onPause();
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
		Intent intent = new Intent(Compass.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		finish();
	}
}