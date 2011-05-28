package com.helpWanted;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.Service;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.media.*;
import android.media.MediaPlayer.OnCompletionListener;

public class Shake extends Activity {
	SoundControl aa;
	ShakeControl bb;
	private Vibrator mVibrator01;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mVibrator01 = (Vibrator) getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);
		final ToggleButton mtogglebutton1 = (ToggleButton) findViewById(R.id.myTogglebutton1);
		final ToggleButton mtogglebutton2 = (ToggleButton) findViewById(R.id.myTogglebutton2);
		final ToggleButton mtogglebutton3 = (ToggleButton) findViewById(R.id.myTogglebutton3);
		/*
		 * File file = new File("/sdcard/sos.mp3"); Uri uri =
		 * Uri.fromFile(file); AsyncPlayer bb = new AsyncPlayer("A");
		 * bb.play(this, uri, false, 1); bb.stop();
		 */

		aa = new SoundControl("sos", this);
		aa.run();

		bb = new ShakeControl("sos", this);
		bb.run();

		mtogglebutton1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				aa.stop();
				bb.stop();
			}
		});

		mtogglebutton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				aa.run();
				bb.run();
			}
		});
		mtogglebutton3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mtogglebutton3.isChecked()) {

					mVibrator01.vibrate(
							new long[] { 1000, 50, 1000, 50, 1000 }, 0);

					Toast.makeText(Shake.this, getString(R.string.str_ok),
							Toast.LENGTH_SHORT).show();
				} else {

					mVibrator01.cancel();

					Toast.makeText(Shake.this, getString(R.string.str_end),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void stop() {
		aa.stop();
		bb.stop();
	}
}