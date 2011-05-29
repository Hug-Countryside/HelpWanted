package com.helpWanted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class BackgroundLight extends Activity {

	private LinearLayout mLinearLayout;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		mLinearLayout = (LinearLayout) findViewById(R.id.myLinearLayout1);
		BackgroundLightControl blc = new BackgroundLightControl(mLinearLayout,
				this);
		blc.setBackgroundlight(android.graphics.Color.RED, "红色");
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
		Intent intent = new Intent(BackgroundLight.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2() {
		finish();
	}
}