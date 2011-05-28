package com.helpWanted;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MorseToX extends Activity {
	private ProgressBar myProgress;
	EditText inputText;
	ShakeControl shakeControl;
	SoundControl soundControl;
	private AudioManager audioMa;
	public static final int ITEM0 = Menu.FIRST;
	public static final int ITEM1 = Menu.FIRST + 1;
	private int volume = 0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.morseui);
		ImageButton sound = (ImageButton) findViewById(R.id.sound);
		ImageButton shake = (ImageButton) findViewById(R.id.shake);
		ImageButton soundAndshake = (ImageButton) findViewById(R.id.soundAndshake);
		ImageButton stop = (ImageButton) findViewById(R.id.stop);
		ImageButton sos = (ImageButton) findViewById(R.id.sos);
		inputText = (EditText) findViewById(R.id.inputText);
		sound.setOnClickListener(new SoundListener());
		shake.setOnClickListener(new ShakeListener());
		soundAndshake.setOnClickListener(new SoundAndShakeListener());
		stop.setOnClickListener(new StopListener());
		sos.setOnClickListener(new SosListener());
	}

	private String getInput() {
		return inputText.getText().toString();
	}

	public class SoundListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String str = getInput();
			if (str != null) {
				// /Toast.makeText(MorseToX.this, "str", Toast.LENGTH_SHORT);
				soundControl = new SoundControl(str, MorseToX.this);
				try {
					soundControl.run();
					shakeControl.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// new
			// AlertDialog.Builder(MorseToX.this).setTitle("AASDASD").setMessage(str).setPositiveButton(
			// "ok",
			// new DialogInterface.OnClickListener(){
			// public void onClick(DialogInterface dialogiinterface, int i){
			//
			// }
			// }
			// ).show();

		}
	}

	public class ShakeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String str = getInput();
			if (str != null) {
				shakeControl = new ShakeControl(str, MorseToX.this);
				try {
					shakeControl.run();
					soundControl.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class SosListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String str = "sos";
			shakeControl = new ShakeControl(str, MorseToX.this);
			soundControl = new SoundControl(str, MorseToX.this);
			try {
				shakeControl.run();
				soundControl.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class SoundAndShakeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String str = getInput();
			if (str != null) {
				shakeControl = new ShakeControl(str, MorseToX.this);
				soundControl = new SoundControl(str, MorseToX.this);
				try {
					soundControl.run();
					shakeControl.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class StopListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			try {
				soundControl.stop();
				shakeControl.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			actionClickMenuItem2(); break;

		}
		return super.onOptionsItemSelected(item);}

	private void actionClickMenuItem1(){
		Intent intent = new Intent(MorseToX.this, About.class);
		startActivity(intent);
	}

	private void actionClickMenuItem2(){
		finish();
	}
}