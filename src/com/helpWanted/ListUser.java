package com.helpWanted;

import java.util.ArrayList;
import java.util.List;

import com.db.PhoneName_DBAdapter;
import com.db.SettingValue_DBAdapter;
import com.model.User;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListUser extends ListActivity {

	PhoneName_DBAdapter p_db;
	SettingValue_DBAdapter sv_db;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	public static final int ITEM2 = Menu.FIRST + 2;
	private List<String> names = new ArrayList<String>();

	// private List phones = new ArrayList();
	// private List value = new ArrayList();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listuser);
		p_db = new PhoneName_DBAdapter(this);
		p_db.open();
		// p_db.insertTitle(username, phone);
		// String name = null;
		/*
		 * 
		 */
		Cursor c = p_db.getAllTitles();
		int i = 0;
		try {

			if (c.moveToFirst()) {
				do {
					names.add(c.getString(1) + ":" + c.getString(2));
					p_db.deleteTitle(i);
					// (String.valueOf(i));
					i++;
					// Toast.makeText(this, i, Toast.LENGTH_LONG).show();
				} while (c.moveToNext());
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
		}

		ListAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, 0, names);
		getListView().setTextFilterEnabled(true);
		setListAdapter(adapter);
		p_db.close();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ITEM0, 0, "�� ��").setIcon(R.drawable.add);
		menu.add(0, ITEM1, 0, "�� ��").setIcon(R.drawable.process);
		menu.add(0, ITEM2, 0, "�ص���һҳ").setIcon(R.drawable.refresh);
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
		case ITEM2:
			actionClickMenuItem3();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void actionClickMenuItem1() {
		Intent intent = new Intent(ListUser.this, AddUser.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		Intent intent = new Intent(ListUser.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem3() {
		finish();
	}

}
