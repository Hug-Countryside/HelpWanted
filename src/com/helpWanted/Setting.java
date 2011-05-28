package com.helpWanted;

import com.db.PhoneName_DBAdapter;
import com.db.SettingValue_DBAdapter;

import android.app.Activity;
import android.content.DialogInterface;
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

public class Setting extends Activity {

	PhoneName_DBAdapter p_db;
	SettingValue_DBAdapter sv_db;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);

		ImageButton cm = (ImageButton) findViewById(R.id.cm);
		ImageButton sm = (ImageButton) findViewById(R.id.ss);
		cm.setOnClickListener(new CmListener());
		sm.setOnClickListener(new SmListener());
	}

	public class CmListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(Setting.this, ListUser.class);
			startActivity(intent);
		}

	}

	public class SmListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(Setting.this, ModifySetting.class);
			startActivity(intent);
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
		Intent intent = new Intent(Setting.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		finish();
	}
	/*
	 * 
	 * 
	 * public void DisplayTitle(Cursor c) { Toast.makeText(this, "id: " +
	 * c.getString(0) + "\n" + "NAME: " + c.getString(1) + "\n" + "PHONE: " +
	 * c.getString(2), Toast.LENGTH_LONG).show(); }
	 * 
	 * //setContentView(R.layout.main); // String username = "huang"; // String
	 * phone = "13811625441"; p_db = new PhoneName_DBAdapter(this); p_db.open();
	 * // p_db.insertTitle(username, phone); String name = null; try { Cursor c
	 * = p_db.getAllTitles();
	 * 
	 * if (c.moveToFirst()) { do { DisplayTitle(c); } while (c.moveToNext()); }
	 * } catch(Exception ex) {
	 * Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show(); }
	 * p_db.close();
	 */
}
