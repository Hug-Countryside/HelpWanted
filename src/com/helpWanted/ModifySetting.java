package com.helpWanted;

import com.db.SettingValue_DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ModifySetting extends Activity{
	SettingValue_DBAdapter s_db;
	RadioButton radioButton1;	
	RadioButton radioButton2;	
	//RadioButton radioButton3;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	String mode;
	String msg1;
	EditText content;
	Button saveModify;
	SettingValue_DBAdapter sv_db;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.modifysetting);
	        display();
	        sv_db = new SettingValue_DBAdapter(this);
	        radioButton1 = (RadioButton)findViewById(R.id.radio1);
	        radioButton2 = (RadioButton)findViewById(R.id.radio2);
	       // radioButton3 = (RadioButton)findViewById(R.id.radio3);
	        content = (EditText)findViewById(R.id.helpMsg);
	        
	        content.setText(msg1);
	        saveModify = (Button)findViewById(R.id.saveModify);


	        saveModify.setOnClickListener(new SaveListener());
	}
	
	public void update(){
        if(radioButton1.isChecked()){
			mode = "1";
		}
        else if(radioButton2.isChecked()){
			mode = "2";
		}
	}
	
	public class SaveListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			String msg = content.getText().toString();
			update();
			//Toast.makeText(ModifySetting.this, msg, Toast.LENGTH_LONG);
			sv_db.open();
			sv_db.updateTitle(1, mode, msg);
			//sv_db.deleteTitle(0);
			//sv_db.insertTitle(mode, msg);
			//sv_db.updateTitle(2, 2, msg);
			new AlertDialog.Builder(ModifySetting.this).setTitle("提示").setMessage("保存成功！").setPositiveButton(
					"ok",
					new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialogiinterface, int i){
							
						}
					}
				).show();
			sv_db.close();
		}
		
	}
	
	public void display(){
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
					//Toast.makeText(this, "mode="+mode, Toast.LENGTH_LONG).show();
				} while (s.moveToNext());
			}
		} catch (Exception ex) {
			Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
		}
//		Toast.makeText(GraviyActivity.this, id + msg1 + mode,
//				Toast.LENGTH_SHORT).show();
		s.close();
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
			actionClickMenuItem2(); break;

		}
		return super.onOptionsItemSelected(item);}

	private void actionClickMenuItem1(){
		Intent intent = new Intent(ModifySetting.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2(){
		finish();
	}
}
