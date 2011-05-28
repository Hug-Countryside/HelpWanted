package com.helpWanted;

import com.db.SettingValue_DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main extends Activity {
	/** Called when the activity is first created. */
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	String mode;
	String msg1;
	SettingValue_DBAdapter s_db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initize();
		ImageButton b1 = (ImageButton) findViewById(R.id.button1);
		ImageButton b2 = (ImageButton) findViewById(R.id.button2);
		ImageButton b3 = (ImageButton) findViewById(R.id.button3);
		ImageButton b4 = (ImageButton) findViewById(R.id.button4);
		ImageButton b5 = (ImageButton) findViewById(R.id.button5);
		ImageButton b6 = (ImageButton) findViewById(R.id.button6);
		b1.setOnClickListener(new Button1Listener());
		b2.setOnClickListener(new Button2Listener());
		b3.setOnClickListener(new Button3Listener());
		b4.setOnClickListener(new Button4Listener());
		b5.setOnClickListener(new Button5Listener());
		b6.setOnClickListener(new Button6Listener());
	}

	public class Button1Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, Compass.class);
			startActivity(intent);
		}
	}

	public class Button2Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, MorseToX.class);
			startActivity(intent);
		}
	}

	public class Button3Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, Setting.class);
			startActivity(intent);
		}
	}

	public class Button4Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, GraviyActivity.class);
			startActivity(intent);
		}
	}

	public class Button5Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, Help.class);
			startActivity(intent);
		}
	}

	public class Button6Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(Main.this, BackgroundLight.class);
			startActivity(intent);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "�� ��").setIcon(R.drawable.process);
		menu.add(0, ITEM1, 0, "�� ��").setIcon(R.drawable.warning);
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
		Intent intent = new Intent(Main.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		finish();
	}

	private void initize() {
		s_db = new SettingValue_DBAdapter(this);
		s_db.open();
		// String id = "100";
		Cursor s = s_db.getTitle(1);
		int i = 0;
		try {

			if (s.moveToFirst()) {
				do {
					mode = s.getString(1);
					msg1 = s.getString(2);
					// id = s.getString(0);
				} while (s.moveToNext());
			} else {
				String defaultMsg = "��ȣ�����";
				s_db.insertTitle("1", defaultMsg);
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
		}
	}

}