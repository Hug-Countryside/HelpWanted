package com.helpWanted;

import com.db.PhoneName_DBAdapter;
import com.db.SettingValue_DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends Activity {
	private EditText name;
	private EditText phone;
	PhoneName_DBAdapter p_db;
	SettingValue_DBAdapter sv_db;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduser);
		p_db = new PhoneName_DBAdapter(this);
		name = (EditText) findViewById(R.id.myEditText1);
		phone = (EditText) findViewById(R.id.myEditText2);
		Button add = (Button) findViewById(R.id.myButton1);
		add.setOnClickListener(new addListener());
	}

	//
	public class addListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			String username = name.getText().toString();
			String userphone = phone.getText().toString();
			p_db.open();
			p_db.insertTitle(username, userphone);
			p_db.close();
			Intent intent = new Intent(AddUser.this, ListUser.class);
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
	}

	private void actionClickMenuItem2() {
		finish();
	}
}
