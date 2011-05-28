package com.helpWanted;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BackgroundLightControl {
	private LinearLayout m_LinearLayout;
	private int m_Color;
	private String m_ColorName;
	private Activity m_Activity;

	public BackgroundLightControl(LinearLayout linearLayout, Activity activity) {
		this.m_LinearLayout = linearLayout;
		this.m_Activity = activity;
	}

	public void setBackgroundlight(int color, String colorName) {
		this.m_Color = color;
		this.m_ColorName = colorName;
		Toast.makeText(this.m_Activity, this.m_ColorName, Toast.LENGTH_LONG)
				.show();
		this.m_LinearLayout.setBackgroundColor(this.m_Color);
	}

	public int getBackgroundlight() {
		return this.m_Color;
	}
}
