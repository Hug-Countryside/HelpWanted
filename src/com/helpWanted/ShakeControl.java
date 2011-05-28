package com.helpWanted;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;
import android.widget.Toast;

public class ShakeControl implements MorseSignal {
	private String m_MorseCode;
	private String m_MorseString;
	private Vibrator m_Vibrator;
	private Activity m_Activity;

	public ShakeControl(String str, Activity activity) {
		this.m_MorseString = str;
		this.m_Activity = activity;
		this.m_Vibrator = (Vibrator) this.m_Activity.getApplication()
				.getSystemService(Service.VIBRATOR_SERVICE);
		Morse morse = new Morse(this.m_MorseString);
		this.m_MorseCode = morse.getMorseCode();
	}

	public void run() {
		this.m_Vibrator.vibrate(getShakeArray(), -1);
		Toast.makeText(this.m_Activity, this.m_MorseString, Toast.LENGTH_LONG)
				.show();
	}

	public void stop() {
		this.m_Vibrator.cancel();
	}

	private long[] getShakeArray() {
		long[] shakeArray = new long[this.m_MorseCode.length() * 2];
		for (int i = 0; i < this.m_MorseCode.length() * 2; i += 2) {
			shakeArray[i] = 110;
			if (this.m_MorseCode.charAt(i / 2) == 'a') {
				shakeArray[i + 1] = 750;
			} else if (this.m_MorseCode.charAt(i / 2) == 'A') {
				shakeArray[i + 1] = 400;
			}
		}
		shakeArray[0] = 500;
		return shakeArray;
	}
}
