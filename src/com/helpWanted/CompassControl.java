package com.helpWanted;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class CompassControl {
	private ImageView m_ImageView;
	private TextView m_TextView;
	private SensorManager m_SensorManager;
	private Activity m_Activity;

	private Bitmap m_Bitmap;

	public CompassControl(ImageView imageView, TextView textView,
			Activity activity) {
		this.m_ImageView = imageView;
		this.m_TextView = textView;
		this.m_Activity = activity;
		this.m_SensorManager = (SensorManager) this.m_Activity
				.getSystemService(Context.SENSOR_SERVICE);
		this.m_Bitmap = BitmapFactory.decodeResource(
				this.m_Activity.getResources(), R.drawable.p0);
		this.m_ImageView.setScaleType(ScaleType.CENTER);
		this.m_ImageView.setImageBitmap(this.m_Bitmap);
	}

	public void onResume() {
		m_SensorManager.registerListener(mSensorEventListener,
				m_SensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void onPause() {
		m_SensorManager.unregisterListener(mSensorEventListener);
	}

	private void doMatrix(int degree) {
		switch (degree) {
		case 0:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p0);
			break;
		case 1:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p10);
			break;
		case 20:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p20);
			break;
		case 30:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p30);
			break;
		case 40:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p40);
			break;
		case 50:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p50);
			break;
		case 60:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p60);
			break;
		case 70:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p70);
			break;
		case 80:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p80);
			break;
		case 90:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p90);
			break;
		case 100:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p100);
			break;
		case 110:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p110);
			break;
		case 120:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p120);
			break;
		case 130:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p130);
			break;
		case 140:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p140);
			break;
		case 150:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p150);
			break;
		case 160:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p160);
			break;
		case 170:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p170);
			break;
		case 180:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p180);
			break;
		case 190:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p190);
			break;
		case 200:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p200);
			break;
		case 210:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p210);
			break;
		case 220:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p220);
			break;
		case 230:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p230);
			break;
		case 240:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p240);
			break;
		case 250:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p250);
			break;
		case 260:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p260);
			break;
		case 270:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p270);
			break;
		case 280:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p280);
			break;
		case 290:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p290);
			break;
		case 300:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p300);
			break;
		case 310:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p310);
			break;
		case 320:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p320);
			break;
		case 330:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p330);
			break;
		case 340:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p340);
			break;
		case 350:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p350);
			break;
		default:
			this.m_Bitmap = BitmapFactory.decodeResource(
					this.m_Activity.getResources(), R.drawable.p0);
			break;
		}

		this.m_ImageView.setImageBitmap(this.m_Bitmap);
	}

	private final SensorEventListener mSensorEventListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
				float x_data = event.values[SensorManager.DATA_X];

				if ((x_data > 0 && x_data <= 10)) {
					doMatrix(0);
				} else if ((x_data > 10 && x_data <= 20)) {
					doMatrix(10);
				} else if ((x_data > 20 && x_data <= 30)) {
					doMatrix(20);
				} else if ((x_data > 30 && x_data <= 40)) {
					doMatrix(30);
				} else if ((x_data > 40 && x_data <= 50)) {
					doMatrix(40);
				} else if ((x_data > 50 && x_data <= 60)) {
					doMatrix(50);
				} else if ((x_data > 60 && x_data <= 70)) {
					doMatrix(60);
				} else if ((x_data > 70 && x_data <= 80)) {
					doMatrix(70);
				} else if ((x_data > 80 && x_data <= 90)) {
					doMatrix(80);
				} else if ((x_data > 90 && x_data <= 100)) {
					doMatrix(90);
				} else if ((x_data > 100 && x_data <= 110)) {
					doMatrix(100);
				} else if ((x_data > 110 && x_data <= 120)) {
					doMatrix(110);
				} else if ((x_data > 120 && x_data <= 130)) {
					doMatrix(120);
				} else if ((x_data > 130 && x_data <= 140)) {
					doMatrix(130);
				} else if ((x_data > 140 && x_data <= 150)) {
					doMatrix(140);
				} else if ((x_data > 150 && x_data <= 160)) {
					doMatrix(150);
				} else if ((x_data > 160 && x_data <= 170)) {
					doMatrix(160);
				} else if ((x_data > 170 && x_data <= 180)) {
					doMatrix(170);
				} else if ((x_data > 180 && x_data <= 190)) {
					doMatrix(180);
				} else if ((x_data > 190 && x_data <= 200)) {
					doMatrix(190);
				} else if ((x_data > 200 && x_data <= 210)) {
					doMatrix(200);
				} else if ((x_data > 210 && x_data <= 220)) {
					doMatrix(210);
				} else if ((x_data > 220 && x_data <= 230)) {
					doMatrix(220);
				} else if ((x_data > 230 && x_data <= 240)) {
					doMatrix(230);
				} else if ((x_data > 240 && x_data <= 250)) {
					doMatrix(240);
				} else if ((x_data > 250 && x_data <= 260)) {
					doMatrix(250);
				} else if ((x_data > 260 && x_data <= 270)) {
					doMatrix(260);
				} else if ((x_data > 270 && x_data <= 280)) {
					doMatrix(270);
				} else if ((x_data > 280 && x_data <= 290)) {
					doMatrix(280);
				} else if ((x_data > 290 && x_data <= 300)) {
					doMatrix(290);
				} else if ((x_data > 300 && x_data <= 310)) {
					doMatrix(300);
				} else if ((x_data > 310 && x_data <= 320)) {
					doMatrix(310);
				} else if ((x_data > 320 && x_data <= 330)) {
					doMatrix(320);
				} else if ((x_data > 330 && x_data <= 340)) {
					doMatrix(330);
				} else if ((x_data > 340 && x_data <= 350)) {
					doMatrix(340);
				} else if ((x_data > 350 && x_data <= 360)) {
					doMatrix(350);
				} else {
					doMatrix(0);
				}

				if ((x_data > 0 && x_data <= 22.5) || x_data > 337.5) {
					m_TextView.setText("����" + String.valueOf(x_data));
				} else if (x_data > 22.5 && x_data <= 67.5) {
					m_TextView.setText("������" + String.valueOf(x_data));
				} else if (x_data > 67.5 && x_data <= 112.5) {
					m_TextView.setText("����" + String.valueOf(x_data));
				} else if (x_data > 112.5 && x_data <= 157.5) {
					m_TextView.setText("���Ϸ�" + String.valueOf(x_data));
				} else if (x_data > 157.5 && x_data <= 202.5) {
					m_TextView.setText("�Ϸ�" + String.valueOf(x_data));
				} else if (x_data > 202.5 && x_data <= 247.5) {
					m_TextView.setText("���Ϸ�" + String.valueOf(x_data));
				} else if (x_data > 247.5 && x_data <= 292.5) {
					m_TextView.setText("����" + String.valueOf(x_data));
				} else if (x_data > 292.5 && x_data <= 337.5) {
					m_TextView.setText("������" + String.valueOf(x_data));
				}
			}
		}
	};
}
